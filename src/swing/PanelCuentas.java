package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import modelo.Handler;

@SuppressWarnings("serial")
public class PanelCuentas extends PanelClientes{
	
	public PanelCuentas(Handler miCoordinador) {
		super(miCoordinador);
		listaUsuarios = new JTable(new CuentaModel(miCoordinador.mostrarTodosCuentas()));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Cuentas");
	}
	
	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerocuenta = (miHandler.mostrarTodosCuentas().get(listaUsuarios.getSelectedRow()).getNumeroCuenta());
				miHandler.bajaCuenta(numerocuenta);
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
 					PanelCuentas.this.miHandler.mostarMiPanelModificaCuenta(miHandler.mostrarTodosCuentas().get(listaUsuarios.getSelectedRow()));
 				} catch (ArrayIndexOutOfBoundsException e1) {
 					miHandler.mostrarError("Seleccione una opcion ");
 				}
			}
		});
	}
}
