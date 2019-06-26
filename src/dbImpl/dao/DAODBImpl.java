package dbImpl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import basics.DBManager;
import exceptions.DAOException;
import exceptions.DBException;

public abstract class DAODBImpl {
	
	public Connection coneccionDB(Connection c) throws DAOException {
		try {
			c = DBManager.connect();
		} catch (DBException e) {
			throw new DAOException();
		}
		return c;
	}
	
	public void statement(Connection c, String sql) throws SQLException {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
	}
	
	public void desconeccionDB(Connection c) throws DAOException{		
		try{
			c.close();
		} catch (SQLException e1) {
			throw new DAOException();
        }
	}
	
	public void rollbackDB(Connection c) throws DAOException{
		try{
			c.rollback();
		} catch (SQLException e1) {
			throw new DAOException();
        }
	}
}