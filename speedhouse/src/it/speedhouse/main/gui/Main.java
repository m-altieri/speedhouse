package it.speedhouse.main.gui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Classe da cui parte l'esecuzione quando questo sistema viene eseguito come applicazione Java.
 * Predispone l'ambiente operativo e mostra la prima finestra da cui parte l'interazione con l'utente.
 * @author Altieri Massimiliano
 *
 */
public class Main {
	
	public static void main(String[] args)
	{
		primoAvvio();
		
		Index finestra = new Index();
		finestra.setVisible(true);
	}
	
	/**
	 * Metodo eseguito la prima volta che viene eseguito il programma su questo computer.
	 * Serve per predisporre l'ambiente operativo
	 * Crea il file databases.txt che conterrà i nomi di tutti i database creati.
	 */
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
