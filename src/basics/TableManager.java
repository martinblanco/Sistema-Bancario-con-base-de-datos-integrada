package basics;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
				e1.printStackTrace();
			}
			throw e;
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	public void dropClienteTable() {

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