package practica4.ej1;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum FigurasMusicales {
	REDONDA("w"),
	BLANCA("h"),
	NEGRA("q"),
	CORCHEA("i"),
	SEMICORCHEA("s"),
	FUSA("t"),
	SEMIFUSA("x");
	
	private Image image;
	
	private String valor;
	
	FigurasMusicales(String valor){
		try {
			this.setImage(ImageIO.read(getClass().getResource("compositor-imagenes/" + this.name().toLowerCase() + ".png")));
			this.setValor(valor);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage() {
		return image;
	}

	private void setImage(Image image) {
		this.image = image;
	}

	public String getValor() {
		return valor;
	}

	private void setValor(String valor) {
		this.valor = valor;
	}
	
}
