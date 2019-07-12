package service;

import java.util.ArrayList;
import java.util.List;
import dao.TarjetaDebitoDao;
import entidades.TarjetaDebito;
import exceptions.DAOException;
import exceptions.ServicioException;
import exceptions.TarjetaDebitoException;

public class TarjetaDebitoService {
	private TarjetaDebitoDao dao;
	
	public TarjetaDebitoService(TarjetaDebitoDao dao){
		setTarjetaDebitoDao(dao);
	}

	public void setTarjetaDebitoDao(TarjetaDebitoDao dao) {
		this.dao = dao;
	}
	
	public void altaTarjetaDebito(TarjetaDebito tarjeta) throws TarjetaDebitoException, ServicioException{
		try {
			if(dao.consultarTarjetaDebito(tarjeta.getNumeroTarjeta()).getNumeroTarjeta() == 0){
				dao.insertarTarjetaDebito(tarjeta);
			} else
				throw new TarjetaDebitoException("El numero de Tarjeta de Debito ya existe en la base de datos");
		} catch (DAOException e) {
			throw new ServicioException("Error al insertar Tarjeta de Debito");
		}
	}
	
	public TarjetaDebito consultarTarjetaDebito(int numeroTarjeta) throws ServicioException{
		TarjetaDebito tarjetaConsulta = new TarjetaDebito();
		tarjetaConsulta = null;
		try{
			tarjetaConsulta = dao.consultarTarjetaDebito(numeroTarjeta);
		} catch(DAOException e){
			throw new ServicioException("Error al consultar Tarjeta de Debito");
		}
		return tarjetaConsulta;
	}
	
	public void eliminarTarjetaDebito(int numeroTarjeta) throws ServicioException, TarjetaDebitoException{
		try {
			if(dao.consultarTarjetaDebito(numeroTarjeta).getNumeroCuenta() != 0){
				dao.eliminarTarjetaDebito(numeroTarjeta);
			} else {
				throw new TarjetaDebitoException("No se ha encontrado la Tarjeta de Debito");
			}
		} catch (DAOException e) {
			throw new ServicioException("Error al eliminar Tarjeta de Debito");
		}
		
	}
	
	public List<TarjetaDebito> listarTarjetaDebito() throws ServicioException{
		List<TarjetaDebito> listarTarjetaDebito = new ArrayList<TarjetaDebito>();
		try {
			listarTarjetaDebito.addAll(dao.listarTarjetaDebito());
		} catch (DAOException e) {
			throw new ServicioException("Error al Listar las Tarjetas de Debito");
		}
		return listarTarjetaDebito;
	}
	
	public void modificarTarjetaDebito(int numeroTarjeta, float saldo) throws ServicioException{
        try {
			dao.modificarTarjetaDebito(numeroTarjeta,saldo);
		} catch (DAOException e) {
			throw new ServicioException("Error modificar Saldo Disponible");
		}
	}
}
