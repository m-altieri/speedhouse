package it.speedhouse.main.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuDatabase extends JMenu {
	
	private ActionListener asc;
	private JMenu visualizza;
	private JMenuItem creaTabella;
	private JMenuItem aggiungiCampo;
	private JMenuItem aggiungiTupla;
	private JMenuItem modifica;
	private JMenuItem elimina;
	
	
	public MenuDatabase(ActionListener f)
	{
		super();
		
		asc = f;
		
		this.setText("Database");
		
		visualizza = new JMenu();
		visualizza.setText("Visualizza tabella");
		visualizza.setActionCommand("visualizza");
		creaTabella = new JMenuItem();
		
	}

}
