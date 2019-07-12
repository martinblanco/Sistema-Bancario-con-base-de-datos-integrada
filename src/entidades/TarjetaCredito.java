package entidades;

public class TarjetaCredito {
	private int dni;
	private int numeroTarjeta;
	private int numeroCuenta;
	private float apagar;
	
	public TarjetaCredito(){
	}
	
	public TarjetaCredito(int numeroTarjeta, int dni, int numeroCuenta, float apagar) {
        this.numeroTarjeta = numeroTarjeta;
        this.dni = dni;
        this.numeroCuenta = numeroCuenta;
        this.apagar = apagar;
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
	
	public float getApagar() {
		return apagar;
	}
	public void setApagar(float apagar) {
		this.apagar = apagar;
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		return "Tarjeta de Credito [Dni =" + dni + "numeroCuenta=" + numeroCuenta + ", numeroTarjeta=" + numeroTarjeta + ", apagar=" + apagar + "]";
	}

}
