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
						int numerotarjeta = (int) Math.floor(Math.random()*(100000-1000+1)+1000);
						tarjeta.setNumeroCuenta(Integer.parseInt(textNumeroCuenta.getText()));
						tarjeta.setNumeroTarjeta(numerotarjeta);
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
	
	public void editarTarjeta(int dni){
		 textDni.setText(dni+"");
		 textDni.setEditable(false);
	}
	
	@Override
	public String setTitulo(String titulo) {
		titulo = "Alta Tarjeta de Debito";
		return super.setTitulo(titulo);
	}
		
	@Override
	protected void agregarBotones() {
		   JLabel labelNroCuenta = new JLabel();
	       JLabel labelDniCliente = new JLabel();
	       JLabel labelSaldo = new JLabel();
	       
	       labelNroCuenta.setText("Nro Cuenta");
	       add(labelNroCuenta, gridLabel(1, 4));
	       labelDniCliente.setText("DNI Cliente");
	       add(labelDniCliente, gridLabel(1, 2));
	       labelSaldo.setText("Saldo$ ");
	       add(labelSaldo, gridLabel(1,6));
	       
	       textNumeroCuenta = new JTextField();
	       textSaldo = new JTextField();
	       
	       add(textNumeroCuenta, gridText(3, 4));
	       add(textDni, gridText(3, 2));
	       add(textSaldo, gridText(3, 6));
	}
}

