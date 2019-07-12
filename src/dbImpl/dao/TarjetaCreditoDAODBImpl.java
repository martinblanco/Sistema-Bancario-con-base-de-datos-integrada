package dbImpl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.TarjetaCreditoDao;
import entidades.TarjetaCredito;
import exceptions.DAOException;

public class TarjetaCreditoDAODBImpl extends DAODBImpl implements TarjetaCreditoDao{
	
	public void insertarTarjetaCredito(TarjetaCredito tarjeta) throws DAOException{
    	int numeroCuenta = tarjeta.getNumeroCuenta();
        int numeroTarjeta = tarjeta.getNumeroTarjeta();
        float apagar = tarjeta.getApagar();
        int dni = tarjeta.getDni();
    	String sql = "INSERT INTO tarjetacredito (numerotarjeta, dni, numerocuenta, apagar) VALUES  ('" + numeroTarjeta + "', '" + dni + "', '" + numeroCuenta + "', '" + apagar + "')";
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
	
	public void eliminarTarjetaCredito(int numeroTarjeta) throws DAOException{
		String sql = "DELETE FROM tarjetacredito WHERE numerotarjeta = '" + numeroTarjeta + "'";
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
	
	public void modificarTarjetaCredito(int numeroTarjeta, float apagar) throws DAOException {
        String sql = "UPDATE tarjetacredito set apagar = '" + apagar + "' WHERE numerotarjeta = '" + numeroTarjeta + "'";
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
    public List<TarjetaCredito> listarTarjetaCredito() throws DAOException {
        List<TarjetaCredito> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarjetacredito";
        Connection c = null;
		c = coneccionDB(c);
        try {
        	TarjetaCredito resultado = null;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	System.out.println("\r\nTarjeta Consultada: ");
            	System.out.println(rs.getString("numerotarjeta") + " " + rs.getString("numerocuenta") + " " + rs.getString("dni") + " " + rs.getString("apagar"));
            	int rsnumerocuenta = rs.getInt("numerocuenta");
            	int rsdni = rs.getInt("dni");
            	int rsnumerotarjeta = rs.getInt("numerotarjeta");
            	float rsapagar = rs.getFloat("apagar");
				resultado = new TarjetaCredito(rsnumerotarjeta,rsdni,rsnumerocuenta,rsapagar);
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
	    public TarjetaCredito consultarTarjetaCredito(int numeroTarjeta) throws DAOException{
	  		TarjetaCredito resultado = new TarjetaCredito();
	        String sql = "SELECT * FROM tarjetacredito WHERE numerotarjeta = '" + numeroTarjeta + "'";
	        Connection c = null;
			c = coneccionDB(c);
	        try {
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            if (rs.next()) {
	            	System.out.println("\r\nTarjeta Consultada: ");
	            	System.out.println(rs.getString("numerotarjeta") + " " + rs.getString("numerocuenta") + " " + rs.getString("dni") + " " + rs.getString("apagar"));
	            	int rsnumerocuenta = rs.getInt("numerocuenta");
	            	int rsdni = rs.getInt("dni");
	            	int rsnumerotarjeta = rs.getInt("numerotarjeta");
	            	float rsapagar = rs.getFloat("apagar");
					resultado = new TarjetaCredito(rsnumerotarjeta,rsdni,rsnumerocuenta,rsapagar);
	            }
	        } catch (SQLException e) {
	            rollbackDB(c);
	        } finally {
	        	desconeccionDB(c);
	        }
	        return resultado;
	    }
}
