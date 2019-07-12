package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import modelo.Handler;

@SuppressWarnings("serial")
public class HomeBankingTarjetaDebito extends PanelClientes{
	private int dni;
	
	public HomeBankingTarjetaDebito(Handler miCoordinador,int dni) {
		super(miCoordinador);
		listaUsuarios = new JTable(new TarjetaDebitoModel(miCoordinador.mostrarTodosTarjetasDebito(dni)));
		panelUsuarios.setViewportView(listaUsuarios);
		titulo.setText("Mis Tarjetas de Debito");
		setDni(dni);
	}

	@Override
	protected void setBotonEliminar() {
		botonEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int numerotarjeta = (miHandler.mostrarTodosTarjetasDebito(dni).get(listaUsuarios.getSelectedRow()).getNumeroTarjeta());
				miHandler.bajaTarjetaDebito(numerotarjeta);
				HomeBankingTarjetaDebito.this.miHandler.mostrarSucces("Correcto");
				HomeBankingTarjetaDebito.this.miHandler.mostrarHomeBankingTarjetaDebito(dni);
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
					HomeBankingTarjetaDebito.this.miHandler.mostrarHomeBankingDepositarDebito(miHandler.mostrarTodosTarjetasDebito(dni).get(listaUsuarios.getSelectedRow()));
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
