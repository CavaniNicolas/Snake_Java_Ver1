
import java.awt.Color;
import java.awt.Image;

public class BodyCell {
	
	private int x;
	private int y;
	private Color color;

	private Image image;
	private Dir dir;

	/**Constructeur : Coordonnees + couleur unie */
	public BodyCell(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**Constructeur : Coordonnees + image, avec une couleur par default darkGray pour le serpent */
	public BodyCell(int x, int y, Image image) {
		this(x, y, Color.darkGray);
		this.image = image;
	}

	public BodyCell(int x, int y, Image image, Dir dir) {
		this(x, y, image);
		this.dir = dir;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Dir getDir() {
		return this.dir;
	}

	public Color getColor() {
		return this.color;
	}

	public Image getImage() {
		return this.image;
	}
}