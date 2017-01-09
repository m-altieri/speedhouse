package it.speedhouse.main.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuFunzioni menuFunzioni;
	
	public BarraMenu(ActionListener f)
	{
		super();
		
		menuFunzioni = new MenuFunzioni(f);
		this.add(menuFunzioni);
		
	}
	
	public MenuFunzioni getMenuFunzioni()
	{
		return menuFunzioni;
	}
}
