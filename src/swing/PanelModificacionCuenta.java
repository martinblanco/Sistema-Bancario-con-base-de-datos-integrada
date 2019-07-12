package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.Cuenta;

@SuppressWarnings("serial")
public class PanelModificacionCuenta extends PanelGenerico {
	public PanelModificacionCuenta(){
		super();
	}

	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float CA;
				float CC;
				float CCD;
				int NRCUENTA;
				try {
					CA = Float.parseFloat(textCajaAhorro.getText());
					CC = Float.parseFloat(textCuentaCorriente.getText());
					NRCUENTA = Integer.parseInt(textNumeroCuenta.getText());
					CCD = Float.parseFloat(textCajaDolares.getText());
					if(CA > 0 || CC > 0 || CCD > 0){
						PanelModificacionCuenta.this.miHandler.modificarCuenta(NRCUENTA,CC,CA,CCD);
					} else
						PanelModificacionCuenta.this.miHandler.mostrarError("No puede ingresar valores negativos a la caja ");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					PanelModificacionCuenta.this.miHandler.mostrarError("Ingrese valores numericos");
				}
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelModificacionCuenta.this.miHandler.mostrarTodosCuentas();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Modificacion de Cuentas";
		return super.setTitulo(titulo);
	}
	
	   public void editarCuenta(Cuenta miCuenta){
			 textNumeroCuenta.setText(miCuenta.getNumeroCuenta()+"");
			 textNumeroCuenta.setEditable(false);
			 textCajaAhorro.setText(miCuenta.getCajaAhorros()+"");
			 textCuentaCorriente.setText(miCuenta.getCuentaCorriente()+"");
			 textCajaDolares.setText(miCuenta.getCajaDolares()+"");
		   }
	
	@Override
	protected void agregarBotones() {
		   JLabel labelNumeroCuenta = new JLabel();
	       JLabel labelCuentaCorriente = new JLabel();
	       JLabel labelCajaAhorro = new JLabel();
	       JLabel labelCajaDolares = new JLabel();
	       
	       textNumeroCuenta = new JTextField();
	       textCuentaCorriente = new JTextField();
	       textCajaAhorro = new JTextField();
	       textCajaDolares = new JTextField();
	       
	       labelNumeroCuenta.setText("Numero Cuenta");
	       add(labelNumeroCuenta, gridLabel(1, 2));
	       labelCuentaCorriente.setText("Cuenta Corriente");
	       add(labelCuentaCorriente, gridLabel(1, 4));
	       labelCajaAhorro.setText("Caja Ahorro");
	       add(labelCajaAhorro, gridLabel(1, 6));
	       labelCajaDolares.setText("Caja Ahorro (Dolares)");
	       add(labelCajaDolares, gridLabel(1, 8));
	       
	       add(textCuentaCorriente, gridText(3, 4));
	       add(textNumeroCuenta, gridText(3, 2));
	       add(textCajaAhorro, gridText(3, 6));
	       add(textCajaDolares, gridText(3, 8));
	}
}
