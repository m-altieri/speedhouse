package it.speedhouse.main.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * La piccola finestra che si apre quando si seleziona l'item "Crea database..." dal menù Funzioni.
 */
public class CreaDatabase extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 500;
	private static final int HEIGHT = 200;
	
	private JFrame asc;
	
	private JLabel nome;
	private JTextField txtNome;
	private JButton conferma;
	
	/**
	 * Crea la finestra di creazione del database.
	 * @param asc	La finestra ActionListener dalla quale si è generato questo oggetto.
	 */
	public CreaDatabase(JFrame asc)
	{
		this.asc = asc;
		
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("speedhouse - Crea database");
		this.getContentPane().setLayout(new FlowLayout());
		
		nome = new JLabel("Nome");
		txtNome = new JTextField();
		txtNome.setPreferredSize(new Dimension(100,30));
		conferma = new JButton("Conferma");
		conferma.setActionCommand("Conferma");
		conferma.addActionListener(this);
		
		
		this.getContentPane().add(nome);
		this.getContentPane().add(txtNome);
		this.getContentPane().add(conferma);
		
		this.setVisible(true);
	}

	/**
	 * Gestisce l'evento scatenato dal click sul tasto di conferma.
	 * Memorizza permanentemente il nome del database inserito nel campo di testo.
	 * @param e	L'evento scatenato dal tasto di conferma.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand())
		{
		case "Conferma":
			PrintWriter pw = null;
			try {
				// Apre lo stream di scrittura in append
				pw = new PrintWriter(new FileOutputStream("databases.txt", true));
			} catch (FileNotFoundException f) {
				f.printStackTrace();
			}
			pw.println(txtNome.getText());
			pw.close();
			MenuFunzioni mf = (MenuFunzioni) asc.getJMenuBar().getMenu(0);
			try {
				mf.aggiornaDB();
			} catch (FileNotFoundException f) {
				f.printStackTrace();
			}
			dispose();
			break;
		default:
			break;
		}
	}
}
