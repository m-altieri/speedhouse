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
	File fw; // utilizzato per passare il nome del file nei test 
	File fw1;
	File fw2;
	//Scanner p;
	ArrayList<String[]> righe; 
	ArrayList<String[]> righe1; 
	ArrayList<String[]> righe2;
	String [] array;
	String [] array1;
	String [] array2;
	
	// utilizzati per i test estraiTipi
	String [] tipi;
	String [] tipi1;
	String [] tipi2;
	String [] tipi3;
	File fw4;
	File fw5;
	
	//utilizzati per i test estraiColonne
	String [] colonne1;

	
	@Before
	public void setUp() {
		//utilizzati per i test estraiRighe
		righe = new ArrayList<String[]>();
		fw = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\fileProva.txt");
		array = new String[3];
		array[0] = "marco";
		array[1] = "de vito";
		array[2] = "21";
		righe.add(array);
		
		righe1 = new ArrayList<String[]>();
		fw1 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\t2.txt");
		array1 = new String[3];
		array1[0] = "massi";
		array1[1] = "altieri";
		array1[2] = "20";
		righe1.add(array);
		righe1.add(array1);
		
		righe2 = new ArrayList<String[]>();
		array2 = new String[3];
		array2[0] = "marco";
		array2[1] = "devito";
		array2[2] = "21";
		righe2.add(array2);
		
		//utilizzati per i test estraiTipi
		fw4 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\FileTestestraiTipi.txt");
		fw5 = new File("C:\\Users\\Massi\\git\\speedhouse\\speedhouse\\src\\it\\speedhouse\\test\\FileTestestraiTipi2.txt");
		tipi = new String[2];
		tipi[0] = "int";
		tipi[1] = "int";
		
		tipi1 = new String[3];
		tipi1[0] = "string";
		tipi1[1] = "string";
		tipi1[2] = "int";
		
		tipi2 = new String[5];
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
		colonne1[2] = "età";
		
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
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(fw),righe), true);
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(fw1),righe1), true);
			
			// test 2
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(fw),righe1), false);
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(fw1),righe), false);
			
			//test 3
			Assert.assertEquals(Utility.equals(ServiziFile.estraiRighe(fw),righe2), false);
			
		
	}
	
	/**
	 * 
	 */
	@Test
	public void testEstraiTipi () throws FileNotFoundException
	{	
		
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(fw),tipi),false);
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(fw),tipi3),false);
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(fw4),tipi),true);
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(fw),tipi1),true);
			Assert.assertEquals(Utility.equals(ServiziFile.estraiTipi(fw5),tipi2),true);
			
		
		
	}
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	@Test
	public void testEstraiColonne() throws FileNotFoundException
	{
				Assert.assertEquals(Utility.equals(ServiziFile.estraiColonne(fw),colonne1),true);
				Assert.assertEquals(Utility.equals(ServiziFile.estraiColonne(fw5),colonne1),false);			
	}
}
