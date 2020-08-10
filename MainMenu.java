
import java.util.Scanner;
import java.util.ArrayList;

public class MainMenu {

	public static void main(String[] args) {
		
		Page mainPage = new Page("Back", new ArrayList<Page>(), new Window("MainPage"));

		Page playPage = new Page("Play", new ArrayList<Page>());

		Page optionPage = new Page("Options", new ArrayList<Page>());

		Page quitPage = new Page("Quit", new ArrayList<Page>());

		mainPage.addPage(playPage);
		mainPage.addPage(optionPage);
		mainPage.addPage(quitPage);
		
		playPage.addPage(mainPage);
		optionPage.addPage(mainPage);
		quitPage.addPage(mainPage);

		Menu menu = new Menu(mainPage);

		Scanner sc = new Scanner(System.in);
		String choice;

		while (!menu.doWeQuit()) {
			menu.displayCurrentPage();
			choice = sc.nextLine();
			menu.goToPage(choice);
		}
	}
}