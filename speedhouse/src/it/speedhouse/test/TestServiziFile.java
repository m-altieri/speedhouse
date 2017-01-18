package it.speedhouse.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.speedhouse.main.statics.ServiziFile;
import junit.framework.Assert;

@SuppressWarnings("deprecation")

public class TestServiziFile {
	
	// utilizzati per i test estraiRighe
	File file0; // utilizzato per passare il nome del file nei test 
	File file1;
	File file2;

	ArrayList<String[]> righe; 
	ArrayList<String[]> righe1; 
	ArrayList<String[]> righe2;
	
	//risultati attesi, che confronteremo con i risultati reali delle funzioni testate
	String [] array = {"Marco", "De Vito", "21"};
	String [] array1 = {"Massimiliano", "Altieri", "20"};
	String [] array2 = {"Emanuele", "Stea", "21"};
	
	// utilizzati per i test estraiTipi
	String [] tipi;
	String [] tipi1;
	String [] tipi2;
	String [] tipi3;
	File file4;
	File file5;
	
	//utilizzati per i test estraiColonne
	String [] colonne1;

	
	@Before
	public void setUp() {
		//utilizzati per i test estraiRighe
		righe = new ArrayList<String[]>();	//un ArrayList contenente array di stringhe, in cui inseriremo i record all'interno del file
		file0 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\fileProva.txt");	//File che contiene una sola riga oltre all'intestazione
		
		righe.add(array);	//"Marco", "De Vito", "21"
		
		righe1 = new ArrayList<String[]>();
		file1 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\t2.txt");	//file contenente due righe oltre all'intestazione
		
		righe1.add(array);	//array corrispondente alla prima riga del file t2.txt (non tenendo conto dell'intestazione)
		righe1.add(array1);	//array corrispondente alla seconda riga del file t2.txt
		
		righe2 = new ArrayList<String[]>();	
		righe2.add(array2);	
		
		//utilizzati per i test estraiTipi
		file4 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\FileTestestraiTipi.txt");	//contiene 2 numeri interi
		file5 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\FileTestestraiTipi2.txt");	//contiene 2 numeri decimali e 3 interi
		tipi = new String[2];	//contiene i tipi presenti nel primo file
		tipi[0] = "int";
		tipi[1] = "int";
		
		tipi1 = new String[3];
		tipi1[0] = "string";
		tipi1[1] = "string";
		tipi1[2] = "int";
		
		tipi2 = new String[5];	//contiene i tipi presenti nel secondo file
		tipi2[0] = "decimal";
		tipi2[1] = "int";
		tipi2[2] = "decimal";
		tipi2[3] = "int";
		tipi2[4] = "int";
		
		tipi3 = new String[3];
		tipi3[0] = "string";
		tipi3[1] = "string";
		tipi3[2] = "decimal";
		
		colonne1 = new String[3];
		colonne1[0] = "nome";
		colonne1[1] = "cognome";
		colonne1[2] = "eta";
		
	}
	
	@After
	public void tearDown() 
	{
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testEstraiRighe() throws FileNotFoundException
	{	
	
			// test 1
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(file0),righe), true);	//viene confrontato il risultato della funzione estraiRighe, con righe, il quale contiene le righe all'interno del file
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(file1),righe1), true);	//le righe estratte attraverso la funzione estraiRighe, corrispondono con quelle all'interno di file1, che sono precedentemente state caricate in righe1
			
			// test 2
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(file0),righe1), false);	//righe1 contiene una riga in più rispetto a file0
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(file1),righe), false);	//righe contiene una riga, file1 ne contiene due
			
			//test 3
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(file0),righe2), false);
			
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testEstraiTipi () throws FileNotFoundException
	{	
		
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(file0),tipi),false);	//file0 contiene tre valori di tipo String, tipi 2 di tipo int
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(file0),tipi3),false);	//Contengono entrambi tre valori ma di tipo diverso
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(file4),tipi),true);	//contengono entrambi due valori di tipo int
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(file0),tipi1),true);	//contengono valori dello stesso tipo
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(file5),tipi2),true);	//contengono lo stesso tipo di valori
			
		
		
	}
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void testEstraiColonne() throws FileNotFoundException
	{
				Assert.assertEquals(Utility.equals(ServiziFile.estraiColonne(file0),colonne1),true);	//file0 contiene le colonne scritte nell'array colonne1
				Assert.assertEquals(Utility.equals(ServiziFile.estraiColonne(file5),colonne1),false);	//file5 contiene più colonne rispetto a quelle in colonne1
	}
}
