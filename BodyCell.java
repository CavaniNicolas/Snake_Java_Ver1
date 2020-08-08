
import java.awt.Color;

public class BodyCell {
	
	private int x;
	private int y;
	private Color color;

	public BodyCell(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
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

	public Color getColor() {
		return this.color;
	}
}