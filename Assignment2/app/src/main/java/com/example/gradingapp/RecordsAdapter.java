package com.example.gradingapp;

public class RecordsAdapter {
    private int id;
    private String firstName;
    private String lastName;
    private int marks;
    private int credit;
    private String course;

    //getters and setters
    public void setId(int id){this.id = id;}
    public void setFirstName(String firstName){this.firstName= firstName;}
    public void setLastName(String lastName){this.lastName= lastName;}
    public void setMarks(int marks){this.marks = marks;}
    public void setCredit(int credit){this.credit = credit;}
    public void setCourse(String course){this.course = course;}

    public int getId(){return this.id;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public int getMarks(){return this.marks;}
    public int getCredit(){return this.credit;}
    public String getCourse(){return this.course;}
}
