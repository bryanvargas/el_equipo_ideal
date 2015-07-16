package modelo;

import java.io.Serializable;

public class Jugador implements Serializable {
	private static final long serialVersionUID = -4556513984121728552L;
	private static int count  = 0;
	private int $id;
	private String $nombre;
	private String $seleccion_nacional;
	private TipoJugador $posicion;
	private Double $puntaje;
	private Integer $amarillas;
	private Integer $rojas;
	
	public Jugador(String $nombre, String $seleccion_nacional,
			TipoJugador $posicion, Double $puntaje, Integer $amarillas,
			Integer $rojas) {
		super();
		this.$nombre = $nombre;
		this.$seleccion_nacional = $seleccion_nacional;
		this.$posicion = $posicion;
		this.$puntaje = $puntaje;
		this.$amarillas = $amarillas;
		this.$rojas = $rojas;
		this.$id = count;
		count++;
	}
	
	public Jugador(int id,String $nombre, String $seleccion_nacional,
			TipoJugador $posicion, Double $puntaje, Integer $amarillas,
			Integer $rojas) {
		this($nombre,$seleccion_nacional,$posicion,$puntaje,$amarillas,$rojas);
		this.$id = id;
		count++;
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
	public void setSeleccion_nacional(String $seleccion_nacional) {
		this.$seleccion_nacional = $seleccion_nacional;
	}
	public TipoJugador getPosicion() {
		return $posicion;
	}
	public void setPosicion(TipoJugador $posicion) {
		this.$posicion = $posicion;
	}
	public Double getPuntaje() {
		return $puntaje;
	}
	public void setPuntaje(Double $puntaje) {
		this.$puntaje = $puntaje;
	}
	public Integer getAmarillas() {
		return $amarillas;
	}
	public void setAmarillas(Integer $amarillas) {
		this.$amarillas = $amarillas;
	}
	public Integer getRojas() {
		return $rojas;
	}
	public void setRojas(Integer $rojas) {
		this.$rojas = $rojas;
	}
	public int getId() {
		return $id;
	}
	public void setId(int $id) {
		this.$id = $id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (($amarillas == null) ? 0 : $amarillas.hashCode());
		result = prime * result + $id;
		result = prime * result + (($nombre == null) ? 0 : $nombre.hashCode());
		result = prime * result
				+ (($posicion == null) ? 0 : $posicion.hashCode());
		result = prime * result
				+ (($puntaje == null) ? 0 : $puntaje.hashCode());
		result = prime * result + (($rojas == null) ? 0 : $rojas.hashCode());
		result = prime
				* result
				+ (($seleccion_nacional == null) ? 0 : $seleccion_nacional
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (!other.canEqual(this)) {
			return false;
		}
		if ($amarillas == null) {
			if (other.$amarillas != null)
				return false;
		} else if (!$amarillas.equals(other.$amarillas))
			return false;
		if ($id != other.$id)
			return false;
		if ($nombre == null) {
			if (other.$nombre != null)
				return false;
		} else if (!$nombre.equals(other.$nombre))
			return false;
		if ($posicion != other.$posicion)
			return false;
		if ($puntaje == null) {
			if (other.$puntaje != null)
				return false;
		} else if (!$puntaje.equals(other.$puntaje))
			return false;
		if ($rojas == null) {
			if (other.$rojas != null)
				return false;
		} else if (!$rojas.equals(other.$rojas))
			return false;
		if ($seleccion_nacional == null) {
			if (other.$seleccion_nacional != null)
				return false;
		} else if (!$seleccion_nacional.equals(other.$seleccion_nacional))
			return false;
		
		return true;
	}
	
	public boolean canEqual(Object other) {
        return (other instanceof Jugador);
    }
	
}
