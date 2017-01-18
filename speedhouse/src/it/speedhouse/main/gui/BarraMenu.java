package it.speedhouse.main.gui;

import javax.swing.JMenuBar;

/**
 * Barra dei menù presente in ogni classe derivata da Finestra.
 * Per sottoclassi di Finestra, per utilizzare BarraMenu occorre istanziarla e poi passarla al metodo setJMenuBar.
 * @see Finestra
 */
public class BarraMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private MenuFunzioni menuFunzioni;
	private Finestra asc;
	
	/**
	 * Crea una BarraMenu, già riempita di tutti i menù e sottomenù prevista.
	 * @param f	L'oggetto ActionListener che gestisce gli eventi scatenati dal menù, preferibilmente
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
	 * Ottieni il menù "Funzioni" contenuto in questa barra.
	 * @return	Il menù "Funzioni".
	 */
	public MenuFunzioni getMenuFunzioni()
	{
		return menuFunzioni;
	}

}
