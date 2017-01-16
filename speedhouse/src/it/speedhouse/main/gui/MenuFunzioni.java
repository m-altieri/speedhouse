package it.speedhouse.main.gui;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuFunzioni extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ActionListener asc;
	private JMenuItem importaCsv;
	private JMenuItem creaDatabase;
	private JMenu selezionaDatabase;
	private ArrayList<JMenuItem> databaseCreati;
	private JMenuItem produciGrafici;
	
	public MenuFunzioni(ActionListener f)
	{
		super();
		
		asc = f;
		
		this.setText("Funzioni");
		
		databaseCreati = new ArrayList<JMenuItem>();
		
		importaCsv = new JMenuItem();
		importaCsv.setText("Importa file csv...");
		importaCsv.setActionCommand("importaCsv");
		creaDatabase = new JMenuItem();
		creaDatabase.setText("Crea database...");
		creaDatabase.setActionCommand("creaDatabase");
		selezionaDatabase = new JMenu();
		selezionaDatabase.setText("Seleziona database");
		selezionaDatabase.setActionCommand("selezionaDatabase");
		produciGrafici = new JMenuItem();
		produciGrafici.setText("Produci grafici");
		produciGrafici.setActionCommand("produciGrafici");
		
		this.add(importaCsv);
		this.add(creaDatabase);
		this.add(selezionaDatabase);
		this.add(produciGrafici);
		
		importaCsv.addActionListener(f);
		creaDatabase.addActionListener(f);
		selezionaDatabase.addActionListener(f);
		produciGrafici.addActionListener(f);
		
	}
	
	/**
	 * all'inizio l'utente deve selezionare un database. se non l'ha fatto le altre funzioni non sono disponibili.
	 * @throws FileNotFoundException
	 */
	public void forzaSelezioneDB()
	{
		//abilita la funzione solo se esistono già database creati
		File databases = new File("databases.txt");
		Scanner s = null;
		try {
			s = new Scanner(databases);
		} catch (FileNotFoundException e) {
			;
		}
		if (s.hasNext())
			selezionaDatabase.setEnabled(true);
		else
			selezionaDatabase.setEnabled(false);
		
		//visualizza nel menu tutti i db creati
		while (s.hasNext())
			databaseCreati.add(new JMenuItem(s.nextLine()));
		for (JMenuItem e : databaseCreati) {
			selezionaDatabase.add(e);
			e.setActionCommand(e.getText());
			e.addActionListener(asc);
		}
		databaseCreati.clear();
		s.close();
		
		importaCsv.setEnabled(false);
		creaDatabase.setEnabled(true);
		produciGrafici.setEnabled(false);
	}
	
	public void sbloccaImportazione()
	{
		importaCsv.setEnabled(true);
		creaDatabase.setEnabled(true);
		produciGrafici.setEnabled(true);
		selezionaDatabase.setEnabled(true);
	}
	
	public void aggiornaDB() throws FileNotFoundException
	{
		//rimuovi prima tutti
		selezionaDatabase.removeAll();
		
		//visualizza nel menu tutti i db creati
		File databases = new File("databases.txt");
		Scanner s = new Scanner(databases);
		while (s.hasNext())
			databaseCreati.add(new JMenuItem(s.nextLine()));
		for (JMenuItem e : databaseCreati) {
			selezionaDatabase.add(e);
			e.setActionCommand(e.getText());
			e.addActionListener(asc);
		}
		databaseCreati.clear();
		s.close();
	}
	
	public ArrayList<JMenuItem> getDatabaseCreati()
	{
		return databaseCreati;
	}
}
