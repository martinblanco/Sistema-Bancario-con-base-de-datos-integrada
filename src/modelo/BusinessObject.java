package modelo;

import java.util.ArrayList;
import java.util.List;
import entidades.Cliente;
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
}