package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import entidades.TarjetaDebito;
import modelo.Handler;

@SuppressWarnings("serial")
public class PanelAltaTarjetaDebito extends PanelGenerico{
	
	JTextField textNumeroTarjeta;
	JTextField textSaldo;
	
	public PanelAltaTarjetaDebito() {
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
				TarjetaDebito tarjeta = new TarjetaDebito();
				if ((PanelAltaTarjetaDebito.this.miHandler.buscarCliente(Integer.parseInt(textDni.getText())).getNombre() != null) && 
					(PanelAltaTarjetaDebito.this.miHandler.buscarCuenta(Integer.parseInt(textNumeroCuenta.getText())).getNumeroCuenta() != 0)) {
					try {
						tarjeta.setNumeroCuenta(Integer.parseInt(textNumeroCuenta.getText()));
						tarjeta.setNumeroTarjeta(Integer.parseInt(textNumeroTarjeta.getText()));
						tarjeta.setDni(Integer.parseInt(textDni.getText()));
						tarjeta.setSaldo(Float.parseFloat(textSaldo.getText()));
						
					} catch (NumberFormatException e1) {
						PanelAltaTarjetaDebito.this.miHandler.mostrarError("Ingrese bien los valores ");
					}
					PanelAltaTarjetaDebito.this.miHandler.altaTarjetaDebito(tarjeta);
				} else
					PanelAltaTarjetaDebito.this.miHandler.mostrarError("No existe el cliente o la cuenta en la base de datos ");
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelAltaTarjetaDebito.this.miHandler.mostrarPanelTarjetaDebito();
			}
		});
	}
	
	@Override
	public String setTitulo(String titulo) {
		titulo = "Alta Tarjeta de Debito";
		return super.setTitulo(titulo);
	}
		
	@Override
	protected void agregarBotones() {
		   JLabel labelNroTarjeta = new JLabel();
		   JLabel labelNroCuenta = new JLabel();
	       JLabel labelDniCliente = new JLabel();
	       JLabel labelSaldo = new JLabel();
	       
	       labelNroTarjeta.setText("Nro Tarjeta");
	       add(labelNroTarjeta, gridLabel(1, 2));
	       labelNroCuenta.setText("Nro Cuenta");
	       add(labelNroCuenta, gridLabel(1, 4));
	       labelDniCliente.setText("DNI Cliente");
	       add(labelDniCliente, gridLabel(1, 6));
	       labelSaldo.setText("Saldo Disponible $ ");
	       add(labelSaldo, gridLabel(1,8));
	       
	       textNumeroCuenta = new JTextField();
	       textNumeroTarjeta = new JTextField();
	       textSaldo = new JTextField();
	       
	       add(textNumeroTarjeta, gridText(3, 2));
	       add(textNumeroCuenta, gridText(3, 4));
	       add(textDni, gridText(3, 6));
	       add(textSaldo, gridText(3, 8));
	}
}

