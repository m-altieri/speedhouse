package it.speedhouse.main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Finestra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BarraMenu barraMenu;


	public Finestra()
	{
		super();
				
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(800,600));
		this.setResizable(false);
		this.setLocation(300, 300);
		this.setTitle("speedhouse");
		this.setVisible(true);
	}
}
