package com.task5.stages;

public class Stage2_Model {
    String text;
    int image;

    public Stage2_Model(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public Stage2_Model()
    {
        this.text ="";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
