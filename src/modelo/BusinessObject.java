package modelo;

import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import exceptions.BusinessObjectException;
import exceptions.ClienteException;
import exceptions.LoginException;
import exceptions.ServicioException;
import service.ClienteService;


public class BusinessObject {
	private ClienteService cliente;
	private final String PASS = "admin";
	
	public BusinessObject(ClienteService cliente){
		setCliente(cliente);
	}
	
	private void setCliente(ClienteService cliente){
		this.cliente = cliente;
	}
	
	public Cliente consultarCliente(int dni) throws BusinessObjectException{
		Cliente miCliente = new Cliente();
		miCliente = null;
		try {
			miCliente = cliente.consultarCliente(dni);
		} catch (ServicioException e) {
			throw new BusinessObjectException("Hubo un problema en la base de datos", e);
		}
		return miCliente;
	}
	
	public void eliminacionCliente(int dni) throws BusinessObjectException, ClienteException{
		try {
			if(cliente.consultarCliente(dni).getApellido() != null){
				cliente.eliminarClientecondni(dni);
			} else {
				throw new ClienteException("No se ha encontrado el usuario");
			}
		} catch (ServicioException e) {
			throw new BusinessObjectException("Hubo un problema en la base de datos" , e);
		}
	}
	
	public void altaUsuario(Cliente miCliente) throws BusinessObjectException, ClienteException{
		try {
			if(cliente.consultarCliente(miCliente.getDni()).getNombre() == null){
				cliente.insertarCliente(miCliente);
			} else
				throw new ClienteException("El usuario ya existe en la base de datos ");
		} catch (ServicioException e) {
			throw new BusinessObjectException("Hubo un problema en la base de datos" , e);
		}
	}
	
	public List<Cliente> mostrarTodosUsuarios() throws BusinessObjectException{
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		try {
			listaClientes.addAll(cliente.listarClientes());
		} catch (ServicioException e) {
			throw new BusinessObjectException("Hubo un problema en la base de datos" , e);
		}
		return listaClientes;
	}
	
	public void modificarCliente(String nom, String ape, int dni) throws BusinessObjectException{
		try {
			cliente.modificarCliente(nom, ape, dni);
		} catch (ServicioException e) {
			throw new BusinessObjectException("Hubo un problema en la base de datos" , e);
			
		}
	}
	
	public boolean validarLogin(int index, String pass) throws LoginException, BusinessObjectException {
		boolean caso = false;
		if(index==1){
			if(pass.equals(PASS)){		
				caso = true;
			} else
				throw new LoginException("Login incorrecto");
		} else{
			int dni = Integer.parseInt(pass);
			if(consultarCliente(dni).getNombre()== null){
				throw new LoginException("Login incorrecto, su dni no aparece en la base de datos ");
			} else
				caso = true;
		}
		return caso;
	}
}
