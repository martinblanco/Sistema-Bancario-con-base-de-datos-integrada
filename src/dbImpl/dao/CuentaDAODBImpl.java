package dbImpl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.CuentaDao;
import entidades.Cuenta;
import exceptions.DAOException;

public class CuentaDAODBImpl extends DAODBImpl implements CuentaDao{
	
	public void insertarCuenta(Cuenta cuenta) throws DAOException{
    	int numeroCuenta = cuenta.getNumeroCuenta();
        float cuentaCorriente = cuenta.getCuentaCorriente();
        float cajaAhorros = cuenta.getCajaAhorros();
        float cajaDolares = cuenta.getCajaDolares();
        int dni = cuenta.getDniCliente();
    	String sql = "INSERT INTO CUENTA (numerocuenta, dni, cajaahorro, cajadolares, cuentacorriente) VALUES  ('" + numeroCuenta + "', '" + dni + "', '" + cajaAhorros + "', '" + cajaDolares + "', '" + cuentaCorriente + "')";
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
	
	public void eliminarCuenta(int numeroCuenta) throws DAOException{
		String sql = "DELETE FROM CUENTA WHERE numerocuenta = '" + numeroCuenta + "'";
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
	
	public void modificarCuenta(int numeroCuenta, float cajaAhorro, float cajaDolares, float cuentaCorriente) throws DAOException {
        String sql = "UPDATE cuenta set cajaahorro = '" + cajaAhorro + "', cajadolares = '" + cajaDolares + "', cuentacorriente = '" + cuentaCorriente + "' WHERE numerocuenta = '" + numeroCuenta + "'";
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
	
	public void depositarCuenta(int numeroCuenta, float cajaAhorro, float cajaDolares, float cuentaCorriente) throws DAOException {
        String sql = "UPDATE cuenta set cajaahorro = (cajaahorro+'" + cajaAhorro + "') , cajadolares = (cajadolares+'" + cajaDolares + "') , cuentacorriente = (cuentacorriente+'" + cuentaCorriente + "') WHERE numerocuenta = '" + numeroCuenta + "'";
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
    public List<Cuenta> listarCuentas() throws DAOException {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta";
        Connection c = null;
		c = coneccionDB(c);
        try {
        	Cuenta resultado = null;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	System.out.println("\r\nCuenta Consultada: ");
            	System.out.println(rs.getString("numerocuenta") + " " + rs.getString("cajaahorro") + " " + rs.getString("dni") + " " + rs.getString("cajadolares") + " " + rs.getString("cuentacorriente"));
            	int rsnumerocuenta = rs.getInt("numerocuenta");
            	int rsdni = rs.getInt("dni");
            	float rscajaahorro = rs.getFloat("cajaahorro");
            	float recajadolares = rs.getFloat("cajadolares");
            	float recuentacorriente = rs.getFloat("cuentacorriente");
				resultado = new Cuenta(rsnumerocuenta,rsdni,rscajaahorro,recajadolares,recuentacorriente);
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
	    public Cuenta consultarCuenta(int numeroCuenta) throws DAOException{
	    	Cuenta resultado = new Cuenta();
	        String sql = "SELECT * FROM cuenta WHERE numeroCuenta = '" + numeroCuenta + "'";
	        Connection c = null;
			c = coneccionDB(c);
	        try {
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            if (rs.next()) {
	            	System.out.println("\r\nCuenta Consultado: ");
	                System.out.println(rs.getString("numerocuenta") + " " + rs.getString("cajaahorro") + " " + rs.getString("dni") + " " + rs.getString("cajadolares") + " " + rs.getString("cuentacorriente"));
	                int rsdni = rs.getInt("dni");
	                int rsnumerocuenta = rs.getInt("numerocuenta");
	                resultado.setNumeroCuenta(rsnumerocuenta);
	                float rscajaahorro = rs.getFloat("cajahorro");
	                float recajadolares = rs.getFloat("cajadolares");
	                float recuentacorriente = rs.getFloat("cuentacorriente");
	                resultado = new Cuenta(rsnumerocuenta,rsdni,rscajaahorro,recajadolares,recuentacorriente);
	            }
	        } catch (SQLException e) {
	            rollbackDB(c);
	        } finally {
	        	desconeccionDB(c);
	        }
	        return resultado;
	    }
	
		@Override
	    public List<Cuenta> listarCuentas(int dni) throws DAOException {
	        List<Cuenta> lista = new ArrayList<>();
	        String sql = "SELECT * FROM cuenta WHERE dni = '" + dni + "'";
	        Connection c = null;
			c = coneccionDB(c);
			System.out.println(dni+"ultimo");
	        try {
	        	System.out.println(dni+"ultimo");
	        	Cuenta resultado = null;
	            Statement s = c.createStatement();
	            System.out.println(dni+"ultimo");
	            ResultSet rs = s.executeQuery(sql);;
	            System.out.println(dni+"ultimo");
	            while (rs.next()) {
	            	System.out.println("\r\nCuenta Consultada: ");
	            	System.out.println(rs.getString("numerocuenta") + " " + rs.getString("cajaahorro") + " " + rs.getString("dni") + " " + rs.getString("cajadolares") + " " + rs.getString("cuentacorriente"));
	            	int rsnumerocuenta = rs.getInt("numerocuenta");
	            	int rsdni = rs.getInt("dni");
	            	float rscajaahorro = rs.getFloat("cajaahorro");
	            	float recajadolares = rs.getFloat("cajadolares");
	            	float recuentacorriente = rs.getFloat("cuentacorriente");
					resultado = new Cuenta(rsnumerocuenta,rsdni,rscajaahorro,recajadolares,recuentacorriente);
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
