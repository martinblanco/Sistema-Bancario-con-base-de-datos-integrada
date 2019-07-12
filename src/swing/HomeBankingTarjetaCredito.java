package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import modelo.Handler;

@SuppressWarnings("serial")
public class HomeBankingTarjetaCredito extends PanelClientes{
	private int dni;
	
	public HomeBankingTarjetaCredito(Handler miCoordinador,int dni) {
		super(miCoordinador);
		listaUsuarios = new JTable(new TarjetaCreditoModel(miCoordinador.mostrarTodosTarjetasCredito(dni)));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Mis Tarjetas de Credito");
		setDni(dni);
	}

	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerotarjeta = (miHandler.mostrarTodosTarjetasCredito(dni).get(listaUsuarios.getSelectedRow()).getNumeroTarjeta());
				miHandler.bajaTarjetaCredito(numerotarjeta);
				HomeBankingTarjetaCredito.this.miHandler.mostrarSucces("Correcto");
				HomeBankingTarjetaCredito.this.miHandler.mostrarHomeBankingCuentas(dni);
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
					HomeBankingTarjetaCredito.this.miHandler.mostrarHomeBankingDepositarCredito(miHandler.mostrarTodosTarjetasCredito(dni).get(listaUsuarios.getSelectedRow()));
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
