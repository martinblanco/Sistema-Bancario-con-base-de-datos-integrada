package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import modelo.Handler;

@SuppressWarnings("serial")
public class HomeBankingTransacciones extends PanelClientes{
	private int dni;
	
	public HomeBankingTransacciones(Handler miCoordinador,int dni) {
		super(miCoordinador);
		listaUsuarios = new JTable(new TransaccionModel(miCoordinador.mostrarTodosTransacciones(dni)));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Mis Transacciones");
		botonModificar.setText("Neueva Transaccion");
		botonEliminar.setText("Neueva Transaccion");
		setDni(dni);
	}
	
	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					HomeBankingTransacciones.this.miHandler.mostrarHomeBankingNuevaTransaccion(miHandler.mostrarTodosCuentas(dni).get(listaUsuarios.getSelectedRow()));
 				} catch (ArrayIndexOutOfBoundsException e1) {
 					miHandler.mostrarError("Seleccione una opcion ");
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
					HomeBankingTransacciones.this.miHandler.mostrarHomeBankingNuevaTransaccion(miHandler.mostrarTodosCuentas(dni).get(listaUsuarios.getSelectedRow()));
 				} catch (ArrayIndexOutOfBoundsException e1) {
 					miHandler.mostrarError("Seleccione una opcion ");
 				}
			}
		});
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
}
