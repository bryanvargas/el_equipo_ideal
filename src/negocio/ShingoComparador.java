package negocio;
import java.util.Comparator;
import modelo.Jugador;
import modelo.TipoJugador;
/**
 * Clase que se encarga de ordenar un EQUIPO segun su posicion en la cancha,
 * como criterio de ordenacion.
 * @author Shingo
 */
public class ShingoComparador implements Comparator<Jugador> {
	//my shingoComparator fucking TOTAL!!!!!!
	@Override
	public int compare(Jugador o1, Jugador o2) {
		TipoJugador[] $pos = {
				TipoJugador.arquero,
				TipoJugador.defensor,
				TipoJugador.mediocampista,
				TipoJugador.delantero};
		int $jugador_uno = -1;
		int $jugador_dos = -1;
		for (int i = 0; i < $pos.length; i++) {
			if (o1.getPosicion()==$pos[i]) $jugador_uno=i;
			if (o2.getPosicion()==$pos[i]) $jugador_dos=i;
		}
		return $jugador_uno - $jugador_dos;
	}
}
