package com.example.sos;

class LocationDetails {
    private String longitude;
    private  String latitude;
    private String country;
    private String locality;
    private String address;
    private String date;
    //getters and setters

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setLocality(String locality){
        this.locality = locality;
    }
    public void setDate(String date){
        this.date= date;
    }
    public String getDate(){
        return  this.date;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getLongitude(){return this.longitude;}
    public String getLatitude(){return this.latitude;}
    public String getCountry(){return this.country;}
    public String getLocality(){return this.locality;}
    public String getAddress(){return this.address;}
}
