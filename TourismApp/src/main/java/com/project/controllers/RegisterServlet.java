package com.project.controllers;

import com.example.tourismapp.utils.FileHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final String USERS_FILE = "users.txt";
    private static final String ADMINS_FILE = "admins.txt";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String record = email + "," + password + "\n";
        String file = role.equals("admin") ? ADMINS_FILE : USERS_FILE;

        FileHandler.writeToFile(file, true, record);
        response.sendRedirect("login.jsp");
    }
}
