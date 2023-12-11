package com.itcs;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/operation")
public class OperationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the selected operation parameter from the request
        String operation = request.getParameter("operation");

        // Forward the request to the selected operation servlet
        if ("+".equals(operation)) {
            // Forward to the AdditionServlet
            request.getRequestDispatcher("/addition").forward(request, response);
        } else if ("*".equals(operation)) {
            // Forward to the MultiplicationServlet
            request.getRequestDispatcher("/multiplication").forward(request, response);
        } else {
            // Handle unknown operation
            response.getWriter().println("Unknown operation selected.");
        }
    }
}
