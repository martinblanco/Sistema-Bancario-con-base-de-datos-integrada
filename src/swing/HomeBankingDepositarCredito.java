package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.TarjetaCredito;

@SuppressWarnings("serial")
public class HomeBankingDepositarCredito extends PanelGenerico {
	JTextField textNumeroTarjeta;
	JTextField textApagar;
	
	public HomeBankingDepositarCredito(){
		super();
	}

	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float APAGAR;
				int NRCUENTA;
				try {
					APAGAR = Float.parseFloat(textApagar.getText());
					NRCUENTA = Integer.parseInt(textNumeroTarjeta.getText());
					if(APAGAR > 0){
						HomeBankingDepositarCredito.this.miHandler.pagarTarjetaCredito(NRCUENTA,APAGAR);
						HomeBankingDepositarCredito.this.miHandler.mostrarSucces("Correcto");
						HomeBankingDepositarCredito.this.miHandler.mostrarHomeBanking();
					} else
						HomeBankingDepositarCredito.this.miHandler.mostrarError("No puede ingresar valores negativos a la caja");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					HomeBankingDepositarCredito.this.miHandler.mostrarError("Ingrese valores numericos");
				}
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HomeBankingDepositarCredito.this.miHandler.mostrarHomeBanking();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Pagar en la Tarjeta de Credito";
		return super.setTitulo(titulo);
	}
	
	public void editarTarjeta(TarjetaCredito miTarjeta){
		textNumeroTarjeta.setText(miTarjeta.getNumeroTarjeta()+"");
		textNumeroTarjeta.setEditable(false);
		textApagar.setText("0");
	}
	
	@Override
	protected void agregarBotones() {
		   JLabel labelNumeroTarjeta = new JLabel();
	       JLabel labelApagar = new JLabel();
	       
	       textNumeroTarjeta = new JTextField();
	       textApagar= new JTextField();
	       
	       labelNumeroTarjeta .setText("Numero Tarjeta");
	       add(labelNumeroTarjeta, gridLabel(1, 2));
	       labelApagar.setText("Cantidad a Depositar: ");
	       add(labelApagar, gridLabel(1, 4));
	       
	       add(textNumeroTarjeta, gridText(3, 2));
	       add(textApagar, gridText(3, 4));
	}
}
