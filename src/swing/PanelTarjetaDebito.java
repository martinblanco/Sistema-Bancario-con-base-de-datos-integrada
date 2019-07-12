package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import modelo.Handler;

@SuppressWarnings("serial")
public class PanelTarjetaDebito extends PanelClientes{
	
	public PanelTarjetaDebito(Handler miCoordinador) {
		super(miCoordinador);
		listaUsuarios = new JTable(new TarjetaDebitoModel(miCoordinador.mostrarTodosTarjetasDebito()));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Tarjetas de Debito");
	}

	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerotarjeta = (miHandler.mostrarTodosTarjetasDebito().get(listaUsuarios.getSelectedRow()).getNumeroTarjeta());
				miHandler.bajaTarjetaDebito(numerotarjeta);
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
					PanelTarjetaDebito.this.miHandler.mostarMiPanelModificaTarjetaDebito(miHandler.mostrarTodosTarjetasDebito().get(listaUsuarios.getSelectedRow()));
 				} catch (ArrayIndexOutOfBoundsException e1) {
 					miHandler.mostrarError("Seleccione una opcion ");
 				}
			}
		});
	}
}
