import java.util.ArrayList;

public class Page {

	private String name; // reference, nom du bouton pour acceder a cette page
	private ArrayList<Page> pagesLinked = new ArrayList<Page>(); // Liste des pages accessibles depuis cette page
	private Window window = null;

	private int index; // index unique de la page
	private static int nbInstances = 0; // nombre total de pages

	public Page(String name, ArrayList<Page> pagesLinked) {
		this.name = name;
		this.pagesLinked = pagesLinked;

		this.index = nbInstances;
		nbInstances++;
	}

	public Page(String name, ArrayList<Page> pagesLinked, Window window) {
		this(name, pagesLinked);
		this.window = window;
	}

	public void addPage(Page newPage) {
		this.pagesLinked.add(newPage);
	}


	/**Renvoie l'index de la page vers laquelle on peut aller depuis cette page.
	 * @return -1 si la page n'est pas atteignable a partir de la currentPage
	 */
	public int getPageIndex(String button) {
		int index = -1;

		int i = 0;
		while (index == -1 && i<pagesLinked.size()) {
			if (button.equals(pagesLinked.get(i).getName())) {
				index = pagesLinked.get(i).getIndex();
			}
			i++;
		}

		return index;
	}

	/**Affiche la page */
	public void displayPage() {
		String name = this.name;
		if (this.window != null) {
			name = this.window.getName();
		}

		System.out.println(" _____________");
		System.out.println(" | Page Name : " + name);
		System.out.println(" | Boutons : ");
		for (int i=0; i<pagesLinked.size(); i++) {
			System.out.println(" |    " + pagesLinked.get(i).getName());
		}
		System.out.println(" |____________\n");
	}
	

	public ArrayList<Page> getPagesLinked() {
		return this.pagesLinked;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return this.index;
	}

	public int getnbPages() {
		return nbInstances;
	}

}