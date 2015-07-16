package vista;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JLabel;
public class GraficosCancha extends JDialog{
	private static final long serialVersionUID = -6242828211941516852L;
	private JLabel cancha;
	private JLabel $arquero,
				   $defensor_1,
				   $defensor_2,
				   $defensor_3,
				   $defensor_4,
				   $mediocampista_1,
				   $mediocampista_2,
				   $mediocampista_3,
				   $delantero_1,
				   $delantero_2,
				   $delantero_3;
				   
	public GraficosCancha(){
		cancha = new JLabel();
		$arquero = new JLabel("ALguen");
		cancha.setIcon(Utils.createIcon("/images/cancha.png"));
		add(cancha);
		setUpJLabelsEquipo();
        setTitle("Equipo Fucking Ideal");
        setSize(600,375);
        setResizable(false); 
        setLocationRelativeTo(null);	       
    }
	
	public void setData(ArrayList<String> $jugadores) {		
		$arquero.setText($jugadores.get(0));
		$defensor_1.setText($jugadores.get(1));
		$defensor_2.setText($jugadores.get(2));
		$defensor_3.setText($jugadores.get(3));
		$defensor_4.setText($jugadores.get(4));
		$mediocampista_1.setText($jugadores.get(5));
		$mediocampista_2.setText($jugadores.get(6));
		$mediocampista_3.setText($jugadores.get(7));
		$delantero_1.setText($jugadores.get(8));
		$delantero_2.setText($jugadores.get(9));
		$delantero_3.setText($jugadores.get(10));
	}
	
	private void setUpJLabelsEquipo(){
		$defensor_1 = new JLabel();
		$defensor_1.setBounds(160, 2, 120, 50);
		$defensor_2 = new JLabel();
		$defensor_2.setBounds(145, 86, 120, 50);
		$arquero = new JLabel();
		$arquero.setBounds(70, 145, 120, 50);
		$defensor_3 = new JLabel();
		$defensor_3.setBounds(145, 217, 120, 50);
		$defensor_4 = new JLabel();
		$defensor_4.setBounds(160, 300, 120, 50);
		$mediocampista_1 = new JLabel();
		$mediocampista_1.setBounds(285, 35, 120, 50);
		$mediocampista_2 = new JLabel();
		$mediocampista_2.setBounds(248, 145, 120, 50);
		$mediocampista_3 = new JLabel();
		$mediocampista_3.setBounds(285, 245, 120, 50);
		$delantero_1 = new JLabel();
		$delantero_1.setBounds(410, 58, 120, 50);
		$delantero_2 = new JLabel();
		$delantero_2.setBounds(410, 140, 120, 50);
		$delantero_3 = new JLabel();
		$delantero_3.setBounds(408, 229, 120, 50);

		cancha.add($arquero);		
		cancha.add($defensor_1);		
		cancha.add($defensor_2);
		cancha.add($defensor_3);
		cancha.add($defensor_4);
		cancha.add($mediocampista_1);
		cancha.add($mediocampista_2);
		cancha.add($mediocampista_3);
		cancha.add($delantero_1);
		cancha.add($delantero_2);
		cancha.add($delantero_3);
	}
}
