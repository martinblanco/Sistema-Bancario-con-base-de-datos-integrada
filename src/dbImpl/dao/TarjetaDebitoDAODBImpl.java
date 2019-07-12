package dbImpl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.TarjetaDebitoDao;
import entidades.TarjetaDebito;
import exceptions.DAOException;

public class TarjetaDebitoDAODBImpl extends DAODBImpl implements TarjetaDebitoDao{
	
	public void insertarTarjetaDebito(TarjetaDebito tarjeta) throws DAOException{
    	int numeroCuenta = tarjeta.getNumeroCuenta();
        int numeroTarjeta = tarjeta.getNumeroTarjeta();
        float saldo = tarjeta.getSaldo();
        int dni = tarjeta.getDni();
    	String sql = "INSERT INTO tarjetadebito (numerotarjeta, dni, numerocuenta, saldo) VALUES  ('" + numeroTarjeta + "', '" + dni + "', '" + numeroCuenta + "', '" + saldo + "')";
    	Connection c = null;
    	c = coneccionDB(c);
    	try {
    		statement(c,sql);
    	} catch (SQLException e) {
            throw new DAOException();
        }finally {
    		desconeccionDB(c);
        }			
    }
	
	public void eliminarTarjetaDebito(int numeroTarjeta) throws DAOException{
		String sql = "DELETE FROM tarjetadebito WHERE numerotarjeta = '" + numeroTarjeta + "'";
    	Connection c = null;
    	c = coneccionDB(c);
    	try {
    		statement(c,sql);
    	} catch (SQLException e) {
            throw new DAOException();
        }finally {
    		desconeccionDB(c);
        }			
    }
	
	public void modificarTarjetaDebito(int numeroTarjeta, float saldo) throws DAOException {
        String sql = "UPDATE tarjetadebito set saldo = '" + saldo + "' WHERE numerotarjeta = '" + numeroTarjeta + "'";
        Connection c = null;
        try {
        	c = coneccionDB(c);
        	statement(c,sql);
        } catch (SQLException e) {
        	throw new DAOException();
    	}finally {
    		desconeccionDB(c);
    	}
    }
	
	public void depositarTarjetaDebito(int numeroTarjeta, float saldo) throws DAOException {
		System.out.println(saldo);
		String sql = "UPDATE tarjetadebito set saldo = (saldo+'" + saldo + "') WHERE numerotarjeta = '" + numeroTarjeta + "'";
        Connection c = null;
        try {
        	c = coneccionDB(c);
        	statement(c,sql);
        } catch (SQLException e) {
        	throw new DAOException();
    	}finally {
    		desconeccionDB(c);
    	}
    }
	
	@Override
    public List<TarjetaDebito> listarTarjetaDebito() throws DAOException {
        List<TarjetaDebito> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarjetadebito";
        Connection c = null;
		c = coneccionDB(c);
        try {
        	TarjetaDebito resultado = null;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	System.out.println("\r\nTarjeta Consultada: ");
            	System.out.println(rs.getString("numerotarjeta") + " " + rs.getString("numerocuenta") + " " + rs.getString("dni") + " " + rs.getString("saldo"));
            	int rsnumerocuenta = rs.getInt("numerocuenta");
            	int rsdni = rs.getInt("dni");
            	int rsnumerotarjeta = rs.getInt("numerotarjeta");
            	float rssaldo = rs.getFloat("saldo");
				resultado = new TarjetaDebito(rsnumerotarjeta,rsdni,rsnumerocuenta,rssaldo);
				lista.add(resultado);
            }    
        } catch (SQLException e) {
        	rollbackDB(c);
        } finally {
        	desconeccionDB(c);
        }
        return lista;
    }
	
	  	@Override
	    public TarjetaDebito consultarTarjetaDebito(int numeroTarjeta) throws DAOException{
	  		TarjetaDebito resultado = new TarjetaDebito();
	        String sql = "SELECT * FROM tarjetadebito WHERE numerotarjeta = '" + numeroTarjeta + "'";
	        Connection c = null;
			c = coneccionDB(c);
	        try {
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            if (rs.next()) {
	            	System.out.println("\r\nTarjeta Consultada: ");
	            	System.out.println(rs.getString("numerotarjeta") + " " + rs.getString("numerocuenta") + " " + rs.getString("dni") + " " + rs.getString("saldo"));
	            	int rsnumerocuenta = rs.getInt("numerocuenta");
	            	int rsdni = rs.getInt("dni");
	            	int rsnumerotarjeta = rs.getInt("numerotarjeta");
	            	float rssaldo = rs.getFloat("saldo");
					resultado = new TarjetaDebito(rsnumerotarjeta,rsdni,rsnumerocuenta,rssaldo);
	            }
	        } catch (SQLException e) {
	            rollbackDB(c);
	        } finally {
	        	desconeccionDB(c);
	        }
	        return resultado;
	    }
	  	
		@Override
	    public List<TarjetaDebito> listarTarjetaDebito(int dni) throws DAOException {
	        List<TarjetaDebito> lista = new ArrayList<>();
	        String sql = "SELECT * FROM tarjetadebito WHERE dni= '" + dni + "'";
	        Connection c = null;
			c = coneccionDB(c);
	        try {
	        	TarjetaDebito resultado = null;
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next()) {
	            	System.out.println("\r\nTarjeta Consultada: ");
	            	System.out.println(rs.getString("numerotarjeta") + " " + rs.getString("numerocuenta") + " " + rs.getString("dni") + " " + rs.getString("saldo"));
	            	int rsnumerocuenta = rs.getInt("numerocuenta");
	            	int rsdni = rs.getInt("dni");
	            	int rsnumerotarjeta = rs.getInt("numerotarjeta");
	            	float rssaldo = rs.getFloat("saldo");
					resultado = new TarjetaDebito(rsnumerotarjeta,rsdni,rsnumerocuenta,rssaldo);
					lista.add(resultado);
	            }    
	        } catch (SQLException e) {
	        	rollbackDB(c);
	        } finally {
	        	desconeccionDB(c);
	        }
	        return lista;
	    }
}
