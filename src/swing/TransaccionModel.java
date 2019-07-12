package swing;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import entidades.Transaccion;

@SuppressWarnings("serial")
public class TransaccionModel extends AbstractTableModel {

	private List<Transaccion> transaccion;
	private static final int NROTRANSACCION = 0;
	private static final int DNI = 1; 
	private static final int NROCUENTA = 2; 
	private static final int MONTO = 3;
	private static final int NROCUENTADESTINO = 4;
	private String[] titulos = {"Numero Transaccion", "DNI Cliente", "Numero Cuenta", "Monto", "Nro Cuenta Destino"};
		
	public TransaccionModel(List<Transaccion> transaccion){
		this.transaccion = transaccion;
	}
	
	@Override
	public int getRowCount() {
		return transaccion.size();
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Transaccion miTransaccion = transaccion.get(row);
		switch(column){
		case NROTRANSACCION: return miTransaccion.getNumeroTransaccion();
		case DNI: return miTransaccion.getDni();
		case NROCUENTA: return miTransaccion.getNumeroCuenta();
		case MONTO: return miTransaccion.getMonto();
		case NROCUENTADESTINO: return miTransaccion.getNumeroCuentaDestino();
		}
		return null;
	}
	
	public Transaccion getTransaccion(int index){
		return transaccion.get(index);
	}
	
	public String getColumnName(int column){
		return titulos[column];
	}

}
