package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.TarjetaCredito;
import modelo.Handler;

@SuppressWarnings("serial")
public class PanelAltaTarjetaCredito extends PanelGenerico{
	
	JTextField textNumeroTarjeta;
	JTextField textApagar;
	
	public PanelAltaTarjetaCredito() {
		super();
	}
	
	@Override
	public void setHandler(Handler miHandler) {
		super.setHandler(miHandler);
	}
	
	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TarjetaCredito tarjeta = new TarjetaCredito();
				if ((PanelAltaTarjetaCredito.this.miHandler.buscarCliente(Integer.parseInt(textDni.getText())).getNombre() != null) && 
					(PanelAltaTarjetaCredito.this.miHandler.buscarCuenta(Integer.parseInt(textNumeroCuenta.getText())).getNumeroCuenta() != 0)) {
					try {
						tarjeta.setNumeroCuenta(Integer.parseInt(textNumeroCuenta.getText()));
						tarjeta.setNumeroTarjeta(Integer.parseInt(textNumeroTarjeta.getText()));
						tarjeta.setDni(Integer.parseInt(textDni.getText()));
						tarjeta.setApagar(Float.parseFloat(textApagar.getText()));
						
					} catch (NumberFormatException e1) {
						PanelAltaTarjetaCredito.this.miHandler.mostrarError("Ingrese bien los valores ");
					}
					PanelAltaTarjetaCredito.this.miHandler.altaTarjetaCredito(tarjeta);
				} else
					PanelAltaTarjetaCredito.this.miHandler.mostrarError("No existe el cliente o la cuenta en la base de datos ");
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelAltaTarjetaCredito.this.miHandler.mostrarPanelTarjetaCredito();
			}
		});
	}
	
	@Override
	public String setTitulo(String titulo) {
		titulo = "Alta Tarjeta de Credito";
		return super.setTitulo(titulo);
	}
		
	@Override
	protected void agregarBotones() {
		   JLabel labelNroTarjeta = new JLabel();
		   JLabel labelNroCuenta = new JLabel();
	       JLabel labelDniCliente = new JLabel();
	       JLabel labelApagar = new JLabel();
	       
	       labelNroTarjeta.setText("Nro Tarjeta");
	       add(labelNroTarjeta, gridLabel(1, 2));
	       labelNroCuenta.setText("Nro Cuenta");
	       add(labelNroCuenta, gridLabel(1, 4));
	       labelDniCliente.setText("DNI Cliente");
	       add(labelDniCliente, gridLabel(1, 6));
	       labelApagar.setText("Saldo a Pagar $ ");
	       add(labelApagar, gridLabel(1,8));
	       
	       textNumeroCuenta = new JTextField();
	       textNumeroTarjeta = new JTextField();
	       textApagar = new JTextField();
	       
	       add(textNumeroTarjeta, gridText(3, 2));
	       add(textNumeroCuenta, gridText(3, 4));
	       add(textDni, gridText(3, 6));
	       add(textApagar, gridText(3, 8));
	}
}
