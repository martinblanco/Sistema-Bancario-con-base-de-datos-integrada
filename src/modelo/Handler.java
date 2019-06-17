package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import service.ClienteService;
import entidades.Cliente;
import exceptions.BusinessObjectException;
import exceptions.LoginException;
import exceptions.ServicioException;
import exceptions.ClienteException;
import swing.*;

public class Handler {
	private ClienteService cliente;
	private MiFrame miFrame;
	private int dniCliente;

	public Handler(){
		ClienteService cliente = new ClienteService();
		//BusinessObject miLogica = new BusinessObject(cliente);
		//setBusinessObject(miLogica);
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
	
	public void setBusinessObject(ClienteService cliente) {
		this.cliente = cliente;
	}
	
	public void setMiFrame(MiFrame miFrame){
		this.miFrame = miFrame;
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


	public int getDniCliente() {
		return dniCliente;
	}


	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}	
	
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
		} catch (BusinessObjectException e) {
			e.printStackTrace();
		}
		return caso;
	}
}