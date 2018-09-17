package com.example.piiik98.designthinkingapp;

public class Upload {

    public String imageName;
    public String imageURL;

    public Upload() {

    }

    public Upload(String imageName, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;

    }
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
