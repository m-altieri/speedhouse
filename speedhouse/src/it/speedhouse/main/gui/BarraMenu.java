package it.speedhouse.main.gui;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JMenuBar;

public class BarraMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuFunzioni menuFunzioni;
	private MenuDatabase menuDatabase;
	private ActionListener asc;
	
	public BarraMenu(ActionListener f)
	{
		super();
		
		asc = f;
		
		menuFunzioni = new MenuFunzioni(asc);
		this.add(menuFunzioni);
		menuFunzioni.setActionCommand("menuFunzioni");
		
		menuDatabase = new MenuDatabase(asc);
		
		
	}
	
	public MenuFunzioni getMenuFunzioni()
	{
		return menuFunzioni;
	}
	
	public MenuDatabase getMenuDatabase()
	{
		return menuDatabase;
	}
}
