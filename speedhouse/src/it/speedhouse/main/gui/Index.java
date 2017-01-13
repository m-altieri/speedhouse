package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Index extends Finestra implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel databaseNonSelezionato;
		
	public BarraMenu getBarraMenu()
	{
		return barraMenu;
	}
	
	public Index()
	{
		super();
		
		barraMenu = new BarraMenu(this);
		this.setJMenuBar(barraMenu);
		
		databaseNonSelezionato = new JLabel("Non hai ancora selezionato un database...");
		this.add(databaseNonSelezionato, BorderLayout.CENTER);
		
//		try {
			barraMenu.getMenuFunzioni().forzaSelezioneDB();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //finchè non seleziona un db le altre funzioni sono disabilitate
		
		this.setVisible(true);
	}

	/**
	 * action listener delle funzioni della barra dei menù
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand()) {
		case "importaCsv":
			dispose();
			break;
		case "creaDatabase":
			new CreaDatabase(this);
			break;
		case "selezionaDatabase":
			dispose();
			break;
		case "produciGrafici":
			dispose();
			break;
		default: 
			dispose();
			new FinestraDatabase(e.getActionCommand());
			break;
		}
	}
	
	
}
