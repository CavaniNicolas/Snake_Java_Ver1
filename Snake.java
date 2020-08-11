
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.util.ArrayList;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Snake extends JPanel {
	
	private Graphics g;

	private int boxSize; // longueur du cote d'un carre a l'ecran
	private int nbApple = 2; // nombre de pommes a l'ecran simultanement

	private boolean play = true; // en train de jouer
	private Dir dir = Dir.Left; // direction du serpent, initialement vert la gauche
	private boolean allowInput = true; // booleen autorise l'input de touche clavier

	private int scoreApple = 0; // nombre de pommes mangees

	ArrayList<BodyCell> body = new ArrayList<BodyCell>(); // Liste contenant toutes les cellules du serpent
	ArrayList<Apple> apples = new ArrayList<Apple>(); // Liste contenant toutes les pommes a l'ecran

	private BufferedImage displayBuffer;

	public Snake() {
		initSnake();
		this.displayBuffer = new BufferedImage(MyWindow.WindowWidth, MyWindow.WindowHeight, BufferedImage.TYPE_INT_RGB);
	}

	public Snake(Graphics g) {
		this.g = g;
		initSnake();
	}

	/**Lance le jeu */
	public void playGame() {

		while (play) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, MyWindow.WindowWidth, MyWindow.WindowHeight);
			
			createApples();

			displayApple();
			displaySnake();
			// repaint();

			sleep(150);
			move();
			checkCollision();
			setAllowInput(true);
		}
	}



	/*-----------------------------------*/
	/* Affichage des elements */
	/*-----------------------------------*/


	public void paint(Graphics g) {
		prepaint(this.displayBuffer.getGraphics());
		g.drawImage(this.displayBuffer, 0, 0, null);
	}

	public void prepaint(Graphics g) {
		displaySnake(g);
		displayApple(g);
	}

	/**Affiche le serpent*/
	public void displaySnake(Graphics g) {

		for (int i=0; i<body.size(); i++) {
			
			if (body.get(i).getImage() != null) {
				
				body.get(i).rotateImage();

				g.drawImage(body.get(i).getImage(), body.get(i).getX() * this.boxSize, body.get(i).getY() * this.boxSize, this.boxSize, this.boxSize, this);

			} else {
				g.setColor(body.get(i).getColor());
				g.fillOval(body.get(i).getX() * this.boxSize, body.get(i).getY() * this.boxSize, this.boxSize, this.boxSize);
			}

		}
		showScore(g);
	}


	/**Affiche le serpent*/
	public void displaySnake() {

		for (int i=0; i<body.size(); i++) {
			
			if (body.get(i).getImage() != null) {
				
				body.get(i).rotateImage();

				g.drawImage(body.get(i).getImage(), body.get(i).getX() * this.boxSize, body.get(i).getY() * this.boxSize, this.boxSize, this.boxSize, this);

			} else {
				g.setColor(body.get(i).getColor());
				g.fillOval(body.get(i).getX() * this.boxSize, body.get(i).getY() * this.boxSize, this.boxSize, this.boxSize);
			}

		}
		showScore();
	}



	/**Affiche le nombre de pommes mangees */
	public void showScore(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString(Integer.toString(scoreApple), 30, MyWindow.WindowHeight - 30);
	}

	/**Affiche le nombre de pommes mangees */
	public void showScore() {
		g.setFont(new Font("Tahoma", Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString(Integer.toString(scoreApple), 30, MyWindow.WindowHeight - 30);
	}



	/**Affiche les pommes */
	public void displayApple(Graphics g) {
		for (int i=0; i<apples.size(); i++) {
			
			if (apples.get(i).getImage() != null) {
				g.drawImage(apples.get(i).getImage(), apples.get(i).getX() * this.boxSize, apples.get(i).getY() * this.boxSize, this.boxSize, this.boxSize, this);
			} else {
				g.setColor(apples.get(i).getColor());
				g.fillRect(apples.get(i).getX() * this.boxSize, apples.get(i).getY() * this.boxSize, this.boxSize, this.boxSize);
			}
		}
	}


	/**Affiche les pommes */
	public void displayApple() {
		for (int i=0; i<apples.size(); i++) {
			
			if (apples.get(i).getImage() != null) {
				g.drawImage(apples.get(i).getImage(), apples.get(i).getX() * this.boxSize, apples.get(i).getY() * this.boxSize, this.boxSize, this.boxSize, this);
			} else {
				g.setColor(apples.get(i).getColor());
				g.fillRect(apples.get(i).getX() * this.boxSize, apples.get(i).getY() * this.boxSize, this.boxSize, this.boxSize);
			}
		}
	}


	/**Display Game Over Text */
	public void displayGameOver() {
		g.setFont(new Font("Tahoma", Font.BOLD, 72));
		g.setColor(Color.black);
		g.drawString("Game Over !", MyWindow.WindowWidth / 2 - 200, MyWindow.WindowHeight / 2 - 30);
	}





	/*-----------------------------------*/
	/* Creation des elements */
	/*-----------------------------------*/


	/**Initialisation dun serpent de taille 3*/
	public void initSnake() {

		int nbBoxX = MyWindow.nbBoxWidth;
		int nbBoxY = MyWindow.nbBoxHeight;

		this.boxSize = MyWindow.boxSize;
	
		for (int i=0; i<3; i++) {
			if (i == 0) {
				// La tete est verte et est au milieu de l'ecran
				createSnakeCell(nbBoxX / 2, nbBoxY / 2, "assets/snakeHead.png", Dir.Left);
				//createSnakeCell(nbBoxX / 2, nbBoxY / 2, Color.green);

			} else {
				// Le corps est noir et le serpent regarde vers la gauche
				createSnakeCell(nbBoxX/2, nbBoxY/2, "assets/snakeStraightBody.png", Dir.Left);
				// createSnakeCell(nbBoxX / 2 + i, nbBoxY / 2, Color.darkGray));
			}

		}
	}

	/**Creer une nouvelle cellule pour le corps du serpent avec une couleur unie*/
	public void createSnakeCell(int x, int y, Color color) {
		body.add(new BodyCell(x, y, color));
	}

	/**Creer une nouvelle cellule pour le corps du serpent avec une image*/
	public void createSnakeCell(int x, int y, String imagePath, Dir dir) {
		try {
			body.add(new BodyCell(x, y, ImageIO.read(new File(imagePath)), dir ));
		} catch (Exception e) {
			body.add(new BodyCell(x, y, Color.darkGray));
		}
	}

	/**Creer des pommes avec des coordonnees aleatoires */
	public void createApples() {

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
				addApple(x, y, "assets/apple.png");
				//addApple(x, y, Color.red);
			}
		}
	}

	/**Creer une pomme d'une couleur unie */
	public void addApple(int x, int y, Color color) {
		apples.add(new Apple(x, y, color));
	}

	/**Creer une pomme a partir dune image */
	public void addApple(int x, int y, String imagePath) {
		try {
			apples.add(new Apple(x, y, ImageIO.read(new File(imagePath)) ));
		} catch (Exception e) {
			apples.add(new Apple(x, y, Color.red));
		}
	}






	/*-----------------------------------*/
	/* Deplacement du snake */
	/*-----------------------------------*/

	/**Deplace le serpent en fonction de sa direction <b>dir */
	public void move() {

		int prX;
		int prY;
		Dir prDir;

		BodyCell a;

		for (int i=body.size()-1; i>0; i--) {
			a = body.get(i-1);
			prX = a.getX();
			prY = a.getY();
			prDir = a.getDir();

			body.get(i).setX(prX);
			body.get(i).setY(prY);
			body.get(i).setDir(prDir);
		}

		a = body.get(0);

		if (this.dir == Dir.Up) {
			a.setY(a.getY()-1);
			a.setDir(Dir.Up);
		}
		if (this.dir == Dir.Down) {
			a.setY(a.getY()+1);
			a.setDir(Dir.Down);
		}
		if (this.dir == Dir.Left) {
			a.setX(a.getX()-1);
			a.setDir(Dir.Left);
		}
		if (this.dir == Dir.Right) {
			a.setX(a.getX()+1);
			a.setDir(Dir.Right);
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
				createSnakeCell(tail.getX(), tail.getY(), "assets/snakeStraightBody.png", this.dir);
				//createSnakeCell(tail.getX(), tail.getY(), Color.darkGray);
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
		if ((head.getX() < 0 || head.getX() > MyWindow.nbBoxWidth - 1) || 
		(head.getY() < 0 || head.getY() > MyWindow.nbBoxHeight - 1)) {
			this.play = false;
			displayGameOver();
		}
	}



	/*-----------------------------------*/
	/* Divers */
	/*-----------------------------------*/

	/**Delay */
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**Modifie la direction <dir> du serpent */
	public void setDir(Dir dir) {
		this.dir = dir;
	}

	/**Renvoie la direction dans laquelle le serpent va */
	public Dir getDir() {
		return this.dir;
	}

	public void setAllowInput(boolean bool) {
		this.allowInput = bool;
	}

	public boolean isInputAllowed() {
		return this.allowInput;
	}
}