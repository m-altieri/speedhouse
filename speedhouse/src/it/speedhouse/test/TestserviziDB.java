package it.speedhouse.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.speedhouse.main.statics.ServiziDB;
import it.speedhouse.main.statics.ServiziFile;
import junit.framework.Assert;

@SuppressWarnings("deprecation")

public class TestserviziDB {
	
	Connection conn = null;
	Statement stmt = null;
	String[] tipi = {"int" , "decimal" , "string"};
	String[] colonne = {"Sezione", "Referendum" ,"Maschi" ,"Femmine", "Totale", "Si", "No", "Bianche", "Contestate" ,"Nulle"};
	String[] tipi1;
	
	@Before
	public void setUp() {
		
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
	public void testOttieniTabelle() throws Exception
	{
		ServiziDB.eliminaTabella("dbtest", "tabellatest");
		// Creazione database e tabella di test 
		String[] colonne = {"Sezione", "Referendum", "Maschi", "Femmine", "Totale", "Si", "No", "Bianche", "Contestate", "Nulle"};
		String[] tipi = new String[10];
		for (int i = 0; i < 10; tipi[i++] = "int");
		ServiziDB.creaTabella("dbtest", tipi, "tabellatest", colonne);
		
		// Test
		Assert.assertTrue(colonne.equals(ServiziDB.ottieniTabelle("dbtest")));
		
		// Eliminazione database creato
		ServiziDB.eliminaTabella("dbtest", "tabellatest");
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testCreaTabelle() {
		Assert.assertTrue(conn == null);
		Assert.assertTrue(stmt == null);
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testInserisciDati() {
		
		Assert.assertTrue(conn == null);
		Assert.assertTrue(stmt == null);
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void selezionaColonne() {
		
		Assert.assertTrue(conn == null);
		Assert.assertTrue(stmt == null);
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void getNumeroColonne() {
ServiziDB.ottieniTabelle("tete");
		
		Assert.assertTrue(conn == null);
		Assert.assertTrue(stmt == null);
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testEstraiColonne() {
		
		Assert.assertTrue(conn == null);
		Assert.assertTrue(stmt == null);
	}
	
	/**
	 * @throws FileNotFoundException 
	 *
	 */
	@Test
	public void testGetColonna() {
		
		Assert.assertTrue(conn == null);
		Assert.assertTrue(stmt == null);
	}	
	
}
