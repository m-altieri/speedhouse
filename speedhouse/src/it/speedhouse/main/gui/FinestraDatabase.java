package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.speedhouse.main.statics.ServiziDB;
import it.speedhouse.main.statics.ServiziFile;

/**
 * Finestra dalla quale è possibile importare file, selezionare tra le tabelle inserite, produrre grafici.
 * E' una delle finestre principali.
 * @author Altieri Massimiliano
 */
public class FinestraDatabase extends Finestra implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	
	private static final int SELEZIONATORE_WIDTH = 300;
	private static final int SELEZIONATORE_HEIGHT = 40;
	
	private String nomeDb;
	private JComboBox<String> selezionatore;
	private JLabel lblSeleziona;
	private JPanel pannelloSuperiore;
	
	private boolean aggiornato;
	
	private JFileChooser fc;
	
	/**
	 * Crea la finestra, con tutti i suoi componenti, e la visualizza.
	 * @param nomeDb	Nome del database al quale si è acceduti.
	 */
	public FinestraDatabase(String nomeDb)
	{
		super();
		
		this.nomeDb = nomeDb;
		this.setTitle("speedhouse - " + this.nomeDb);
		
		this.barraMenu = new BarraMenu(this);
		this.setJMenuBar(barraMenu);
		
		this.impostaStato("Pronto");
		this.update(getGraphics());
		
		lblSeleziona = new JLabel("Seleziona tabella");
		selezionatore = new JComboBox<String>();
		selezionatore.setPreferredSize(new Dimension(SELEZIONATORE_WIDTH, SELEZIONATORE_HEIGHT));
		selezionatore.addFocusListener(this);
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setLayout(new FlowLayout());
		pannelloSuperiore.add(lblSeleziona);
		pannelloSuperiore.add(selezionatore);
		
		this.add(pannelloSuperiore, BorderLayout.NORTH);
		
	}
	
	/**
	 * Metodo get per ottenere il nome semplice del database attualmente utilizzato.
	 * @return Il nome del database al quale si è acceduti.
	 */
	public String getNomeDb()
	{
		return nomeDb;
	}

	/**
	 * Gestore degli eventi.
	 * @param e	Evento scatenato. Può essere una qualsiasi operazione effettuabile da questa finestra.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
		case "importaCsv":
			fc = new JFileChooser();
			fc.showOpenDialog(null);
			File file = fc.getSelectedFile();
			
			if (file != null) {
				
				aggiornato = false;
				this.impostaStato("Importazione csv...");
				this.update(getGraphics());
				lblSeleziona.setEnabled(false);
				selezionatore.setEnabled(false);
				this.barraMenu.setEnabled(false);
				this.paintAll(getGraphics());
				
				try {
					this.impostaStato("Creazione tabella...");
					this.paintAll(getGraphics());
					// Usa come nome della tabella il nome del csv ma senza le ultime 4 lettere (.csv)
					ServiziDB.creaTabella(this.nomeDb, ServiziFile.estraiTipi(file), file.getName().substring(0, file.getName().length() - 4), ServiziFile.estraiColonne(file));
					this.impostaStato("Inserimento dati...");
					this.paintAll(getGraphics());
					ServiziDB.inserisciDati(this.nomeDb, file.getName().substring(0, file.getName().length() - 4), ServiziFile.estraiRighe(file));
				} catch (FileNotFoundException f) {
					f.printStackTrace();
				}
	
				this.impostaStato("Pronto");
				this.paintAll(getGraphics());
				lblSeleziona.setEnabled(true);
				selezionatore.setEnabled(true);
				this.barraMenu.setEnabled(true);
				this.paintAll(getGraphics());
			}
			break;
		case "creaDatabase":
			new CreaDatabase(this);
			break;
		case "produciGrafici":
			dispose();
			FinestraGrafici fg = new FinestraGrafici(nomeDb, selezionatore.getSelectedItem().toString());
			fg.setVisible(true);
			break;
		case "cmbVisualizza":
			dispose();
			break;
		default: 
			dispose();
			new FinestraDatabase(e.getActionCommand());
			break;
		}
	}

	/**
	 * Istruzioni eseguite quando la finestra ottiene il focus.
	 * Per evitare bug, la finestra oltre ad acquisire il focus ha bisogno anche che la variabile aggiornato sia false, 
	 * altrimenti in alcuni casi la finestra rileva un continuo guadagno di focus e di conseguenza il programma viene 
	 * percepito come bloccato dall'utente.
	 */
	@Override
	public void focusGained(FocusEvent e) {
			
		if (aggiornato)
			return;
		impostaStato("Aggiornamento elenco database...");
		this.update(getGraphics());
		try {
			barraMenu.getMenuFunzioni().aggiornaDB();
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		}
		impostaStato("Pronto");
		this.update(getGraphics());
		lblSeleziona.setEnabled(false);
		selezionatore.setEnabled(false);
		this.barraMenu.setEnabled(false);
		this.update(getGraphics());

		System.out.println("debug");
		impostaStato("Ottenimento tabelle dal database...");
		this.update(getGraphics());
		ArrayList<String> tabelle = ServiziDB.ottieniTabelle(this.nomeDb);
		
		impostaStato("Pronto");
		this.update(getGraphics());
		selezionatore.removeAllItems();
		for (String t : tabelle) {
			selezionatore.addItem(t);
		}
		aggiornato = true;
		
		lblSeleziona.setEnabled(true);
		selezionatore.setEnabled(true);
		this.barraMenu.setEnabled(true);
		this.update(getGraphics());
	}

	/**
	 * Metodo con corpo nullo.
	 * E' stato inserito solo per rispettare il contratto dell'interfaccia FocusListener.
	 */
	@Override
	public void focusLost(FocusEvent arg0) {}
}
