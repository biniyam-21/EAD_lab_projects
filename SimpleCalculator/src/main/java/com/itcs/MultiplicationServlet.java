package com.itcs;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/multiplication")
public class MultiplicationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the request
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        // Perform multiplication
        try {
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);
            int result = num1 * num2;

            // Set the result as a request attribute (not necessary for this approach)

            // Write the result directly to the response
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<link rel=\"stylesheet\" href='style.css' >");
           
            response.getWriter().println("<div class=\"container\">");
            response.getWriter().println("<div class=\"cookiesContent\">");
            response.getWriter().println("<h1>The result is:</h1>");
            response.getWriter().println("<h3>" + result + "</h3>");
            response.getWriter().println("<a href=\"index.html\">");
            response.getWriter().println("<button>try again</button>");
            response.getWriter().println("</a>");
            response.getWriter().println("</div>");
            response.getWriter().println("</div>");
            response.getWriter().println("</body></html>");

        } catch (NumberFormatException e) {
            // Handle invalid input
            response.setContentType("text/plain");
            response.getWriter().println("Invalid input. Please enter valid numbers.");
        }
    }
}

