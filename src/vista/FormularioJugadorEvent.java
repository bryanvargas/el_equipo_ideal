package vista;
import java.util.EventObject;

public class FormularioJugadorEvent extends EventObject {
	private static final long serialVersionUID = 6586754890752998345L;
	private String $nombre;
	private String $seleccion_nacional;
	private int $posicion;
	private double $puntaje;
	private int $amarillas;
	private int $rojas;	
	public FormularioJugadorEvent(Object source) {
		super(source);		
	}
	public FormularioJugadorEvent(Object source, String $nombre, String $seleccion_nacional,
			int $posicion, double $puntaje, int $amarillas, int $rojas) {
		super(source);
		this.$nombre = $nombre;
		this.$seleccion_nacional = $seleccion_nacional;
		this.$posicion = $posicion;
		this.$puntaje = $puntaje;
		this.$amarillas = $amarillas;
		this.$rojas = $rojas;
	}
	public String getNombre() {
		return $nombre;
	}
	public void setNombre(String $nombre) {
		this.$nombre = $nombre;
	}
	public String getSeleccion_nacional() {
		return $seleccion_nacional;
	}
	public void set$seleccion_nacional(String $seleccion_nacional) {
		this.$seleccion_nacional = $seleccion_nacional;
	}
	public int getPosicion() {
		return $posicion;
	}
	public void setPosicion(int $posicion) {
		this.$posicion = $posicion;
	}
	public double getPuntaje() {
		return $puntaje;
	}
	public void setPuntaje(double $puntaje) {
		this.$puntaje = $puntaje;
	}
	public int getAmarillas() {
		return $amarillas;
	}
	public void setAmarillas(int $amarillas) {
		this.$amarillas = $amarillas;
	}
	public int getRojas() {
		return $rojas;
	}
	public void setRojas(int $rojas) {
		this.$rojas = $rojas;
	}	
}
