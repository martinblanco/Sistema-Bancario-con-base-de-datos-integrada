package dao;

import java.util.List;

import entidades.Transaccion;
import exceptions.DAOException;

public interface TransaccionDao {

	public void insertarTransaccion(Transaccion transaccion) throws DAOException;
	public void eliminarTransaccion(int numeroTransaccion) throws DAOException;
	public List<Transaccion> listarTransaccion() throws DAOException;
	public Transaccion consultarTransaccion(int dni) throws DAOException;
	public List<Transaccion> listarTransaccionDni(int dni) throws DAOException;
}
