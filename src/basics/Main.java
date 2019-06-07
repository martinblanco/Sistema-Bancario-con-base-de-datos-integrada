package basics;

import java.sql.SQLException;

import dao.ClienteDao;
import dbImpl.dao.ClienteDAODBImpl;
import entidades.Cliente;
import exceptions.DAOException;

public class Main {

		public static void main(String [] args) throws SQLException, DAOException {

			TableManager tm = new TableManager();
			try {
				tm.createClienteTable();
			}catch (SQLException sqle) {
				System.out.println("----->");
				System.out.println("ATENCION: La base de datos de ejemplo ya existe - mensaje excepcion:" + sqle.getMessage());
				System.out.println("<----");
			}
			
			
			ClienteDao dm = new ClienteDAODBImpl();
			
			String nom = "cargado1";
			String ape = "ape1";
			int dni = 1;
			Cliente aInsertar = new Cliente();
			aInsertar.setNombre(nom);
			aInsertar.setApellido(ape);
			aInsertar.setDni(dni);
			dm.insertarCliente(aInsertar);
			
			String nomx = "john";
			String apex = "apellido2";
			int dni2 = 2;
			Cliente paraEditar = new Cliente(nomx, apex, dni2);
			dm.insertarCliente(paraEditar);
			
			System.out.println("Ahora voy a mostrar el usuario recien cargado");
			int unDni = 2;
			dm.consultarCliente(unDni);
			System.out.println("---------");
			
			System.out.println("Voy a modificar un usuario");
			String nom3 = "john salchijohn";
			String ape3 = "ape2";
			int dni3 = 2;
			Cliente aEditar = new Cliente(nom3, ape3, dni3);
			dm.modificarCliente(aEditar);

			System.out.println("Tengo estos usuarios:");
			dm.listarClientes();
			System.out.println("------");
			
			
			System.out.println("Voy a borrar un usuario segun su username");
			int dnib = 1;
			dm.eliminarClientecondni(dnib);
			
			System.out.println("Tengo estos usuarios:");
			dm.listarClientes();
			System.out.println("------");
			
			//tm.dropUserTable();

}
}
