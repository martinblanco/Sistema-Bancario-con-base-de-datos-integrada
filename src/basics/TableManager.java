package basics;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.DBException;

public class TableManager {

	public void createClienteTable() throws SQLException {

		Connection c = DBManager.connect();

		String sql = "CREATE TABLE cliente ( id INTEGER IDENTITY, nombre VARCHAR(256), apellido VARCHAR(256), dni INTEGER, cajaahorro FLOAT, cuentacorriente FLOAT)";

		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				throw new SQLException("Problema al crear la table", e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void dropClienteTable() throws SQLException {

		Connection c = DBManager.connect();

		String sql = "DROP TABLE cliente";

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