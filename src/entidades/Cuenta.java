package entidades;

public class Cuenta {
	private int numeroCuenta;
	private float cuentaCorriente;
	private float cajaAhorros;
	private float cajaDolares;
	private int dniCliente;
	
	public Cuenta(){
	}
	
	public Cuenta(int numeroCuenta, int dniCliente, float cajaAhorros, float cajaDolares, float cuentaCorriente) {
        this.numeroCuenta = numeroCuenta;
        this.dniCliente = dniCliente;
        this.cajaAhorros = cajaAhorros;
        this.cajaDolares = cajaDolares;
        this.cuentaCorriente = cuentaCorriente;
    }
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public float getCuentaCorriente() {
		return cuentaCorriente;
	}
	
	public void setCuentaCorriente(float cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	
	public float getCajaAhorros() {
		return cajaAhorros;
	}
	
	public void setCajaAhorros(float cajaAhorros) {
		this.cajaAhorros = cajaAhorros;
	}
	
	public float getCajaDolares() {
		return cajaDolares;
	}
	
	public void setCajaDolares(float cajaDolares) {
		this.cajaDolares = cajaDolares;
	}
	
	public int getDniCliente() {
		return dniCliente;
	}
	
	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}
	
	@Override
	public String toString() {
		return "Cuentas [Dni Cliente =" + dniCliente + ", numeroCuenta=" + numeroCuenta + ", cuentaCorriente="
				+ cuentaCorriente + ", cajaAhorros=" + cajaAhorros + "cajaDolares=" + cajaDolares + "]";
	}
	
}
