package vista;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Jugador;

public class JugadorTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -6143580836685582793L;

	private List<Jugador> db;
	
	private String[] colName = {"ID","Nombre", "Seleccion", "Posicion","Puntaje", "Amarillas",
								"Rojas"};
	
	public JugadorTableModel(){}	
	
	@Override
	public String getColumnName(int column) {		
		return colName[column];
	}
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (db == null) return;
		
		Jugador jugador = db.get(rowIndex);
		switch (columnIndex) {
		case 1:	
			jugador.setNombre((String) value);
			break;	
		case 4:	
			double puntaje = Double.parseDouble((String) value);
			jugador.setPuntaje(puntaje);
			break;		
		default:
			break;
		}
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 4:	
			return true;
		case 1:	
			return true;
		default:
			return false;
		}
	}


	public void setData(List<Jugador> db){
		this.db = db;
	}	
	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {		
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {		
		Jugador person  = db.get(rowIndex);
		switch(columnIndex){
		case 0:
			return person.getId();
		case 1:
			return person.getNombre();
		case 2:
			return person.getSeleccion_nacional();
		case 3:
			return person.getPosicion();
		case 4:
			return person.getPuntaje();
		case 5:
			return person.getAmarillas();
		case 6:
			return person.getRojas();
		}
		return null;
	}

}
