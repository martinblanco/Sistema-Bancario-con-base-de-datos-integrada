package basics;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.DBException;

public class DBManager {
	
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost//{DIR}/ejemplo";
	@SuppressWarnings("unused")
	private static final String DB_NAME = "/proyecto";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	public static Connection connect() throws DBException {
		Connection c = null;
		try {
			Class.forName(DBManager.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			String url = DB_BASE_URL.replace("{DIR}", obtenerUbicacionBase());
			c = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException();
		}
		return c;
	}

	private static String obtenerUbicacionBase() {
		File currDir = new File("h2/base_de_datos/");
		return currDir.getAbsolutePath();
	}
}