package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import swing.*;

import dao.ClienteDao;
import dao.CuentaDao;
import dbImpl.dao.ClienteDAODBImpl;
import dbImpl.dao.CuentaDAODBImpl;
import service.ClienteService;
import service.CuentaService;
import entidades.Cliente;
import entidades.Cuenta;
import exceptions.LoginException;
import exceptions.ServicioException;
import exceptions.ClienteException;
import exceptions.CuentaException;


public class Handler {
	private ClienteService cliente;
	private CuentaService cuenta;
	private MiFrame miFrame;
	private int dniCliente;

	public Handler(){
			ClienteDao clienteDao = new ClienteDAODBImpl();
			ClienteService clienteService = new ClienteService(clienteDao);
			setCliente(clienteService);
			
			CuentaDao cuentaDao = new CuentaDAODBImpl();
			CuentaService cuentaService = new CuentaService(cuentaDao);
			setCuenta(cuentaService);	
	}
	
	public ClienteService getCliente() {
		return cliente;
	}

	public void setCliente(ClienteService cliente) {
		this.cliente = cliente;
	}
	
	public CuentaService getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaService cuenta) {
		this.cuenta = cuenta;
	}
	
	public void setMiFrame(MiFrame miFrame){
		this.miFrame = miFrame;
	}
	
	//////////////////////////// Paneles Clientes
	
	public void altaCliente(Cliente miCliente){
		 try {
			cliente.insertarCliente(miCliente);
			mostrarSucces("Se ha dado de alta al Cliente " + miCliente.getNombre());
			if(JOptionPane.showConfirmDialog(null, "Quiere seguir ingresando Clientes?") == 1){
				mostarMiPanelAlta();
			} else
				mostarMiPanelTodos();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		} catch(ClienteException e2){
			mostrarError(e2.getMessage());
			mostarMiPanelAlta();
		}
	}
	
	public void mostarMiPanelAlta(){
		PanelAltaClientes miPanelAlta = new PanelAltaClientes();
		miPanelAlta.setHandler(this);
		miFrame.setPanel(miPanelAlta);
		
	}public void mostrarError(String error){
		JOptionPane.showMessageDialog(null,error,"Error",JOptionPane.ERROR_MESSAGE);
	}
	public void mostrarSucces(String exito){
		JOptionPane.showMessageDialog(null,exito,"Exito",JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostarMiPanelTodos(){
		PanelClientes miLista = new PanelClientes(this);
		miFrame.setPanel(miLista);

	}
	
	public void mostarMiPanelModifica(Cliente miCliente){
		PanelModificacion miPanelModificacion = new PanelModificacion();
		miPanelModificacion.setHandler(this);
		miPanelModificacion.editarCliente(miCliente);
		miFrame.setPanel(miPanelModificacion);
	}
	
	public List<Cliente> mostrarTodos(){
		List<Cliente> miLista = new ArrayList<Cliente>();
		try {
			miLista.addAll(cliente.listarClientes());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public Cliente buscarCliente(int dni){
		Cliente clientebuscado = null;
		try {
			clientebuscado = cliente.consultarCliente(dni);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		return clientebuscado;
	}
	
	public void eliminarCliente(int dni){
		try {
			cliente.eliminarClientecondni(dni);
			mostarMiPanelTodos();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		} catch(ClienteException e2){
			mostrarError(e2.getMessage());
		}
	}
	
	public void modificarCliente(String nom ,String ape, int dni){
		try {
			cliente.modificarCliente(nom,ape,dni);
			mostarMiPanelTodos();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}	
	
	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}	
	
	///////////////////Login
	
	public boolean validarLogin(int index, String pass) {
		boolean caso = false;
		try {
			caso = cliente.validarLogin(index, pass);
			mostrarSucces("Login Exitoso");	
			if(index == 2){
				miFrame.setMenuUsuario();
				setDniCliente(Integer.parseInt(pass));
				miFrame.setVisible(true);
			} else {
				miFrame.setMenuAdmin();
				miFrame.setVisible(true);
			}
		} catch (LoginException e) {
			mostrarError(e.getMessage());
		}
		return caso;
	}

	////////////////////////////////////////////////////////////Cuentas
	
	public void mostrarPanelAltaCuentas(){
		PanelAltaCuentas altaCuentas = new PanelAltaCuentas();
		altaCuentas.setHandler(this);
		miFrame.setPanel(altaCuentas);
	}
	
	public void mostrarPanelCuentas(){
		PanelCuentas cuentas = new PanelCuentas(this);
		miFrame.setPanel(cuentas);
	}
	
	public List<Cuenta> mostrarCuentas(){
		List<Cuenta> miLista = new ArrayList<Cuenta>();
		try {
			miLista.addAll(cuenta.listarCuentas());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	
	
	public void bajaCuenta(int numeroCuenta){
		try {
			cuenta.eliminarCuenta(numeroCuenta);
		} catch (CuentaException e) {
			mostrarError(e.getMessage());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		
	}
	
	public void altaCuenta(Cuenta miCuenta){
		try {
			cuenta.altaCuenta(miCuenta);
		} catch (ServicioException e) {
			e.printStackTrace();
		} catch (CuentaException e) {
			mostrarError(e.getMessage());
			//mostrarHomeBanking();
		}
	}
	
	
}