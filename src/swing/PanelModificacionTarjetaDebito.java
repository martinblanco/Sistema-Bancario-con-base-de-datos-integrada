package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import entidades.TarjetaDebito;

@SuppressWarnings("serial")
public class PanelModificacionTarjetaDebito extends PanelGenerico{
	JTextField textNumeroTarjeta;
	JTextField textSaldo;
	
	public PanelModificacionTarjetaDebito(){
		super();
	}
	
	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int NRTARGETA;
				float SALDO;
				try {
					NRTARGETA = Integer.parseInt(textNumeroTarjeta.getText());
					SALDO = Float.parseFloat(textSaldo.getText());
					if(SALDO > 0){
						PanelModificacionTarjetaDebito.this.miHandler.modificarTarjetaDebito(NRTARGETA,SALDO);
					} else
						PanelModificacionTarjetaDebito.this.miHandler.mostrarError("No puede ingresar valores negativos a la caja ");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					PanelModificacionTarjetaDebito.this.miHandler.mostrarError("Ingrese valores numericos");
				}
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelModificacionTarjetaDebito.this.miHandler.mostrarTodosTarjetasDebito();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Modificacion de Tarjetas de Debito";
		return super.setTitulo(titulo);
	}
	
	public void editarTarjetaDebito(TarjetaDebito miTarjeta){
		textNumeroTarjeta.setText(miTarjeta.getNumeroTarjeta()+"");
		textNumeroTarjeta.setEditable(false);
		textSaldo.setText(miTarjeta.getSaldo()+"");
	}
	
	@Override
	protected void agregarBotones() {
		   JLabel labelNumeroTarjeta = new JLabel();
	       JLabel labelApagar = new JLabel();
	       
	       textNumeroTarjeta = new JTextField();
	       textSaldo = new JTextField();
	       
	       labelNumeroTarjeta.setText("Numero de Tarjeta de Credito");
	       add(labelNumeroTarjeta, gridLabel(1, 2));
	       labelApagar.setText("Saldo a pagar actual $");
	       add(labelApagar, gridLabel(1, 4));
	       
	       add(textNumeroTarjeta, gridText(3, 2));
	       add(textSaldo, gridText(3, 4));
	}
}
