package it.speedhouse.test;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.speedhouse.main.statics.ServiziDB;
import junit.framework.Assert;

@SuppressWarnings("deprecation")

public class TestServiziDB {

	Connection conn = null;
	Statement stmt = null;
	String[] colonne;
	String[] tipi;

	@Before
	public void setUp() {
		colonne = new String[3];
		colonne[0] = "id";
		colonne[1] = "nome";
		colonne[2] = "cognome";

		tipi = new String[3];
		for (int i = 0; i < 3; tipi[i++] = "int");
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
	public void testCreaTabelle()
	{
		// Arrange
		String[] tabelle = {"nomeTabella"};

		// Act
		ServiziDB.creaTabella("dataBase3", tipi, "nomeTabella", colonne);

		// Assert
		Assert.assertTrue(Utility.equals(ServiziDB.ottieniTabelle("dataBase3"), tabelle));

		// Eliminazione tabella creata
		ServiziDB.eliminaTabella("dataBase3", "nomeTabella");
	}

	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testOttieniTabelle() throws Exception
	{
		// Arrange
		ServiziDB.creaTabella("dbtest", tipi, "tabellatest", colonne);

		// Act
		String[] tabelle = new String[1];
		tabelle[0] = "tabellatest";
		String[] tabelle1 = new String[1];
		tabelle1[0] = "tabellaOrari";

		// Assert	
		Assert.assertTrue(Utility.equals(ServiziDB.ottieniTabelle("dbtest"), tabelle));
		Assert.assertFalse(Utility.equals(ServiziDB.ottieniTabelle("dbtest"), tabelle1));

		// Eliminazione database creato
		ServiziDB.eliminaTabella("dbtest", "tabellatest");
	}	

	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testInserisciDati()
	{
		// Arrange
		
		String db = "testdati";
		String nometabella = "nomeTabella";
		String[] nuovitipi = new String[3];
		ArrayList <String> nuovecolonne1 = new ArrayList<String>();
		for (int i = 0; i < 3; nuovitipi[i++] = "varchar(100)");
		// Act
		String[] nuovecolonne = {"nome", "cognome", "eta"};
		nuovecolonne1.add(nuovecolonne[0]);
		nuovecolonne1.add(nuovecolonne[1]);
		nuovecolonne1.add(nuovecolonne[2]);
		
		ServiziDB.creaTabella(db, nuovitipi, nometabella, nuovecolonne);
		String[] riga1 = {"mario", "rossi", "30"};
		String[] riga2 = {"giuseppe", "verdi", "40"};
		String[] riga3 = {"marcello", "marro", "11"};
		ArrayList<String[]> dati = new ArrayList<String[]>();
		dati.add(riga1);
		dati.add(riga2);
		dati.add(riga3);
		
		ServiziDB.inserisciDati(db, nometabella, dati);
		Assert.assertTrue(Utility.equals(ServiziDB.selezionaColonne(db, nometabella, nuovecolonne1), dati));
		dati.remove(2);
		Assert.assertFalse(Utility.equals(ServiziDB.selezionaColonne(db, nometabella, nuovecolonne1), dati));
		ServiziDB.eliminaTabella(db, nometabella);
	}

	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testSelezionaColonne()
	{
		String db = "testdati";
		String nometabella = "nomeTabella";
		String[] nuovitipi = new String[3];
		ArrayList <String> nuovecolonne1 = new ArrayList<String>();
		for (int i = 0; i < 3; nuovitipi[i++] = "varchar(100)");
		// Act
		String[] nuovecolonne = {"nome", "cognome", "eta"};
		nuovecolonne1.add(nuovecolonne[0]);
		nuovecolonne1.add(nuovecolonne[1]);
		nuovecolonne1.add(nuovecolonne[2]);
		
		ServiziDB.creaTabella(db, nuovitipi, nometabella, nuovecolonne);
		String[] riga1 = {"mario", "rossi", "30"};
		String[] riga2 = {"giuseppe", "verdi", "40"};
		String[] riga3 = {"marcello", "marro", "11"};
		ArrayList<String[]> dati = new ArrayList<String[]>();
		dati.add(riga1);
		dati.add(riga2);
		dati.add(riga3);
		
		ServiziDB.inserisciDati(db, nometabella, dati);
		Assert.assertTrue(Utility.equals(ServiziDB.selezionaColonne(db, nometabella, nuovecolonne1), dati));
		dati.remove(2);
		Assert.assertFalse(Utility.equals(ServiziDB.selezionaColonne(db, nometabella, nuovecolonne1), dati));
		ServiziDB.eliminaTabella(db, nometabella);
		
	}

	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testGetNumeroColonne() {
		String db = "testdati";
		String nometabella = "nomeTabella";
		String[] nuovitipi = new String[3];
		ArrayList <String> nuovecolonne1 = new ArrayList<String>();
		for (int i = 0; i < 3; nuovitipi[i++] = "decimal");
		// Act
		String[] nuovecolonne = {"nome", "cognome", "eta"};
		nuovecolonne1.add(nuovecolonne[0]);
		nuovecolonne1.add(nuovecolonne[1]);
		nuovecolonne1.add(nuovecolonne[2]);
		
		ServiziDB.creaTabella(db, nuovitipi, nometabella, nuovecolonne);
		Assert.assertTrue(ServiziDB.getNumeroColonne(db, nometabella) == nuovecolonne.length);
		Assert.assertFalse(ServiziDB.getNumeroColonne(db, nometabella) == nuovecolonne.length + 1);
		Assert.assertFalse(ServiziDB.getNumeroColonne(db, nometabella) == nuovecolonne.length - 1);
		Assert.assertFalse(ServiziDB.getNumeroColonne(db, nometabella) == - nuovecolonne.length);
		ServiziDB.eliminaTabella(db, nometabella);
	}

	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testEstraiColonne() {

		String db = "testdati";
		String nometabella = "nomeTabella";
		String[] nuovitipi = new String[3];
		ArrayList <String> nuovecolonne1 = new ArrayList<String>();
		for (int i = 0; i < 3; nuovitipi[i++] = "decimal");
		// Act
		String[] nuovecolonne = {"nome", "cognome", "eta"};
		nuovecolonne1.add(nuovecolonne[0]);
		nuovecolonne1.add(nuovecolonne[1]);
		nuovecolonne1.add(nuovecolonne[2]);
		
		ServiziDB.creaTabella(db, nuovitipi, nometabella, nuovecolonne);
		Assert.assertTrue(Utility.equals(ServiziDB.estraiColonne(db, nometabella) , nuovecolonne));
		ServiziDB.eliminaTabella(db, nometabella);
	}

	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testGetColonna() {
		String db = "testdati";
		String nometabella = "nomeTabella";
		String[] nuovitipi = new String[3];
		ArrayList <String> nuovecolonne1 = new ArrayList<String>();
		ServiziDB.eliminaTabella(db, nometabella);


		for (int i = 0; i < 3; nuovitipi[i++] = "decimal");
		// Act
		String[] nuovecolonne = {"nome", "cognome", "eta"};
		nuovecolonne1.add(nuovecolonne[0]);
		nuovecolonne1.add(nuovecolonne[1]);
		nuovecolonne1.add(nuovecolonne[2]);
		
		ServiziDB.creaTabella(db, nuovitipi, nometabella, nuovecolonne);
		Assert.assertTrue((("nome").equals(ServiziDB.getColonna(db, nometabella, 1))));
		Assert.assertFalse((("nomep").equals(ServiziDB.getColonna(db, nometabella, 1))));
		ServiziDB.eliminaTabella(db, nometabella);

	}	

}
