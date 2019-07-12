package dbImpl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.TransaccionDao;
import entidades.Transaccion;
import exceptions.DAOException;

public class TransaccionDAODBImpl extends DAODBImpl implements TransaccionDao{

	@Override
    public void insertarTransaccion(Transaccion transaccion) throws DAOException{
    	int numerocuenta = transaccion.getNumeroCuenta();
        int numeroCuentaDestino = transaccion.getNumeroCuentaDestino();
        int dni = transaccion.getDni();
        float monto = transaccion.getMonto();
        int numeroTransaccion = transaccion.getNumeroTransaccion();
    	String sql = "INSERT INTO transaccion (numerotransaccion, numerocuenta, destino, dni, monto) VALUES  ('" + numeroTransaccion + "', '" + numerocuenta + "', '" + numeroCuentaDestino + "', '" + dni + "', '" + monto + "')";
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
 
    @Override
    public void eliminarTransaccion(int numeroTransaccion) throws DAOException{
        String sql = "DELETE FROM transaccion WHERE numerotransaccion = '" + numeroTransaccion + "'";
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

//    @Override
//    public void modificarCliente(String nom,String ape, int dni) throws DAOException {
//        String sql = "UPDATE cliente set nombre = '" + nom + "', apellido = '" + ape + "' WHERE dni = '" + dni + "'";
//        Connection c = null;
//        try {
//        	c = coneccionDB(c);
//        	statement(c,sql);
//        } catch (SQLException e) {
//        	throw new DAOException();
//    	}finally {
//    		desconeccionDB(c);
//    	}
//    }

    @Override
    public List<Transaccion> listarTransaccion() throws DAOException {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM transaccion";
        Connection c = null;
		c = coneccionDB(c);
        try {
        	Transaccion resultado = null;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	System.out.println("\r\nTransaccion: ");
                System.out.println(rs.getString("numerotransaccion") + " " + rs.getString("numerocuenta") + " " + rs.getString("monto") + " " + rs.getString("destino"));
                int rsnumerocuenta = rs.getInt("numerocuenta");
				int rsnumerocuentadestino = rs.getInt("destino");
				int rsdni = rs.getInt("dni");
				int rsnumeroTransaccion = rs.getInt("numeroTransaccion");
				float rsmonto = rs.getFloat("monto");
				resultado = new Transaccion(rsdni,rsnumerocuenta,rsmonto,rsnumerocuentadestino,rsnumeroTransaccion);
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
    public Transaccion consultarTransaccion(int numeroTransaccion) throws DAOException{
    	Transaccion resultado = new Transaccion();
        String sql = "SELECT * FROM transaccion WHERE numerotransaccion = '" + numeroTransaccion + "'";
        Connection c = null;
		c = coneccionDB(c);
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
            	System.out.println("\r\nTransaccion: ");
                System.out.println(rs.getString("numerotransaccion") + " " + rs.getString("numerocuenta") + " " + rs.getString("monto") + " " + rs.getString("destino"));
                int rsnumerocuenta = rs.getInt("numerocuenta");
				int rsnumerocuentadestino = rs.getInt("destino");
				int rsdni = rs.getInt("dni");
				int rsnumeroTransaccion = rs.getInt("numeroTransaccion");
				float rsmonto = rs.getFloat("monto");
				resultado = new Transaccion(rsdni,rsnumerocuenta,rsmonto,rsnumerocuentadestino,rsnumeroTransaccion);
            }
        } catch (SQLException e) {
            rollbackDB(c);
        } finally {
        	desconeccionDB(c);
        }
        return resultado;
    }
	
    @Override
    public List<Transaccion> listarTransaccionDni(int dni) throws DAOException {
        List<Transaccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM transaccion WHERE dni = '" + dni + "'";
        Connection c = null;
		c = coneccionDB(c);
        try {
        	Transaccion resultado = null;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	System.out.println("\r\nTransaccion por dni: ");
                System.out.println(rs.getString("numeroTransaccion") + " " + rs.getString("numerocuenta") + " " + rs.getString("monto") + " " + rs.getString("numeroCuentaDestino"));
                int rsnumerocuenta = rs.getInt("numerocuenta");
				int rsnumerocuentadestino = rs.getInt("numeroCuentaDestino");
				int rsdni = rs.getInt("dni");
				int rsnumeroTransaccion = rs.getInt("numeroTransaccion");
				float rsmonto = rs.getFloat("monto");
				resultado = new Transaccion(rsdni,rsnumerocuenta,rsmonto,rsnumerocuentadestino,rsnumeroTransaccion);
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