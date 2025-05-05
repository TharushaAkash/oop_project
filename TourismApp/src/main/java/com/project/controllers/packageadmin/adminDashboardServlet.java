package com.project.controllers.packageadmin;

import com.project.model.TourismPackage;
import com.project.service.PackageManager;
import com.project.bst.PackageNode;
import com.project.utils.FileHandler;
import com.project.bst.PackageTree;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
@WebServlet(name = "adminDashboardServlet", value = "/admin-dashboard")

public class adminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PackageManager.readPackages();


        //ArrayList<TourismPackage> packages = PackageManager.getPackages();
        //System.out.println(packages.size());

                // 1. Load from file\

                PackageManager.readPackages();
                ArrayList<TourismPackage> filePackages = PackageManager.getPackages();


        //To display newly added packages without server restart
                // 2. Insert into BST if not already done
                PackageTree packageTree = (PackageTree) getServletContext().getAttribute("packageTree");
                if (packageTree == null) {
                    packageTree = new PackageTree();
                    for (TourismPackage pkg : filePackages) {
                        packageTree.insert(pkg);
                    }
                    getServletContext().setAttribute("packageTree", packageTree);
                }

                // 3. Get all packages from BST (includes newly added ones)
                ArrayList<TourismPackage> allPackages = packageTree.getAllPackages();

                // 4. Send to JSP
                request.setAttribute("packages", allPackages);
                request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);





        //request.setAttribute("packages", packages);

        //request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }


}





