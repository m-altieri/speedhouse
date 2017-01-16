package it.speedhouse.test;

import org.junit.*;

import junit.framework.Assert;
import it.speedhouse.main.statics.ServiziGenerici;

@SuppressWarnings("deprecation")
public class TestServiziGenerici {


	public static final String intMax = "2147483647";
	public static final String intMin = "-2147483647";

	@Before
	public void setUp() {

	}
	
	@After
	public void tearDown() {
		
	}

	/**
	 * Testing del metodo isInteger appartenente alla classe ServiziGenerici del package it.speedhouse.main.statics.
	 * Precondizione: Deve essere passata una variabile di tipo stringa.
	 * Il metodo restituisce true se la variabile contiene un numero intero, mentre nel caso contrario restituisce false.
	 * 
	 * 
	 */
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
		Assert.assertEquals(ServiziGenerici.isInteger(Integer.toString(Integer.parseInt(intMax) + 1)), true);
		
		
		
	}
	
	/*
	 * Testing del metodo isDecimal appartenente alla classe ServiziGenerici del package it.speedhouse.main.statics.
	 * Precondizione: Deve essere passata una variabile di tipo stringa.
	 * Il metodo restituisce true se la variabile contiene un numero intero o decimale, mentre nel caso contrario restituisce false.
	 */
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
		
		//test casi limite
		Assert.assertEquals(ServiziGenerici.isDecimal(intMax),true);
		Assert.assertEquals(ServiziGenerici.isDecimal(intMin),true);
		Assert.assertEquals(ServiziGenerici.isInteger(Integer.toString(Integer.parseInt(intMax) + 1)), true);
	}	
	
	/*
	 * Testing del metodo isString appartenente alla classe ServiziGenerici del package it.speedhouse.main.statics.
	 * Precondizione: Deve essere passata una variabile di tipo stringa.
	 * Il metodo restituisce true se la variabile contiene un numero intero o decimale, mentre nel caso contrario restituisce false.
	 */
	@Test
	public void testIsString()
	{
		//test base
		Assert.assertEquals(ServiziGenerici.isString("Hello World!"), true);
		Assert.assertEquals(ServiziGenerici.isString("ç@45	qwe+ù§"), true);
		Assert.assertEquals(ServiziGenerici.isString(".5"), false);
		Assert.assertEquals(ServiziGenerici.isString("-9"), false);
		
		//test casi limite
		Assert.assertEquals(ServiziGenerici.isString(""), true);
		Assert.assertEquals(ServiziGenerici.isString("\n"), true);

	}
	
	/*
	 * Testing del metodo isNumber appartenente alla classe ServiziGenerici del package it.speedhouse.main.statics.
	 * Precondizione: Deve essere passata una variabile di tipo stringa.
	 * Il metodo restituisce true se la variabile contiene un numero intero o decimale, mentre nel caso contrario restituisce false.
	 */
	@Test
	public void testIsNumber()
	{
		//test base
		Assert.assertEquals(ServiziGenerici.isNumber("Hello World!"), false);
		Assert.assertEquals(ServiziGenerici.isNumber("2"), true);
		Assert.assertEquals(ServiziGenerici.isNumber("-678"), true);
		Assert.assertEquals(ServiziGenerici.isNumber("+5.19"), true);
		
		//test casi limite
		Assert.assertEquals(ServiziGenerici.isNumber(intMax), true);
		Assert.assertEquals(ServiziGenerici.isNumber(intMin), true);
		Assert.assertEquals(ServiziGenerici.isNumber(intMin + "." + intMax), true);

	}
	
	/*
	 * 
	 */
	@Test
	public void testWhatIs()
	{
		Assert.assertEquals(ServiziGenerici.whatIs("k"), "string");
		Assert.assertEquals(ServiziGenerici.whatIs(" "), "string");
		Assert.assertEquals(ServiziGenerici.whatIs(".5"), "decimal");
		Assert.assertEquals(ServiziGenerici.whatIs("+120"), "int");
	}
	
	
}


// da rivedere i commenti dei test perchè sono uguali a quelli dei metodi
