
import java.util.ArrayList;

public class Menu {

	private Page mainPage; // Page racine
	private Page currentPage; // Page que l'on affiche
	private boolean doWeQuit = false; // est ce quon quitte le programme ?

	public Menu() {

		this.mainPage = new Page("MainPage", new ArrayList<Page>());
		this.currentPage = mainPage;

		Page playPage = new Page("Play", new ArrayList<Page>());
		Page optionPage = new Page("Options", new ArrayList<Page>());
		Page quitPage = new Page("Quit", new ArrayList<Page>());

		mainPage.addPage(playPage);
		mainPage.addPage(optionPage);
		mainPage.addPage(quitPage);

		playPage.addPage(mainPage);
		optionPage.addPage(mainPage);
		quitPage.addPage(mainPage);
	}

	public Menu(Page mainPage) {
		this.mainPage = mainPage;
		this.currentPage = mainPage;
	}

	public void displayCurrentPage() {
		this.currentPage.displayPage();
	}

	public boolean doWeQuit() {
		return this.doWeQuit;
	}

	public Page getCurrentPage() {
		return this.currentPage;
	}

	public void goToPage(String choice) {
		int index = -1;

		ArrayList<Page> pagesLinked = currentPage.getPagesLinked();

		int i = 0;
		while (index == -1 && i<pagesLinked.size()) {
			if (choice.equals(pagesLinked.get(i).getName())) {
				index = pagesLinked.get(i).getIndex();
			}
			i++;
		}

		if (index != -1) {
			i--;
			this.currentPage = pagesLinked.get(i);
			changingPageListener();
		}
	}


	public void changingPageListener() {
		if (this.currentPage.getName() == "Play") {
			MyWindow window = new MyWindow(24, 24, 30);
			window.startGame();
			this.doWeQuit = true;
		}
		if (this.currentPage.getName() == "Quit") {
			this.doWeQuit = true;
		}
	}
}