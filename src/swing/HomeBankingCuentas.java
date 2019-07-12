package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import modelo.Handler;

@SuppressWarnings("serial")
public class HomeBankingCuentas extends PanelClientes{
	private int dni;
	
	public HomeBankingCuentas(Handler miCoordinador,int dni) {
		super(miCoordinador);
		listaUsuarios = new JTable(new CuentaModel(miCoordinador.mostrarTodosCuentas(dni)));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Mis Cuentas");
		botonModificar.setText("Depositar/Retirar");
		setDni(dni);
	}
	
	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerocuenta = (miHandler.mostrarTodosCuentas(dni).get(listaUsuarios.getSelectedRow()).getNumeroCuenta());
				miHandler.bajaCuenta(numerocuenta, dni);
				HomeBankingCuentas.this.miHandler.mostrarSucces("Correcto");
				miHandler.mostrarHomeBankingCuentas(dni);
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
 					HomeBankingCuentas.this.miHandler.mostrarHomeBankingDepositar(miHandler.mostrarTodosCuentas(dni).get(listaUsuarios.getSelectedRow()));
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
