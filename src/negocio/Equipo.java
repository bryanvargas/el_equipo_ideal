package negocio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Jugador;
import modelo.TipoJugador;

public class Equipo implements Iterable<Jugador>{
	private List<Jugador> $equipo;
	
	public Equipo()
	{
		this.$equipo=new ArrayList<Jugador>();
	}

	public void agregar (Jugador o)	{
		this.$equipo.add(o);
	}

	
	public boolean controlFormacion(){
		boolean bandera = true;
			if (getArqueros()>1 || getDefensores()>4
					|| getMediocampistas()>3	||getDelantero()>3) {				
				bandera =false;
			}	
		return bandera;
	}
	
	public boolean controlMismaSeleccion(){		
		for (Jugador j : $equipo) 
			if (frecuencySeleccion(j.getSeleccion_nacional())>5) 
				return false;		
		return true;
	}
	
	public boolean controlJugadoresAmarilla(){
		return countAmarillas();
	}
	
	public boolean controlJugadoresRoja(){
		return countRojas();
	}
	
	public boolean cumpleRestricciones(){
		if(!controlFormacion())return false;
		if(!controlMismaSeleccion())return false;
		if(!controlJugadoresAmarilla())return false;
		if(!controlJugadoresRoja())return false;		
		return true;
	}
	
	public Double getPutaje(){
		double $puntaje = 0;
		for (Jugador j : $equipo) 
			$puntaje += j.getPuntaje();
		return $puntaje;
	}
	
	public int getArqueros(){		
		return getCantidad(TipoJugador.arquero);
	}
	public int getDefensores(){
		return getCantidad(TipoJugador.defensor);
	}
	public int getMediocampistas(){
		return getCantidad(TipoJugador.mediocampista);
	}
	public int getDelantero(){
		return getCantidad(TipoJugador.delantero); 
	}


	public int getCantidad(TipoJugador $tipo) {
		int $cantidad = 0;
		for (Jugador j : $equipo) 
			if (j.getPosicion()==$tipo) 
				$cantidad +=1;
		return $cantidad;
	}

	public int frecuencySeleccion(String o){		
		int result = 0;     
	    for (Jugador e : $equipo)
	        if (o.equals(e.getSeleccion_nacional()))
	            result++;        
	    return result;
	}

	public boolean countAmarillas() {
		int result = 0;     
	    for (Jugador e : $equipo)
	        if (e.getAmarillas()>0)
	            result++;  
	    return result <= 3 ? true:false;
	}

	public boolean countRojas() {
		int result = 0;     
	    for (Jugador e : $equipo)
	        if (e.getRojas()>0)
	            result++;        
	    return result <= 1 ? true:false;
	}

	public void removeJugador(int i) {
		$equipo.remove(i);		
	}

	public boolean contain(Jugador j) {
		return $equipo.contains(j)?true:false;
	}
	public int getSize(){
		return $equipo.size();
	}

	public Jugador getJugador(int i){
		return $equipo.get(i);
	}
	@Override
	public Iterator<Jugador> iterator() {
		return new ShingoIterator($equipo);
	}

	public List<Jugador> getEquipo() {
		return $equipo;
	}
	

	@Override
	public String toString() {
		String salida = "";
		for (Jugador i : $equipo) {
			salida += " " +i.getPosicion();
		}
		return salida;
	}
	
}