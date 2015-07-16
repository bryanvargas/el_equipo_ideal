package negocio;

import java.util.Iterator;
import java.util.List;

import modelo.Jugador;

public class ShingoIterator implements Iterator<Jugador> {
	private Iterator<Jugador> iterator;
	public ShingoIterator(List<Jugador> lista) {
		iterator = lista.iterator();
	}
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Jugador next() {
		return iterator.next();
	}
	@Override
	public void remove() {
		iterator.remove();
	}	

}
