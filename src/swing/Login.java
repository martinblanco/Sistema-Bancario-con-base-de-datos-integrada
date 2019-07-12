package swing;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Handler;

@SuppressWarnings("serial")
public class Login extends javax.swing.JDialog {

    private javax.swing.JButton botonAceptar;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JComboBox<String> opcionesUsuario;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPasswordField textPassword;
    private Handler miHandler;

    public void setMiHandler(Handler miHandler) {
		this.miHandler = miHandler;
	}
	
    public Login(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(350,250);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelPass = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        opcionesUsuario = new javax.swing.JComboBox<>();
        textPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        labelTitulo.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Ventana Login");
        labelTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 50;
        panelPrincipal.add(labelTitulo, gridBagConstraints);

        labelUsuario.setText("Usuario");
        panelPrincipal.add(labelUsuario, gridButton(1, 11));

        labelPass.setText("Pass");
        panelPrincipal.add(labelPass, gridButton(1, 14));
        labelPass.setVisible(false);

        botonAceptar.setText("Aceptar");
        setBotonAceptar();
        panelPrincipal.add(botonAceptar, gridButton(9, 18));

        opcionesUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Administrador", "Cliente" }));
        opcionesUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = opcionesUsuario.getSelectedIndex();
				if(opcionesUsuario.getSelectedItem()=="Cliente"){
					labelPass.setText("DNI");
				} else
					labelPass.setText("Pass");
				if(index == 0){
					labelPass.setVisible(false);
					textPassword.setVisible(false);
				} else {
					labelPass.setVisible(true);
					textPassword.setVisible(true);
				}
			}
		});
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        panelPrincipal.add(opcionesUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 150;
        panelPrincipal.add(textPassword, gridBagConstraints);
        textPassword.setVisible(false);

        getContentPane().add(panelPrincipal);

        pack();
    }

    private GridBagConstraints gridButton(int x, int y){
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        return gridBagConstraints;
    }
    
    private void setBotonAceptar(){
    	botonAceptar.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(opcionesUsuario.getSelectedIndex()==0){
						miHandler.mostrarError("Seleccione una opcion correcta");
					} else{
						if(miHandler.validarLogin(opcionesUsuario.getSelectedIndex(), textPassword.getText()) == true ){
							Login.this.dispose();
						}
					}
				} catch (NumberFormatException e1) {
					miHandler.mostrarError("No deje campos vacios ");
				}
				
			}
		});
    }

	
    
    
}