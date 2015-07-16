package negocio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.Jugador;

public class Backtracking {
	private int $size_jugadores;
	private int[] A;
	private List<Jugador> instancia;
	private List<Equipo> $equipo ;


	public Backtracking(List<Jugador> inst){
		$size_jugadores = inst.size();
		instancia = inst;	
		A = new int[$size_jugadores+1];
		$equipo = new ArrayList<Equipo>();
	}	
	

	/**
	 *obteneme mediante recursion todas las soluciones validas
	 *que cumplan con las restricciones del TP
	 */
	//obteneme mediante recursion todas las soluciones validas
	//que cumplan con loas restricciones del TP
	public void backtrack(){			
		soluciones(0);
	}	
	public void soluciones(int etapa){
		int i = 0;
		if (etapa>$size_jugadores-1) return;
		do {			
			A[etapa]=i;		
			//controla que sea valido y ademas que siempre
			//la cantidad de jugadores disponibles para seleccionar de la instancia
			//sea mayor a la cantidad de jugadores que faltan para completar el EQUIPO.
			//....se entiende o no?????????????????????????????????????????????????
			if (esValido()&&(11-miEquipo().getSize() < instancia.size()-etapa)) {	
				if (etapa==$size_jugadores-1 && miEquipo().getSize()==11){ 
					$equipo.add(miEquipo());
				}
				else 
					soluciones(etapa+1);	
			}
			i++;
		} while (A[etapa]!=1);
		A[etapa]=-1;		
	}

	/**
	 * agrega el jugador al Equipo, en donde el elemento sea 1
	 * y devolve ese fucking equipo....no te duermas gil de goma y no escribas sandeces!!!!
	 * @return un Equipo
	 */
	//agrega el jugador al Equipo, en donde el elemento sea 1
	//y devolve ese fucking equipo....no te duermas gil de goma y no escribas sandeces!!!!
	private Equipo miEquipo()	{	
		Equipo ret = new Equipo();		
		for (int i=0;i<$size_jugadores;++i)
			if  (A[i]==1){
			ret.agregar(instancia.get(i));			
		}		
		return ret;
	}	
	
	/**
	 * controlar por cada agregacion de jugador
	 * que cumpla con las restricciones, devolviendo un booleano
	 * @return true si es valido
	 */
	//controlar por cada agregacion de jugador
	//que cumpla con las restricciones, devolviendo un booleano
	public boolean esValido() {
		Equipo ret = new Equipo();				
		for (int i=0;i<$size_jugadores;++i){	
			if  (A[i]==1){
			ret.agregar(instancia.get(i));				
			}			
		}
		return ret.cumpleRestricciones();
	}
	
	public List<Equipo> getEquipo() {
		return Collections.unmodifiableList($equipo);
	}

}
