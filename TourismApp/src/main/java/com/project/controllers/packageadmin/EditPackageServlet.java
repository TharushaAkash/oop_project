package com.project.controllers.packageadmin;

import com.project.model.TourismPackage;
import com.project.bst.PackageTree;
import com.project.service.PackageManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "EditPackageServlet", value = "/edit-package")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,    // 1MB
        maxFileSize = 1024 * 1024 * 10,                  // 10MB
        maxRequestSize = 1024 * 1024 * 15)
// 15MB
public class EditPackageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        //ArrayList<TourismPackage> bstPackages = packageTree.getAllPackages();
        ArrayList<TourismPackage> packages = PackageManager.getPackages();
        //ArrayList<TourismPackage> allPackages = packageTree.getAllPackages();

        TourismPackage selected = null;
        for (TourismPackage pkg : packages) {
            if (pkg.getId().equals(id)) {
                selected = pkg;
                break;
            }
        }

        if (selected != null) {
            request.setAttribute("pkg", selected);
            request.getRequestDispatcher("edit-package.jsp").forward(request, response);
        } else {
            response.sendRedirect("admin-dashboard?error=Package not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        String durationStr = request.getParameter("durationDays");
        int durationDays = Integer.parseInt(durationStr);
        String accommodationType = request.getParameter("accommodationType");
        int discount = Integer.parseInt(request.getParameter("discount"));

        // Handle image file upload (if any)
        Part filePart = request.getPart("image");
        String imageFileName = null;

        if (filePart != null && filePart.getSize() > 0) {
            // If a new image is uploaded, save it
            imageFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/images/");
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // Create images/ directory if not exists
            }

            String filePath = uploadDir + File.separator + imageFileName;
            filePart.write(filePath);  // Save the uploaded image
        } else {
            // If no image uploaded, use the existing image file name
            TourismPackage existing = PackageManager.findPackage(id);
            if (existing != null) {
                imageFileName = existing.getImageFileName();
            } else {
                imageFileName = "default.jpg"; // Default image if package not found
            }
        }
        // Update the package using the PackageManager
        PackageManager.updatePackage(id, name, description, price, imageFileName, durationDays, accommodationType, discount);

        // Force the rebuild of the package tree in the context (if used)
        getServletContext().removeAttribute("packageTree");  // Optional, if using a tree in servlet context

        // Redirect to the admin dashboard after update
        response.sendRedirect("admin-dashboard");
    }
}
