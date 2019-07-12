package swing;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import entidades.Cliente;
import modelo.Handler;

@SuppressWarnings("serial")
public class HomeBanking extends javax.swing.JPanel {

	private JButton Transacciones;
	private JButton botonAltaCuenta;
	private JButton botonAltaCredito;
	private JButton botonBajaCuenta;
	private JButton botonAltaDebito;
	private JButton botonBajaCredito;
	private JButton botonBajaDebito;
	private JButton botonSalir;
	private Handler miHandler;
	private JLabel labelTitulo;
	private int dni;

	public void setMiHandler(Handler miHandler) {
		this.miHandler = miHandler;
	}

	public HomeBanking(Handler miHandler) {
		setMiHandler(miHandler);
		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		Transacciones = new JButton();
		labelTitulo = new JLabel();
		botonSalir = new JButton();
		botonAltaCuenta = new JButton();
		botonBajaCuenta = new JButton();
		botonAltaDebito = new JButton();
		botonAltaCredito = new JButton();
		botonBajaCredito= new JButton();
		botonBajaDebito = new JButton();

		setBackground(new java.awt.Color(204, 255, 255));
		setLayout(new java.awt.GridBagLayout());

		Transacciones.setText("Tus Transacciones");
		add(Transacciones, gridButton(12, 20));
		Transacciones.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					if(HomeBanking.this.miHandler.mostrarTodosTransacciones(dni).isEmpty() == true){
						HomeBanking.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay Tarjetas de credito ingresadas");
					}else
						HomeBanking.this.miHandler.mostrarHomeBankingTransaccion(dni);
			}
		});

		botonSalir.setText("Salir");
		add(botonSalir, gridButton(12, 22));
		botonSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Esta seguro que quiere salir? ") == 0) {
					System.exit(0);
				}

			}
		});
		
		
		botonAltaCuenta.setText("Alta Cuentas ");
		add(botonAltaCuenta, gridButton(0, 14));
		botonAltaCuenta.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					HomeBanking.this.miHandler.mostrarHomeBankingAltaCuentas(dni);
			}
		});
		botonBajaCuenta.setText("Tus Cuentas");
		add(botonBajaCuenta, gridButton(16, 14));
		botonBajaCuenta.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					if(HomeBanking.this.miHandler.mostrarTodosCuentas(dni).isEmpty() == true){
						HomeBanking.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay Tarjetas de credito ingresadas");
					}else
						HomeBanking.this.miHandler.mostrarHomeBankingCuentas(dni);
			}
		});
		
		botonBajaDebito.setText("Tus T. Debito");
		add(botonBajaDebito, gridButton(16, 16));
		botonBajaDebito.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					if(HomeBanking.this.miHandler.mostrarTodosTarjetasDebito(dni).isEmpty() == true){
						HomeBanking.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay Tarjetas de debito ingresadas");
					}else
						HomeBanking.this.miHandler.mostrarHomeBankingTarjetaDebito(dni);
			}
		});
		botonAltaDebito.setText("Alta T. Debito");
		add(botonAltaDebito, gridButton(0, 16));
		botonAltaDebito.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					HomeBanking.this.miHandler.mostrarHomeBankingAltaDebito(dni);
			}
		});
		
		botonAltaCredito.setText("Alta T. Credito");
		add(botonAltaCredito, gridButton(0, 18));
		botonAltaCredito.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					HomeBanking.this.miHandler.mostrarHomeBankingAltaCredito(dni);
			}
		});
		botonBajaCredito.setText("Tus T. Credito");
		add(botonBajaCredito, gridButton(16, 18));
		botonBajaCredito.addActionListener(new ActionListener() {
		@Override
			public void actionPerformed(ActionEvent e) {
					if(HomeBanking.this.miHandler.mostrarTodosTarjetasCredito(dni).isEmpty() == true){
						HomeBanking.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay Tarjetas de credito ingresadas");
					}else
						HomeBanking.this.miHandler.mostrarHomeBankingTarjetaCredito(dni);
			}
		});
		

		labelTitulo.setFont(new java.awt.Font("Arial", 0, 20));
		labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 25;
		gridBagConstraints.ipadx = 114;
		gridBagConstraints.ipady = 5;
		add(labelTitulo, gridBagConstraints);

	}

	private GridBagConstraints gridButton(int x, int y) {
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = x;
		gridBagConstraints.gridy = y;
		return gridBagConstraints;
	}

	public void setTitulo(Cliente miCliente) {
		String titulo = "Cliente: " + miCliente.getNombre() + " " + miCliente.getApellido();
		setDni(miCliente.getDni());
		labelTitulo.setText(titulo);
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	
}