
import java.awt.Color;
import java.awt.Image;

public class Cherry extends Fruit {

	/**Constructeur : Coordonnees + couleur unie */
	public Cherry(int x, int y, Color color) {
		super(x, y, color);
	}

	/**Constructeur : Coordonnees + image, avec une couleur par default rose pour la cerise */
	public Cherry(int x, int y, Image image) {
		super(x, y, image, Color.pink);
	}
}