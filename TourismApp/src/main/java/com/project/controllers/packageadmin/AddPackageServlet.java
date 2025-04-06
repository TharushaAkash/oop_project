package com.project.controllers.packageadmin;

import com.project.model.TourismPackage;
import com.project.utils.FileHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "AddPackageServlet", value = "/add-package")
public class AddPackageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");


        //Create Package Object
        TourismPackage pkg = new TourismPackage(id, name, description, price);

        String record = pkg.getId() + "," + pkg.getName() + "," + pkg.getDescription() + "," + pkg.getPrice() + "\n";
        String path = "package.txt";
        //Remove this ---- getServletContext().getRealPath("/") +

        if (FileHandler.writeToFile(path, true, record)) {
            response.sendRedirect("admin-dashboard.jsp");
        } else {
            response.getWriter().println("Error saving package.");
        }
    }
}
