package com.idiot.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteRecoredServlet
 */
@WebServlet("/deleteurl")
public class deleteRecordServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	private static final String query = "delete from birthdates where id=?";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(jakarta.servlet.http.HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//get the id of record
		int id = Integer.parseInt(req.getParameter("id"));
	

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
			
			ps.setInt(1, id);
			int cnt = ps.executeUpdate();
			if(cnt == 1) {
				pw.println("<h3>Record deleted successfully.</h3>");
			}
			else pw.println("<h3>Record is not deleted.</h3>");
			
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
