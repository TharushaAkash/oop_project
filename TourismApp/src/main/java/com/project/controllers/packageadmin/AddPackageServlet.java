package com.project.controllers.packageadmin;

import com.example.tourismapp.utils.FileHandler;
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

        String record = id + "," + name + "," + description + "," + price + "\n";
        String path = "package.txt";
        //Remove this ---- getServletContext().getRealPath("/") +

        if (FileHandler.writeToFile(path, true, record)) {
            response.sendRedirect("admin-dashboard.jsp");
        } else {
            response.getWriter().println("Error saving package.");
        }
    }
}
