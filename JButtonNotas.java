package practica4.ej1;

import javax.swing.JButton;

public class JButtonNotas extends JButton {
	private String nombre;
	
	public JButtonNotas(String texto){
		this.setNombre(texto);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
