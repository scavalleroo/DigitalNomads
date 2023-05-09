package com.digitalnomads.android.ui.suggested_places;

import androidx.lifecycle.ViewModel;

import com.digitalnomads.android.R;
import com.digitalnomads.android.models.PlaceModel;
import com.digitalnomads.android.models.UserModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestedPlacesViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    ArrayList<PlaceModel> places = new ArrayList<PlaceModel>(7);

    public SuggestedPlacesViewModel() {
        //users.add(new UserModel("Clara Rodriguez", "today", "Linguistic expert", "Spanish, German, French", R.drawable.clara_rodriguez, 2));
       places.add(new PlaceModel("Starbucks", "Calle de la Princessa, 23", "10:00-20:00", "Beverages and food available", new ArrayList<>(Arrays.asList(R.drawable.starbucks_beverage1, R.drawable.starbucks_beverage2, R.drawable.starbucks_beverage3)),R.drawable.startbucks, 4.5));
       places.add(new PlaceModel("Panaria", "Calle de Atocha, 12", "06:00-19:00", "Beverages and food available", new ArrayList<>(Arrays.asList(R.drawable.panaria_beverage1, R.drawable.panaria_beverages2, R.drawable.panaria_beverages3)),R.drawable.panaria, 3.3));
       places.add(new PlaceModel("Faborit", "Plaza de España, 4", "07:00-22:00", "Beverages and food available", new ArrayList<>(Arrays.asList(R.drawable.faborit_beverages2, R.drawable.faborit_beverages1, R.drawable.faborit_beverages3)),R.drawable.faborit, 4.1));
       places.add(new PlaceModel("Santander", "Calle de Atocha, 51", "09:00-20:00", "Beverages available", new ArrayList<>(Arrays.asList(R.drawable.santander_beverages1, R.drawable.santander_beverages2, R.drawable.santander_beverages3)),R.drawable.santander, 3.9));
       places.add(new PlaceModel("La Bicicleta", "Pl. de San Ildefonso, 9", "09:00-21:00", "Beverages and food available", new ArrayList<>(Arrays.asList(R.drawable.bicicleta_beverages1, R.drawable.bicicleta_beverages2, R.drawable.bicicleta_beverages3)),R.drawable.bicicleta, 4.2));


    }

    public void save() {
    }

    public void cancel() {
    }
}