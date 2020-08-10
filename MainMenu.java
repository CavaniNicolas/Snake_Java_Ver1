
import java.util.Scanner;
import java.util.ArrayList;

public class MainMenu {

	public static void main(String[] args) {
		
		Page rootPage = new Page("RootPage", "Back", new ArrayList<Page>());

		Page playPage = new Page("Play", new ArrayList<Page>());

		Page optionPage = new Page("Options", new ArrayList<Page>());

		Page option2Page = new Page("Options2", new ArrayList<Page>());

		Page quitPage = new Page("Quit", new ArrayList<Page>());

		rootPage.addPage(playPage);
		rootPage.addPage(optionPage);
		rootPage.addPage(quitPage);
		
		optionPage.addPage(option2Page);
		option2Page.addPage(optionPage);

		playPage.addPage(rootPage);
		optionPage.addPage(rootPage);
		quitPage.addPage(rootPage);

		playPage.setParentIndex(rootPage.getIndex());
		optionPage.setParentIndex(rootPage.getIndex());
		quitPage.setParentIndex(rootPage.getIndex());
		option2Page.setParentIndex(optionPage.getIndex());


		Menu menu = new Menu(rootPage);

		Scanner sc = new Scanner(System.in);
		String choice;

		while (!menu.doWeQuit()) {
			menu.displayCurrentPage();
			choice = sc.nextLine();
			menu.goToPage(choice);
		}
	}
}