package vista;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class App {	
	public static void main(String[] args) {
		System.out.println();
		try
		{
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
		    SwingUtilities.invokeLater(new Runnable() {			
				@Override
				public void run() {
					new MainFrame();							
				}
			});	
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		
	}
}
