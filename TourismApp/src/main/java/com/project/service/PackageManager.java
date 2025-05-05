package com.project.service;

import com.project.model.TourismPackage;
import com.project.bst.PackageTree; // Import the PackageTree class
import com.project.utils.FileHandler;

import java.util.ArrayList;

public class PackageManager {
    private static ArrayList<TourismPackage> packages = null;
    private static final String fileName = "package.txt";
    private static int id = 0;
    private static PackageTree packageTree = new PackageTree(); // Create PackageTree instance

    // Load packages from file (and rebuild PackageTree)
    public static void readPackages() {

        // Always reset and reload from file
        packages = new ArrayList<>();
        packageTree = new PackageTree(); // Reset tree as well
        /*if (packages != null)
            return;*/

        packages = new ArrayList<>();
        String[] data = FileHandler.readFromFile(fileName);

        String packageId = "";

        for (String record : data) {
            String[] fields = record.split(",");

            // ✅ Skip if line doesn't have at least 8 fields
            if (fields.length < 8) {
                System.err.println("⚠️ Skipping invalid record: " + record);
                continue;
            }

            try {
                packageId = fields[0];
                String name = fields[1];
                String description = fields[2];
                String price = fields[3];
                int durationDays = Integer.parseInt(fields[5]);
                String accommodationType = fields[6];
                int discount = Integer.parseInt(fields[7]);

                //String imageFileName = (fields.length >= 9) ? fields[4] : "default.jpg";
                String imageFileName = fields[4];
                TourismPackage pkg = new TourismPackage(packageId, name, description, price, imageFileName, durationDays, accommodationType, discount);
                packages.add(pkg);
                packageTree.insert(pkg);
            } catch (Exception e) {
                System.err.println("⚠️ Failed to parse package: " + record);
                e.printStackTrace();
            }
        }

        try {
            id = Integer.parseInt(packageId);
        } catch (NumberFormatException e) {
            id = 0; // fallback
        }
    }


    public static TourismPackage findPackage(String id) {
        System.out.println("Searching for package with ID: " + id); // Debugging line
        TourismPackage pkg = packageTree.search(id.trim());

        if (pkg == null) {
            // If not found in BST, check the ArrayList
            for (TourismPackage p : packages) {
                if (p.getId().trim().equalsIgnoreCase(id.trim())) {
                    return p;
                }
            }
        }
        return pkg;
    }


    // Add a new package
    public static void addPackage(String id, String name, String description, String price, String imageFileName, int durationDays, String accommodationType, int discount) {
        TourismPackage pkg = new TourismPackage(id, name, description, price, imageFileName, durationDays, accommodationType, discount);
        packages.add(pkg);
        packageTree.insert(pkg); // Insert into PackageTree after adding to the list
        FileHandler.writeToFile(fileName, true, pkg.toString());
    }

    public static void removePackage(String id) {
        packages.remove(findPackage(id));
        packageTree.delete(id); // Delete from PackageTree (BST)
        savePackagesToFile();
    }

    public static void updatePackage(String id, String name, String description, String price, String imageFileName, int durationDays, String accommodationType, int discount) {
        TourismPackage pkg = findPackage(id);
        if (pkg != null) {
            pkg.setName(name);
            pkg.setDescription(description);
            pkg.setPrice(price);
            pkg.setImageFileName(imageFileName);
            pkg.setDurationDays(durationDays);
            pkg.setAccommodationType(accommodationType);
            pkg.setDiscount(discount);
        }
        savePackagesToFile();
    }

    // Save all packages back to the file
    public static void savePackagesToFile() {
        StringBuilder allPackages = new StringBuilder();
        for (TourismPackage pkg : packages) {
            allPackages.append(pkg.toString());
        }
        FileHandler.writeToFile(fileName, false, allPackages.toString());
    }

    public static int getNextID() {
        return ++id;
    }

    public static ArrayList<TourismPackage> getPackages() {
        return packages;
    }

    // Get the PackageTree (BST)
    public static PackageTree getPackageTree() {
        return packageTree;
    }
}
