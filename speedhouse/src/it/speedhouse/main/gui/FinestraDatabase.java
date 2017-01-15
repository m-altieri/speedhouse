package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.speedhouse.main.statics.ServiziDB;
import it.speedhouse.main.statics.ServiziFile;

public class FinestraDatabase extends Finestra implements ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SELEZIONATORE_WIDTH = 300;
	private static final int SELEZIONATORE_HEIGHT = 40;
	
	private String nomeDb;
	private JComboBox<String> selezionatore;
	private JLabel lblSeleziona;
	private JButton cmbVisualizza;
	private JPanel pannelloSuperiore;
	
	private boolean aggiornato;
	
	private JFileChooser fc;
	
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
		cmbVisualizza = new JButton("Visualizza");
		cmbVisualizza.setActionCommand("cmbVisualizza");
		cmbVisualizza.addActionListener(this);
		selezionatore.addFocusListener(this);
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setLayout(new FlowLayout());
		pannelloSuperiore.add(lblSeleziona);
		pannelloSuperiore.add(selezionatore);
		pannelloSuperiore.add(cmbVisualizza);
		
		this.add(pannelloSuperiore, BorderLayout.NORTH);
		
	}
	
	public String getNomeDb()
	{
		return nomeDb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "importaCsv":
			fc = new JFileChooser();
			fc.showOpenDialog(null);
			File file = fc.getSelectedFile();
			
			if (file != null) {

				aggiornato = false;
				this.impostaStato("Importazione csv...");
				this.update(getGraphics());
				lblSeleziona.setEnabled(false);
				cmbVisualizza.setEnabled(false);
				selezionatore.setEnabled(false);
				this.barraMenu.setEnabled(false);
				this.update(getGraphics());
				try {
					this.impostaStato("Creazione tabella...");
					this.update(getGraphics());
					//usa come nome della tabella il nome del csv ma senza le ultime 4 lettere (.csv)
					ServiziDB.creaTabella(this.nomeDb, ServiziFile.estraiTipi(file), file.getName().substring(0, file.getName().length() - 4), ServiziFile.estraiColonne(file));
					this.impostaStato("Inserimento dati...");
					this.update(getGraphics());
					ServiziDB.inserisciDati(this.nomeDb, file.getName().substring(0, file.getName().length() - 4), ServiziFile.estraiRighe(file));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
	
				this.impostaStato("Pronto");
				this.update(getGraphics());
				lblSeleziona.setEnabled(true);
				cmbVisualizza.setEnabled(true);
				selezionatore.setEnabled(true);
				this.barraMenu.setEnabled(true);
				this.update(getGraphics());
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
		cmbVisualizza.setEnabled(false);
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
		cmbVisualizza.setEnabled(true);
		selezionatore.setEnabled(true);
		this.barraMenu.setEnabled(true);
		this.update(getGraphics());
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		System.out.println("debug");
	}
}
