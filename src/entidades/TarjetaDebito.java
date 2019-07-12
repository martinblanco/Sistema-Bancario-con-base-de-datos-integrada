package entidades;

public class TarjetaDebito {
	private int dni;
	private int numeroTarjeta;
	private int numeroCuenta;
	private float saldo;
	
	public TarjetaDebito(){
	}
	
	public TarjetaDebito(int numeroTarjeta, int dni, int numeroCuenta, float saldo) {
        this.numeroTarjeta = numeroTarjeta;
        this.dni = dni;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
	
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		return "Tarjeta de Credito [Dni =" + dni + "numeroCuenta=" + numeroCuenta + ", numeroTarjeta=" + numeroTarjeta + ", saldo=" + saldo + "]";
	}

}
