package com.example.piiik98.designthinkingapp;

public class User {

    public String name;
    public String email;
    public String pass;
    public String pnumber;
    public String fLanguage;
    public String sLanguage;
    public String from;
    public String nationality;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String email, String pass, String pnumber, String fLanguage, String sLanguage, String from, String nationality) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.pnumber = pnumber;
        this.fLanguage = fLanguage;
        this.sLanguage = sLanguage;
        this.from = from;
        this.nationality = nationality;
    }

    public User(String username, String email, String pass, String pnumber) {
        this.name = username;
        this.email = email;
        this.pass = pass;
        this.pnumber = pnumber;

    }

    public String getfLanguage() {
        return fLanguage;
    }

    public void setfLanguage(String fLanguage) {
        this.fLanguage = fLanguage;
    }

    public String getsLanguage() {
        return sLanguage;
    }

    public void setsLanguage(String sLanguage) {
        this.sLanguage = sLanguage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }
}