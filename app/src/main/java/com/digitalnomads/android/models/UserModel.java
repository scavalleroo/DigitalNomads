package com.digitalnomads.android.models;

import java.util.ArrayList;

public class UserModel {
    private String fullName;
    private String availability;
    private String field;
    private String languages;
    private int idImage;
    private int yearsOfExperience;

    public UserModel(String fullName, String availability, String field, String languages, int idImage, int yearsOfExperience) {
        this.fullName = fullName;
        this.availability = availability;
        this.field = field;
        this.languages = languages;
        this.idImage = idImage;
        this.yearsOfExperience = yearsOfExperience;
    }


    public boolean passFilter(ArrayList<String> fields, ArrayList<String> languages, ArrayList<String> availabilities, int distance) {
        if(!fields.isEmpty() && !fields.contains(fields) || !availabilities.isEmpty() && !availabilities.contains(this.availability)) {
            return false;
        }
        if(!languages.isEmpty()) {
            String[] arr = this.languages.split(",");
            for (String s : arr) {
                if (languages.contains(s)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
