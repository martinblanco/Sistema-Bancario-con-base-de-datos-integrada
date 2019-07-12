package basics;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	public void createClienteTable() throws SQLException {
		String sql = "CREATE TABLE cliente ( id INTEGER IDENTITY, nombre VARCHAR(256), apellido VARCHAR(256), dni INTEGER, cajaahorro FLOAT, cuentacorriente FLOAT)";
		conectarDB(sql);
		}

	public void createCuentaTable() throws SQLException {
		String sql = "CREATE TABLE cuenta ( id INTEGER IDENTITY, numerocuenta INTEGER, dni INTEGER, cajaahorro FLOAT, cajadolares FLOAT, cuentacorriente FLOAT)";
		conectarDB(sql);
		}
	
	public void createTransaccionTable() throws SQLException {
		String sql = "CREATE TABLE transaccion ( id INTEGER IDENTITY, numerotransaccion INTEGER, numerocuenta INTEGER, dni INTEGER, monto FLOAT, destino INTEGER)";
		conectarDB(sql);
		}
	
	public void createTarjetaCreditoTable() throws SQLException {
		String sql = "CREATE TABLE tarjetacredito ( id INTEGER IDENTITY, numerotarjeta INTEGER, dni INTEGER, numerocuenta INTEGER, apagar FLOAT)";
		conectarDB(sql);
		}
	
	public void createTarjetaDebitoTable() throws SQLException {
		String sql = "CREATE TABLE tarjetadebito ( id INTEGER IDENTITY, numerotarjeta INTEGER, dni INTEGER, numerocuenta INTEGER, saldo FLOAT)";
		conectarDB(sql);
		}
	
	public void conectarDB(String sql) throws SQLException{
		Connection c = DBManager.connect();
			try {
				Statement s = c.createStatement();
				s.execute(sql);
				c.commit();
			} catch (SQLException e) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					throw new SQLException("Problema al borrar la table", e);
				}
			} finally {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}