package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private List<Jugador> $jugadores;
	private Connection conn;
	
	public Database() {
		this.$jugadores = new LinkedList<Jugador>();
	}
	
	public void connect() throws Exception{
		if (conn != null)
			return;		
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		conn = DriverManager.getConnection("jdbc:sqlite:jugadores.sqlite");
	}
	
	public void disconnect(){
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("Ha ocurrido un fucking problema");
			}
		}
	}
	
	public void save() throws SQLException{
		String $control_sql = "SELECT COUNT(*) AS count FROM jugador WHERE id = ?";		
		PreparedStatement $ps = conn.prepareStatement($control_sql);
		
		String $insert = "INSERT INTO jugador (id,nombre,seleccion,posicion,puntaje,amarillas,rojas)"
				+ " VALUES(?,?,?,?,?, ?,?)";
		PreparedStatement $insert_ps = conn.prepareStatement($insert);
		
		String $update = "UPDATE jugador SET nombre = ? ,seleccion = ?,posicion = ?,puntaje = ?,amarillas=?,rojas=? WHERE id = ?";
		PreparedStatement $update_ps = conn.prepareStatement($update);
		
		for (Jugador $j: $jugadores) {
			int id = $j.getId();
			String $nombre = $j.getNombre();
			String $seleccion = $j.getSeleccion_nacional();
			TipoJugador $posicion = $j.getPosicion();
			double $puntaje = $j.getPuntaje();
			int $amarillas = $j.getAmarillas();
			int $rojas = $j.getRojas();
			
			$ps.setInt(1, id);
			ResultSet $rs = $ps.executeQuery();
			
			$rs.next();
			int $count = $rs.getInt(1);
			
			if ($count ==0) {
				int $col = 1;
				$insert_ps.setInt($col++, id);
				$insert_ps.setString($col++, $nombre);
				$insert_ps.setString($col++, $seleccion);
				$insert_ps.setString($col++, $posicion.name());
				$insert_ps.setDouble($col++, $puntaje);
				$insert_ps.setInt($col++, $amarillas);
				$insert_ps.setInt($col++, $rojas);
				
				$insert_ps.executeUpdate();				
			}else {
				int $col = 1;
				$update_ps.setString($col++, $nombre);
				$update_ps.setString($col++, $seleccion);
				$update_ps.setString($col++, $posicion.name());
				$update_ps.setDouble($col++, $puntaje);
				$update_ps.setInt($col++, $amarillas);
				$update_ps.setInt($col++, $rojas);
				$update_ps.setInt($col++, id);
				
				$update_ps.executeUpdate();			
			}
		}
		$update_ps.close();
		$insert_ps.close();
		$ps.close();
	}
	
	public void load() throws SQLException{
		$jugadores.clear();		
		String $sql = "SELECT id,nombre,seleccion,posicion,puntaje,amarillas,rojas FROM jugador ORDER BY nombre";
		Statement $select_stmt = conn.createStatement();
		
		ResultSet $rt = $select_stmt.executeQuery($sql);
		while ($rt.next()) {
			int id = $rt.getInt("id");
			String $nombre = $rt.getString("nombre");
			String $seleccion = $rt.getString("seleccion");
			String $posicion = $rt.getString("posicion");
			double $puntaje = $rt.getDouble("puntaje");
			int $amarillas = $rt.getInt("amarillas");
			int $rojas = $rt.getInt("rojas");
			
			Jugador $jugador =new Jugador(id, $nombre, $seleccion, 
					TipoJugador.valueOf($posicion), $puntaje, $amarillas, $rojas); 
			$jugadores.add($jugador);
			
		}
		$rt.close();
		$select_stmt.close();
	}
	
	public void addJugador(Jugador $jugador){
		this.$jugadores.add($jugador);
	}
	
	public void removeJugador(int index){
		$jugadores.remove(index);
	}
	
	public void saveToFile(File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);		
		Jugador[] $arr_jugador = $jugadores.toArray(new Jugador[$jugadores.size()]);		
		oos.writeObject($arr_jugador);
		oos.close();
	}
	
	public void loadFromFile(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);		
		try {
			Jugador[] $arr_jugador = (Jugador[])ois.readObject();
			$jugadores.clear();
			$jugadores.addAll(Arrays.asList($arr_jugador));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		ois.close();
	}
	/**
	 * @return Lista de Jugadores solo de fucking lectura :P
	 */
	public List<Jugador> getJugadoresShingo() {
		
		return Collections.unmodifiableList($jugadores);
	}

	public int size() {
		return $jugadores.size();
	}	
	
	
}
