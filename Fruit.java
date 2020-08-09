
import java.awt.Color;
import java.awt.Image;

public class Fruit {

	protected int x;
	protected int y;

	private Color color;
	private Image image;

	/**Constructeur : Coordonnees + couleur unie */
	public Fruit(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**Constructeur : Coordonnees + image, avec une couleur par default */
	public Fruit(int x, int y, Image image, Color color) {
		this(x, y, color);
		this.image = image;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Color getColor() {
		return this.color;
	}

	public Image getImage() {
		return this.image;
	}
}