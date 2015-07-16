package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

import modelo.Jugador;
import negocio.Controlador;

public class MainFrame extends JFrame implements ProgressDialogListener {
	private static final long serialVersionUID = 1781427952197104293L;
	// private JButton btn;
	private Toolbar toolbar;
	private FormularioJugadorPanel formPanel;
	private JFileChooser fileChooser;
	private Controlador controlador;
	private TableJugadorPanel tablePanel;
	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	private EquipoIdealDialog equipoIdeal;
	private ProgressDialog progressDialog;
	private SwingWorker<List<Jugador>, Integer> worker;
	public MainFrame() {
		super("El TP de la Copa America V.000000000000000000001 BETA");
		setIconImage(Utils.createImageIcon("/images/icon.png").getImage());		
		setJMenuBar(createMenuBar());
		setLayout(new BorderLayout());
		progressDialog = new ProgressDialog((Window)getParent(), "Procesando solicitud...");
		progressDialog.setListener(this);
		equipoIdeal = new EquipoIdealDialog(this);
		toolbar = new Toolbar();
		formPanel = new FormularioJugadorPanel();
		tablePanel = new TableJugadorPanel();
		tabPane = new JTabbedPane();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,formPanel,tabPane);
		
		splitPane.setOneTouchExpandable(true);
		
		tabPane.addTab("Person Database", tablePanel);
		controlador = new Controlador();

		tablePanel.setData(controlador.getJugador());
		connect();
		try {
			controlador.load();
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		}
		tablePanel.setPersonTableListener(new JugadorTableListener(){
			public void rowDelete(int row){
				controlador.removeJugador(row);
			}
		});
		
	

		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new JugadorArchivoFilter());
		formPanel.setVisible(true);
		
		toolbar.setToolbarListener(new ToolbarListener() {
			public void saveEventOccured() {
				connect();
				try {
					controlador.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this,"No se posible guardar datos a la base de datos",
							"Fucking Save Problem",JOptionPane.ERROR_MESSAGE);
				
				}
			}
			
			public void refreshEventOccured() {				
				connect();			
				try {					
					controlador.load();					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this,"No se posible Mostrar datos de la base de datos",
							"Fucking Load Problem",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}				
				tablePanel.refresh();				
			}

			@Override
			public void fuerzaBrutaEventOccured() {		
				swingWorker(1);			
			}

			@Override
			public void backtrackingEventOccured() {
				swingWorker(0);
			}
			
		});

		formPanel.setFormListener(new FormularioJugadorListener() {
			public void formEventOccurred(FormularioJugadorEvent e) {
				controlador.addJugador(e);
				tablePanel.refresh();
			}
		});		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (close() == JOptionPane.OK_OPTION){
				controlador.disconnect();
				dispose();				
				//fucking recolector GARBAGE
				System.gc();
				System.exit(0);
				}
			}					
		});

		add(toolbar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	//implemntacion a refactorizar inmediatamente cuando tenga algo de tiempo
	//dado que esta terriblemente mal implementado...
	protected void swingWorker(final int eleccion) {
		progressDialog.setMaximo(controlador.countJugadores());
		progressDialog.setVisible(true);
		worker = new SwingWorker<List<Jugador>, Integer>(){			
			@Override
			protected void process(List<Integer> $elem) {
				int $valores = $elem.get($elem.size()-1);
				progressDialog.setValue($valores);
			}
			@Override
			protected void done() {
				progressDialog.setVisible(false);				
				if (isCancelled()) {
					return; 
				}
				try {
					get();
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Disculpe ocurrio un problema, intentelo de nuevo");
				} catch (ExecutionException e) {
					JOptionPane.showMessageDialog(null, "Disculpe ocurrio un problema, intentelo de nuevo");
				}
			}
			@Override
			protected List<Jugador> doInBackground() throws Exception {
				if (isCancelled()){
					return null;
				}
				switch (eleccion) {
				case 0:
					equipoIdeal.setData(controlador.getMejoresEquiposBacktracking());					
					equipoIdeal.setVisible(true);
					break;
				case 1:
					equipoIdeal.setData(controlador.getMejoresEquiposFuerzaBruta());				
					equipoIdeal.setVisible(true);
					break;
				default:
					break;
				}
				
				return null;
			}			
		};		
		worker.execute();			
	}


	

	private void connect(){
		try {
			controlador.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this,"No se puede conectar a la base de datos",
					"Fucking Connection Problem",JOptionPane.ERROR_MESSAGE);
		}
	}
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("Archivo");
		fileMenu.setFont(new Font("Thaoma", Font.PLAIN, 12));

		JMenuItem exportDataItem = new JMenuItem("Exportar Equipo...");
		JMenuItem importDataItem = new JMenuItem("Importar Equipo...");
		JMenuItem exitItem = new JMenuItem("Exito");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Formulario");
		windowMenu.setFont(new Font("Thaoma", Font.PLAIN, 12));
		JMenu showMenu = new JMenu("Mostrar");
		

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Panel");
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);	

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if (menuItem.isSelected()) {
					splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
				}
				formPanel.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I ,
				ActionEvent.CTRL_MASK));
		
		

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controlador.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not load data from file", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controlador.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						System.err.println(e1.getMessage());
					}
				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (close() == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					for(WindowListener listener: listeners){
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}
			}
		});
		return menuBar;

	}
	private int close(){
		int $action = JOptionPane.showConfirmDialog(rootPane,
	    		"¿Desea realmente salir de la Aplicacion?", "Salir de la Aplicacion", 
	    		JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
	    		Utils.createIcon("/images/icon.png"));
		return $action;	       
	}  

	
	@Override
	public void cancelled() {
		if (worker!=null) {
			worker.cancel(true);
		}
	}
}
