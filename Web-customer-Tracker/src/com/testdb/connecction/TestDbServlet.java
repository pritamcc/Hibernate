package com.testdb.connecction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		//Setum Connection Variable
		String user ="springstudent";
		String pass ="springstudent";
       
		String jdbcurl="jdbc:mysql://localhost:3307/web_customer_tracker?useSSL=false";	
	    String driver ="com.mysql.jdbc.Driver";
	    
	    
	    //get Connection to database
	    try{
	    	   PrintWriter out = response.getWriter();
	    	   out.println("Connectiong to databse"+ jdbcurl);
	    	   
	    	   Class.forName(driver);
	    	   
	    	   Connection myconn = DriverManager.getConnection(jdbcurl,user,pass);
	    	   
	    	   out.println("SuccesS!!");
	    	     
	    	  myconn.close(); 
	    }
	    catch(Exception exc)
	    {
	    	exc.printStackTrace();
	    	throw new ServletException(exc);
	    }	    
	}

}
