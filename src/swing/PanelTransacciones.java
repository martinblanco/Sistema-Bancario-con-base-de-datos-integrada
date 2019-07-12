package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import modelo.Handler;

@SuppressWarnings("serial")
public class PanelTransacciones extends PanelClientes{
	
	public PanelTransacciones(Handler miCoordinador) {
		super(miCoordinador);
		listaUsuarios = new JTable(new TransaccionModel(miCoordinador.mostrarTodosTransacciones()));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Transacciones");
		botonModificar.setText("Nueva Transaccion");
	}
	
	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerotransaccion = (miHandler.mostrarTodosTransacciones().get(listaUsuarios.getSelectedRow()).getNumeroTransaccion());
				miHandler.bajaTransaccion(numerotransaccion);
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
				miHandler.mostrarPanelCuentasTransaccion();
			}
		});
	}
}