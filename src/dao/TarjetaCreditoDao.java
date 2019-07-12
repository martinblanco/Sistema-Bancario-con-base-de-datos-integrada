package dao;

import java.util.List;
import entidades.TarjetaCredito;
import exceptions.DAOException;

public interface TarjetaCreditoDao {
	public void insertarTarjetaCredito(TarjetaCredito tarjeta) throws DAOException;
	public void eliminarTarjetaCredito(int numeroTarjeta) throws DAOException;
	public void modificarTarjetaCredito(int numeroTarjeta, float apagar) throws DAOException;
	public List<TarjetaCredito> listarTarjetaCredito() throws DAOException;
	public TarjetaCredito consultarTarjetaCredito(int numeroTarjeta) throws DAOException;
}
