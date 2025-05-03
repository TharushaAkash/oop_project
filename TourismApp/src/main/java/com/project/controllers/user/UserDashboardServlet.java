package com.project.controllers.user;

import com.project.bst.PackageTree;
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

        
        //To display newly added packages without server restart
        // 2. Insert into BST if not already done
        PackageTree packageTree = (PackageTree) getServletContext().getAttribute("packageTree");
        if (packageTree == null) {
            packageTree = new PackageTree();
            for (TourismPackage pkg : packages) {
                packageTree.insert(pkg);
            }
            getServletContext().setAttribute("packageTree", packageTree);
        }

        // 3. Get all packages from BST (includes newly added ones)
        ArrayList<TourismPackage> allPackages = packageTree.getAllPackages();


        // 4. Send to JSP
        request.setAttribute("packages", allPackages);
        request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);






        // Set list as request attribute
        //request.setAttribute("packages", packages);

        // Forward to JSP
       // request.getRequestDispatcher("user-dashboard.jsp").forward(request, response);



    }
}
