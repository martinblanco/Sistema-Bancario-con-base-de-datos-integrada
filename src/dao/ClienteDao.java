package dao;

import java.util.List;
import entidades.Cliente;
import exceptions.DAOException;

public interface ClienteDao {

	public void insertarCliente(Cliente c) throws DAOException;
	public void eliminarClientecondni(int dni) throws DAOException;
	public void modificarCliente(String nom,String ape, int dni) throws DAOException; 
	public List<Cliente> listarClientes() throws DAOException; 
	public Cliente consultarCliente(int dni) throws DAOException;
	
}
