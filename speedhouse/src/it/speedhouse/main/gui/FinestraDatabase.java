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

	private static final int SELEZIONATORE_WIDTH = 300;
	private static final int SELEZIONATORE_HEIGHT = 40;
	
	private String nomeDb;
	private JComboBox<String> selezionatore;
	private JLabel lblSeleziona;
	private JButton cmbSeleziona;
	private JPanel pannelloSuperiore;
	private JLabel caricamento;
	
	private boolean aggiornato;
	
	private JFileChooser fc;
	
	public FinestraDatabase(String nomeDb)
	{
		super();
		
		this.nomeDb = nomeDb;
		this.setTitle("speedhouse - " + this.nomeDb);
		
		this.barraMenu = new BarraMenu(this);
		this.setJMenuBar(barraMenu);
		
		caricamento = new JLabel("     Caricamento...");
		caricamento.setFont(new Font("Default", Font.PLAIN, 22));
		
		lblSeleziona = new JLabel("Seleziona tabella");
		selezionatore = new JComboBox<String>();
		selezionatore.setPreferredSize(new Dimension(SELEZIONATORE_WIDTH, SELEZIONATORE_HEIGHT));
		cmbSeleziona = new JButton("Visualizza");
		cmbSeleziona.setActionCommand("cmbSeleziona");
		cmbSeleziona.addActionListener(this);
		selezionatore.addFocusListener(this);
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setLayout(new FlowLayout());
		pannelloSuperiore.add(lblSeleziona);
		pannelloSuperiore.add(selezionatore);
		pannelloSuperiore.add(cmbSeleziona);
		pannelloSuperiore.add(caricamento);
		
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
				caricamento.setVisible(true);
				lblSeleziona.setEnabled(false);
				cmbSeleziona.setEnabled(false);
				selezionatore.setEnabled(false);
				this.update(getGraphics());
				try {
					//usa come nome della tabella il nome del csv ma senza le ultime 4 lettere (.csv)
					ServiziDB.creaTabella(this.nomeDb, ServiziFile.estraiTipi(file), file.getName().substring(0, file.getName().length() - 4), ServiziFile.estraiColonne(file)); 
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
	
				caricamento.setVisible(false);
				lblSeleziona.setEnabled(true);
				cmbSeleziona.setEnabled(true);
				selezionatore.setEnabled(true);
				this.update(getGraphics());
			}
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

	@Override
	public void focusGained(FocusEvent e) {
			
		if (aggiornato)
			return;
		
		try {
			barraMenu.getMenuFunzioni().aggiornaDB();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		caricamento.setVisible(true);
		lblSeleziona.setEnabled(false);
		cmbSeleziona.setEnabled(false);
		selezionatore.setEnabled(false);
		this.update(getGraphics());

		System.out.println("debug");
		ArrayList<String> tabelle = ServiziDB.ottieniTabelle(this.nomeDb);
		selezionatore.removeAllItems();
		for (String t : tabelle) {
			selezionatore.addItem(t);
		}
		aggiornato = true;
		
		caricamento.setVisible(false);
		lblSeleziona.setEnabled(true);
		cmbSeleziona.setEnabled(true);
		selezionatore.setEnabled(true);
		this.update(getGraphics());
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		System.out.println("debug");
	}
}
