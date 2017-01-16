package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Index extends Finestra implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel speedhouse;
	private JTextArea help;
	private Font helpFont;
	
	public BarraMenu getBarraMenu()
	{
		return barraMenu;
	}
	
	public Index()
	{
		super();
		
		impostaStato("Pronto");
		barraMenu = new BarraMenu(this);
		this.setJMenuBar(barraMenu);
		
		speedhouse = new JLabel("speedhouse");
		speedhouse.setFont(new Font("Arial", Font.ITALIC, 90));
		this.add(speedhouse, BorderLayout.NORTH);
		
		help = new JTextArea();
		help.setLineWrap(true);
		help.setEditable(false);
		help.setBackground(getBackground());
		helpFont = new Font("Arial", Font.PLAIN, 20);
		
		help.setFont(helpFont);
		help.setText("\n\n\nHai bisogno di selezionare un database per iniziare."
				+ "\nSe non ne hai ancora creato uno, clicca sul menù Funzioni e poi su \"Crea database...\"."
				+ "\nSuccessivamente puoi selezionarlo dal menù Funzioni > Seleziona database."
				+ "\nDopodichè puoi importare dei file .csv andando su Funzioni > Importa file csv..."
				+ "\nInfine con \"Produci grafici\" potrai visualizzare istogrammi e altri grafici selezionando"
				+ "\ndelle colonne a scelta dalle tue tabelle.");
		this.add(help, BorderLayout.CENTER);
		
		// Finchè non seleziona un db le altre funzioni sono disabilitate
		barraMenu.getMenuFunzioni().forzaSelezioneDB();

		this.setVisible(true);
	}

	/**
	 * Gestisce gli eventi scatenati dalla barra dei menù.
	 * Apre una nuova finestra CreaDatabase se l'utente clicca su "Crea Database..." e apre 
	 * una nuova finestra FinestraDatabase se seleziona un database dall'elenco.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "creaDatabase":
			new CreaDatabase(this);
			break;
		default: 
			dispose();
			new FinestraDatabase(e.getActionCommand());
			break;
		}
	}
	
}
