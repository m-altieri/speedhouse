package it.speedhouse.main.statics;

/**
 * Classe astratta contenente metodi statici di utilizzo generico.
 * @author Altieri Massimiliano
 *
 */
public abstract class ServiziGenerici {

	/**
	 * Determina se la stringa passata rappresenta un intero.
	 * Può essere anche negativo.
	 * Non è necessario che la variabile sia compatibile col tipo int per essere considerata un intero
	 * @param s - La stringa da verificare.
	 * @return true se s rappresenta un intero, false altrimenti.
	 */
	public static boolean isInteger(String s)
	{
		return s.matches("[-+]?\\d+");
	}
	
	/**
	 * Determina se la stringa passata rappresenta un decimale.
	 * Può essere anche negativo.
	 * Si presuppone che il carattere di separazione delle cifre decimali sia il punto e non la virgola.
	 * @param s - La stringa da verificare.
	 * @return true se s rappresenta un decimale, false altrimenti.
	 */
	public static boolean isDecimal(String s)
	{
		return s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	/**
	 * Determina se la stringa passata rappresenta effettivamente una stringa e non un numero.
	 * @param s - La stringa da verificare.
	 * @return true se s non rappresenta un numero, false altrimenti.
	 */
	public static boolean isString(String s)
	{
		return !s.matches("[-+]?\\d+") && !s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	/**
	 * Determina se la stringa passata rappresenta un numero (intero o decimale).
	 * Può essere anche negativo.
	 * Nel caso di numero decimale, si presuppone che il carattere di separazione delle cifre decimali sia il punto e non la virgola.
	 * @param s - La stringa da verificare.
	 * @return true se s rappresenta un numero, false altrimenti.
	 */
	public static boolean isNumber(String s)
	{
		return s.matches("[-+]?\\d+") || s.matches("[-+]?\\d*\\.?\\d+");
	}
	
	/**
	 * Determina se una stringa rappresenta un numero intero, un numero decimale, o una stringa.
	 * @param s - La stringa da verificare.
	 * @return int se s rappresenta un numero intero, decimal se rappresenta un numero decimale e string se non rappresenta un numero.
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

