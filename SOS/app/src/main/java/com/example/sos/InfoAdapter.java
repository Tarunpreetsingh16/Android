package com.example.sos;

public class InfoAdapter {
    String title;
    String subtitle;
    int image;

    //getters and setters
    public void setTitle(String title){
        this.title = title;
    }

    public void setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }

    public void setImage(int image){
        this.image = image;
    }

    public String getTitle(){return this.title;}
    public String getSubtitle(){return this.subtitle;}
    public int getImage(){return this.image;}
}
