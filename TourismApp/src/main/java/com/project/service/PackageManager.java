package com.project.service;

import com.project.model.TourismPackage;
import com.project.utils.FileHandler;

import java.util.ArrayList;

public class PackageManager {
    private static ArrayList<TourismPackage> packages = null;
    private static final String fileName = "package.txt";
    private static int id = 0;

    public static void readPackages() {
        if (packages != null)
            return;

        packages = new ArrayList<>();
        String[] data = FileHandler.readFromFile(fileName);

        String packageId = "";
        for (String record : data) {
            String[] fields = record.split(",");
            packageId = fields[0];
            String name = fields[1];
            String description = fields[2];
            String price = fields[3];
            int durationDays = Integer.parseInt(fields[5]);


            String imageFileName = fields.length >= 6 ? fields[4] : "default.jpg"; // fallback if missing
            TourismPackage pkg = new TourismPackage(packageId, name, description, price, imageFileName, durationDays);


            //TourismPackage pkg = new TourismPackage(packageId, name, description, price);

            packages.add(pkg);
        }

        id = Integer.parseInt(packageId);

    }

    public static TourismPackage findPackage(String id) {
        for (TourismPackage pkg : packages) {
            if (pkg.getId().equals(id)) {
                return pkg;
            }
        }
        return null;
    }

    public static void addPackage(String id, String name, String description, String price, String imageFileName, int durationDays) {
        TourismPackage pkg = new TourismPackage(id, name, description, price, imageFileName, durationDays);
        packages.add(pkg);
        FileHandler.writeToFile(fileName, true, pkg.toString());
    }

    public static void removePackage(String id) {
        packages.remove(findPackage(id));
        savePackagesToFile();
    }

    public static void updatePackage(String id, String name, String description, String price, String imageFileName, int durationDays) {
        TourismPackage pkg = findPackage(id);
        if (pkg != null) {
            pkg.setName(name);
            pkg.setDescription(description);
            pkg.setPrice(price);
            pkg.setImageFileName(imageFileName);
            pkg.setDurationDays(durationDays);
        }
        savePackagesToFile();
    }

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
}
