package com.cg.util;
import java.sql.*;
import java.util.*;
import java.io.*;

public class DbUtils {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn=null;
		try {
		// loading properties from file
		//FileInputStream fin=new FileInputStream ("db.properties");
		DbUtils ob=new DbUtils();
		InputStream is = ob.getClass().getClassLoader()
	            .getResourceAsStream("db.properties");
		Properties props=new Properties();
		//props.load(fin);
		props.load(is);
		
		// Extracting properties 
		String driver = props.getProperty("db.driver");
		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String pwd = props.getProperty("db.password");
		
			  Class.forName(driver);
		       conn= DriverManager.getConnection(url,user,pwd);
		      System.out.println("Connected");
		    
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		      
	}

}
