
import java.awt.Color;

public class Apple {
	
	private int x;
	private int y;
	private Color color;

	public Apple(int x, int y, Color color) {
		this.x = x;
		this.y = y;
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