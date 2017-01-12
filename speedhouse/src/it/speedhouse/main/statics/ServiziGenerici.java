package it.speedhouse.main.statics;

public class ServiziGenerici {

	public static boolean isInteger(String s)
	{
		return s.matches("[-+]?\\d+");
	}
	
	public static boolean isDecimal(String s)
	{
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	public static boolean isString(String s)
	{
		return !s.matches("[-+]?\\d+") && !s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	public static boolean isNumber(String s)
	{
		return s.matches("[-+]?\\d+") || s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	/**
	 * Controlla se una stringa rappresenta un intero, un decimale, o una stringa.
	 * @param s
	 * @return int se è intero, decimal se è decimale e string se è una stringa.
	 */
	public static String whatIs(String s)
	{
		if (s.matches("[-+]?\\d+"))
			return "int";
		else if (s.matches("[-+]?\\d*\\.?\\d+"))
			return "decimal";
		else
			return "string";
	}
	
}

