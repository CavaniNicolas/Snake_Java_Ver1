import java.util.ArrayList;

public class Page {

	private String pageName; // Nom de la page
	private String buttonName; // reference, nom du bouton pour acceder a cette page (ce qu'il faut tapper)
	private ArrayList<Page> pagesLinked = new ArrayList<Page>(); // Liste des pages accessibles depuis cette page
	private Window window = null;

	private int index; // index unique de la page
	private int parentIndex; // index du parent
	private static int nbInstances = 0; // nombre total de pages

	public Page(String pageName, String buttonName, ArrayList<Page> pagesLinked) {
		this.pageName = pageName;
		this.buttonName = buttonName;

		this.pagesLinked = pagesLinked;

		this.index = nbInstances;
		nbInstances++;

		this.window = new Window(pageName);
	}

	public Page(String pageName, ArrayList<Page> pagesLinked) {
		this(pageName, pageName, pagesLinked);
	}

	public void addPage(Page newPage) {
		this.pagesLinked.add(newPage);
	}

	/**Affiche la page */
	public void displayPage() {
		String name = this.pageName;
		if (this.window != null) {
			name = this.window.getName();
		}

		System.out.println(" _____________");
		System.out.println(" | Page Name : " + this.window.getName());
		System.out.println(" | Boutons : ");
		for (int i=0; i<pagesLinked.size(); i++) {
			System.out.println(" |    " + pagesLinked.get(i).getButtonName());
		}
		System.out.println(" |____________\n");
	}



	/**Renvoie l'index de la page vers laquelle on peut aller depuis cette page.
	 * @return -1 si la page n'est pas atteignable a partir de la currentPage
	 */
	public int getPageIndex(String button) {
		int index = -1;

		int i = 0;
		while (index == -1 && i<pagesLinked.size()) {
			if (button.equals(pagesLinked.get(i).getButtonName())) {
				index = pagesLinked.get(i).getIndex();
			}
			i++;
		}

		return index;
	}


	public ArrayList<Page> getPagesLinked() {
		return this.pagesLinked;
	}
	
	public String getPageName() {
		return this.pageName;
	}
	
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	public String getButtonName() {
		return this.buttonName;
	}
	
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public int getnbPages() {
		return nbInstances;
	}

	public int getParentIndex() {
		return this.parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public void setWindowName(String name) {
		this.window.setName(name);
	}

}