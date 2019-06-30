package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.Cuenta;
import modelo.Handler;

public class PanelAltaCuentas extends PanelGenerico {
	
	JTextField textNumeroCuenta;
	
	public PanelAltaCuentas() {
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
				Cuenta cuenta = new Cuenta();
				if (PanelAltaCuentas.this.miHandler.buscarCliente(Integer.parseInt(textDni.getText())).getNombre() != null) {
					try {
						cuenta.setNumeroCuenta(Integer.parseInt(textNumeroCuenta.getText()));
						cuenta.setDniCliente(Integer.parseInt(textDni.getText()));
						cuenta.setCajaAhorros(Float.parseFloat(textCajaAhorro.getText()));
						cuenta.setCajaDolares(Float.parseFloat(textCajaDolares.getText()));
						cuenta.setCuentaCorriente(Float.parseFloat(textCuentaCorriente.getText()));
						PanelAltaCuentas.this.miHandler.altaCuenta(cuenta);
					} catch (NumberFormatException e1) {
						PanelAltaCuentas.this.miHandler.mostrarError("Ingrese bien los valores ");
					}
					//PanelAltaCuentas.this.miHandler.setCuenta(Integer.parseInt(textDni.getText()), Integer.parseInt(textNumeroCuenta.getText()));
				} else
					PanelAltaCuentas.this.miHandler.mostrarError("No existe el cliente en la base de datos ");

				
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelAltaCuentas.this.miHandler.mostrarPanelCuentas();
			}
		});
	}
	
	@Override
	public String setTitulo(String titulo) {
		titulo = "Alta Cuentas";
		return super.setTitulo(titulo);
	}
	
	@Override
	protected void agregarBotones() {
	       JLabel labelNroCuenta = new JLabel();
	       JLabel labelDniCliente = new JLabel();
	       JLabel labelCuentaCorriente = new JLabel();
	       JLabel labelCajaAhorro = new JLabel();
	       JLabel labelCajaDolares = new JLabel();
	       
	       labelNroCuenta.setText("Nro Cuenta");
	       add(labelNroCuenta, gridLabel(1, 2));
	       labelDniCliente.setText("DNI Cliente");
	       add(labelDniCliente, gridLabel(1, 4));
	       labelCuentaCorriente.setText("Cuenta Corriente $:");
	       add(labelCuentaCorriente, gridLabel(1,8));
	       labelCajaAhorro.setText("Caja Ahorros $:");
	       add(labelCajaAhorro, gridLabel(1, 6));
	       labelCajaDolares.setText("Caja Ahorros en $USD:");
	       add(labelCajaDolares, gridLabel(1, 10));
	       
	       textNumeroCuenta = new JTextField();
	       textCuentaCorriente = new JTextField();
	       textCajaAhorro = new JTextField();
	       textCajaDolares = new JTextField();
	       
	       add(textNumeroCuenta, gridText(3, 2));
	       add(textDni, gridText(3, 4));
	       add(textCuentaCorriente, gridText(3, 8));
	       add(textCajaAhorro, gridText(3, 6));
	       add(textCajaDolares, gridText(3, 10));
	}
	
}
