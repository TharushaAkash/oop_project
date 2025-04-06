package com.project.controllers.user;

import com.project.model.TourismPackage;
import com.project.service.PackageManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserDashboardServlet", value = "/user-dashboard")
public class UserDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read packages from file (only loads once)
        PackageManager.readPackages();

        // Get the list of packages
        ArrayList<TourismPackage> packages = PackageManager.getPackages();
        System.out.println(packages.size());

        // Set list as request attribute
        request.setAttribute("packages", packages);

        // Forward to JSP
        request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);



    }
}
