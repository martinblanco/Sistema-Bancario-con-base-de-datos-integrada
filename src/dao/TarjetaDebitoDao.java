package dao;

import java.util.List;

import entidades.TarjetaDebito;
import exceptions.DAOException;

public interface TarjetaDebitoDao {
	public void insertarTarjetaDebito(TarjetaDebito tarjeta) throws DAOException;
	public void eliminarTarjetaDebito(int numeroTarjeta) throws DAOException;
	public void modificarTarjetaDebito(int numeroTarjeta, float saldo) throws DAOException;
	public List<TarjetaDebito> listarTarjetaDebito() throws DAOException;
	public TarjetaDebito consultarTarjetaDebito(int numeroTarjeta) throws DAOException;
	public List<TarjetaDebito> listarTarjetaDebito(int dni) throws DAOException;
	public void depositarTarjetaDebito(int numeroTarjeta, float saldo) throws DAOException;
}
