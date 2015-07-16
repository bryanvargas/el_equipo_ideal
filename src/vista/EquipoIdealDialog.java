package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Jugador;
import negocio.Equipo;


public class EquipoIdealDialog  extends JDialog{
	private static final long serialVersionUID = 6082945099559462694L;
	private JList<String> jlista;
	private DefaultListModel<String> messageListModel;
	private JTextField $nombre;
	private JTextField $seleccion;
	private JTextField $posicion;
	private JTextField $puntaje;
	private JTextField $amarilla;
	private JTextField $rojas;
	private JLabel $candidatas;
	private ScrollPane $scroll;
	private JButton $button;
	private GraficosCancha $graficos;
	private Equipo $equipo_win;
	public EquipoIdealDialog(JFrame parent) {
		super(parent, "Equipo Ideal - Detalles", false);
		messageListModel = new DefaultListModel<String>();	
		jlista = new JList<String>(messageListModel);
		$scroll = new ScrollPane();
		$graficos =  new GraficosCancha();
		$nombre = new JTextField(12);
		$nombre.setEditable(false);
		$seleccion =  new JTextField(12);
		$seleccion.setEditable(false);
		$posicion =  new JTextField(12);
		$posicion.setEditable(false);	
		$amarilla = new JTextField(12);
		$amarilla.setEditable(false);
		$puntaje = new JTextField(12);		
		$puntaje.setEditable(false);
		$rojas = new JTextField(12);
		$rojas.setEditable(false);
		$candidatas =  new JLabel();		
		$button = new JButton("Grafico");
		
		//cada ves que se selecciona un nombre de la lista, se cargan los datos 
		//del jugador
		jlista.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int $index = jlista.getSelectedIndex();
				Jugador j = $equipo_win.getJugador($index);
				$nombre.setText(j.getNombre());
				$seleccion.setText(j.getSeleccion_nacional());
				$posicion.setText(j.getPosicion().toString());
				$puntaje.setText(j.getPuntaje().toString());
				$amarilla.setText(j.getAmarillas().toString());
				$rojas.setText(j.getRojas().toString());				
			}
		});
		
		//mostrame el grafico de la fucking cancha fucking asshole!!!!
		$button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				$graficos.setVisible(true);
			}
		});
		
		$scroll.add(jlista);
		$scroll.setPreferredSize(new Dimension(80,200));
		layoutControls();
		setSize(350,295);//setUp lienzo del jdialog
		setResizable(false); 
		setLocationRelativeTo(parent);
	}
	
	//setUp Componentes del JDialog
	private void layoutControls(){
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		JPanel listaPanel =  new JPanel();
		
		int space = 1;
		Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
		Border titleBorder = BorderFactory.createTitledBorder("Informacion");
		Border listaBorder = BorderFactory.createTitledBorder("Plantel ");
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
		buttonsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,null));
		listaPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,listaBorder));		
		
		controlsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setLayout(new GridBagLayout());
		listaPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
	
		
		//////Nombre///////////			
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		controlsPanel.add(new JLabel("Nombre: "), gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		controlsPanel.add($nombre, gc);			
	
		////////Seleccion//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		controlsPanel.add(new JLabel("Seleccion: "), gc);
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		controlsPanel.add($seleccion, gc);
	
		////////Posicion//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Posicion: "), gc);	
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($posicion, gc);
		
		////////Puntaje//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Puntaje: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($puntaje, gc);
		
		////////Amarillas//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Amarilla: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($amarilla, gc);
		
////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		controlsPanel.add(new JLabel("Rojas: "), gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($rojas, gc);
		
		
////////Next Row//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(3, 0, 0, 5);
		JTextArea $t = new JTextArea("");
		$t.setEditable(false);
		controlsPanel.add($t, gc);	
		
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(3, 0, 0, 0);
		controlsPanel.add($candidatas, gc);		

		//***********************//
		gc.gridy = 0;	
	
		gc.gridx = 1;	

		gc.gridheight = 10;
		
		gc.fill = GridBagConstraints.PAGE_START;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 5);
		listaPanel.add($scroll, gc);
//		
		
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.SOUTH;
		listaPanel.add($button, gc);	
		

		setLayout(new BorderLayout());
		add(listaPanel, BorderLayout.WEST);
		add(controlsPanel,BorderLayout.CENTER);
		add(buttonsPanel,BorderLayout.EAST);		
	}
	
	public void setData(Equipo mejoresVAEquipos) {		
		$equipo_win = mejoresVAEquipos;		
		cargarJugadoresEnJLista();
		cargarEnGrafico();
	}
	
	private void cargarJugadoresEnJLista() {		
		messageListModel.clear();
		for (Jugador j : $equipo_win) {
			messageListModel.addElement(j.getNombre());
		}
	}
	private void cargarEnGrafico(){
		ArrayList<String> $jugadores = new ArrayList<String>();
		for (Jugador j : $equipo_win) {
			$jugadores.add(j.getNombre());
		}
		$graficos.setData($jugadores);
	}
}
