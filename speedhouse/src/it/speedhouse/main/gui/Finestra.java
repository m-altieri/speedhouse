package it.speedhouse.main.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Finestra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Finestra()
	{
		super();
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setTitle("speedhouse");
	}
}
