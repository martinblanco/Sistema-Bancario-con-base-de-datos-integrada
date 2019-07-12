package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import modelo.Handler;

@SuppressWarnings("serial")
public class PanelTarjetaCredito extends PanelClientes{
	
	public PanelTarjetaCredito(Handler miCoordinador) {
		super(miCoordinador);
		listaUsuarios = new JTable(new TarjetaCreditoModel(miCoordinador.mostrarTodosTarjetasCredito()));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Tarjetas de Credito");
	}

	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerotarjeta = (miHandler.mostrarTodosTarjetasCredito().get(listaUsuarios.getSelectedRow()).getNumeroTarjeta());
				miHandler.bajaTarjetaCredito(numerotarjeta);
				} catch (ArrayIndexOutOfBoundsException e1) {
    				miHandler.mostrarError("Seleccione una opcion");
    			}
			}
		});
	}

	@Override
	protected void setBotonModificar() {
		botonModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PanelTarjetaCredito.this.miHandler.mostarMiPanelModificaTarjetaCredito(miHandler.mostrarTodosTarjetasCredito().get(listaUsuarios.getSelectedRow()));
 				} catch (ArrayIndexOutOfBoundsException e1) {
 					miHandler.mostrarError("Seleccione una opcion ");
 				}
			}
		});
	}
}
