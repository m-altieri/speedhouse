package it.speedhouse.main.statics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServiziDB {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://db4free.net:3306";

	static final String USER = "marcomassiema";
	static final String PASS = "marcomassiema";
	  
	public static void creaDB(String nome)
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
		      sql2 = "create table database" + nome + "();";
		      
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
	    	  String[] db = tabella.split("_");
	    	  System.out.println(db[0]);
	    	  if (db[0].equals(nomeDb)) {
	    		  tabelle.add(db[1]);
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
		    	  switch (tipi[i]) {
		    	  case "int":
		    		  sql2 += "INT";
		    		  break;
		    	  case "decimal":
		    		  sql2 += "FLOAT(8,4)";
		    		  break;
		    	  case "string":
		    		  sql2 += "VARCHAR(30)";
		    		  break;
		    	  }
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
	
	public static void inserisciDati(ArrayList<String[]> righe)
	{
		
	}
	
	public static void main(String[] args)
	{
		
	}
}
