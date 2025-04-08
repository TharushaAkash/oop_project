package com.project.controllers.packageadmin;

import com.project.model.TourismPackage;
import com.project.utils.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "AddPackageServlet", value = "/add-package")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,    // 1MB
        maxFileSize = 1024 * 1024 * 10,                  // 10MB
        maxRequestSize = 1024 * 1024 * 15)               // 15MB
public class AddPackageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        int durationDays = Integer.parseInt(request.getParameter("durationDays"));

        // Handle image upload
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String uploadDir = getServletContext().getRealPath("/images/");
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) uploadFolder.mkdirs(); // create images/ if not exists

        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);  // save uploaded image

        // Create Package Object
        TourismPackage pkg = new TourismPackage(id, name, description, price, fileName, durationDays);

        // Save to package.txt (include image filename)
        String record = pkg.getId() + "," + pkg.getName() + "," + pkg.getDescription() + "," + pkg.getPrice() + "," + pkg.getImageFileName() + "," + pkg.getDurationDays() + "\n";
        String path = "package.txt";

        if (FileHandler.writeToFile(path, true, record)) {
            response.sendRedirect("add-package.jsp");
        } else {
            response.getWriter().println("Error saving package.");
        }
    }


}
