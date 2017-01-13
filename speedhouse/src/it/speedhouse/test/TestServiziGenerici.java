package it.speedhouse.test;
import it.speedhouse.main.statics.*;

import org.junit.*;

import it.speedhouse.main.statics.ServiziFile;
import junit.framework.Assert;
import it.speedhouse.main.statics.ServiziGenerici;
import it.speedhouse.main.statics.ServiziGenerici;
import it.speedhouse.main.statics.*
;public class TestServiziGenerici {

	public static final String intMax = "2147483647";
	public static final String intMin = "2147483647";
	
	@Before
	public void setUp() {

	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	public void testIsInteger()
	{
		//test base
		Assert.assertEquals(ServiziGenerici.isInteger("-2"), true);
		Assert.assertEquals(ServiziGenerici.isInteger("+2"), true);
		Assert.assertEquals(ServiziGenerici.isInteger("0"), true);
		Assert.assertEquals(ServiziGenerici.isInteger("2"), true);
		Assert.assertEquals(ServiziGenerici.isInteger("+0"), true);
		Assert.assertEquals(ServiziGenerici.isInteger("-0"), true);
		Assert.assertEquals(ServiziGenerici.isInteger("String prova"), false);
		
		//test numeri decimali
		Assert.assertEquals(ServiziGenerici.isInteger("2.2342453452"), false);
		Assert.assertEquals(ServiziGenerici.isInteger("+2.2"), false);
		Assert.assertEquals(ServiziGenerici.isInteger("-2678.2"), false);
		
		
		//caratteri di escape
		Assert.assertEquals(ServiziGenerici.isInteger(" "), false);
		Assert.assertEquals(ServiziGenerici.isInteger("\n"), false);
		Assert.assertEquals(ServiziGenerici.isInteger("\t"), false);
		Assert.assertEquals(ServiziGenerici.isInteger(""), false);
		
		
		//casi limite valore Integer
		Assert.assertEquals(ServiziGenerici.isInteger(intMax), true);
		Assert.assertEquals(ServiziGenerici.isInteger(intMin), true);
		
		
	}
	
	@Test
	public void testIsDecimal()
	{
		//test base
		Assert.assertEquals(ServiziGenerici.isDecimal("-10"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("+6"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("0"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("8"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("+0"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("-0"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("String prova"), false);

		//test numeri decimali
		Assert.assertEquals(ServiziGenerici.isDecimal("2.2342453452"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("+2678.00000000000009"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("+0.99"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("-2678.2"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("-2678.00000"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("-0.2"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("-0.0"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("-.0"), true);
		Assert.assertEquals(ServiziGenerici.isDecimal("6."), false);
	}	
	
	@Test
	public void testisString()
	{
		Assert.assertEquals(true, true);

	}
	
	@Test
	public void testWhatIs()
	{
		Assert.assertEquals(true, true);

	}
	
	
}
