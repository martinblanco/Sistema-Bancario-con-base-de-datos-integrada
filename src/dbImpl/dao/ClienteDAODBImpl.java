package dbImpl.dao;

import dao.ClienteDao;
import exceptions.DAOException;
import entidades.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAODBImpl extends DAODBImpl implements ClienteDao{
    
	@Override
    public void insertarCliente(Cliente cliente) throws DAOException{
    	String nombre = cliente.getNombre();
        String apellido = cliente.getApellido();
        int dni = cliente.getDni();
    	String sql = "INSERT INTO cliente (nombre, apellido, dni) VALUES  ('" + nombre + "', '" + apellido + "', '" + dni + "')";
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
    public void eliminarClientecondni(int dni) throws DAOException{
        String sql = "DELETE FROM cliente WHERE dni = '" + dni + "'";
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
    public void modificarCliente(String nom,String ape, int dni) throws DAOException {
        String sql = "UPDATE cliente set nombre = '" + nom + "', apellido = '" + ape + "' WHERE dni = '" + dni + "'";
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
    public List<Cliente> listarClientes() throws DAOException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        Connection c = null;
		c = coneccionDB(c);
        try {
        	Cliente resultado = null;
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	System.out.println("\r\nCliente Consultado: ");
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellido") + " " + rs.getString("dni"));
                String rsnombre = rs.getString("nombre");
				String rsapellido = rs.getString("apellido");
				int rsdni = rs.getInt("dni");
				resultado = new Cliente(rsnombre,rsapellido,rsdni);
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
    public Cliente consultarCliente(int dni) throws DAOException{
    	Cliente resultado = new Cliente();
        String sql = "SELECT * FROM cliente WHERE dni = '" + dni + "'";
        Connection c = null;
		c = coneccionDB(c);
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                System.out.println("\r\nCliente Consultado: ");
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellido") + " " + rs.getString("dni"));
                String rsnombre = rs.getString("nombre");
				String rsapellido = rs.getString("apellido");
				int rsdni = rs.getInt("dni");
				resultado.setNombre(rsnombre);
				resultado.setApellido(rsapellido);
				resultado.setDni(rsdni);
            }
        } catch (SQLException e) {
            rollbackDB(c);
        } finally {
        	desconeccionDB(c);
        }
        return resultado;
    }

}