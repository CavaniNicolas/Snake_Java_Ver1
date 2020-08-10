
import java.util.ArrayList;

public class Menu {

	private Page rootPage; // Page racine
	private Page currentPage; // Page que l'on affiche
	private boolean doWeQuit = false; // est ce quon quitte le programme ?

	public Menu() {

		this.rootPage = new Page("RootPage", new ArrayList<Page>());
		this.currentPage = rootPage;

		Page playPage = new Page("Play", new ArrayList<Page>());
		Page optionPage = new Page("Options", new ArrayList<Page>());
		Page quitPage = new Page("Quit", new ArrayList<Page>());

		rootPage.addPage(playPage);
		rootPage.addPage(optionPage);
		rootPage.addPage(quitPage);

		playPage.addPage(rootPage);
		optionPage.addPage(rootPage);
		quitPage.addPage(rootPage);
	}

	public Menu(Page rootPage) {
		this.rootPage = rootPage;
		this.currentPage = rootPage;
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

		this.currentPage.setButtonName(this.currentPage.getPageName());

		int index = -1;
		ArrayList<Page> pagesLinked = currentPage.getPagesLinked();

		int i = 0;
		while (index == -1 && i<pagesLinked.size()) {
			if (choice.equals(pagesLinked.get(i).getButtonName())) {
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
		if (this.currentPage.getPageName() == "Play") {
			MyWindow window = new MyWindow(24, 24, 30);
			window.startGame();
			this.doWeQuit = true;
		}
		if (this.currentPage.getPageName() == "Quit") {
			this.doWeQuit = true;
		}

		updateButtonsName();
	}

	public void updateButtonsName() {
		ArrayList<Page> pagesLinked = this.currentPage.getPagesLinked();

		boolean isFound = false;

		int i = 0;
		while (!isFound && i<pagesLinked.size()) {
			if (this.currentPage.getParentIndex() == pagesLinked.get(i).getIndex()) {
				pagesLinked.get(i).setButtonName("Back");
				isFound = true;
			}
			i++;
		}

	}
}