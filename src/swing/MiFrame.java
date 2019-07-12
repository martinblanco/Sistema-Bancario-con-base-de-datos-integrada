package swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import modelo.Handler;

public class MiFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
    public MiFrame() {
        initComponents();
        setSize(700,700);
        setLocationRelativeTo(null);
    }
    
    public javax.swing.JLabel labelTitulo;
    public javax.swing.JPanel panelPrincipal;
    public javax.swing.JPanel panelTitulo;
    public javax.swing.JScrollPane panelVariable;
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10,item11;
	JMenu menuClientes,homeBanking,menuCuentas,menuTransacciones,menuTarjetaCredito,menuTarjetaDebito ;
	JMenuBar menuBar;

	//PanelUsuarios panel;
	Handler miHandler;

	public void setCoordinador(Handler miCoordinador) {
		miHandler = miCoordinador;		
	}
    
    
    private void initComponents() {

        panelPrincipal = new JPanel();
        panelTitulo = new JPanel();
        labelTitulo = new JLabel();
        panelVariable = new JScrollPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout(5, 5));

        panelPrincipal.setLayout(new BorderLayout());

        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setText("Bienvenido al sistema");
        
        labelTitulo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panelTitulo.add(labelTitulo);

        this.setJMenuBar(setMenuBar());

        panelPrincipal.add(panelTitulo, BorderLayout.PAGE_START);
        panelPrincipal.add(panelVariable, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }                    


    
    public void setPanel(JPanel panel){
    	panelVariable.setViewportView(panel);
    }
    
    private JMenuBar setMenuBar(){
    	menuBar = new JMenuBar();
    	
    	menuClientes = new JMenu("Clientes ");
    	
    	menuBar.add(menuClientes);
    	item1 = new JMenuItem("Consultar Todos los Clientes");
    	item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(MiFrame.this.miHandler.mostrarTodosClientes().isEmpty() == true){
					MiFrame.this.miHandler.mostrarError("No se puede mostrar la lista ya que no hay Clientes ingresados ");
				} else
					MiFrame.this.miHandler.mostarMiPanelClientes();
			}
		});
    	item2 = new JMenuItem("Alta Cliente");
    	item2.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				MiFrame.this.miHandler.mostarMiPanelAltaClientes();
			}
		});
    	menuClientes.add(item1);
    	menuClientes.add(item2);
    	
    	menuCuentas = new JMenu("Cuentas");
    	menuBar.add(menuCuentas);
    	item3 = new JMenuItem("Consultar Cuentas ");
    	item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					if(MiFrame.this.miHandler.mostrarTodosCuentas().isEmpty() == true){
						MiFrame.this.miHandler.mostrarError("No se puede mostrar el panel ya que no hay cuentas ingresadas ");
					}else
						MiFrame.this.miHandler.mostrarPanelCuentas();
			}
		});
    	item4 = new JMenuItem("Crear cuentas ");
    	item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MiFrame.this.miHandler.mostrarPanelAltaCuentas();
			}
		});
    	menuCuentas.add(item3);
    	menuCuentas.add(item4);
    	
    	menuTransacciones = new JMenu("Transacciones");
    	menuBar.add(menuTransacciones);
    	item5 = new JMenuItem("Nueva Transaccion");
    	item5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					if(MiFrame.this.miHandler.mostrarTodosCuentas().isEmpty() == true){
						MiFrame.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay cuentas ingresadas");
					}else
						MiFrame.this.miHandler.mostrarPanelCuentasTransaccion();
			}
		});
    	item6 = new JMenuItem("Todas Las Transacciones");
    	item6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					if(MiFrame.this.miHandler.mostrarTodosTransacciones().isEmpty() == true){
						MiFrame.this.miHandler.mostrarError("No se puede mostrar el panel ya que no hay Transacciones realizadas ");
					}else
						MiFrame.this.miHandler.mostrarPanelTransaccion();
			}
		});
    	menuTransacciones.add(item5);
    	menuTransacciones.add(item6);
    	
    	menuTarjetaCredito = new JMenu("Tarjeta de Credito");
    	menuBar.add(menuTarjetaCredito);
    	item8 = new JMenuItem("Consultar Todas las Tarjetas de Creditos");
    	item8.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
					if(MiFrame.this.miHandler.mostrarTodosTarjetasCredito().isEmpty() == true){
						MiFrame.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay Tarjetas de credito ingresadas");
					}else
						MiFrame.this.miHandler.mostrarPanelTarjetaCredito();
			}
		});
    	item9 = new JMenuItem("Alta Tarjeta de Credito");
    	item9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MiFrame.this.miHandler.mostarMiPanelAltaTarjetaCredito();
			}
		});
    	menuTarjetaCredito.add(item8);
    	menuTarjetaCredito.add(item9);
    	
    	menuTarjetaDebito = new JMenu("Tarjeta de Debito");
    	menuBar.add(menuTarjetaDebito);
    	item10 = new JMenuItem("Consultar Todas las Tarjetas de Debito");
    	item10.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
					if(MiFrame.this.miHandler.mostrarTodosTarjetasDebito().isEmpty() == true){
						MiFrame.this.miHandler.mostrarError("No se puede realizar transaccion ya que no hay Tarjetas de debito ingresadas");
					}else
						MiFrame.this.miHandler.mostrarPanelTarjetaDebito();
			}
		});
    	item11 = new JMenuItem("Alta Tarjeta de Debito");
    	item11.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MiFrame.this.miHandler.mostarMiPanelAltaTarjetaDebito();
			}
		});
    	menuTarjetaDebito.add(item10);
    	menuTarjetaDebito.add(item11);
    	
    	homeBanking = new JMenu("Home Banking");
    	menuBar.add(homeBanking);
    	item7 = new JMenuItem("Abrir HomeBanking");
//    	/*item7.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MiFrame.this.miHandler.mostrarHomeBanking();
//
//			}
//		});*/
    	homeBanking.add(item7);
    	
    	return menuBar;
    }
    
    public void setMenuUsuario(){
    	menuClientes.setEnabled(false);
    	menuCuentas.setEnabled(false);
    	menuTransacciones.setEnabled(false);
    	menuTarjetaCredito.setEnabled(false);
    }
    
    public void setMenuAdmin(){
    	homeBanking.setEnabled(false);
    }
        
}