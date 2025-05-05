package com.project.controllers.packageadmin;

import com.project.bst.PackageTree;
import com.project.service.PackageManager;
import com.project.model.TourismPackage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "viewPackageServlet", value = "/adminViewPackage")
public class ViewPackageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null || id.isEmpty()) {
            response.sendRedirect("admin-dashboard.jsp?error=Invalid+Package+ID");
            return;
        }



        // 1. Load all packages from memory (both ArrayList and PackageTree)
        PackageManager.readPackages();

        ArrayList<TourismPackage> filePackages = PackageManager.getPackages();

        PackageTree packageTree = (PackageTree) getServletContext().getAttribute("packageTree");
        if (packageTree == null) {
            packageTree = new PackageTree();
            for (TourismPackage pkg : filePackages) {
                packageTree.insert(pkg);
            }
            getServletContext().setAttribute("packageTree", packageTree);
        }






        // 2. Find the specific package by ID (using both ArrayList and PackageTree)
        TourismPackage pkg = PackageManager.findPackage(id);

        if (pkg != null) {
            // Set the package as an attribute to send to the JSP
            request.setAttribute("pkg", pkg);

            // Forward the request to the JSP that will display the package details
            request.getRequestDispatcher("/adminViewPackage.jsp").forward(request, response);
        } else {
            // If package not found, redirect back with an error message
            response.sendRedirect("admin-dashboard.jsp?error=Package+Not+Found");
        }
    }
}
