
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class BodyCell {
	
	private int x;
	private int y;
	private Color color;

	private BufferedImage image;
	private Dir dir;

	/**Constructeur : Coordonnees + couleur unie */
	public BodyCell(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**Constructeur : Coordonnees + image, avec une couleur par default darkGray pour le serpent */
	public BodyCell(int x, int y, BufferedImage image, Dir dir) {
		this(x, y, Color.darkGray);
		this.image = image;
		this.dir = dir;
	}


	/**Rotate image en fonction de la direction <b>dir */
	public void rotateImage() {
		BufferedImage blankCanvas = new BufferedImage(MyWindow.boxSize, MyWindow.boxSize, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)blankCanvas.getGraphics();
		g2d.rotate(Math.toRadians(this.dir.getAngle()), this.x*MyWindow.boxSize + MyWindow.boxSize/2, this.y*MyWindow.boxSize + MyWindow.boxSize/2);
		//this.image = blankCanvas;
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

	public void setImage(BufferedImage image) {
		this.image = image;
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

	public BufferedImage getImage() {
		return this.image;
	}
}