package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PanelModificacionCliente extends PanelGenerico {
	public PanelModificacionCliente(){
		super();
	}

	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom;
				String ape;
				int DNI;
				try {
					nom = textNombre.getText();
					ape = textApellido.getText();
					DNI = Integer.parseInt(textDni.getText());
					PanelModificacionCliente.this.miHandler.modificarCliente(nom,ape,DNI);
				} catch (NumberFormatException e1) {
					PanelModificacionCliente.this.miHandler.mostrarError("Ingrese valores numericos ");
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
				PanelModificacionCliente.this.miHandler.mostarMiPanelClientes();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Modificacion de Clientes";
		return super.setTitulo(titulo);
	}
	
	@Override
	protected void agregarBotones() {
		super.agregarBotones();
	}
}