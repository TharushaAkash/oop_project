package com.project.model;

public class TourismPackage {
    private String id;
    private String name;
    private String description;
    private String price;
    private String imageFileName;
    private int durationDays;


    public TourismPackage(String id, String name, String description, String price, String imagefileName, int durationDays) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageFileName = imagefileName;
        this.durationDays = durationDays;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price = price;
    }


    public String getImageFileName(){
        return imageFileName;
    }
    public void setImageFileName(String imageFileName){
        this.imageFileName = imageFileName;
    }
    public int getDurationDays(){
        return durationDays;
    }
    public void setDurationDays(int durationDays){
        this.durationDays = durationDays;
    }


    public void displayDetails(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
        System.out.println("ImageFileName: " + imageFileName);
        System.out.println("Duration: " + durationDays);
    }



    @Override
    public String toString() {
        return id + "," + name + "," + description + "," + price + "," + imageFileName + "," + durationDays + "\n";
    }

}
