package com.project.controllers;

import com.project.utils.FileHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/login")
public class AuthServlet extends HttpServlet {
    private static final String USERS_FILE = "users.txt";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        String file = role.equals("admin") ? "admins.txt" : "users.txt";
        String[] records = FileHandler.readFromFile(file);
        String files = USERS_FILE;


        for (String record : records) {
            String[] parts = record.split(","); //Split in to 2 parts (String = [email, password])

            //Check part1 == user submitted email and part2 == password
            if (parts.length == 2 && parts[0].equals(email) && parts[1].equals(password)) {  //check actually it has 2 parts
                //create a new session
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("role", role);


                if (role.equals("admin")) {
                    response.sendRedirect("admin-dashboard"); //Change to admin-dashboard
                } else {
                    response.sendRedirect("user-dashboard");
                }
                return;
            }
        }

        response.sendRedirect("login.jsp?error=Invalid email or password");
    }
}
