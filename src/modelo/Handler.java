package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import swing.*;

import dao.ClienteDao;
import dao.CuentaDao;
import dao.TarjetaCreditoDao;
import dao.TarjetaDebitoDao;
import dao.TransaccionDao;
import dbImpl.dao.ClienteDAODBImpl;
import dbImpl.dao.CuentaDAODBImpl;
import dbImpl.dao.TarjetaCreditoDAODBImpl;
import dbImpl.dao.TarjetaDebitoDAODBImpl;
import dbImpl.dao.TransaccionDAODBImpl;
import service.ClienteService;
import service.CuentaService;
import service.TarjetaCreditoService;
import service.TarjetaDebitoService;
import service.TransaccionService;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.TarjetaCredito;
import entidades.TarjetaDebito;
import entidades.Transaccion;
import exceptions.LoginException;
import exceptions.ServicioException;
import exceptions.TarjetaCreditoException;
import exceptions.TarjetaDebitoException;
import exceptions.TransaccionException;
import exceptions.ClienteException;
import exceptions.CuentaException;

public class Handler {
	private ClienteService cliente;
	private CuentaService cuenta;
	private TransaccionService transaccion;
	private TarjetaCreditoService tarjetacredito;
	private TarjetaDebitoService tarjetadebito;
	private MiFrame miFrame;
	private int dniCliente;

	public Handler(){
			ClienteDao clienteDao = new ClienteDAODBImpl();
			ClienteService clienteService = new ClienteService(clienteDao);
			setCliente(clienteService);
			
			CuentaDao cuentaDao = new CuentaDAODBImpl();
			CuentaService cuentaService = new CuentaService(cuentaDao);
			setCuenta(cuentaService);
			
			TransaccionDao transaccionDao = new TransaccionDAODBImpl();
			TransaccionService transaccionService = new TransaccionService(transaccionDao);
			setTransaccion(transaccionService);	
			
			TarjetaCreditoDao tarjetacreditoDao = new TarjetaCreditoDAODBImpl();
			TarjetaCreditoService tarjetacreditoService = new TarjetaCreditoService(tarjetacreditoDao);
			setTargetaCredito(tarjetacreditoService);	
			
			TarjetaDebitoDao tarjetaDebitoDao = new TarjetaDebitoDAODBImpl();
			TarjetaDebitoService tarjetaDebitoService = new TarjetaDebitoService(tarjetaDebitoDao);
			setTargetaDebito(tarjetaDebitoService);	
	}
	
	public void setMiFrame(MiFrame miFrame){
		this.miFrame = miFrame;
	}
	
	public ClienteService getCliente() {
		return cliente;
	}

	public void setCliente(ClienteService cliente) {
		this.cliente = cliente;
	}
	
