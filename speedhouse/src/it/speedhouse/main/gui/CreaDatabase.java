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

public class CreaDatabase extends JFrame implements ActionListener {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 200;
	
	
	private JFrame asc;
	
	private JLabel nome;
	private JTextField txtNome;
	private JButton conferma;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "Conferma":
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileOutputStream("databases.txt", true));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			//pw.println(); //per andare a capo, risolve il bug in cui creando un nuovo db viene messo accanto all'ultimo
			pw.println(txtNome.getText());
			pw.close();
			MenuFunzioni mf = (MenuFunzioni) asc.getJMenuBar().getMenu(0);
			try {
				mf.aggiornaDB();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mf.sbloccaImportazione();
			dispose();
			break;
		default:
			break;
		}
	}
}
