package com.idiot.servlet;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RegisterServlet
 */
//@WebServlet("/RegisterServlet")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String query = "insert into birthdates(PersonName, Birthdate) values(?,?)";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//get person details
		String Pname = req.getParameter("Pname");
		String Bdate = req.getParameter("Bdate");
		
		
		// load jdbc Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		//generate the connection
		try {
			Connection con  = DriverManager.getConnection("jdbc:mysql:///birthregistry", "root", "gamer");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, Pname);
			ps.setString(2, Bdate);
		    
		    int cnt = ps.executeUpdate();
		    if(cnt==1) {
		    	pw.println("Record is inserted Successfully.");
		    }
		    else {
		    	pw.println("Record is not inserted.");
		    }
		}
		catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "<h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "<h1>");
			
		}
		pw.println("<a href='Home.html'>Home</a>");
		pw.println("<br/>");
		pw.println("<a href='FetchList'>Get the List</a>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
