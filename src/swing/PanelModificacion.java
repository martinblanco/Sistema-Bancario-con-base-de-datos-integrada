package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PanelModificacion extends PanelGenerico {
	public PanelModificacion(){
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
	    		    	PanelModificacion.this.miHandler.modificarCliente(nom,ape,DNI);
						//PanelModificacion.this.miHandler.mostrarError("No puede ingresar valores negativos a la caja ");
				} catch (NumberFormatException e1) {
					PanelModificacion.this.miHandler.mostrarError("Ingrese valores numericos ");
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
				PanelModificacion.this.miHandler.mostarMiPanelTodos();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Modificacion";
		return super.setTitulo(titulo);
	}
	
	@Override
	protected void agregarBotones() {
		super.agregarBotones();
	}
}