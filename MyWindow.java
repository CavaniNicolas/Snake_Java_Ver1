
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyWindow extends JFrame implements KeyListener {

	JPanel container = new JPanel();

	public static int WindowWidth;
	public static int WindowHeight;

	public static int nbBoxWidth;
	public static int nbBoxHeight;
	public static int boxSize;

	Snake snake;

	public MyWindow() {
		this(12, 12, 30);
	}

	public MyWindow(int nbBoxX, int nbBoxY, int boxSizeP) {
		nbBoxWidth = nbBoxX;
		nbBoxHeight = nbBoxY;
		boxSize = boxSizeP;

		WindowWidth = boxSizeP * nbBoxX;
		WindowHeight = boxSizeP * nbBoxY;

		this.setTitle("Snake");
		this.setSize(WindowWidth, WindowHeight);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//commenter cette ligne permet de rester dans le menu console une fois le jeu finit,
		//mais quitter le jeu dans le menu avec la page Quit ne tuera pas le processus

		container.setBackground(Color.GRAY);

		this.addKeyListener(this);

		this.setContentPane(container);
		this.setVisible(true);

		snake = new Snake(this.getGraphics());
		// snake = new Snake();
	}

	public void startGame() {
		snake.playGame();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyChar();
		//System.out.print("Code clavier "+code+"\n ");

		if (snake.isInputAllowed()) {

			if (code == 122 && snake.getDir() != Dir.Down) { // Si on veut aller Up mais pas si on va deja Down
				snake.setDir(Dir.Up);
				snake.setAllowInput(false);
			}
			if (code == 115 && snake.getDir() != Dir.Up) { // Si on veut aller Down mais pas si on va deja Up
				snake.setDir(Dir.Down);
				snake.setAllowInput(false);
			}
			if (code == 113 && snake.getDir() != Dir.Right) { // Si on veut aller Left mais pas si on va deja Right
				snake.setDir(Dir.Left);
				snake.setAllowInput(false);
			}
			if (code == 100 && snake.getDir() != Dir.Left) { // Si on veut aller Right mais pas si on va deja Left
				snake.setDir(Dir.Right);
				snake.setAllowInput(false);
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}