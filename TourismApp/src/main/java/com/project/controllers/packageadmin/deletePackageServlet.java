package com.project.controllers.packageadmin;

import com.project.bst.PackageTree;
import com.project.service.PackageManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "deletePackageServlet", value = "/delete-package")
public class deletePackageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String packageId = request.getParameter("id");

        if (packageId == null || packageId.isEmpty()) {
            response.sendRedirect("admin-dashboard.jsp?error=Invalid+Package+ID");
            return;
        }

        // 1. Delete from file + memory list
        PackageManager.removePackage(packageId);

        // 2. Delete from BST if it exists
        PackageTree packageTree = (PackageTree) getServletContext().getAttribute("packageTree");
        if (packageTree != null) {
            try {
                packageTree.delete(packageId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format for BST deletion");
            }
        }

        // 3. Redirect to dashboard
        response.sendRedirect("admin-dashboard?message=Package+Removed+Successfully");



    }
}
