package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.Jugador;

public class TableJugadorPanel extends JPanel {
	private static final long serialVersionUID = 3490186430265787204L;
	private JTable table;
	private JugadorTableModel tableModel;
	private JPopupMenu popup;
	private JugadorTableListener personTableListener;
	
	
	public TableJugadorPanel() {
		tableModel = new JugadorTableModel();
		table = new JTable(tableModel);
		popup =  new JPopupMenu();
		
		JMenuItem removeItem  = new JMenuItem("Eliminar Fila");
		popup.add(removeItem);		
		
		table.addMouseListener(new MouseAdapter() {			
			public void mousePressed(MouseEvent e) {		
				int row = table.rowAtPoint(e.getPoint());				
				table.getSelectionModel().setSelectionInterval(row, row);
				if(e.getButton()==MouseEvent.BUTTON3){
					popup.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		removeItem.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				int row  = table.getSelectedRow();
				if (personTableListener != null) {
					personTableListener.rowDelete(row);
					tableModel.fireTableRowsDeleted(row, row);
				}				
			}
		});
		
		setLayout(new BorderLayout());		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Jugador> db){
		tableModel.setData(db);
	}
	
	public void refresh(){
		tableModel.fireTableDataChanged();
	}
	
	public void setPersonTableListener(JugadorTableListener listener){
		this.personTableListener  = listener;
	}
}
