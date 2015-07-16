package negocio;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import vista.FormularioJugadorEvent;
import modelo.Database;
import modelo.Jugador;
import modelo.TipoJugador;

public class Controlador {
	Database db = new Database(); 
	public static int contador = 0;	
	public List<Jugador> getJugador(){		
		return db.getJugadoresShingo();
	}
	
	public void removeJugador(int index){	
		db.removeJugador(index);
	}
	
	public void save() throws SQLException{
		db.save();
	}
	
	public void load() throws SQLException{
		db.load();
	}
	
	public void disconnect(){
		db.disconnect();
	}
	
	public void connect() throws Exception{
		db.connect();
	}

	public void addJugador(FormularioJugadorEvent ev){
		String $nombre = ev.getNombre();
		String $seleccion_nacional = ev.getSeleccion_nacional();
		int $posicion_id = ev.getPosicion();
		double $puntaje = ev.getPuntaje();
		int $amarillas = ev.getAmarillas();
		int $rojas = ev.getRojas();		
		TipoJugador $posicion = null;		
		switch ($posicion_id) {
		case 0:
			$posicion = TipoJugador.arquero;
			break;
		case 1:
			$posicion = TipoJugador.defensor;
			break;
		case 2:
			$posicion = TipoJugador.mediocampista;
			break;
		case 3:
			$posicion = TipoJugador.delantero;
			break;
		}		
	
		Jugador $jugador = new Jugador($nombre, $seleccion_nacional, $posicion, $puntaje, $amarillas, $rojas);		
		db.addJugador($jugador);
	}
	
	public void saveToFile(File file) throws IOException{
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException{
		db.loadFromFile(file);
	}
	

	
	public int countJugadores(){
		return db.size();
	}
	
	public Equipo getMejoresEquiposFuerzaBruta(){	
		return fuerzaBruta(db.getJugadoresShingo());
	}
	public static Equipo fuerzaBruta(List<Jugador> list) {
		Equipo $equipo_final=null;
		contador  = 0;
		FuerzaBruta gen =new FuerzaBruta(list);
		while (gen.hasNext()){
			Equipo actual=gen.next();
			if (actual.getSize()==11 && actual.cumpleRestricciones()) {		
				if ($equipo_final==null ||actual.getPutaje()>$equipo_final.getPutaje()) {
					$equipo_final = actual;					
				}				
			}	contador++;		
		}
		Collections.sort($equipo_final.getEquipo(), new ShingoComparador());
		return $equipo_final;
	}
	
	public Equipo getMejoresEquiposBacktracking() {	
		return backtracking(db.getJugadoresShingo());
	}
	
	public static Equipo backtracking(List<Jugador> list) {	  
		  Equipo $equipo_final=null;
			contador = 0;
			Backtracking gen =new Backtracking(list);			
			gen.backtrack();
			for (Equipo c : gen.getEquipo()){
				Equipo actual=c;				
					if ($equipo_final==null ||actual.getPutaje()>$equipo_final.getPutaje() ) {
						$equipo_final = actual;					
					}
					contador++;
			}	
			Collections.sort($equipo_final.getEquipo(), new ShingoComparador());
			return $equipo_final;

	  }

	public static int contador() {
		return contador;
		
	}
	
}