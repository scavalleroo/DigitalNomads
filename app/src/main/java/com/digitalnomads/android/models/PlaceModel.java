package com.digitalnomads.android.models;

import java.util.ArrayList;

public class PlaceModel {
    private String name;
    private String address;
    private String openingsHours;
    private String extra;
    private String offers;
    private int numberOfSeats;
    private ArrayList<Integer> idsImage;
    private String distance;
    private int idMainImage;
    private double stars;

    public PlaceModel(String name, String address, String openingsHours, String extra, String offers, String distance, int numberOfSeats, ArrayList<Integer> idsImage, int idMainImage, double stars) {
        this.name = name;
        this.address = address;
        this.openingsHours = openingsHours;
        this.extra = extra;
        this.offers = offers;
        this.distance = distance;
        this.numberOfSeats = numberOfSeats;
        this.idsImage = idsImage;
        this.idMainImage = idMainImage;
        this.stars = stars;
    }

    public boolean passFilter(ArrayList<Integer> ratings, ArrayList<String> offers, ArrayList<String> distance, int numberOfSeats) {
        if(!ratings.isEmpty()) {
            int min = ratings.get(0); // initialize min with first element
            for (int i = 1; i < ratings.size(); i++) {
                int current = ratings.get(i);
                if (current < min) {
                    min = current; // update min if current is smaller
                }
            }
            if (this.stars < min) {
                return false;
            }
        }

        if(!offers.isEmpty()) {
            return offers.contains(this.offers);
        }

        if(!distance.isEmpty()) {
            return distance.contains(this.distance);
        }

        return numberOfSeats <= this.numberOfSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningsHours() {
        return openingsHours;
    }

    public void setOpeningsHours(String openingsHours) {
        this.openingsHours = openingsHours;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public ArrayList<Integer> getIdsImage() {
        return idsImage;
    }

    public void setIdsImage(ArrayList<Integer> idsImage) {
        this.idsImage = idsImage;
    }

    public int getIdMainImage() {
        return idMainImage;
    }

    public void setIdMainImage(int idMainImage) {
        this.idMainImage = idMainImage;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
