package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.Cuenta;
import entidades.Transaccion;

@SuppressWarnings("serial")
public class PanelNuevaTransaccion extends PanelGenerico{
	JTextField textCuentaDestino;
	JTextField textMonto;
	JTextField textNumeroTransaccion;
	
	public PanelNuevaTransaccion(){
		super();
	}
	
	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float CC;
				float MONTO;
				int NRCUENTA;
				int NRCUENTADESTINO;
				int DNI;
				Transaccion transaccion = null;
				try {					
					CC = Float.parseFloat(textCuentaCorriente.getText());
					MONTO = Float.parseFloat(textMonto.getText());
					NRCUENTA = Integer.parseInt(textNumeroCuenta.getText());
					DNI = Integer.parseInt(textDni.getText());
					NRCUENTADESTINO = Integer.parseInt(textCuentaDestino.getText());
					int NRTRANSACCION = (int) Math.floor(Math.random()*(100000-1000+1)+1000);
					transaccion = new Transaccion(DNI,NRCUENTA,MONTO,NRCUENTADESTINO,NRTRANSACCION);
					if(CC - MONTO >= 0){
						PanelNuevaTransaccion.this.miHandler.altaTransaccion(transaccion);
					} else
						PanelNuevaTransaccion.this.miHandler.mostrarError("Saldo insuficiente");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					PanelNuevaTransaccion.this.miHandler.mostrarError("Ingrese valores numericos");
				}
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelNuevaTransaccion.this.miHandler.mostrarPanelCuentasTransaccion();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Nueva Transaccion";
		return super.setTitulo(titulo);
	}
	
	public void nuevaTransaccion(Cuenta miCuenta){
		textDni.setText(miCuenta.getDniCliente()+"");
		textDni.setEditable(false);
		textNumeroCuenta.setText(miCuenta.getNumeroCuenta()+"");
		textNumeroCuenta.setEditable(false);
		textCuentaCorriente.setText(miCuenta.getCuentaCorriente()+"");
		textCuentaCorriente.setEditable(false);
	}
	
	@Override
	protected void agregarBotones() {
		   JLabel labelDni = new JLabel();
		   JLabel labelNumeroCuenta = new JLabel();
	       JLabel labelCuentaCorriente = new JLabel();
	       JLabel labelCuentaDestino = new JLabel();
	       JLabel labelMonto = new JLabel();
	       
	       textDni = new JTextField();
	       textNumeroCuenta = new JTextField();
	       textCuentaCorriente = new JTextField();
	       textCuentaDestino = new JTextField();
	       textMonto = new JTextField();
	       
	       labelDni.setText("Numero Dni");
	       add(labelDni, gridLabel(1, 2));
	       labelNumeroCuenta.setText("Numero Cuenta Origen");
	       add(labelNumeroCuenta, gridLabel(1, 4));
	       labelCuentaCorriente.setText("Cuenta Corriente");
	       add(labelCuentaCorriente, gridLabel(1, 6));
	       labelCuentaDestino.setText("Cuenta Destino");
	       add(labelCuentaDestino, gridLabel(1, 8));
	       labelMonto.setText("Monto a mandar");
	       add(labelMonto, gridLabel(1, 10));
	       
	       add(textDni, gridText(3, 2));
	       add(textNumeroCuenta, gridText(3, 4));
	       add(textCuentaCorriente, gridText(3, 6));
	       add(textCuentaDestino, gridText(3, 8));
	       add(textMonto, gridText(3, 10));
	}
}
