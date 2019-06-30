package dao;

import java.util.List;

import entidades.Cuenta;
import exceptions.DAOException;

public interface CuentaDao {
	
	public void insertarCuenta(Cuenta cuenta) throws DAOException;
	public void eliminarCuenta(int numeroCuenta) throws DAOException;
	public void modificarCuenta(int numeroCuenta, float cajaAhorro, float cajaDolares, float cuentaCorriente) throws DAOException;
	public List<Cuenta> listarCuentas() throws DAOException;
	public Cuenta consultarCuenta(int numeroCuenta) throws DAOException;
	
}
