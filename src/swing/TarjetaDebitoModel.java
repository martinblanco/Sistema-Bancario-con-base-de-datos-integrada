package swing;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import entidades.TarjetaDebito;

@SuppressWarnings("serial")
public class TarjetaDebitoModel extends AbstractTableModel {
	
	private List<TarjetaDebito> tarjeta;
	private static final int NROTARJETA = 0;
	private static final int DNI = 1; 
	private static final int NROCUENTA = 2; 
	private static final int SALDO = 3;
	private String[] titulos = {"Numero de Tarjeta", "DNI Cliente", "Numero Cuenta", "Saldo Desponible"};
		
	public TarjetaDebitoModel(List<TarjetaDebito> tarjeta){
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
		TarjetaDebito miCuenta = tarjeta.get(row);
		switch(column){
		case NROCUENTA: return miCuenta.getNumeroCuenta();
		case DNI: return miCuenta.getDni();
		case NROTARJETA: return miCuenta.getNumeroTarjeta();
		case SALDO: return miCuenta.getSaldo();
		}
		return null;
	}
	
	public TarjetaDebito getCliente(int index){
		return tarjeta.get(index);
	}
	
	public String getColumnName(int column){
		return titulos[column];
	}
}
