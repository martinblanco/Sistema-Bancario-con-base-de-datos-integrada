package swing;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import entidades.TarjetaCredito;

@SuppressWarnings("serial")
public class TarjetaCreditoModel extends AbstractTableModel {
	
	private List<TarjetaCredito> tarjeta;
	private static final int NROTARJETA = 0;
	private static final int DNI = 1; 
	private static final int NROCUENTA = 2; 
	private static final int APAGAR = 3;
	private String[] titulos = {"Numero de Tarjeta", "DNI Cliente", "Numero Cuenta", "Saldo a pagar"};
		
	public TarjetaCreditoModel(List<TarjetaCredito> tarjeta){
		this.tarjeta = tarjeta;
	}
	
	@Override
	public int getRowCount() {
		return tarjeta.size();
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		TarjetaCredito miCuenta = tarjeta.get(row);
		switch(column){
		case NROCUENTA: return miCuenta.getNumeroCuenta();
		case DNI: return miCuenta.getDni();
		case NROTARJETA: return miCuenta.getNumeroTarjeta();
		case APAGAR: return miCuenta.getApagar();
		}
		return null;
	}
	
	public TarjetaCredito getCliente(int index){
		return tarjeta.get(index);
	}
	
	public String getColumnName(int column){
		return titulos[column];
	}
}