	public CuentaService getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaService cuenta) {
		this.cuenta = cuenta;
	}
	
	public TransaccionService getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(TransaccionService transaccion) {
		this.transaccion = transaccion;
	}
	
	public TarjetaCreditoService getTargetaCredito() {
		return tarjetacredito;
	}

	public void setTargetaCredito(TarjetaCreditoService tarjetacredito) {
		this.tarjetacredito = tarjetacredito;
	}
	
	public TarjetaDebitoService getTargetaDebito() {
		return tarjetadebito;
	}

	public void setTargetaDebito(TarjetaDebitoService tarjetadebito) {
		this.tarjetadebito = tarjetadebito;
	}
	

	//////////////////////////// Paneles Clientes
	
	public void altaCliente(Cliente miCliente){
		 try {
			cliente.insertarCliente(miCliente);
			mostrarSucces("Se ha dado de alta al Cliente " + miCliente.getNombre());
			if(JOptionPane.showConfirmDialog(null, "Quiere seguir ingresando Clientes?") == 1){
				mostarMiPanelAltaClientes();
			} else
				mostarMiPanelClientes();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		} catch(ClienteException e2){
			mostrarError(e2.getMessage());
			mostarMiPanelAltaClientes();
		}
	}
	
	public void mostarMiPanelAltaClientes(){
		PanelAltaClientes miPanelAlta = new PanelAltaClientes();
		miPanelAlta.setHandler(this);
		miFrame.setPanel(miPanelAlta);
	}


	public void mostarMiPanelClientes(){
		PanelClientes miLista = new PanelClientes(this);
		miFrame.setPanel(miLista);
	}
	
	public void mostarMiPanelModificaCliente(Cliente miCliente){
		PanelModificacionCliente miPanelModificacion = new PanelModificacionCliente();
		miPanelModificacion.setHandler(this);
		miPanelModificacion.editarCliente(miCliente);
		miFrame.setPanel(miPanelModificacion);
	}
	
	public List<Cliente> mostrarTodosClientes(){
		List<Cliente> miLista = new ArrayList<Cliente>();
		try {
			miLista.addAll(cliente.listarClientes());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public Cliente buscarCliente(int dni){
		Cliente clientebuscado = null;
		try {
			clientebuscado = cliente.consultarCliente(dni);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		return clientebuscado;
	}
	
	public void eliminarCliente(int dni){
		try {
			cliente.eliminarClientecondni(dni);
			mostarMiPanelClientes();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		} catch(ClienteException e2){
			mostrarError(e2.getMessage());
		}
	}
	
	public void modificarCliente(String nom ,String ape, int dni){
		try {
			cliente.modificarCliente(nom,ape,dni);
			mostarMiPanelClientes();
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}	
	
	////////////////////////////////////////////////////////////Cuentas
	
	public void mostrarPanelAltaCuentas(){
		PanelAltaCuentas altaCuentas = new PanelAltaCuentas();
		altaCuentas.setHandler(this);
		miFrame.setPanel(altaCuentas);
	}
	
	public void mostrarPanelCuentas(){
		PanelCuentas cuentas = new PanelCuentas(this);
		miFrame.setPanel(cuentas);
	}
	
	public void mostarMiPanelModificaCuenta(Cuenta miCuenta){
		PanelModificacionCuenta miPanelModificacion = new PanelModificacionCuenta();
		miPanelModificacion.setHandler(this);
		miPanelModificacion.editarCuenta(miCuenta);
		miFrame.setPanel(miPanelModificacion);
	}
		
	public List<Cuenta> mostrarTodosCuentas(){
		List<Cuenta> miLista = new ArrayList<Cuenta>();
		try {
			miLista.addAll(cuenta.listarCuentas());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public List<Cuenta> mostrarTodosCuentas(int dni){
		List<Cuenta> miLista = new ArrayList<Cuenta>();
		try {
			miLista.addAll(cuenta.listarCuentas(dni));
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public Cuenta buscarCuenta(int numeroCuenta){
		Cuenta cuentabuscada = null;
		try {
			cuentabuscada = cuenta.consultarCuenta(numeroCuenta);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		return cuentabuscada;
	}
	
	public void bajaCuenta(int numeroCuenta){
		try {
			cuenta.eliminarCuenta(numeroCuenta);
			mostrarPanelCuentas();
		} catch (CuentaException e) {
			mostrarError(e.getMessage());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		
	}
	
	public void bajaCuenta(int numeroCuenta, int dni){
		try {
			cuenta.eliminarCuenta(numeroCuenta);
		} catch (CuentaException e) {
			mostrarError(e.getMessage());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		
	}
	
	public void altaCuenta(Cuenta miCuenta){
		try {
			cuenta.altaCuenta(miCuenta);
			mostrarSucces("Se ha dado de alta a la Cuenta: " + miCuenta.getNumeroCuenta());
		} catch (ServicioException e) {
			e.printStackTrace();
		} catch (CuentaException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void modificarCuenta(int numeroCuenta, float cuentacorriente, float cajaahorro, float cajadolares){
		try {
			cuenta.modificarCuenta(numeroCuenta,cuentacorriente,cajaahorro,cajadolares);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}	
	
	public void depositarCuenta(int numeroCuenta, float cuentacorriente, float cajaahorro, float cajadolares){
		try {
			cuenta.depositarCuenta(numeroCuenta,cuentacorriente,cajaahorro,cajadolares);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////////////////////////// TRANSACCIONES
	
	public void mostrarPanelTransaccion(){
		PanelTransacciones transaccion = new PanelTransacciones(this);
		miFrame.setPanel(transaccion);
	}
	
	public void mostrarPanelCuentasTransaccion(){
		PanelCuentasTransaccion transaccion = new PanelCuentasTransaccion(this);
		miFrame.setPanel(transaccion);
	}
	
	public void mostarMiPanelNuevaTransaccion(Cuenta miCuenta){
		PanelNuevaTransaccion miPanelTransaccion = new PanelNuevaTransaccion();
		miPanelTransaccion.setHandler(this);
		miPanelTransaccion.nuevaTransaccion(miCuenta);
		miFrame.setPanel(miPanelTransaccion);
	}

	public List<Transaccion> mostrarTodosTransacciones(){
		List<Transaccion> miLista = new ArrayList<Transaccion>();
		try {
			miLista.addAll(transaccion.listarTransaccion());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public List<Transaccion> mostrarTodosTransacciones(int dni){
		List<Transaccion> miLista = new ArrayList<Transaccion>();
		try {
			miLista.addAll(transaccion.listarTransaccion(dni));
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public Transaccion buscarTransaccion(int numeroTransaccion){
		Transaccion transaccionbuscada = null;
		try {
			transaccionbuscada = transaccion.consultarTransaccion(numeroTransaccion);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		return transaccionbuscada;
	}
	
	public void bajaTransaccion(int numeroTransaccion){
		try {
			transaccion.eliminarTransaccion(numeroTransaccion);
			mostrarPanelTransaccion();
		} catch (TransaccionException e) {
			mostrarError(e.getMessage());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		
	}
	
	public void altaTransaccion(Transaccion miTransaccion){
		try {
			transaccion.altaTransaccion(miTransaccion);
			mostrarSucces("Se ha realizado la Transaccion: " + miTransaccion.getNumeroTransaccion());
		} catch (ServicioException e) {
			e.printStackTrace();
		} catch (TransaccionException e) {
			mostrarError(e.getMessage());
		}
	}
	
	/////////////////////////////////////////////////////////////TARJETAS DE CREDITO
	
	public void mostrarPanelTarjetaCredito(){
		PanelTarjetaCredito tarjeta = new PanelTarjetaCredito(this);
		miFrame.setPanel(tarjeta);
	}

	public void mostarMiPanelAltaTarjetaCredito(){
		PanelAltaTarjetaCredito miPanelAlta = new PanelAltaTarjetaCredito();
		miPanelAlta.setHandler(this);
		miFrame.setPanel(miPanelAlta);
	}
	
	public void mostarMiPanelModificaTarjetaCredito(TarjetaCredito miTarjeta){
		PanelModificacionTarjetaCredito miPanelModificacion = new PanelModificacionTarjetaCredito();
		miPanelModificacion.setHandler(this);
		miPanelModificacion.editarTarjetaCredito(miTarjeta);
		miFrame.setPanel(miPanelModificacion);
	}
	
	public List<TarjetaCredito> mostrarTodosTarjetasCredito(){
		List<TarjetaCredito> miLista = new ArrayList<TarjetaCredito>();
		try {
			miLista.addAll(tarjetacredito.listarTarjetaCredito());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public List<TarjetaCredito> mostrarTodosTarjetasCredito(int dni){
		List<TarjetaCredito> miLista = new ArrayList<TarjetaCredito>();
		try {
			miLista.addAll(tarjetacredito.listarTarjetaCredito(dni));
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public TarjetaCredito buscarTarjetaCredito(int numeroTarjeta){
		TarjetaCredito tarjetabuscada = null;
		try {
			tarjetabuscada = tarjetacredito.consultarTarjetaCredito(numeroTarjeta);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		return tarjetabuscada;
	}
	
	public void bajaTarjetaCredito(int numeroTarjeta){
		try {
			tarjetacredito.eliminarTarjetaCredito(numeroTarjeta);
		} catch (TarjetaCreditoException e) {
			mostrarError(e.getMessage());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void altaTarjetaCredito(TarjetaCredito miTarjetaCredito){
		try {
			tarjetacredito.altaTarjetaCredito(miTarjetaCredito);
			mostrarSucces("Se ha dado de alta la Tarjeta de Credito: " + miTarjetaCredito.getNumeroTarjeta());
		} catch (ServicioException e) {
			e.printStackTrace();
		} catch (TarjetaCreditoException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void modificarTarjetaCredito(int numeroTarjeta, float apagar){
		try {
			tarjetacredito.modificarTarjetaCredito(numeroTarjeta,apagar);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void pagarTarjetaCredito(int numeroTarjeta, float apagar){
		try {
			tarjetacredito.pagarTarjetaCredito(numeroTarjeta,apagar);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////// Tarjeta de Debito
	public void mostrarPanelTarjetaDebito(){
		PanelTarjetaDebito tarjeta = new PanelTarjetaDebito(this);
		miFrame.setPanel(tarjeta);
	}

	public void mostarMiPanelAltaTarjetaDebito(){
		PanelAltaTarjetaDebito miPanelAlta = new PanelAltaTarjetaDebito();
		miPanelAlta.setHandler(this);
		miFrame.setPanel(miPanelAlta);
	}
	
	public void mostarMiPanelModificaTarjetaDebito(TarjetaDebito miTarjeta){
		PanelModificacionTarjetaDebito miPanelModificacion = new PanelModificacionTarjetaDebito();
		miPanelModificacion.setHandler(this);
		miPanelModificacion.editarTarjetaDebito(miTarjeta);
		miFrame.setPanel(miPanelModificacion);
	}
	
	public List<TarjetaDebito> mostrarTodosTarjetasDebito(){
		List<TarjetaDebito> miLista = new ArrayList<TarjetaDebito>();
		try {
			miLista.addAll(tarjetadebito.listarTarjetaDebito());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public List<TarjetaDebito> mostrarTodosTarjetasDebito(int dni){
		List<TarjetaDebito> miLista = new ArrayList<TarjetaDebito>();
		try {
			miLista.addAll(tarjetadebito.listarTarjetaDebito(dni));
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
		return miLista;
	}
	
	public TarjetaDebito buscarTarjetaDebito(int numeroTarjeta){
		TarjetaDebito tarjetabuscada = null;
		try {
			tarjetabuscada = tarjetadebito.consultarTarjetaDebito(numeroTarjeta);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
		return tarjetabuscada;
	}
	
	public void bajaTarjetaDebito(int numeroTarjeta){
		try {
			tarjetadebito.eliminarTarjetaDebito(numeroTarjeta);
		} catch (TarjetaDebitoException e) {
			mostrarError(e.getMessage());
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void altaTarjetaDebito(TarjetaDebito miTarjetaDebito){
		try {
			tarjetadebito.altaTarjetaDebito(miTarjetaDebito);
			mostrarSucces("Se ha dado de alta la Tarjeta de Debito: " + miTarjetaDebito.getNumeroTarjeta());
		} catch (ServicioException e) {
			e.printStackTrace();
		} catch (TarjetaDebitoException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void modificarTarjetaDebito(int numeroTarjeta, float saldo){
		try {
			tarjetadebito.modificarTarjetaDebito(numeroTarjeta,saldo);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void depositarTarjetaDebito(int numeroTarjeta, float saldo){
		try {
			tarjetadebito.depositarTarjetaDebito(numeroTarjeta,saldo);
		} catch (ServicioException e) {
			mostrarError(e.getMessage());
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////////////HomeBanking
	
	public void mostrarHomeBanking(){
		HomeBanking hb = new HomeBanking(this);
		hb.setTitulo(buscarCliente(getDniCliente()));
		miFrame.setPanel(hb);	
	}
	
	public void mostrarHomeBankingDepositar(Cuenta miCuenta){
		HomebankingDepositar miHomeBankingCuentas = new HomebankingDepositar();
		miHomeBankingCuentas.setHandler(this);
		miHomeBankingCuentas.editarCuenta(miCuenta);
		miFrame.setPanel(miHomeBankingCuentas);
	}
	
	public void mostrarHomeBankingCuentas(int dni){
		HomeBankingCuentas cuentas = new HomeBankingCuentas(this,dni);
		miFrame.setPanel(cuentas);
	}
	
	public void mostrarHomeBankingAltaCuentas(int dni){
		PanelAltaCuentas altaCuentas = new PanelAltaCuentas();
		altaCuentas.setHandler(this);
		altaCuentas.editarCuenta(dni);
		miFrame.setPanel(altaCuentas);
	}
	
	public void mostrarHomeBankingTarjetaDebito(int dni){
		HomeBankingTarjetaDebito tarjeta = new HomeBankingTarjetaDebito(this,dni);
		miFrame.setPanel(tarjeta);
	}
	
	public void mostrarHomeBankingDepositarDebito(TarjetaDebito miTarjeta){
		HomeBankingDepositarDebito miHomeBankingCuentas = new HomeBankingDepositarDebito();
		miHomeBankingCuentas.setHandler(this);
		miHomeBankingCuentas.editarCuenta(miTarjeta);
		miFrame.setPanel(miHomeBankingCuentas);
	}
	
	public void mostrarHomeBankingAltaDebito(int dni){
		PanelAltaTarjetaDebito altaCuentas = new PanelAltaTarjetaDebito();
		altaCuentas.setHandler(this);
		altaCuentas.editarTarjeta(dni);
		miFrame.setPanel(altaCuentas);
	}
	
	public void mostrarHomeBankingTarjetaCredito(int dni){
		HomeBankingTarjetaCredito tarjeta = new HomeBankingTarjetaCredito(this,dni);
		miFrame.setPanel(tarjeta);
	}
	
	public void mostrarHomeBankingDepositarCredito(TarjetaCredito miTarjeta){
		HomeBankingDepositarCredito miHomeBankingCuentas = new HomeBankingDepositarCredito();
		miHomeBankingCuentas.setHandler(this);
		miHomeBankingCuentas.editarTarjeta(miTarjeta);
		miFrame.setPanel(miHomeBankingCuentas);
	}
	
	public void mostrarHomeBankingAltaCredito(int dni){
		PanelAltaTarjetaCredito altaCuentas = new PanelAltaTarjetaCredito();
		altaCuentas.setHandler(this);
		altaCuentas.editarTarjeta(dni);
		miFrame.setPanel(altaCuentas);
	}
		
	public void mostrarHomeBankingTransaccion(int dni){
		HomeBankingTransacciones miHomeBankingCuentas = new HomeBankingTransacciones(this,dni);
		miFrame.setPanel(miHomeBankingCuentas);
	}
	
	public void mostrarHomeBankingNuevaTransaccion(Cuenta miCuenta){
		PanelNuevaTransaccion altaCuentas = new PanelNuevaTransaccion();
		altaCuentas.setHandler(this);
		altaCuentas.nuevaTransaccion(miCuenta);
		miFrame.setPanel(altaCuentas);
	}
	
	////////////////////////////////////////////////////////////Mensajes
	
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(null,error,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarSucces(String exito){
		JOptionPane.showMessageDialog(null,exito,"Exito",JOptionPane.INFORMATION_MESSAGE);
	}
	
	/////////////////////////////////////////////////////////////////////////Login
	
	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}
	
	public boolean validarLogin(int index, String pass) {
		boolean caso = false;
		try {
			caso = cliente.validarLogin(index, pass);
			mostrarSucces("Login Exitoso");	
			if(index == 2){
				miFrame.setMenuUsuario();
				setDniCliente(Integer.parseInt(pass));
				miFrame.setVisible(true);
			} else {
				miFrame.setMenuAdmin();
				miFrame.setVisible(true);
			}
		} catch (LoginException e) {
			mostrarError(e.getMessage());
		}
		return caso;
	}
}