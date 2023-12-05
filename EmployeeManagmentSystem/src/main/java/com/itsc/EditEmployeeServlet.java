package com.itsc;

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

@WebServlet("/editScreen")
public class EditEmployeeServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	private static final String query = "SELECT ename,designation,salary FROM EMPLOYEES WHERE id=?";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set content type
        res.setContentType("text/html");
        
        //link the bootstrap CSS
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        
        
        //get the Id of employee record
        int id = Integer.parseInt(req.getParameter("id"));
        
        //LOAD JDBC driver
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        //generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///employeesdb", "root", "root"); 
        		
        	PreparedStatement ps = con.prepareStatement(query);) {
        	
        	ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

			pw.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
			pw.println("<div class='container mx-6 my-5'>");
            pw.println("<center><h2 class=\"bg-success text-white card-header\">Edit Employee Record</h2></center>");
            pw.println("<form action='edit?id="+id+"' method='post' class=\"form card\">");
            pw.println("<table class='table table-hover table-striped'>");
            pw.println("<tr>");
            pw.println("<td>Employee Name</td>");
            pw.println("<td><input type='text' name='employeeName' value='"+rs.getString(1)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Employee Designation</td>");
            pw.println("<td><input type='text' name='employeeDesignation' value='" + rs.getString(2) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Employee Salary</td>");
            pw.println("<td><input type='text' name='employeeSalary' value='" + rs.getFloat(3) + "'></td>");
            pw.println("</tr>");
            pw.println("<tr class='card-footer center-block' align='center'>");
            pw.println("<td><button type='submit' class='btn ml-5 btn-outline-success'>Edit</button></td>");
            pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");

        } catch (SQLException se) {
        	se.printStackTrace();
        	pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
        }
        pw.println("&nbsp; &nbsp;");
		pw.println("<button style='background-color:#1047f8;' class=\"btn btn-info  d-block\"><a style='text-decoration:none;color:white' href='index.html'>Home</a></button>");
        pw.println("</div>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
	
}