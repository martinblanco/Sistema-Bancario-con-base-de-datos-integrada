package entidades;

public class Transaccion {
	private int dni;
	private int numeroCuenta;
	private float monto;
	private int numeroCuentaDestino;
	private int numeroTransaccion;
	
	public Transaccion(){
	}
	
	public Transaccion(int dni, int numeroCuenta, float monto, int numeroCuentaDestino, int numeroTransaccion) {
		this.setDni(dni);
		this.numeroCuenta = numeroCuenta;
        this.monto = monto;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.setNumeroTransaccion(numeroTransaccion);
    }
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public int getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}
	public void setNumeroCuentaDestino(int numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public int getNumeroTransaccion() {
		return numeroTransaccion;
	}
	public void setNumeroTransaccion(int numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	
	@Override
	public String toString() {
		return "Transaccion [Dni =" + dni + "numeroCuenta=" + numeroCuenta + ", monto=" + monto + ", numeroCuentaDestino=" + numeroCuentaDestino + ", numeroTransaccion=" + numeroTransaccion + "]";
	}
}
