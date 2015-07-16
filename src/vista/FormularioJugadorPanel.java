package vista;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class FormularioJugadorPanel extends JPanel {
	private static final long serialVersionUID = 5780707960366557074L;
	private JLabel $nombreLabel;
	private JLabel $sel_nacionalLabel;
	private JTextField $nombreField;
	private JComboBox<String> $sel_nacionalField;
	private JButton okBtn;
	private FormularioJugadorListener formListener;
	private JList<Posicion> $posicionList;
	private JTextField $puntajeCombo;
	private JComboBox<Integer> $amarillasCombo;
	private JComboBox<Integer> $rojasCombo;	
	int delay;
	
	public FormularioJugadorPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 257;	
		setPreferredSize(dim);
		setMinimumSize(dim);
		setUpPanel();	
		
		//cuando el fucking usuario de click en el boton ok desde el main,
		//cargame los datos que ingreso, en el evento jugador 
		okBtn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String $nombre = $nombreField.getText();
				String $seleccion = (String) $sel_nacionalField.getSelectedItem();	
				Posicion $posicion = (Posicion)$posicionList.getSelectedValue();
				int $puntaje = Integer.parseInt($puntajeCombo.getText());
				int $amarillas = (int) $amarillasCombo.getSelectedItem();
				int $rojas = (int)$rojasCombo.getSelectedItem();				
				FormularioJugadorEvent ev = new FormularioJugadorEvent(this, $nombre, $seleccion, $posicion.getId(), 
												$puntaje, $amarillas, $rojas);
				
				if (formListener != null) {
					formListener.formEventOccurred(ev);					
				}				
			}
		});
		
		//agrega bordes y incrementa el tamanio del titulo
		Border innerBorder = BorderFactory.createTitledBorder(null, "Jugador", delay, delay, 
				new Font("Thaoma", Font.BOLD, 17), Color.GRAY);
		Border outerBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);		
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outerBorder));		
		layoutComponents();
		
	}
	
	
	public void setUpPanel(){
		$nombreLabel = new JLabel("Name: ");
		$sel_nacionalLabel = new JLabel("Seleccion: ");
		$nombreField = new JTextField(10);
		$sel_nacionalField = new JComboBox<String>();
		$sel_nacionalField.setEditable(false);
		$posicionList = new JList<Posicion>();
		$puntajeCombo = new JTextField(10);		
		$amarillasCombo = new JComboBox<Integer>();
		$amarillasCombo.setEditable(false);
		$rojasCombo = new JComboBox<Integer>();
		$rojasCombo.setEditable(false);
		okBtn = new JButton("OK");
		
//		Set Up mnemoc
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		$nombreLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		$nombreLabel.setLabelFor($nombreField);	
		
		//Set Up combo box.
		for(int f=0;f<Utils.PAISES.length;f++) {   
			$sel_nacionalField.addItem(Utils.PAISES[f]);
		}
		$sel_nacionalField.setMaximumRowCount(5);
		$sel_nacionalField.setSelectedItem(0);
		$sel_nacionalField.setEditable(true);		
		
		
		//Set Up combo box.
		for(int f=0;f<=30;f++) {
			$amarillasCombo.addItem(f);
        }
		$amarillasCombo.setMaximumRowCount(5);
		$amarillasCombo.setSelectedItem(0);
		$amarillasCombo.setEditable(true);
				
		//Set Up combo box.
		for(int f=0;f<=30;f++) {
			$rojasCombo.addItem(f);
        }
		$rojasCombo.setMaximumRowCount(5);
		$rojasCombo.setSelectedItem(0);
		$rojasCombo.setEditable(true);
		
		DefaultListModel<Posicion> $posicion_model = new DefaultListModel<Posicion>();		
		$posicion_model.addElement(new Posicion(0,"Arquero"));
		$posicion_model.addElement(new Posicion(1,"Defensor"));
		$posicion_model.addElement(new Posicion(2,"Mediocampista"));
		$posicion_model.addElement(new Posicion(3,"Delantero"));
		$posicionList.setModel($posicion_model);		
		$posicionList.setPreferredSize(new Dimension(110,85));
		$posicionList.setBorder(BorderFactory.createEtchedBorder());		
		$posicionList.setSelectedIndex(2);
	}
	
	//setUp posicion de los componentes dentro del lienzo
	public void layoutComponents(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//////////Primera Fila//////////////////////////
		gc.gridy = 0;	
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		
		gc.gridx = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($nombreLabel, gc);
		
		gc.gridx = 1; 
		gc.gridy = 0;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($nombreField, gc);
		
		
		
		
		
		
		////////Segunda Fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add($sel_nacionalLabel, gc);
		
		
		gc.gridx = 1;
		gc.gridy = 1;	
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add($sel_nacionalField	, gc);
		
		////////tercera Fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Posicion: "), gc);
		
		gc.gridx = 1;
		gc.anchor =  GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($posicionList, gc);	
		
		////////Cuarta fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Puntaje: "), gc);
		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($puntajeCombo, gc);	
		
		////////quinta fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Amarillas "), gc);		
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($amarillasCombo, gc);
		
		
		////////Sexta fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Rojas "), gc);			
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add($rojasCombo, gc);		
	
		
		////////Septima Fila//////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		
		gc.gridx = 1;		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);		
		
	}

	public void setFormListener(FormularioJugadorListener formListener) {
		this.formListener = formListener;		
	} 
	
}

//clase un poco al pedo, pero es la manera de obtener un id de la seleccion 
//que el usuario seleccione, valga la redundancia, dado que este 
//atributo es un enumarado
class Posicion{
	private String text;
	private int id;
	public Posicion(int id, String text){
		this.id = id;
		this.text = text;
	}
	
	@Override
	public String toString() {	
		return this.text;
	}

	public int getId() {
		return id;
	}
	
	
}

