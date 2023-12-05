package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class AddEmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String query = "INSERT INTO employees (ename, designation, salary) VALUES (?, ?, ?)";


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// get PrintWriter
		PrintWriter pw = res.getWriter();
		
		// set content type
		res.setContentType("text/html");
		
		//link the bootstrap
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        	
		
		// GET Employees info
		String employeeName = req.getParameter("employeeName");
		String employeeDesignation = req.getParameter("employeeDesignation");
		float employeeSalary = Float.parseFloat(req.getParameter("employeeSalary"));
		
		// LOAD JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		// generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///employeesdb", "root", "root");

			
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, employeeName);
			ps.setString(2, employeeDesignation);
			ps.setFloat(3, employeeSalary);
			
			int count = ps.executeUpdate();
			
			
			
			pw.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
			pw.println("<div class='container mx-6 my-5'>");
            
			if (count == 1) {
				
				 pw.println("<h2 class='bg-success mx-6 text-light text-center'>Employee Record Registered Successfully</h2>");
		           
			} else {
				pw.println("<h2 class='bg-danger mx-6 text-light text-center'>Employee Record not Registered");
			}
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1 class='bg-danger text-light text-center'>" + se.getMessage() + "</h2>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1 class='bg-danger text-light text-center'>" + e.getMessage() + "</h2>");
		}
			pw.println("&nbsp; &nbsp;");
			pw.println("<button style='background-color:#1047f8;' class=\"btn btn-info  d-block\"><a style='text-decoration:none;color:white' href='index.html'>Home</a></button>");
			pw.println("&nbsp; &nbsp;");
			pw.println("<button style='background-color:#1047f8;' class=\"btn btn-info d-block\"><a style='text-decoration:none;color:white' href='viewEmployee'>View Employees</a></button>");
			pw.println("</div>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}