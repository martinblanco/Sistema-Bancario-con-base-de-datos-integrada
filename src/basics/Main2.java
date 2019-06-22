package basics;

import javax.swing.JOptionPane;
import entidades.Cliente;
import exceptions.ServicioException;
import service.ClienteService;

public class Main2 {
    public static void main(String[] args) {
        String nombre = "user1";
        String apellido = "email1";
        int dni = 123213;
        Cliente aInsertar = new Cliente();
        aInsertar.setNombre(nombre);
        aInsertar.setApellido(apellido);
        aInsertar.setDni(dni);

        /*try {
            ClienteService s = new ClienteService();
            s.insertarCliente(aInsertar);
        } catch (ServicioException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CREEAR USUARIO");
        }*/
    }
}