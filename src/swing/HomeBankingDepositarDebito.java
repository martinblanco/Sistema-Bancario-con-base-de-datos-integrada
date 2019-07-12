package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.TarjetaDebito;

@SuppressWarnings("serial")
public class HomeBankingDepositarDebito extends PanelGenerico {
	JTextField textNumeroTarjeta;
	JTextField textSaldo;
	
	public HomeBankingDepositarDebito(){
		super();
	}

	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float SALDO;
				int NRCUENTA;
				try {
					SALDO = Float.parseFloat(textSaldo.getText());
					NRCUENTA = Integer.parseInt(textNumeroTarjeta.getText());
					if(SALDO > 0){
						HomeBankingDepositarDebito.this.miHandler.depositarTarjetaDebito(NRCUENTA,SALDO);
						HomeBankingDepositarDebito.this.miHandler.mostrarSucces("Correcto");
						HomeBankingDepositarDebito.this.miHandler.mostrarHomeBanking();
					} else
						HomeBankingDepositarDebito.this.miHandler.mostrarError("No puede ingresar valores negativos a la caja");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					HomeBankingDepositarDebito.this.miHandler.mostrarError("Ingrese valores numericos");
				}
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HomeBankingDepositarDebito.this.miHandler.mostrarHomeBanking();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Depositar en la Tarjeta de Debito";
		return super.setTitulo(titulo);
	}
	
	public void editarCuenta(TarjetaDebito miTarjeta){
		textNumeroTarjeta.setText(miTarjeta.getNumeroTarjeta()+"");
		textNumeroTarjeta.setEditable(false);
		textSaldo.setText("0");
	}
	
	@Override
	protected void agregarBotones() {
		   JLabel labelNumeroTarjeta = new JLabel();
	       JLabel labelSaldo = new JLabel();
	       
	       textNumeroTarjeta = new JTextField();
	       textSaldo= new JTextField();
	       
	       labelNumeroTarjeta .setText("Numero Tarjeta");
	       add(labelNumeroTarjeta, gridLabel(1, 2));
	       labelSaldo.setText("Cantidad a Depositar: ");
	       add(labelSaldo, gridLabel(1, 4));
	       
	       add(textNumeroTarjeta, gridText(3, 2));
	       add(textSaldo, gridText(3, 4));
	}
}
