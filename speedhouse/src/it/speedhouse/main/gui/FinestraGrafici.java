package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.speedhouse.main.statics.ServiziDB;

public class FinestraGrafici extends Finestra implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String database;
	private String tabella;
	private JLabel lblDatabase;
	private JLabel lblTabella;
	private JPanel north;
	
	private JButton cmbIstogramma;
	private JLabel lblIstogramma;
	private JButton cmbTorta;
	private JLabel lblTorta;
	private JPanel center;
	private JCheckBox[] cb;
	private JPanel checkboxGroup;
	private ArrayList<String> colonne;
	private int nColonne;
	private JPanel grafici;
	
	public FinestraGrafici(String database, String tabella)
	{
		super();
		
		this.setTitle("speedhouse - " + database);
		this.setJMenuBar(barraMenu);
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
		colonne = ServiziDB.estraiColonne(this.database, this.tabella);
		this.impostaStato("Pronto");
		
		checkboxGroup = new JPanel();
		checkboxGroup.setLayout(new FlowLayout());
		
		for (int i = 0; i < nColonne; i++) {
			cb[i] = new JCheckBox(colonne.get(i), false);
			checkboxGroup.add(cb[i]);
		}
		
		center.add(checkboxGroup, BorderLayout.NORTH);
		
		cmbIstogramma = new JButton();
		cmbIstogramma.setText("Istogramma");
		cmbIstogramma.setActionCommand("Istogramma");
		cmbIstogramma.addActionListener(this);
		lblIstogramma = new JLabel();
		lblIstogramma.setText("Crea un istogramma con le colonne selezionate.");
		
		cmbTorta = new JButton();
		cmbTorta.setText("Grafico a torta");
		cmbTorta.setActionCommand("Torta");
		cmbTorta.addActionListener(this);
		lblTorta = new JLabel();
		lblTorta.setText("Crea un grafico a torta con le colonne selezionate");
		
		grafici = new JPanel();
		grafici.setLayout(new FlowLayout());
		grafici.add(cmbIstogramma, 0);
		grafici.add(lblIstogramma, 1);
		grafici.add(cmbTorta, 0);
		grafici.add(lblTorta, 1);
		
		center.add(grafici, BorderLayout.CENTER);
		
		this.add(center, BorderLayout.CENTER);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Istogramma":
			
			break;
		case "Torta":
			break;
		default:
			break;
		}
	}

}
