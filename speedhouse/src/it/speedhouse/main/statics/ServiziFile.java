package it.speedhouse.main.statics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiziFile {

	public static ArrayList<String[]> estraiRighe(File file) throws FileNotFoundException
	{
		ArrayList<String[]> righe = new ArrayList<String[]>();
		
		Scanner s = new Scanner(file);
		s.nextLine(); //skippa la prima riga
		while (s.hasNextLine()) {
			righe.add(s.nextLine().replaceAll("\"", "").split(";"));
		}
		
		s.close();
		
		return righe;
	}
	
	public static String[] estraiTipi(File file) throws FileNotFoundException
	{
		String[] tipi;
		
		Scanner s = new Scanner(file);
		s.nextLine(); //skippa la prima riga
		tipi = s.nextLine().replaceAll("\"", "").split(";");
		
		for (int i = 0; i < tipi.length; i++)
		{
			if (tipi[i].matches("[-+]?\\d+")) { // se � intero
				tipi[i] = "int";
			}
			else if (tipi[i].matches("[-+]?\\d*\\.?\\d+")) { // se � decimale
				tipi[i] = "decimal";
			}
			else // se � stringa
				tipi[i] = "string";
		}
		
		s.close();
		
		return tipi;
	}
	
	public static String[] estraiColonne(File file) throws FileNotFoundException
	{
		String[] tipi;
		
		Scanner s = new Scanner(file);
		tipi = s.nextLine().replaceAll("\"", "").split(";"); //togli le virgolette
		s.close();
		
		return tipi;
	}
}