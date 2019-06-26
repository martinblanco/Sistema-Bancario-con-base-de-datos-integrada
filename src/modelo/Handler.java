package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import service.ClienteService;
import entidades.Cliente;
import exceptions.LoginException;
import exceptions.ServicioException;
import exceptions.ClienteException;
import swing.*;

public class Handler {
	private ClienteService cliente;
	private MiFrame miFrame;
	private int dniCliente;

	public Handler(){
	}
	
	public void altaPersona(Cliente miCliente){
		 try {
			ClienteService cliente = new ClienteService();
			cliente.insertarCliente(miCliente);
			mostrarSucces("Se ha dado de alta al usuario " + miCliente.getNombre());
			if(JOptionPane.showConfirmDialog(null, "Quiere seguir ingresando usuarios?") == 1){
				mostarMiPanelAlta();
			} else
				mostarMiPanelAlta();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		} catch(ClienteException e2){
			mostrarError(e2.getMessage());
			mostarMiPanelAlta();
		}
	}
	
	public void mostarMiPanelAlta(){
		PanelAlta miPanelAlta = new PanelAlta();
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
		ClienteService cliente = new ClienteService();
		List<Cliente> miLista = new ArrayList<Cliente>();
		try {
			miLista.addAll(cliente.listarClientes());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	
	public void eliminarPersona(int dni){
		try {
			ClienteService cliente = new ClienteService();
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
			ClienteService cliente = new ClienteService();
			cliente.modificarCliente(nom,ape,dni);
			mostarMiPanelTodos();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setMiFrame(MiFrame miFrame){
		this.miFrame = miFrame;
	}	
	
	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}	
	
	public boolean validarLogin(int index, String pass) {
		boolean caso = false;
		try {
			ClienteService cliente = new ClienteService();
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
}