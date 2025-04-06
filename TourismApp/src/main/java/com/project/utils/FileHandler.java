package com.example.tourismapp.utils;

import java.io.*;

public class FileHandler {
    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static boolean createFile(String fileName) {
        File file = new File(fileName);
        try {
            return file.createNewFile();
        } catch (Exception e) {
            System.out.println("Error creating file: " + fileName);
            return false;
        }
    }

    public static boolean writeToFile(String fileName, boolean append, String data) {
        if (!isFileExist(fileName)) {
            if (!createFile(fileName)) return false;
        }

        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(data);
            return true;
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
            return false;
        }
    }

    public static String[] readFromFile(String fileName) {
        if (!isFileExist(fileName)) return new String[0];

        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }

        return data.toString().isEmpty() ? new String[0] : data.toString().split("\n");
    }
}
