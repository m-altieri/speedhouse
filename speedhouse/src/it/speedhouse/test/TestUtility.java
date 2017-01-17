package it.speedhouse.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
@SuppressWarnings("deprecation")

public class TestUtility {
	
	//ArrayList e array utilizzati per i test
	ArrayList<String[]> l1; 
	ArrayList<String[]> l2;
	ArrayList<String[]> l3;
	ArrayList<String[]> l4;
	ArrayList<String[]> l5;
	ArrayList<String> l6;
	String[] array1 = {"aaa", "bbb", "ccc", "ddd", "eee"};
	String[] array2 = {"111", "222", "333", "444", "555"};
	String[] array3 = {"1", "2", "3", "4", "5", "6"}; //dimensione diversa dalle altre
	
	@Before
	public void setUp(){
		l1 = new ArrayList <String[]>(2);
		l2 = new ArrayList <String[]>(2);
		l3 = new ArrayList <String[]>(3);	//dimensione diversa rispetto agli altri ArrayList
		l4 = new ArrayList <String[]>(2);
		l5 = new ArrayList <String[]>(2);
		l6 = new ArrayList <String>();
		//setto gli ArrayList l1 e l2 uguali
		l1.add(array1);
		l1.add(array2);
		l2.add(array1);
		l2.add(array2);
		//l4 diverso da l1 e l2
		l4.add(array2);
		l4.add(array1);
		//l5 contiene un array di dimensione diversa dagli altri
		l5.add(array1);
		l5.add(array3);
		//l6 è un ArrayList di stringhe che ci serve per testare il confronto tra un ArrayList di stringhe e un array di stringhe
		l6.add("111");
		l6.add("222");
		l6.add("333");
		l6.add("444");
		l6.add("555");

		
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void testEquals1() throws FileNotFoundException
	{	
			// test 1
			Assert.assertEquals(Utility.equals(l1, l2), true);
			Assert.assertEquals(Utility.equals(l1, l3), false);
			Assert.assertEquals(Utility.equals(l1, l4), false);
			Assert.assertEquals(Utility.equals(l1, l5), false);
			Assert.assertEquals(Utility.equals(l6, array2), true);
			Assert.assertEquals(Utility.equals(l6, array1), false);
			Assert.assertEquals(Utility.equals(l6, array3), false);
	}
}
