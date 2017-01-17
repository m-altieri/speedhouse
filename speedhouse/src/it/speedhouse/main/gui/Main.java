package it.speedhouse.main.gui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Main {
	
	public static void main(String[] args)
	{
		primoAvvio();
		
		Index finestra = new Index();
		finestra.setVisible(true);
	}
	
	// Crea il file databases.txt contenente i nomi dei database creati.
	public static void primoAvvio()
	{
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream("databases.txt", true));
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
