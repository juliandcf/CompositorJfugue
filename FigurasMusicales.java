package practica4.ej1;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum FigurasMusicales {
	REDONDA,
	BLANCA,
	NEGRA,
	CORCHEA,
	SEMICORCHEA,
	FUSA,
	SEMIFUSA;
	
	private Image image;
	
	FigurasMusicales(){
		try {
			this.setImage(ImageIO.read(getClass().getResource("compositor-imagenes/" + this.name().toLowerCase() + ".png")));
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
	
}
