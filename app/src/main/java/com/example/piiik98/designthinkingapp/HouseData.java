package com.example.piiik98.designthinkingapp;

public class HouseData {

    public String bathroom;
    public String bedroom;
    public String date;
    public String name;
    public String location;
    public String price;
    public String image;

    public HouseData(String bathroom, String bedroom, String date, String name, String location, String price, String image) {
        this.bathroom = bathroom;
        this.bedroom = bedroom;
        this.date = date;
        this.name = name;
        this.location = location;
        this.price = price;
        this.image = image;
    }

    public HouseData() {

    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
