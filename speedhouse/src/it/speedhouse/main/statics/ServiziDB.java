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
	static final String USE_DB = "use marcomassiema;";

	static final String USER = "marcomassiema";
	static final String PASS = "marcomassiema";

	/**
	 * Permette di connettersi al database. Restituisce un oggetto Connection contenente una connessione valida se essa
	 * è riuscita, null altrimenti.
	 * @return Una connessione al database.
	 */
	private static Connection getConnection()
	{
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException f) {
			f.printStackTrace();
		}
		
		return conn;
	}
	
	private static void useDatabase(Statement s)
	{
		try {
			s.execute(USE_DB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> ottieniTabelle(String nomeDb)
	{
		ArrayList<String> tabelle = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql;
			sql = "SHOW TABLES;";

			useDatabase(stmt);
			stmt.execute(sql);
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

			stmt.close();
			conn.close();
		} catch(SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch(Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		} finally {
			// Chiusura risorse
			try {
				if(stmt!=null)
					stmt.close();
			} catch(SQLException e) {
				;
			}
			try {
				if(conn!=null)
					conn.close();
			} catch(SQLException f) {
				f.printStackTrace();
			}
		}

		return tabelle;
	}

	public static void creaTabella(String nomeDb, String[] tipi, String nomeTabella, String[] colonne)
	{
		Connection conn = null;
		Statement stmt = null;

		try{
			
			conn = getConnection();

			// Esecuzione query
			stmt = conn.createStatement();
			String sql;
			
			useDatabase(stmt);
			sql = "CREATE TABLE " + nomeDb + "_" + nomeTabella + " (";
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
			
			stmt.execute(sql);

			stmt.close();
			conn.close();
		}catch(SQLException se){
			// Errori del JDBC
			se.printStackTrace();
		}catch(Exception e){
			// Errori di Class.forName
			e.printStackTrace();
		}finally{
			// Chiusura risorse
			try {
				if (stmt!=null)
					stmt.close();
			} catch(SQLException e) {
				;
			}
			try {
				if (conn!=null)
					conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Non effettua controlli sulla compatibilità tra tipo dei dati inseriti e tipi accettati dalle colonne della tabella.
	 * @param nomeDb - Nome del database in cui inserire i dati
	 * @param nomeTabella - Nome della tabella in cui inserire i dati
	 * @param righe - Dati da inserire
	 */
	public static void inserisciDati(String nomeDb, String nomeTabella, ArrayList<String[]> righe)
	{
		Connection conn = null;
		Statement stmt = null;

		try{
			
			conn = getConnection();

			// Esecuzione query
			stmt = conn.createStatement();
			String sql;
			useDatabase(stmt);
			sql = "INSERT INTO " + nomeDb + "_" + nomeTabella + " VALUES ";

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
			
			stmt.execute(sql);

			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch (Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
		} finally {
			// Chiusura risorse
			try {
				if (stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				;
			}
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<String[]> selezionaColonne(String nomeDb, String nomeTabella, ArrayList<String> colonne)
	{
		Connection conn = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {

			conn = getConnection();

			stmt = conn.createStatement();

			// Esecuzione query
			useDatabase(stmt);
			String sql = "SELECT ";

			for (int i = 0; i < colonne.size(); i++) {
				sql += colonne.get(i);
				if (colonne.size() - i > 1)
					sql += ", ";
			}

			sql += " FROM " + nomeDb + "_" + nomeTabella + ";";
			
			rs = stmt.executeQuery(sql);

			while (!rs.isLast()) {
				rs.next();
				String[] riga = new String[colonne.size()];
				for (int i = 1; i <= colonne.size(); i++) {
					riga[i-1] = rs.getString(i);
				}
				ret.add(riga);
			}

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

	public static int getNumeroColonne(String database, String tabella)
	{
		Connection conn = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		int nColonne = 0;

		try {

			conn = getConnection();
			stmt = conn.createStatement();

			//Esecuzione query
			useDatabase(stmt);
			String sql;
			sql = "SHOW COLUMNS FROM " + database + "_" + tabella;

			rs = stmt.executeQuery(sql);

			while (!rs.isLast()) {
				rs.next();
				nColonne++;
			}

			stmt.close();
			conn.close();

		} catch (SQLException jdbcProblem) {
			// Errori del JDBC
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			// Errori di Class.forName
			forNameProblem.printStackTrace();
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

			conn = getConnection();

			stmt = conn.createStatement();

			//Esecuzione query
			useDatabase(stmt);
			String sql;
			sql = "SHOW COLUMNS FROM " + database + "_" + tabella;

			rs = stmt.executeQuery(sql);

			while (!rs.isLast()) {
				rs.next();
				colonne.add(rs.getString(1));
			}

			stmt.close();
			conn.close();

		} catch (SQLException jdbcProblem) {
			// Errori del JDBC
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			// Errori di Class.forName
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

			conn = getConnection();

			stmt = conn.createStatement();

			// Esecuzione query
			useDatabase(stmt);
			String sql;
			sql = "SHOW COLUMNS FROM " + nomeDb + "_" + nomeTabella;

			rs = stmt.executeQuery(sql);

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
			// Errori del JDBC
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			// Errori di Class.forName
			forNameProblem.printStackTrace();
		}

		return ret;
	}
}
