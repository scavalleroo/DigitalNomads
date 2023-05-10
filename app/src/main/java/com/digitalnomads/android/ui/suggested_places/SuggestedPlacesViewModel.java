package com.digitalnomads.android.ui.suggested_places;

import androidx.lifecycle.ViewModel;

import com.digitalnomads.android.R;
import com.digitalnomads.android.models.PlaceModel;

import java.util.ArrayList;
import java.util.Arrays;

public class SuggestedPlacesViewModel extends ViewModel {
    ArrayList<PlaceModel> places = new ArrayList<PlaceModel>(5);

    private int numberSeats = 1;
    private ArrayList<String> features;
    private ArrayList<Integer> rating;
    private ArrayList<String> offers;
    private ArrayList<String> distance;
    private int old_numberSeats = 1;
    private ArrayList<String> old_features;
    private ArrayList<Integer> old_rating;
    private ArrayList<String> old_offers;
    private ArrayList<String> old_distance;

    ArrayList<PlaceModel> old_places;

    public SuggestedPlacesViewModel() {
        //users.add(new UserModel("Clara Rodriguez", "today", "Linguistic expert", "Spanish, German, French", R.drawable.clara_rodriguez, 2));
       places.add(new PlaceModel("Starbucks", "Calle de la Princessa, 23", "10:00-20:00", "Beverages and food available", "Digital Nomads", "", 2, new ArrayList<>(Arrays.asList(R.drawable.starbucks_beverage1, R.drawable.starbucks_beverage2, R.drawable.starbucks_beverage3)),R.drawable.startbucks, 4.5));
       places.add(new PlaceModel("Panaria", "Calle de Atocha, 12", "06:00-19:00", "Beverages and food available", "Digital Nomads", "",5, new ArrayList<>(Arrays.asList(R.drawable.panaria_beverage1, R.drawable.panaria_beverages2, R.drawable.panaria_beverages3)),R.drawable.panaria, 3.3));
       places.add(new PlaceModel("Faborit", "Plaza de España, 4", "07:00-22:00", "Beverages and food available", "Digital Nomads", "Closest to both",7, new ArrayList<>(Arrays.asList(R.drawable.faborit_beverages2, R.drawable.faborit_beverages1, R.drawable.faborit_beverages3)),R.drawable.faborit, 4.1));
       places.add(new PlaceModel("Santander", "Calle de Atocha, 51", "09:00-20:00", "Beverages available", "Families", "Closest to you",12, new ArrayList<>(Arrays.asList(R.drawable.santander_beverages1, R.drawable.santander_beverages2, R.drawable.santander_beverages3)),R.drawable.santander, 3.9));
       places.add(new PlaceModel("La Bicicleta", "Pl. de San Ildefonso, 9", "09:00-21:00", "Beverages and food available", "Families", "",4, new ArrayList<>(Arrays.asList(R.drawable.bicicleta_beverages1, R.drawable.bicicleta_beverages2, R.drawable.bicicleta_beverages3)),R.drawable.bicicleta, 4.2));
       old_places = new ArrayList<PlaceModel>(places);

       features = new ArrayList<>();
       rating = new ArrayList<>();
       offers = new ArrayList<>();
       distance = new ArrayList<>();

       old_features = new ArrayList<>();
       old_rating = new ArrayList<>();
       old_offers = new ArrayList<>();
       old_distance = new ArrayList<>();
    }

    public void save() {
        old_numberSeats = numberSeats;
        old_features = new ArrayList<>(features);
        old_rating = new ArrayList<>(rating);
        old_offers = new ArrayList<>(offers);
        old_distance = new ArrayList<>(distance);

        applyFilters();
    }

    public void applyFilters() {
        places = new ArrayList<PlaceModel>(old_places);
        for (int i = this.places.size() - 1; i >= 0; i--) {
            PlaceModel place = this.places.get(i);
            if (!place.passFilter(rating, offers, distance, numberSeats)) {
                this.places.remove(i);
            }
        }
    }

    public void cancel() {
        numberSeats = old_numberSeats;
        features = new ArrayList<>(old_features);
        rating = new ArrayList<>(old_rating);
        offers = new ArrayList<>(old_offers);
        distance = new ArrayList<>(old_distance);
    }

    public ArrayList<PlaceModel> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<PlaceModel> places) {
        this.places = places;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    public ArrayList<String> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<String> features) {
        this.features = features;
    }

    public ArrayList<Integer> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Integer> rating) {
        this.rating = rating;
    }

    public ArrayList<String> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<String> offers) {
        this.offers = offers;
    }

    public ArrayList<String> getDistance() {
        return distance;
    }

    public void setDistance(ArrayList<String> distance) {
        this.distance = distance;
    }

    public int getOld_numberSeats() {
        return old_numberSeats;
    }

    public void setOld_numberSeats(int old_numberSeats) {
        this.old_numberSeats = old_numberSeats;
    }

    public ArrayList<String> getOld_features() {
        return old_features;
    }

    public void setOld_features(ArrayList<String> old_features) {
        this.old_features = old_features;
    }

    public ArrayList<Integer> getOld_rating() {
        return old_rating;
    }

    public void setOld_rating(ArrayList<Integer> old_rating) {
        this.old_rating = old_rating;
    }

    public ArrayList<String> getOld_offers() {
        return old_offers;
    }

    public void setOld_offers(ArrayList<String> old_offers) {
        this.old_offers = old_offers;
    }

    public ArrayList<String> getOld_distance() {
        return old_distance;
    }

    public void setOld_distance(ArrayList<String> old_distance) {
        this.old_distance = old_distance;
    }

    public ArrayList<PlaceModel> getOld_places() {
        return old_places;
    }

    public void setOld_places(ArrayList<PlaceModel> old_places) {
        this.old_places = old_places;
    }
}