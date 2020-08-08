
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.util.ArrayList;

public class Snake extends JPanel {
	
	private Graphics g;

	private int boxSize; // longueur du cote d'un carre a l'ecran
	private int nbApple = 2; // nombre de pommes a l'ecran simultanement

	private boolean play = true; // en train de jouer
	private Dir dir = Dir.Left; // direction du serpent, initialement vert la gauche

	private int scoreApple = 0; // nombre de pommes mangees

	ArrayList<BodyCell> body = new ArrayList<BodyCell>(); // Liste contenant toutes les cellules du serpent
	ArrayList<Apple> apples = new ArrayList<Apple>(); // Liste contenant toutes les pommes a l'ecran

	public Snake(Graphics g) {
		this.g = g;
		initSnake();
	}

	/**Lance le jeu */
	public void playGame() {
		
		while (play) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, MyWindow.WindowWidth, MyWindow.WindowHeight);
			
			createApple();
			displayApple();
			displaySnake();
			sleep(250);
			move();
			checkCollision();
		}
	}

	/**Delay */
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**Initialisation dun serpent de taille 3*/
	public void initSnake() {

		int nbBoxX = MyWindow.nbBoxWidth;
		int nbBoxY = MyWindow.nbBoxHeight;

		this.boxSize = MyWindow.boxSize;
	
		for (int i=0; i<3; i++) {
			if (i == 0) {
				// La tete est verte et est au milieu de l'ecran
				body.add(new BodyCell(this.boxSize * nbBoxX / 2, this.boxSize * nbBoxY / 2, Color.green));
			} else {
				// Le corps est noir et le serpent regarde vers la gauche
				body.add(new BodyCell(this.boxSize * nbBoxX / 2 + i * boxSize, this.boxSize * nbBoxY / 2, Color.darkGray));
			}
		}
	}

	/**Affiche le serpent*/
	public void displaySnake() {
		for (int i=0; i<body.size(); i++) {
			g.setColor(body.get(i).getColor());
			g.fillOval(body.get(i).getX(), body.get(i).getY(), this.boxSize, this.boxSize);
		}
		showScore();
	}

	/**Affiche le nombre de pommes mangees */
	public void showScore() {
		g.setFont(new Font("Tahoma", Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString(Integer.toString(scoreApple), 30, MyWindow.WindowHeight - 30);
	}

	/**Creer des pommes avec des coordonnees aleatoires */
	public void createApple() {

		int i = 0;

		int x;
		int y;

		boolean isPlaced;

		while (apples.size() < nbApple) {

			x = (int)(Math.random() * (double)MyWindow.nbBoxWidth);
			y = (int)(Math.random() * (double)MyWindow.nbBoxHeight);

			isPlaced = true;

			// On verifie si la nouvelle pomme ne se met pas sur le serpent
			i = 0;
			while (isPlaced && i < body.size()) {
				if (x == body.get(i).getX() && y == body.get(i).getY()) {
					isPlaced = false;
				}
				i++;
			}

			// On verifie si la nouvelle pomme ne se met pas sur une autre pomme
			i = 0;
			while (isPlaced && i < apples.size()) {
				if (x == apples.get(i).getX() && y == apples.get(i).getY()) {
					isPlaced = false;
				}
				i++;
			}

			if (isPlaced) {
				apples.add(new Apple(x * boxSize, y * boxSize, Color.red));
			}
		}
	}

	/**Affiche les pommes */
	public void displayApple() {
		for (int i=0; i<apples.size(); i++) {
			g.setColor(apples.get(i).getColor());
			g.fillRect(apples.get(i).getX(), apples.get(i).getY(), this.boxSize, this.boxSize);
		}
	}

	/**Deplace le serpent en fonction de sa direction <b>dir */
	public void move() {

		int prX;
		int prY;

		BodyCell a;

		for (int i=body.size()-1; i>0; i--) {
			a = body.get(i-1);
			prX = a.getX();
			prY = a.getY();

			body.get(i).setX(prX);
			body.get(i).setY(prY);
		}

		a = body.get(0);

		if (this.dir == Dir.Up) {
			a.setY(a.getY()-boxSize);
		}
		if (this.dir == Dir.Down) {
			a.setY(a.getY()+boxSize);
		}
		if (this.dir == Dir.Left) {
			a.setX(a.getX()-boxSize);
		}
		if (this.dir == Dir.Right) {
			a.setX(a.getX()+boxSize);
		}
	}

	/**Gere les collisions */
	public void checkCollision() {

		BodyCell head = body.get(0);
		BodyCell tail = body.get(body.size()-1);

		// Si le serpent avance sur une pomme
		for (int i=0; i<apples.size(); i++) {
			if (head.getX() == apples.get(i).getX() && head.getY() == apples.get(i).getY()) {
				apples.remove(i);
				this.scoreApple += 1;
				body.add(new BodyCell(tail.getX(), tail.getY(), Color.darkGray));
			}
		}

		// Si le serpent avance sur lui meme
		for (int i=1; i<body.size(); i++) {
			if (head.getX() == body.get(i).getX() && head.getY() == body.get(i).getY()) {
				this.play = false;
				displayGameOver();
			}
		}

		// Si le serpent rentre dans un mur
		if ((head.getX() < 0 || head.getX() > MyWindow.WindowWidth - MyWindow.boxSize) || 
		(head.getY() < 0 || head.getY() > MyWindow.WindowHeight - MyWindow.boxSize)) {
			this.play = false;
			displayGameOver();
		}
	}

	/**Display Game Over Text */
	public void displayGameOver() {
		g.setFont(new Font("Tahoma", Font.BOLD, 72));
		g.setColor(Color.black);
		g.drawString("Game Over !", MyWindow.WindowWidth / 2 - 200, MyWindow.WindowHeight / 2 - 30);
	}
	/**Modifie la direction <dir> du serpent */
	public void setDir(Dir dir) {
		this.dir = dir;
	}

	/**Renvoie la direction dans laquelle le serpent va */
	public Dir getDir() {
		return this.dir;
	}
}