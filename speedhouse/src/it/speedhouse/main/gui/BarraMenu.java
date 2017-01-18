package it.speedhouse.main.gui;

import javax.swing.JMenuBar;

/**
 * Barra dei men� presente in ogni classe derivata da Finestra.
 * Per sottoclassi di Finestra, per utilizzare BarraMenu occorre istanziarla e poi passarla al metodo setJMenuBar.
 * @see Finestra
 */
public class BarraMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private MenuFunzioni menuFunzioni;
	private Finestra asc;
	
	/**
	 * Crea una BarraMenu, gi� riempita di tutti i men� e sottomen� prevista.
	 * @param f	L'oggetto ActionListener che gestisce gli eventi scatenati dal men�, preferibilmente
	 * la finestra contenitore di questo oggetto.
	 */
	public BarraMenu(Finestra f)
	{
		super();
		
		asc = f;
		
		menuFunzioni = new MenuFunzioni(asc);
		this.add(menuFunzioni);
		menuFunzioni.setActionCommand("menuFunzioni");		
		
	}
	
	/**
	 * Ottieni il men� "Funzioni" contenuto in questa barra.
	 * @return	Il men� "Funzioni".
	 */
	public MenuFunzioni getMenuFunzioni()
	{
		return menuFunzioni;
	}

}
