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
						int numerotarjeta = (int) Math.floor(Math.random()*(100000-1000+1)+1000);
						tarjeta.setNumeroCuenta(Integer.parseInt(textNumeroCuenta.getText()));
						tarjeta.setNumeroTarjeta(numerotarjeta);
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
	
	public void editarTarjeta(int dni){
		 textDni.setText(dni+"");
		 textDni.setEditable(false);
	}
	
	@Override
	public String setTitulo(String titulo) {
		titulo = "Alta Tarjeta de Credito";
		return super.setTitulo(titulo);
	}
		
	@Override
	protected void agregarBotones() {
		   JLabel labelNroCuenta = new JLabel();
	       JLabel labelDniCliente = new JLabel();
	       JLabel labelApagar = new JLabel();
	       
	       labelDniCliente.setText("DNI Cliente");
	       add(labelDniCliente, gridLabel(1, 2));
	       labelNroCuenta.setText("Nro Cuenta");
	       add(labelNroCuenta, gridLabel(1, 4));
	       labelApagar.setText("A Pagar $ ");
	       add(labelApagar, gridLabel(1,6));
	       
	       textNumeroCuenta = new JTextField();
	       textNumeroTarjeta = new JTextField();
	       textApagar = new JTextField();
	       
	       add(textNumeroCuenta, gridText(3, 4));
	       add(textDni, gridText(3, 2));
	       add(textApagar, gridText(3, 6));
	}
}
