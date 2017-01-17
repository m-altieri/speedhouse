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


	//test
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
	}
	
	public static void creaTabella(String nomeDb, String[] tipi, String nomeTabella, String[] colonne)
	{
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

			for (int i = 0; i < righe.size(); i++) {

				String[] tupla = righe.get(i);
				sql2 += "(";

				for (int j = 0; j < tupla.length; j++) {

					if (ServiziGenerici.isInteger(tupla[j]))
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
	}

	public static ArrayList<String[]> selezionaColonne(String nomeDb, String nomeTabella, ArrayList<String> colonne)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306", "marcomassiema", "marcomassiema");

			stmt = conn.createStatement();

			String sql, sql2;
			sql = "use marcomassiema;";
			sql2 = "SELECT ";

			for (int i = 0; i < colonne.size(); i++) {
				sql2 += colonne.get(i);
				if (colonne.size() - i > 1)
					sql2 += ", ";
			}

			sql2 += " FROM " + nomeDb + "_" + nomeTabella + ";";
			System.out.println(sql2);
			stmt.execute(sql);
			rs = stmt.executeQuery(sql2);

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

		} catch (SQLException jdbcProblem) {
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
			forNameProblem.printStackTrace();
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
				nColonne++;
			}

			stmt.close();
			conn.close();

		} catch (SQLException jdbcProblem) {
			jdbcProblem.printStackTrace();
		} catch (Exception forNameProblem) {
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