package com.idiot.servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchListServlet
 */
//@WebServlet("/FetchListServlet")
@WebServlet("/FetchList")
public class FetchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query = "select * from birthdates";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

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
			ResultSet rs = ps.executeQuery();
			pw.println("<table border='1' align='center'>");
			pw.println("<tr>");
			pw.println("<th> Index</th>");
			pw.println("<th> Person Naame</th>");
			pw.println("<th> Date of Birth</>");
			pw.println("<th> Edit</>");
			pw.println("<th> Delete</>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>" +rs.getInt(1) +"</td>");
				pw.println("<td>" +rs.getString(2) +"</td>");
				pw.println("<td>" +rs.getString(3) +"</td>");
				pw.println("<td> <a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
				pw.println("<td> <a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
		}
		catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "<h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "<h1>");
			
		}
		
		pw.println("<a href='Home.html'> Home</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
