package negocio;
import java.util.List;
import modelo.Jugador;

public class FuerzaBruta{
	private int n;
	private boolean[]A;
	private List<Jugador> instancia;
	public FuerzaBruta(List<Jugador> inst){
		n = inst.size();
		instancia = inst;
		A = new boolean[n+1];
	}
	
	// Proximo elemento	
	public Equipo next(){	
		Equipo ret = new Equipo();		
		for (int i=0;i<n;++i)
			if  (A[i]==true){
			ret.agregar(instancia.get(i));
		}		
		sumarUno();
		return ret;
	}
	
	public boolean hasNext(){		
//		boolean a = ; 
		return A[n]==false;
	}
	
	// Suma 1 al numero en binario representado por el arreglo
	private void sumarUno()	{
		int i = 0;
		while( A[i] == true ){
			A[i] = false;
			++i;
		}		
		A[i] = true;
	}
}
