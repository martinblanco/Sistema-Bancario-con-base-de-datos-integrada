package dbImpl.dao;

import dao.ClienteDao;
import exceptions.DAOException;
import exceptions.DBException;
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
    		Connection c;    		
			try {
				c = DBManager.connect();
			} catch (DBException e1) {
				e1.printStackTrace();
				throw new DAOException("Problema al conectar a la db", e1);	
			}
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
    	            	//throw new DAOException("Problema cerrar conexion ", el);
    	            }
    		}
    	
    	}
 
    @Override
    public void eliminarClientecondni(int dni) throws DAOException{
        String sql = "DELETE FROM cliente WHERE dni = '" + dni + "'";
        Connection c;
		try {
			c = DBManager.connect();
		} catch (DBException e2) {
			e2.printStackTrace();
			throw new DAOException("Problema al conectar a la db", e2);	
		}
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
            	
            	//throw new DAOException("Problema cerrar conexion ", e1);
            }
        }
    }

    @Override
    public void modificarCliente(String nom,String ape, int dni) throws DAOException {

        String sql = "UPDATE cliente set nombre = '" + nom + "', apellido = '" + ape + "' WHERE dni = '" + dni + "'";
        Connection c;
		try {
			c = DBManager.connect();
		} catch (DBException e2) {
			e2.printStackTrace();
			throw new DAOException("Problema al conectar a la db", e2);	
		}
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
            	throw new DAOException("Problema al modificar cliente", e1);
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	//throw new DAOException("Problema cerrar conexion ", e1);
            }
        }
    }

    @Override
    public List<Cliente> listarClientes() throws DAOException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        Connection c;
		try {
			c = DBManager.connect();
		} catch (DBException e2) {
			e2.printStackTrace();
			throw new DAOException("Problema al conectar a la db", e2);	
		}
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
            	throw new DAOException("Problema al listar todos clientes", e1);	
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
            	//throw new DAOException("Problema al cerrar a la db", e1);	
            }
        }
        return lista;
    }

    @Override
    public Cliente consultarCliente(int dni) throws DAOException{
    	Cliente resultado = new Cliente();
        String sql = "SELECT * FROM cliente WHERE dni = '" + dni + "'";
        Connection c;
		try {
			c = DBManager.connect();
		} catch (DBException e2) {
			e2.printStackTrace();
			throw new DAOException("Problema al conectar a la db", e2);	
		}
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                System.out.println("Usuario Consultado:");
                System.out.print("\t" + rs.getInt("id"));
                System.out.print("\t" + rs.getString("nombre"));
                System.out.print("\t" + rs.getString("apellido"));
                System.out.print("\t" + rs.getString("dni"));
                System.out.println();
                String rsnombre = rs.getString("nombre");
				String rsapellido = rs.getString("apellido");
				int rsdni = rs.getInt("dni");
				resultado.setNombre(rsnombre);
				resultado.setApellido(rsapellido);
				resultado.setDni(rsdni);
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
            	throw new DAOException("Problema al liestar el cliente", e1);	
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