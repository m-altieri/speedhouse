package it.speedhouse.main.statics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe astratta contenente servizi statici per operare sui file csv.
 * Per funzionare, i file devono rispettare il seguente standard: 
 * colonne separate da ";", righe separate da "\n", cifre unitarie e decimali separate da ".".
 * @author Altieri Massimiliano
 *
 */
public abstract class ServiziFile {

	/**
	 * Estrae tutto il contenuto di un file csv.
	 * @param file L'oggetto File contenente il percorso del file csv.
	 * @return Un oggetto ArrayList<String[]> contenente tutte le righe disposte come un array bidimensionale.
	 * @throws FileNotFoundException
	 */
	public static ArrayList<String[]> estraiRighe(File file) throws FileNotFoundException
	{
		ArrayList<String[]> righe = new ArrayList<String[]>();
		
		Scanner s = new Scanner(file);

		s.nextLine(); // Skippa la prima riga
		while (s.hasNextLine()) {
			righe.add(s.nextLine().replaceAll("\"", "").split(";"));
		}
	
		s.close();
	
		return righe;
	}
	
	/**
	 * Estrae i tipi delle colonne di un file csv.
	 * @param file L'oggetto File contenente il percorso del file csv.
	 * @return Un array di stringhe contenente i tipi delle colonne.
	 * @throws FileNotFoundException
	 */
	public static String[] estraiTipi(File file) throws FileNotFoundException
	{
		String[] tipi;
		
		Scanner s = new Scanner(file);
		s.nextLine(); //skippa la prima riga
		tipi = s.nextLine().replaceAll("\"", "").split(";");
		
		for (int i = 0; i < tipi.length; i++)
		{
			if (tipi[i].matches("[-+]?\\d+")) { // se è intero
				tipi[i] = "int";
			}
			else if (tipi[i].matches("[-+]?\\d*\\.?\\d+")) { // se è decimale
				tipi[i] = "decimal";
			}
			else // se è stringa
				tipi[i] = "string";
		}
		
		s.close();
		
		return tipi;
	}
	
	/**
	 * Estrae i nomi delle colonne da un file csv.
	 * @param file L'oggetto File contenente il percorso del file csv.
	 * @return Un array di stringhe contenente i nomi delle colonne.
	 * @throws FileNotFoundException
	 */
	public static String[] estraiColonne(File file) throws FileNotFoundException
	{
		String[] colonne;
		
		Scanner s = new Scanner(file);
		colonne = s.nextLine().replaceAll("\"", "").split(";"); //togli le virgolette
		s.close();
		
		return colonne;
	}
}
