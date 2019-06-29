package service;

import java.util.ArrayList;
import java.util.List;
import dao.ClienteDao;
import dbImpl.dao.ClienteDAODBImpl;
import entidades.Cliente;
import exceptions.ClienteException;
import exceptions.DAOException;
import exceptions.LoginException;
import exceptions.ServicioException;

public class ClienteService {
	private final String PASS = "admin";

    public void insertarCliente(Cliente c) throws ServicioException, ClienteException{
    	ClienteDao dao = new ClienteDAODBImpl();
    	try {
    	if(dao.consultarCliente(c.getDni()).getNombre() == null){
    		dao.insertarCliente(c);
		} else
			throw new ClienteException("El usuario ya existe en la base de datos repetido");
	} catch (DAOException e) {
		throw new ServicioException("Hubo un problema en la base de datos");
	}
}

    public void eliminarClientecondni(int dni) throws ServicioException, ClienteException {
    	ClienteDao dao = new ClienteDAODBImpl();
    	try {
    		if(dao.consultarCliente(dni).getNombre() != null){
    			dao.eliminarClientecondni(dni);
			} else {
				System.out.println(dni);
				throw new ClienteException("No se ha encontrado el usuario");
			}
		} catch (DAOException e) {
			System.out.println(dni);
			throw new ServicioException("Error eliminar cliente");
		}
    }

    public void modificarCliente(String nom,String ape, int dni) throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
        try {
			dao.modificarCliente(nom,ape,dni);
		} catch (DAOException e) {
			throw new ServicioException("Error modificar cliente");
		}
    }

    public List<Cliente> listarClientes() throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
    	List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
        	listaClientes.addAll(dao.listarClientes());
		} catch (DAOException e) {
			throw new ServicioException("Error listar todos los clientes");
		}
        return listaClientes;
    }

    public Cliente consultarCliente(int dni) throws ServicioException {
    	ClienteDao dao = new ClienteDAODBImpl();
    	Cliente miCliente = new Cliente();
    	miCliente = null;
        try {
        	miCliente = dao.consultarCliente(dni);
		} catch (DAOException e) {
			throw new ServicioException("Error consultar un cliente");
		}
        return miCliente;
    }
    
	public boolean validarLogin(int index, String pass) throws LoginException {
		ClienteDao dao = new ClienteDAODBImpl();
		boolean caso = false;
		if(index==1){
			if(pass.equals(PASS)){		
				caso = true;
			} else
				throw new LoginException("Login incorrecto");
		} else{
			int dni = Integer.parseInt(pass);
			try {
				if(dao.consultarCliente(dni).getNombre()== null){
					throw new LoginException("Login incorrecto, su dni no aparece en la base de datos ");
				} else
					caso = true;
			} catch (DAOException e) {
				throw new LoginException("ERROR");
			}
		}
		return caso;
	}
}
