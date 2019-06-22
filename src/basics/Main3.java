package basics;

import modelo.Handler;
import swing.Login;
import swing.MiFrame;

public class Main3 {

	public static void main(String[] args) {
		Handler miCoordinador = new Handler();
		MiFrame prueba = new MiFrame();
		prueba.setCoordinador(miCoordinador);
		miCoordinador.setMiFrame(prueba);
		Login lg = new Login(prueba, true);
		lg.setMiHandler(miCoordinador);
		prueba.setVisible(true);
		lg.setVisible(true);
	}

}
