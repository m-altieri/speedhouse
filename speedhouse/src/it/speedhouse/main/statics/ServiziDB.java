package it.speedhouse.main.statics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class ServiziDB {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://db4free.net:3306";
	static final String USER = "marcomassiema";
	static final String PASS = "marcomassiema";

	/**
	 * Esegue la query mysql "USE" per poter usare il database.
	 * Deve essere eseguita prima di poter effettuare qualsiasi query su quel database.
	 * @param conn Oggetto Connection contenente la connessione al database.
	 */
	private static void useDb(Connection conn)
	{
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.execute("USE " + USER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce una connessione valida al database.
	 * @return Un oggetto Connection contenente la connessione.
	 */
	private static Connection getConnection()
	{
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) { // Errori del JDBC
			e.printStackTrace();
		} catch (Exception f) { // Errori di Class.forName
			f.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * Ottieni i nomi delle tabelle presenti nel database passato come argomento.
	 * @param nomeDb Il nome del database di cui ottenere i nomi delle tabelle create.
	 * @return ArrayList di String contenente i nomi delle tabelle create.
	 */
	public static ArrayList<String> ottieniTabelle(String nomeDb)
	{
		ArrayList<String> tabelle = new ArrayList<String>();

		Connection conn = getConnection();
		useDb(conn);
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "show tables;";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String tabella = rs.getString(1);
				String db = "";
				if (tabella.contains("_")) {
					db = tabella.substring(0, tabella.indexOf("_")); //DEBUG//
					tabella = tabella.substring(tabella.indexOf("_") + 1);
					if (db.equals(nomeDb))
						tabelle.add(tabella);
				}
			}

			// Chiusura risorse
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch(Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		}
		
		return tabelle;
	}

	/**
	 * Elimina la tabella scelta.
	 * @param nomeDb Il nome del database contenente la tabella da eliminare.
	 * @param nomeTabella Il nome della tabella da eliminare.
	 */
	public static void eliminaTabella(String nomeDb, String nomeTabella)
	{
		Connection conn = getConnection();
		useDb(conn);
		
		Statement stmt = null;

		try{
			// Creazione e esecuzione query
			String sql = "DROP TABLE " + nomeDb + "_" + nomeTabella + ";";
			stmt = conn.createStatement();
			stmt.execute(sql);

			// Chiusura risorse
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch(Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		}
	}
	
	/**
	 * Crea una tabella.
	 * @param nomeDb Nome del database in cui creare la tabella.
	 * @param tipi Array di stringhe contenente i tipi delle colonne da creare, in ordine. I tipi devono essere quelli della
	 * sintassi di mysql e non di Java. Usare "decimal" per i numeri decimali e "varchar(100)" per le stringhe.
	 * @param nomeTabella Il nome della tabella da creare.
	 * @param colonne Array di stringhe contenente i nomi delle colonne da creare, in ordine.
	 */
	public static void creaTabella(String nomeDb, String[] tipi, String nomeTabella, String[] colonne)
	{
		Connection conn = getConnection();
		useDb(conn);
		
		Statement stmt = null;

		try {
			// Creazione query
			stmt = conn.createStatement();
			String sql = "CREATE TABLE " + nomeDb + "_" + nomeTabella + " (";
			
			for (int i = 0; i < tipi.length; i++) {
				sql += colonne[i] + " ";

				if (tipi[i].equals("string"))
					sql += "varchar(100)";
				else
					sql += tipi[i];

				if (tipi.length - i > 1)
					sql += ", ";
			}
			sql += ");";
			
			// Esecuzione query
			stmt.execute(sql);

			// Chiusura risorse
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		}catch (Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		}
	}

	/**
	 * Inserisce dati in una tabella già creata.
	 * Non effettua controlli sulla compatibilità tra tipo dei dati inseriti e tipi accettati dalle colonne della tabella.
	 * @param nomeDb - Nome del database in cui inserire i dati
	 * @param nomeTabella - Nome della tabella in cui inserire i dati
	 * @param righe - Dati da inserire
	 */
	public static void inserisciDati(String nomeDb, String nomeTabella, ArrayList<String[]> righe)
	{
		Connection conn = getConnection();
		useDb(conn);
		
		Statement stmt = null;

		try{
			// Creazione query
			stmt = conn.createStatement();
			String sql = "INSERT INTO " + nomeDb + "_" + nomeTabella + " VALUES ";

			for (int i = 0; i < righe.size(); i++) {

				String[] tupla = righe.get(i);
				sql += "(";

				for (int j = 0; j < tupla.length; j++) {

					if (ServiziGenerici.isInteger(tupla[j]))
						sql += tupla[j];
					else if (ServiziGenerici.isDecimal(tupla[j]))
						sql += tupla[j];
					else if (tupla[j].equals(""))
						sql += "null";
					else
						sql += "\"" + tupla[j] + "\"";

					if (tupla.length - j > 1)
						sql += ", ";
				}

				sql += ")";

				if (righe.size() - i > 1)
					sql += ", ";
			}
			sql += ";";

			// Esecuzione query
			stmt.execute(sql);

			// Chiusura risorse
			stmt.close();
			conn.close();
		} catch(SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch(Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		}
	}

	/**
	 * 
	 * @param nomeDb
	 * @param nomeTabella
	 * @param colonne
	 * @return
	 */
	public static ArrayList<String[]> selezionaColonne(String nomeDb, String nomeTabella, ArrayList<String> colonne)
	{
		Connection conn = getConnection();
		useDb(conn);
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {
			// Creazione query
			stmt = conn.createStatement();
			String sql = "SELECT ";

			for (int i = 0; i < colonne.size(); i++) {
				sql += colonne.get(i);
				if (colonne.size() - i > 1)
					sql += ", ";
			}

			sql += " FROM " + nomeDb + "_" + nomeTabella + ";";
			
			// Esecuzione query
			rs = stmt.executeQuery(sql);

			// Operazioni sul result set
			while (!rs.isLast()) {
				rs.next();
				String[] riga = new String[colonne.size()];
				for (int i = 1; i <= colonne.size(); i++) {
					riga[i-1] = rs.getString(i);
				}
				ret.add(riga);
			}

			// Chiusura risorse
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch (Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		}

		return ret;
	}

	/**
	 * Ottieni il numero di colonne di una tabella.
	 * @param database Il nome del database contenente la tabella.
	 * @param tabella Il nome della tabella.
	 * @return
	 */
	public static int getNumeroColonne(String database, String tabella)
	{
		Connection conn = getConnection();
		useDb(conn);
		
		Statement stmt = null;
		ResultSet rs = null;
		int nColonne = 0;

		try {
			// Creazione query
			stmt = conn.createStatement();
			String sql = "SHOW COLUMNS FROM " + database + "_" + tabella;

			// Esecuzione query
			rs = stmt.executeQuery(sql);

			// Operazioni sul result set
			while (!rs.isLast()) {
				rs.next();
				nColonne++;
			}

			// Chiusura risorse
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception f) {
			f.printStackTrace();
		}

		return nColonne;
	}

	public static ArrayList<String> estraiColonne(String database, String tabella)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> colonne = new ArrayList<String>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");

			stmt = conn.createStatement();

			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SHOW COLUMNS FROM " + database + "_" + tabella;

			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);

			while (!rs.isLast()) {
				rs.next();
				colonne.add(rs.getString(1));
			}

			stmt.close();
			conn.close();

		} catch (SQLException jdbcProblem) {
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			forNameProblem.printStackTrace();
		}

		return colonne;
	}

	/**
	 * 
	 * @param nomeDb
	 * @param nomeTabella
	 * @param index - L'indice della colonna di cui estrarre il nome. La prima colonna ha indice 1.
	 * @return
	 */
	public static String getColonna(String nomeDb, String nomeTabella, int index)
	{

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String ret = "";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");

			stmt = conn.createStatement();

			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SHOW COLUMNS FROM " + nomeDb + "_" + nomeTabella;

			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);

			int count = 0;
			while (!rs.isLast()) {
				rs.next();
				count++;
				if (count == index)
					ret = rs.getString(1);
			}

			stmt.close();
			conn.close();

		} catch (SQLException jdbcProblem) {
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			forNameProblem.printStackTrace();
		}

		return ret;
	}
}