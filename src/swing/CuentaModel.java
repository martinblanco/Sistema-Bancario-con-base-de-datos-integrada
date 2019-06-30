package swing;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Cuenta;

public class CuentaModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Cuenta> cuenta;
	private static final int NROCUENTA = 0;
	private static final int DNI = 1; 
	private static final int CC = 2; 
	private static final int CA = 3;
	private static final int CD = 4;
	private String[] titulos = {"Numero Cuenta", "DNI Cliente", "Cuenta Corriente", "Caja ahorros", "Caja ahorros ($USD)"};
		
	public CuentaModel(List<Cuenta> cuenta){
		this.cuenta = cuenta;
	}
	
	@Override
	public int getRowCount() {
		return cuenta.size();
	}

	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Cuenta miCuenta = cuenta.get(row);
		switch(column){
		case NROCUENTA: return miCuenta.getNumeroCuenta();
		case DNI: return miCuenta.getDniCliente();
		case CC: return miCuenta.getCuentaCorriente();
		case CA: return miCuenta.getCajaAhorros();
		case CD: return miCuenta.getCajaDolares();
		}
		return null;
	}
	
	public Cuenta getCliente(int index){
		return cuenta.get(index);
	}
	
	public String getColumnName(int column){
		return titulos[column];
	}
	


}
