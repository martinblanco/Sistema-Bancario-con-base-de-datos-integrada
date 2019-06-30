package service;

import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidades.Cuenta;
import exceptions.ServicioException;
import exceptions.CuentaException;
import exceptions.DAOException;

public class CuentaService {
	private CuentaDao dao;
	
	public CuentaService(CuentaDao dao){
		setCuentaDao(dao);
	}

	public void setCuentaDao(CuentaDao dao) {
		this.dao = dao;
	}
	
	public void altaCuenta(Cuenta miCuenta) throws CuentaException, ServicioException{
		try {
			if(dao.consultarCuenta(miCuenta.getNumeroCuenta()).getNumeroCuenta() == 0){
				dao.insertarCuenta(miCuenta);
			} else
				throw new CuentaException("La cuenta ya existe en la base de datos");
		} catch (DAOException e) {
			throw new ServicioException("Error al insertar cuenta");
		}
	}
	
	public Cuenta consultarCuenta(int numeroCuenta) throws ServicioException{
		Cuenta cuentaConsulta = new Cuenta();
		cuentaConsulta = null;
		try{
			cuentaConsulta = dao.consultarCuenta(numeroCuenta);
		} catch(DAOException e){
			throw new ServicioException("Error al consultar Cuenta");
		}
		return cuentaConsulta;
	}
	
	public void eliminarCuenta(int numeroCuenta) throws ServicioException, CuentaException{
		try {
			if(dao.consultarCuenta(numeroCuenta).getNumeroCuenta() != 0){
				dao.eliminarCuenta(numeroCuenta);
			} else {
				throw new CuentaException("No se ha encontrado la Cuenta");
			}
		} catch (DAOException e) {
			throw new ServicioException("Error al eliminar Cuenta");
		}
		
	}
	
	public List<Cuenta> listarCuentas() throws ServicioException{
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		try {
			listaCuentas.addAll(dao.listarCuentas());
		} catch (DAOException e) {
			throw new ServicioException("Error al Listar las Cuentas");
		}
		return listaCuentas;
	}
	
	public void modificarCuenta(int numeroCuenta, float cuentacorriente, float cajaahorro, float cajadolares) throws ServicioException{
        try {
			dao.modificarCuenta(numeroCuenta,cuentacorriente,cajaahorro,cajadolares);
		} catch (DAOException e) {
			throw new ServicioException("Error modificar cliente");
		}
	}
	
//	public void modifCC(int dni, int numeroCuenta) throws ServicioException{
//		try {
//			dao.modificarCuenta(numeroCuenta, dni);
//		} catch (DAOException e) {
//			throw new ServicioException("Error al mod");
//		}
//	}

}
