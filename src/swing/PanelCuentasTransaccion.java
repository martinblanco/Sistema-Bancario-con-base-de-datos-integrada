package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Handler;

@SuppressWarnings("serial")
public class PanelCuentasTransaccion extends PanelCuentas{
	
	protected javax.swing.JButton botonTransaccion;
	
	public PanelCuentasTransaccion(Handler miCoordinador) {
		super(miCoordinador);
		titulo.setText("Seleccionar Cliente");
		botonEliminar.setText("Ver Todas Las Transacciones");
		botonModificar.setText("Nueva Transaccion");
	}
	
	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				miHandler.mostrarPanelTransaccion();
			}
		});
	}

	@Override
	protected void setBotonModificar() {
		botonModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PanelCuentasTransaccion.this.miHandler.mostarMiPanelNuevaTransaccion(miHandler.mostrarTodosCuentas().get(listaUsuarios.getSelectedRow()));
 				} catch (ArrayIndexOutOfBoundsException e1) {
 					miHandler.mostrarError("Seleccione una opcion ");
 				}
			}
		});
	}
}