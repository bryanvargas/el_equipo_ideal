package vista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
public class Toolbar extends JToolBar implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton saveButton;
	private JButton refreshButton;
	private JButton fuerzaBrutaButton;
	private JButton vueltaAtrasButton;
	private ToolbarListener toolbarListener;
	
	
	public Toolbar() {		
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);
		saveButton = new JButton();
		saveButton.setIcon(createIcon("/images/save.png"));
		saveButton.setToolTipText("Save");
		saveButton.addActionListener(this);
		saveButton.setFocusable(false);
		
		refreshButton = new JButton();
		refreshButton.setToolTipText("Refresh");
		refreshButton.addActionListener(this);
		refreshButton.setFocusable(false);
		refreshButton.setIcon(createIcon("/images/refresh.png"));
		
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);
		
		
		fuerzaBrutaButton = new JButton("Fuerza Bruta");
//		fuerzaBrutaButton.setIcon(createIcon("/images/Save16.gif"));
		fuerzaBrutaButton.setToolTipText("Fuerza Bruta");
		fuerzaBrutaButton.addActionListener(this);
		fuerzaBrutaButton.setFocusable(false);		
		
		vueltaAtrasButton = new JButton("Backtracking");
//		fuerzaBrutaButton.setIcon(createIcon("/images/Save16.gif"));
		vueltaAtrasButton.setToolTipText("Vuelta Atras");
		vueltaAtrasButton.addActionListener(this);
		vueltaAtrasButton.setFocusable(false);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(saveButton);
		add(refreshButton);
		addSeparator();
		add(fuerzaBrutaButton);
		add(vueltaAtrasButton);	
	}
	
	private ImageIcon createIcon(String path){
		URL url = getClass().getResource(path);
		if(url == null){
			System.err.println("Ruta de imagen no encontrada: "+ path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
	
	public void setToolbarListener(ToolbarListener listener){
		this.toolbarListener = listener;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		if(clicked == saveButton){
			if (toolbarListener != null) {
				toolbarListener.saveEventOccured();
			}
		}else if(clicked == refreshButton){
			if (toolbarListener != null) {
				toolbarListener.refreshEventOccured();
			}
		}else if(clicked == fuerzaBrutaButton){
			if (toolbarListener != null) {
				toolbarListener.fuerzaBrutaEventOccured();
			}
		}
		else if(clicked == vueltaAtrasButton){
			if (toolbarListener != null) {
				toolbarListener.backtrackingEventOccured();
			}
		}		
	}		
}
