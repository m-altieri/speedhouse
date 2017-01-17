package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.ui.RefineryUtilities;

/**
 * Classe base di tutte le finestre principali del programma, in particolare di tutte le finestre contenenti la barra dei menù.
 * Fornisce il template generale.
 * @author Altieri Massimiliano
 */
public abstract class Finestra extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	protected BarraMenu barraMenu;
	JLabel lblStatus;

	/**
	 * Definisce gli aspetti generali che deve avere ogni finestra principale del programma, come il titolo, il layout,
	 * la barra dello stato del sistema, le dimensioni, e altro.
	 */
	public Finestra()
	{
		super();

		this.setTitle("speedhouse");
		this.setLayout(new BorderLayout());
		
		lblStatus = new JLabel();
		lblStatus.setFont(new Font("Default", Font.PLAIN, 22));
		this.add(lblStatus, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800,600));
		this.setResizable(false);
		RefineryUtilities.centerFrameOnScreen(this);
		this.setVisible(true);
	}
	
	/**
	 * Imposta lo stato del sistema, visualizzato nella barra in basso.
	 * In particolare, lo stato di "Pronto" indica che non è in corso nessuna attività ed è attesa l'attività dell'utente.
	 * @param s	Il nuovo stato del sistema.
	 */
	protected void impostaStato(String s)
	{
		lblStatus.setText(s);
		lblStatus.update(getGraphics());
	}
}
