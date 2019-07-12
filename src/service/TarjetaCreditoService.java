package service;

import java.util.ArrayList;
import java.util.List;
import dao.TarjetaCreditoDao;
import entidades.TarjetaCredito;
import exceptions.DAOException;
import exceptions.ServicioException;
import exceptions.TarjetaCreditoException;

public class TarjetaCreditoService {
	private TarjetaCreditoDao dao;
	
	public TarjetaCreditoService(TarjetaCreditoDao dao){
		setTarjetaCreditoDao(dao);
	}

	public void setTarjetaCreditoDao(TarjetaCreditoDao dao) {
		this.dao = dao;
	}
	
	public void altaTarjetaCredito(TarjetaCredito tarjeta) throws TarjetaCreditoException, ServicioException{
		try {
			if(dao.consultarTarjetaCredito(tarjeta.getNumeroTarjeta()).getNumeroTarjeta() == 0){
				dao.insertarTarjetaCredito(tarjeta);
			} else
				throw new TarjetaCreditoException("El numero de Tarjeta de credito ya existe en la base de datos");
		} catch (DAOException e) {
			throw new ServicioException("Error al insertar Tarjeta de Credito");
		}
	}
	
	public TarjetaCredito consultarTarjetaCredito(int numeroTarjeta) throws ServicioException{
		TarjetaCredito tarjetaConsulta = new TarjetaCredito();
		tarjetaConsulta = null;
		try{
			tarjetaConsulta = dao.consultarTarjetaCredito(numeroTarjeta);
		} catch(DAOException e){
			throw new ServicioException("Error al consultar Tarjeta de Credito");
		}
		return tarjetaConsulta;
	}
	
	public void eliminarTarjetaCredito(int numeroTarjeta) throws ServicioException, TarjetaCreditoException{
		try {
			if(dao.consultarTarjetaCredito(numeroTarjeta).getNumeroCuenta() != 0){
				dao.eliminarTarjetaCredito(numeroTarjeta);
			} else {
				throw new TarjetaCreditoException("No se ha encontrado la Tarjeta de Credito");
			}
		} catch (DAOException e) {
			throw new ServicioException("Error al eliminar Tarjeta de Credito");
		}
		
	}
	
	public List<TarjetaCredito> listarTarjetaCredito() throws ServicioException{
		List<TarjetaCredito> listarTarjetaCredito = new ArrayList<TarjetaCredito>();
		try {
			listarTarjetaCredito.addAll(dao.listarTarjetaCredito());
		} catch (DAOException e) {
			throw new ServicioException("Error al Listar las Tarjetas de Credito");
		}
		return listarTarjetaCredito;
	}
	
	public List<TarjetaCredito> listarTarjetaCredito(int dni) throws ServicioException{
		List<TarjetaCredito> listarTarjetaCredito = new ArrayList<TarjetaCredito>();
		try {
			listarTarjetaCredito.addAll(dao.listarTarjetaCredito(dni));
		} catch (DAOException e) {
			throw new ServicioException("Error al Listar las Tarjetas de Credito");
		}
		return listarTarjetaCredito;
	}
	
	public void modificarTarjetaCredito(int numeroTarjeta, float apagar) throws ServicioException{
        try {
			dao.modificarTarjetaCredito(numeroTarjeta,apagar);
		} catch (DAOException e) {
			throw new ServicioException("Error modificar Saldo a pagar");
		}
	}
	
	public void pagarTarjetaCredito(int numeroTarjeta, float apagar) throws ServicioException{
        try {
			dao.pagarTarjetaCredito(numeroTarjeta,apagar);
		} catch (DAOException e) {
			throw new ServicioException("Error modificar Saldo a pagar");
		}
	}
}
