package it.speedhouse.main.db;

import java.sql.*;

public class ServiziDB {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://db4free.net:3306";

	static final String USER = "marcomassiema";
	static final String PASS = "marcomassiema";
	  
	public static void creaDB()
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
		      sql2 = "create table popopo ("
		      		+ "madonna int );";
		      
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
	
	public static void main(String[] args)
	{
		creaDB();
	}
}
