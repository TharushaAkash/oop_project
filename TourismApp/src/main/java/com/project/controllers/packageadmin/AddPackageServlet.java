package com.project.controllers.packageadmin;

import com.project.model.TourismPackage;
import com.project.utils.FileHandler;
import com.project.bst.PackageTree;

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

        // Add package to BST stored in servlet context
        PackageTree packageTree = (PackageTree) getServletContext().getAttribute("packageTree");
        if (packageTree == null) {
            packageTree = new PackageTree();
        }

        packageTree.insert(pkg);
        getServletContext().setAttribute("packageTree", packageTree);

        // Also write to packages.txt file for backup/persistence
        String record = pkg.toString(); // already formatted with \n
        String textFilePath = "package.txt";
        FileHandler.writeToFile(textFilePath, true, record);

        // Redirect after adding
        response.sendRedirect("admin-dashboard");
    }
}
