package service;

import java.util.ArrayList;
import java.util.List;
import dao.TransaccionDao;
import entidades.Transaccion;
import exceptions.DAOException;
import exceptions.ServicioException;
import exceptions.TransaccionException;

public class TransaccionService {
	private TransaccionDao dao;
	
	public TransaccionService(TransaccionDao dao){
		setTransaccionDao(dao);
	}

	public void setTransaccionDao(TransaccionDao dao) {
		this.dao = dao;
	}
	
	public void altaTransaccion(Transaccion transaccion) throws TransaccionException, ServicioException{
		try {
			if(dao.consultarTransaccion(transaccion.getNumeroTransaccion()).getNumeroTransaccion() == 0){
				dao.insertarTransaccion(transaccion);
			} else
				throw new TransaccionException("Numero de Transaccion duplicado");
		} catch (DAOException e) {
			throw new ServicioException("Error al insertar Transaccion");
		}
	}
	
	public Transaccion consultarTransaccion(int numeroTransaccion) throws ServicioException{
		Transaccion transaccionConsulta = new Transaccion();
		transaccionConsulta = null;
		try{
			transaccionConsulta = dao.consultarTransaccion(numeroTransaccion);
		} catch(DAOException e){
			throw new ServicioException("Error al consultar Transaccion");
		}
		return transaccionConsulta;
	}
	
	public void eliminarTransaccion(int numeroTransaccion) throws ServicioException, TransaccionException{
		try {
			if(dao.consultarTransaccion(numeroTransaccion).getNumeroTransaccion() != 0){
				dao.eliminarTransaccion(numeroTransaccion);
			} else {
				throw new TransaccionException("No se ha encontrado la Transaccion");
			}
		} catch (DAOException e) {
			throw new ServicioException("Error al eliminar la Transaccion");
		}
		
	}
	
	public List<Transaccion> listarTransaccion() throws ServicioException{
		List<Transaccion> listarTransaccion = new ArrayList<Transaccion>();
		try {
			listarTransaccion.addAll(dao.listarTransaccion());
		} catch (DAOException e) {
			throw new ServicioException("Error al Listar las Transacciones");
		}
		return listarTransaccion;
	}
	
	public List<Transaccion> listarTransaccionDni(int dni) throws ServicioException{
		List<Transaccion> listarTransaccion = new ArrayList<Transaccion>();
		try {
			listarTransaccion.addAll(dao.listarTransaccionDni(dni));
		} catch (DAOException e) {
			throw new ServicioException("No hay ninguna transaccion con este Cliente");
		}
		return listarTransaccion;
	}


//	public void modificarCuenta(int numeroCuenta, float cuentacorriente, float cajaahorro, float cajadolares) throws ServicioException{
//        try {
//			dao.modificarCuenta(numeroCuenta,cuentacorriente,cajaahorro,cajadolares);
//		} catch (DAOException e) {
//			throw new ServicioException("Error modificar cliente");
//		}
//	}

}
