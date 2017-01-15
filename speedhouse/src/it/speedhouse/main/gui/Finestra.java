package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.ui.RefineryUtilities;

public class Finestra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BarraMenu barraMenu;
	JLabel lblStatus;


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
	
	protected void impostaStato(String s)
	{
		lblStatus.setText(s);
		lblStatus.update(getGraphics());
	}
}
