package vista;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
public class ProgressDialog extends JDialog{
	private static final long serialVersionUID = -1797695873272583236L;
	private JButton cancelbutton;
	private JProgressBar progressBar;
	private ProgressDialogListener listener;
	public ProgressDialog(Window parent, String titulo) {
		super(parent,titulo, ModalityType.APPLICATION_MODAL);
		
		cancelbutton = new JButton("Cancel");
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		
		
		progressBar.setMaximum(10);
		
		progressBar.setString("Procesando Solicitud...");
		
		setLayout(new FlowLayout());
		
		Dimension size = cancelbutton.getPreferredSize();
		size.width = 400;
		progressBar.setPreferredSize(size);
		
		add(progressBar);
		add(cancelbutton);
		
		
		cancelbutton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener !=null) {
					listener.cancelled();
				}				
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (listener!=null) {
					listener.cancelled();
				}
			}
			
		});
		
		pack();
		
		setLocationRelativeTo(parent);
	}
	
	public void setListener(ProgressDialogListener lis){
		this.listener = lis;
	}
	
	public void setMaximo(int count){
		progressBar.setMaximum(count);
	}
	
	public void setValue(int value){
		int progress = 100*value/progressBar.getMaximum();
		progressBar.setString(String.format("%d%% complete", progress));
		progressBar.setValue(value);
	}

	@Override
	public void setVisible(final boolean visible) {
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				
				if (visible) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				}else {
					setCursor(Cursor.getDefaultCursor());
				}
				ProgressDialog.super.setVisible(visible);
			}
		});
	}
	
	
}
