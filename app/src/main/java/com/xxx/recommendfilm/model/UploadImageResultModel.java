package com.xxx.recommendfilm.model;

public class UploadImageResultModel {
    String imagePath;

    public UploadImageResultModel(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
