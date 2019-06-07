package dbImpl.dao;

import dao.ClienteDao;
import exceptions.DAOException;
import entidades.Cliente;
import basics.DBManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAODBImpl implements ClienteDao{
    @Override
    public void insertarCliente(Cliente cliente) throws DAOException {
    		String nombre = cliente.getNombre();
            String apellido = cliente.getApellido();
            int dni = cliente.getDni();
    		String sql = "INSERT INTO cliente (nombre, apellido, dni) VALUES  ('" + nombre + "', '" + apellido + "', '" + dni + "')";
    		Connection c = DBManager.connect();
    		try {
    			Statement s = c.createStatement();
    			s.executeUpdate(sql);
    			c.commit();
    		} catch (SQLException e) {
    				throw new DAOException("Problema al insertar ", e);
    		} finally{
    			 try {
    	                c.close();
    	            } catch (SQLException el) {
    	            	throw new DAOException("Problema cerrar conexion ", el);
    	            }
    		}
    	
    	}
 
    @Override
    public void eliminarClientecondni(int dni) throws DAOException{
    //public void eliminarClientecondni(int dni) throws DAOException {
        String sql = "DELETE FROM cliente WHERE dni = '" + dni + "'";
        Connection c = DBManager.connect();
        try {
        	Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
        } catch (SQLException e) {
            	throw new DAOException("Problema al cargar el cliente ",e);
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	throw new DAOException("Problema cerrar conexion ", e1);
            }
        }
    }

    @Override
    public void modificarCliente(Cliente cliente) {
        String nom = cliente.getNombre();
        String ape = cliente.getApellido();
        int dni = cliente.getDni();

        String sql = "UPDATE cliente set nombre = '" + nom + "', apellido = '" + ape + "' WHERE dni = '" + dni + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                //no hago nada
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                //no hago nada
            }
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Usuario:");
                System.out.print("\t" + rs.getString("nombre"));
                System.out.print("\t" + rs.getString("apellido"));
                System.out.print("\t" + rs.getString("dni"));
                System.out.println();

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                //no hago nada
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                //no hago nada
            }
        }
        return lista;
    }

    @Override
    public Cliente consultarCliente(int dni) throws DAOException{
    	Cliente resultado = new Cliente();
        String sql = "SELECT * FROM cliente WHERE dni = '" + dni + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Usuario:");
                System.out.print("\t" + rs.getInt("id"));
                System.out.print("\t" + rs.getString("nombre"));
                System.out.print("\t" + rs.getString("apellido"));
                System.out.print("\t" + rs.getString("dni"));
                System.out.println();
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                //no hago nada
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                //no hago nada
            }
        }
        return resultado;
    }

}