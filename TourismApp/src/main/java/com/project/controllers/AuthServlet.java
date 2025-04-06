package com.project.controllers;

import com.example.tourismapp.utils.FileHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/login")
public class AuthServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String file = role.equals("admin") ? "admins.txt" : "users.txt";
        String[] records = FileHandler.readFromFile(file);

        for (String record : records) {
            String[] parts = record.split(",");
            if (parts.length == 2 && parts[0].equals(email) && parts[1].equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("role", role);

                if (role.equals("admin")) {
                    response.sendRedirect("add-package.jsp");
                } else {
                    response.sendRedirect("user-dashboard.jsp");
                }
                return;
            }
        }

        response.getWriter().println("Invalid login. <a href='login.jsp'>Try again</a>");
    }
}
