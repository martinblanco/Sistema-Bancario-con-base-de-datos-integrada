package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import entidades.Cuenta;
import modelo.Handler;

@SuppressWarnings("serial")
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
						int numerocuenta = (int) Math.floor(Math.random()*(100000-1000+1)+1000);
						cuenta.setNumeroCuenta(numerocuenta);
						cuenta.setDniCliente(Integer.parseInt(textDni.getText()));
						cuenta.setCajaAhorros(Float.parseFloat(textCajaAhorro.getText()));
						cuenta.setCajaDolares(Float.parseFloat(textCajaDolares.getText()));
						cuenta.setCuentaCorriente(Float.parseFloat(textCuentaCorriente.getText()));
						
					} catch (NumberFormatException e1) {
						PanelAltaCuentas.this.miHandler.mostrarError("Ingrese bien los valores ");
					}
					PanelAltaCuentas.this.miHandler.altaCuenta(cuenta);
					PanelAltaCuentas.this.miHandler.mostrarPanelAltaCuentas();
				} else
					PanelAltaCuentas.this.miHandler.mostrarError("No existe el cliente en la base de datos ");
			}
		});
	}
	
	@Override
	public String setTitulo(String titulo) {
		titulo = "Alta Cuentas";
		return super.setTitulo(titulo);
	}
	
	public void editarCuenta(int dni){
			 textDni.setText(dni+"");
			 textDni.setEditable(false);
	}
	
	@Override
	protected void agregarBotones() {
	       JLabel labelDniCliente = new JLabel();
	       JLabel labelCuentaCorriente = new JLabel();
	       JLabel labelCajaAhorro = new JLabel();
	       JLabel labelCajaDolares = new JLabel();
	       
	       labelDniCliente.setText("DNI Cliente");
	       add(labelDniCliente, gridLabel(1, 4));
	       labelCuentaCorriente.setText("Cuenta Corriente $:");
	       add(labelCuentaCorriente, gridLabel(1,8));
	       labelCajaAhorro.setText("Caja Ahorros $:");
	       add(labelCajaAhorro, gridLabel(1, 6));
	       labelCajaDolares.setText("Caja Ahorros en $USD:");
	       add(labelCajaDolares, gridLabel(1, 10));
	       
	       textCuentaCorriente = new JTextField();
	       textCajaAhorro = new JTextField();
	       textCajaDolares = new JTextField();
	       
	       add(textDni, gridText(3, 4));
	       add(textCuentaCorriente, gridText(3, 8));
	       add(textCajaAhorro, gridText(3, 6));
	       add(textCajaDolares, gridText(3, 10));
	}
	
}
