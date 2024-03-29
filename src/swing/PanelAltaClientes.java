package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entidades.Cliente;
import modelo.Handler;

@SuppressWarnings("serial")
public class PanelAltaClientes extends PanelGenerico {
	
	public PanelAltaClientes(){
		super();
	}
	
	@Override
	public void setHandler(Handler miCoordinador) {
		this.miHandler = miCoordinador;
	}
	
	@Override
	public void setBotonAplicar() {
		// TODO Auto-generated method stub
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente miCliente = new Cliente();
				try {
					miCliente.setNombre(textNombre.getText());
					miCliente.setApellido(textApellido.getText());
					miCliente.setDni(Integer.parseInt(textDni.getText()));
					PanelAltaClientes.this.miHandler.altaCliente(miCliente);
				} catch (NumberFormatException e1) {
					PanelAltaClientes.this.miHandler.mostrarError("Valores ingresados no validos, no deje campos vacios");
				}
				
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		// TODO Auto-generated method stub
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelAltaClientes.this.miHandler.mostarMiPanelClientes();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Alta Clientes";
		return super.setTitulo(titulo);
	}
	
	@Override
	protected void agregarBotones() {
		super.agregarBotones();
	}
	
	public boolean checkNulos(){
		if(textNombre.equals("") || textApellido.equals("") || textCajaAhorro.equals("") || textCuentaCorriente.equals("") || textDni.equals("")){
			return false;
		}
		else 
			return true;
	}
}