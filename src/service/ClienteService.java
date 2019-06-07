package service;

import java.util.List;

import dao.ClienteDao;
import dbImpl.dao.ClienteDAODBImpl;
import entidades.Cliente;
import exceptions.DAOException;
import exceptions.ServicioException;

public class ClienteService {

    public void insertarCliente(Cliente c) throws ServicioException{
    	ClienteDao dao = new ClienteDAODBImpl();
        try {
            dao.insertarCliente(c);
        } catch (DAOException e) {
            throw new ServicioException("error", e);
        }
    }

    public void eliminarClientecondni(int dni) throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
    	try {
			dao.eliminarClientecondni(dni);
		} catch (DAOException e) {
			throw new ServicioException("error", e);
		}
    }

    public void modificarCliente(Cliente cliente) throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
        try {
			dao.modificarCliente(cliente);
		} catch (DAOException e) {
			throw new ServicioException("error", e);
		}
    }

    public List<Cliente> listarClientes() throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
        try {
        	return dao.listarClientes();
		} catch (DAOException e) {
			throw new ServicioException("error", e);
		}
    }

    public Cliente consultarCliente(int dni) throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
        try {
        	return dao.consultarCliente(dni);
		} catch (DAOException e) {
			throw new ServicioException("error", e);
		}
    }
}
