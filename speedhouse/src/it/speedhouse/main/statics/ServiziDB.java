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

<<<<<<< HEAD
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
=======


	public static ArrayList<String> ottieniTabelle(String nomeDb)
	{
		ArrayList<String> tabelle = new ArrayList<String>();

		Connection conn = null;
		Statement stmt = null;

		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "show tables;";

			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql2);

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

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");

		return tabelle;
	}


	public static void eliminaTabella(String nomeDb, String nomeTabella)
	{
		Connection conn = null;
		Statement stmt = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306","marcomassiema","marcomassiema");
			stmt = conn.createStatement();
			String sql = "USE marcomassiema;";
			String sql2 = "DROP TABLE " + nomeDb + "_" + nomeTabella + ";";
			stmt.execute(sql);
			stmt.execute(sql2);
			stmt.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception f){
			f.printStackTrace();
		}
>>>>>>> refs/heads/pr/4
	}

	public static void creaTabella(String nomeDb, String[] tipi, String nomeTabella, String[] colonne)
	{
		Connection conn = null;
		Statement stmt = null;

		try{
<<<<<<< HEAD
			
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
=======
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "CREATE TABLE " + nomeDb + "_" + nomeTabella + " (";
			for (int i = 0; i < tipi.length; i++) {
				sql2 += colonne[i] + " ";

				if (tipi[i].equals("string"))
					sql2 += "varchar(100)";
				else
					sql2 += tipi[i];

				if (tipi.length - i > 1)
					sql2 += ", ";
			}
			sql2 += ");";
			System.out.println(sql2);//
			stmt.execute(sql);
			stmt.execute(sql2);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
>>>>>>> refs/heads/pr/4
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
<<<<<<< HEAD
			
			conn = getConnection();

			// Esecuzione query
			stmt = conn.createStatement();
			String sql;
			useDatabase(stmt);
			sql = "INSERT INTO " + nomeDb + "_" + nomeTabella + " VALUES ";
=======
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "INSERT INTO " + nomeDb + "_" + nomeTabella + " VALUES ";
>>>>>>> refs/heads/pr/4

			for (int i = 0; i < righe.size(); i++) {

				String[] tupla = righe.get(i);
<<<<<<< HEAD
				sql += "(";
=======
				sql2 += "(";
>>>>>>> refs/heads/pr/4

				for (int j = 0; j < tupla.length; j++) {

					if (ServiziGenerici.isInteger(tupla[j]))
<<<<<<< HEAD
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
=======
						sql2 += tupla[j];
					else if (ServiziGenerici.isDecimal(tupla[j]))
						sql2 += tupla[j];
					else if (tupla[j].equals(""))
						sql2 += "null";
					else
						sql2 += "\"" + tupla[j] + "\"";

					if (tupla.length - j > 1)
						sql2 += ", ";
				}

				sql2 += ")";

				if (righe.size() - i > 1)
					sql2 += ", ";
			}
			sql2 += ";";
			System.out.println(sql2);//
			stmt.execute(sql);
			stmt.execute(sql2);

			//STEP 6: Clean-up environment
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
>>>>>>> refs/heads/pr/4
	}

	public static ArrayList<String[]> selezionaColonne(String nomeDb, String nomeTabella, ArrayList<String> colonne)
	{
		Connection conn = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {

<<<<<<< HEAD
			conn = getConnection();

			stmt = conn.createStatement();

			// Esecuzione query
			useDatabase(stmt);
			String sql = "SELECT ";
=======
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");

			stmt = conn.createStatement();

			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SELECT ";
>>>>>>> refs/heads/pr/4

			for (int i = 0; i < colonne.size(); i++) {
				sql += colonne.get(i);
				if (colonne.size() - i > 1)
					sql += ", ";
			}

<<<<<<< HEAD
			sql += " FROM " + nomeDb + "_" + nomeTabella + ";";
			
			rs = stmt.executeQuery(sql);
=======
			sql2 += " FROM " + nomeDb + "_" + nomeTabella + ";";
			System.out.println(sql2);
			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);
>>>>>>> refs/heads/pr/4

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

<<<<<<< HEAD
		} catch (SQLException e) {
			// Errori del JDBC
			e.printStackTrace();
		} catch (Exception f) {
			// Errori di Class.forName
			f.printStackTrace();
=======
		} catch (SQLException jdbcProblem) {
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			forNameProblem.printStackTrace();
>>>>>>> refs/heads/pr/4
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
<<<<<<< HEAD
=======

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");
>>>>>>> refs/heads/pr/4

			conn = getConnection();
			stmt = conn.createStatement();

<<<<<<< HEAD
			//Esecuzione query
			useDatabase(stmt);
			String sql;
			sql = "SHOW COLUMNS FROM " + database + "_" + tabella;

			rs = stmt.executeQuery(sql);
=======
			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SHOW COLUMNS FROM " + database + "_" + tabella;

			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);
>>>>>>> refs/heads/pr/4

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

<<<<<<< HEAD
			conn = getConnection();

			stmt = conn.createStatement();

			//Esecuzione query
			useDatabase(stmt);
			String sql;
			sql = "SHOW COLUMNS FROM " + database + "_" + tabella;

			rs = stmt.executeQuery(sql);
=======
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");

			stmt = conn.createStatement();

			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SHOW COLUMNS FROM " + database + "_" + tabella;

			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);
>>>>>>> refs/heads/pr/4

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
<<<<<<< HEAD
=======

>>>>>>> refs/heads/pr/4
		Connection conn = null;
		Statement stmt = null;
		
		ResultSet rs = null;
		String ret = "";

		try {

<<<<<<< HEAD
			conn = getConnection();

			stmt = conn.createStatement();

			// Esecuzione query
			useDatabase(stmt);
			String sql;
			sql = "SHOW COLUMNS FROM " + nomeDb + "_" + nomeTabella;

			rs = stmt.executeQuery(sql);
=======
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");

			stmt = conn.createStatement();

			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SHOW COLUMNS FROM " + nomeDb + "_" + nomeTabella;

			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);
>>>>>>> refs/heads/pr/4

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
