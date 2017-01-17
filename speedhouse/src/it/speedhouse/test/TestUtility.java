package it.speedhouse.test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
@SuppressWarnings("deprecation")

public class TestUtility {
	
	ArrayList<String[]> l1; 
	ArrayList<String[]> l2;
	ArrayList<String[]> l3;
	ArrayList<String[]> l4;
	ArrayList<String[]> l5;
	String[] array1 = {"aaa", "bbb", "ccc", "ddd", "eee"};
	String[] array2 = {"111", "222", "333", "444", "555"};
	String[] array3 = {"1", "2", "3", "4", "5", "6"};
	@Before
	public void setUp(){
		l1 = new ArrayList <String[]>(2);
		l2 = new ArrayList <String[]>(2);
		l3 = new ArrayList <String[]>(3);
		l4 = new ArrayList <String[]>(2);
		l5 = new ArrayList <String[]>(2);
		//setto gli ArrayList l1 e l2 uguali
		l1.add(array1);
		l1.add(array2);
		l2.add(array1);
		l2.add(array2);
		l4.add(array2);
		l4.add(array1);
		l5.add(array1);
		l5.add(array3);
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
	}
}
