package swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
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
	JMenuItem item1,item2,item3,item4,item5,item6;
	JMenu menuClientes,homeBanking,menuCuentas ;
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
    	
    	homeBanking = new JMenu("Home Banking");
    	menuBar.add(homeBanking);
    	item5 = new JMenuItem("Abrir HomeBanking");
    	/*item5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MiFrame.this.miHandler.mostrarHomeBanking();

			}
		});*/
    	homeBanking.add(item5);
    	
    	return menuBar;
    }
    
    public void setMenuUsuario(){
    	menuClientes.setEnabled(false);
    	menuCuentas.setEnabled(false);
    }
    
    public void setMenuAdmin(){
    	homeBanking.setEnabled(false);
    }
        

}
