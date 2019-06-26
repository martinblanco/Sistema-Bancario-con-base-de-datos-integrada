package swing;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Cliente;
import modelo.Handler;

public abstract class PanelGenerico extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton botonAplicar;
	JButton botonCancelar;
	JTextField textApellido;
    JTextField textCajaAhorro;
    JTextField textCuentaCorriente;
    JTextField textNombre;
    JTextField textDni;
	Handler miHandler;
   
   public PanelGenerico() {
       initComponents();
   }

   public void setHandler(Handler miCoordinador){
   	this.miHandler = miCoordinador;
   }
   
   private void initComponents() {
       GridBagConstraints gridBagConstraints;
       JLabel labelTitulo = new JLabel();
       botonAplicar = new JButton();
       textNombre = new JTextField();
       textApellido = new JTextField();
       textCuentaCorriente = new JTextField();
       textCajaAhorro = new JTextField();
       botonCancelar = new JButton();
       textDni = new JTextField();
       
       
       

       setBackground(new java.awt.Color(204, 204, 204));
       setLayout(new java.awt.GridBagLayout());


       labelTitulo.setFont(new java.awt.Font("Monaco", 0, 24)); 
       labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
       labelTitulo.setText(setTitulo("Panel Modifica "));
       labelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
       gridBagConstraints = new java.awt.GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 0;
       gridBagConstraints.gridwidth = 13;
       gridBagConstraints.ipadx = 114;
       gridBagConstraints.ipady = 5;
       add(labelTitulo, gridBagConstraints);

       botonCancelar.setText("Cancelar");
       setBotonCancelar();
       add(botonCancelar, gridText(12, 15));

       botonAplicar.setText("Aplicar"); 
       setBotonAplicar();
       add(botonAplicar, gridText(12,13));
       
       agregarBotones();
       
   }              

                        
   
   public void editarCliente(Cliente miCliente){
   	textDni.setText(miCliente.getDni()+"");
   	textDni.setEditable(false);
   	textApellido.setText(miCliente.getApellido());
   	textNombre.setText(miCliente.getNombre());
   }
   
   public void setBotonAplicar(){

   }
   
   public String setTitulo(String titulo){
   	return titulo;
   }
   
   public void setBotonCancelar(){

   }

   protected GridBagConstraints gridText(int x, int y){
   	
       GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
       gridBagConstraints.gridx = x;
       gridBagConstraints.gridy = y;
       gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
       gridBagConstraints.ipadx = 100;
       return gridBagConstraints;
   }
   
   protected GridBagConstraints gridLabel(int x, int y){
       GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
       gridBagConstraints.gridx = x;
       gridBagConstraints.gridy = y;
       return gridBagConstraints;
   }

   protected void agregarBotones(){
       JLabel labelNombre = new JLabel();
       JLabel labelApellido = new JLabel();
       //JLabel labelCuentaCorriente = new JLabel();
       //JLabel labelCajaAhorro = new JLabel();
       JLabel labelDni = new JLabel();
       
       textNombre = new JTextField();
       textApellido = new JTextField();
       //textCuentaCorriente = new JTextField();
       //textCajaAhorro = new JTextField();
       textDni = new JTextField();
       
       labelDni.setText("Dni");
       add(labelDni, gridLabel(1, 2));
       labelNombre.setText("Nombre");
       add(labelNombre, gridLabel(1, 4));
       labelApellido.setText("Apellido");
       add(labelApellido, gridLabel(1, 6));
       //labelCuentaCorriente.setText("Cuenta Corriente");
       //add(labelCuentaCorriente, gridLabel(1,10));
       //labelCajaAhorro.setText("Caja Ahorros");
       //add(labelCajaAhorro, gridLabel(1, 8));
       
       add(textNombre, gridText(3, 4));
       add(textApellido, gridText(3, 6));
       //add(textCuentaCorriente, gridText(3, 10));
       //add(textCajaAhorro, gridText(3, 8));
       add(textDni, gridText(3, 2));
   }


}