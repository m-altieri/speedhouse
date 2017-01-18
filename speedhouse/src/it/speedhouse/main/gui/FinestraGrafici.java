package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.speedhouse.main.statics.ServiziDB;
import it.speedhouse.main.statics.ServiziGrafici;

/**
 * Classe componente dell'interfaccia grafica dedicata alla creazione di grafici effettuando operazioni sui dati inseriti.
 */
public class FinestraGrafici extends Finestra implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private String database;
	private String tabella;
	private JLabel lblDatabase;
	private JLabel lblTabella;
	private JPanel north;
	
	private JButton cmbIstogramma;
	private JLabel lblIstogramma;
	private JPanel center;
	private JCheckBox[] cb;
	private JPanel checkboxGroup;
	private ArrayList<String> colonne;
	private ArrayList<String> colonneSelezionate;
	private int nColonne;
	private JPanel grafici;
	
	private JButton indietro;
	
	/**
	 * Crea la finestra per la creazione di grafici, inizializza tutti i suoi componenti e prepara l'ambiente.
	 * @param database Il database da cui estrarre i dati.
	 * @param tabella La tabella da cui estrarre i dati.
	 */
	public FinestraGrafici(String database, String tabella)
	{
		super();
		
		this.setTitle("speedhouse - " + database);
		this.barraMenu = new BarraMenu(this);
		this.setJMenuBar(barraMenu);
		barraMenu.getMenuFunzioni().setEnabled(false);
		this.database = database;
		this.tabella = tabella;
		
		impostaStato("Pronto");		
		this.paintAll(getGraphics());
		
		lblDatabase = new JLabel();
		lblTabella = new JLabel();
		lblDatabase.setText("Database: " + this.database);
		lblTabella.setText("Tabella: " + this.tabella);
		
		north = new JPanel();
		north.setLayout(new BorderLayout());
		north.add(lblDatabase, BorderLayout.NORTH);
		north.add(lblTabella, BorderLayout.SOUTH);
		
		this.add(north, BorderLayout.NORTH);

		center = new JPanel();
		center.setLayout(new BorderLayout());
		
		this.impostaStato("Estrazione struttura della tabella...");
		this.paintAll(getGraphics());
		
		this.nColonne = ServiziDB.getNumeroColonne(this.database, this.tabella);
		cb = new JCheckBox[nColonne];
		this.impostaStato("Pronto");
		
		checkboxGroup = new JPanel();
		checkboxGroup.setLayout(new FlowLayout());
		
		colonne = ServiziDB.estraiColonne(database, tabella);
		
		for (int i = 0; i < nColonne; i++) {
			cb[i] = new JCheckBox(colonne.get(i), false);
			cb[i].addActionListener(this);
			cb[i].setActionCommand(cb[i].getText());
			checkboxGroup.add(cb[i]);
		}
		
		colonneSelezionate = new ArrayList<String>();
		
		
		center.add(checkboxGroup, BorderLayout.NORTH);
		
		cmbIstogramma = new JButton();
		cmbIstogramma.setText("Istogramma");
		cmbIstogramma.setActionCommand("Istogramma");
		cmbIstogramma.addActionListener(this);
		lblIstogramma = new JLabel();
		lblIstogramma.setText("Crea un istogramma con le colonne selezionate.");
		
		grafici = new JPanel();
		grafici.setLayout(new FlowLayout());
		grafici.add(cmbIstogramma, 0);
		grafici.add(lblIstogramma, 1);
		
		center.add(grafici, BorderLayout.CENTER);
		

		indietro = new JButton("Indietro");
		indietro.addActionListener(this);
		indietro.setActionCommand("Indietro");
		JPanel pulsanteIndietro = new JPanel();
		pulsanteIndietro.setLayout(new FlowLayout());
		pulsanteIndietro.add(indietro);
		
		center.add(pulsanteIndietro, BorderLayout.SOUTH);
		
		this.add(center, BorderLayout.CENTER);
		
	}

	/**
	 * Azioni eseguite quando si clicca sui pulsanti di creazione, quando si attivano o disattivano le checkbox, 
	 * o quando si selezionano opzioni del men�.
	 * @see ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Istogramma":
			this.impostaStato("Creazione grafico in corso...");
			this.paintAll(getGraphics());
			ServiziGrafici.creaIstogramma("Database: " + database, "Tabella: " + tabella, colonneSelezionate, ServiziDB.selezionaColonne(database, tabella, colonneSelezionate));
			System.out.println(colonneSelezionate); //debug
			this.impostaStato("Pronto");
			this.paintAll(getGraphics());
			break;
		case "Indietro":
			new FinestraDatabase(database);
			dispose();
			break;
		default:
			for (int i = 0; i < cb.length; i++) {
				if (e.getActionCommand().equals(cb[i].getActionCommand())) {
					if (cb[i].isSelected())
						this.colonneSelezionate.add(cb[i].getText());
					else
						this.colonneSelezionate.remove(cb[i].getText());
				}
			}
			break;
		}
	}

}
