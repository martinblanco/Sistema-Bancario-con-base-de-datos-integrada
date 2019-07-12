package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import entidades.TarjetaCredito;

@SuppressWarnings("serial")
public class PanelModificacionTarjetaCredito extends PanelGenerico{
	JTextField textNumeroTarjeta;
	JTextField textApagar;
	
	public PanelModificacionTarjetaCredito(){
		super();
	}
	
	@Override
	public void setBotonAplicar() {
		botonAplicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int NRTARGETA;
				float APAGAR;
				try {
					NRTARGETA = Integer.parseInt(textNumeroTarjeta.getText());
					APAGAR = Float.parseFloat(textApagar.getText());
					if(APAGAR > 0){
						PanelModificacionTarjetaCredito.this.miHandler.modificarTarjetaCredito(NRTARGETA,APAGAR);
						PanelModificacionTarjetaCredito.this.miHandler.mostrarSucces("Correcto");
						PanelModificacionTarjetaCredito.this.miHandler.mostrarPanelTarjetaCredito();
					} else
						PanelModificacionTarjetaCredito.this.miHandler.mostrarError("No puede ingresar valores negativos a la caja ");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					PanelModificacionTarjetaCredito.this.miHandler.mostrarError("Ingrese valores numericos");
				}
			}
		});
	}
	
	@Override
	public void setBotonCancelar() {
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PanelModificacionTarjetaCredito.this.miHandler.mostrarTodosTarjetasCredito();
			}
		});	
	}
	@Override
	public String setTitulo(String titulo) {
		titulo = "Panel Modificacion de Tarjetos de Debito";
		return super.setTitulo(titulo);
	}
	
	public void editarTarjetaCredito(TarjetaCredito miTarjeta){
		textNumeroTarjeta.setText(miTarjeta.getNumeroTarjeta()+"");
		textNumeroTarjeta.setEditable(false);
		textApagar.setText(miTarjeta.getApagar()+"");
	}
	
	@Override
	protected void agregarBotones() {
		   JLabel labelNumeroTarjeta = new JLabel();
	       JLabel labelApagar = new JLabel();
	       
	       textNumeroTarjeta = new JTextField();
	       textApagar = new JTextField();
	       
	       labelNumeroTarjeta.setText("Numero de Tarjeta de Credito");
	       add(labelNumeroTarjeta, gridLabel(1, 2));
	       labelApagar.setText("Saldo a pagar actual $");
	       add(labelApagar, gridLabel(1, 4));
	       
	       add(textNumeroTarjeta, gridText(3, 2));
	       add(textApagar, gridText(3, 4));
	}
}
