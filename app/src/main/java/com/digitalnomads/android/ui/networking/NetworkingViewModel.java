package com.digitalnomads.android.ui.networking;

import androidx.lifecycle.ViewModel;

import com.digitalnomads.android.R;
import com.digitalnomads.android.models.UserModel;

import java.util.ArrayList;

public class NetworkingViewModel extends ViewModel {
    ArrayList<UserModel> users = new ArrayList<UserModel>(6);
    private int filterDistance;
    private ArrayList<String> fields;
    private ArrayList<String> languages;
    private ArrayList<String> days;

    private int old_filterDistance;
    private ArrayList<String> old_fields;
    private ArrayList<String> old_languages;
    private ArrayList<String> old_days;

    ArrayList<UserModel> old_users;
    public NetworkingViewModel() {
        users.add(new UserModel("Clara Rodriguez", "today", "Linguistic expert", "Spanish, German, French", R.drawable.clara_rodriguez, 2));
        users.add(new UserModel("Javier Fernandez", "today", "Physic expert", "Spanish, English", R.drawable.javier_fernandez, 3));
        users.add(new UserModel("Lucia Smith", "today", "Data Science student", "English, German", R.drawable.lucia_smith, 4));
        users.add(new UserModel("Maria Gracia", "today", "Politics and Economics", "Spanish, English", R.drawable.maria_gracia, 5));
        users.add(new UserModel("Mark Brown", "today", "UI Design", "English", R.drawable.mark_brown, 3));
        users.add(new UserModel("Monica Rocamura", "today", "Musician", "Spanish, English", R.drawable.monica_rocamura, 4));
        old_users = new ArrayList<UserModel>(users);

        fields = new ArrayList<>();
        languages = new ArrayList<>();
        days = new ArrayList<>();

        old_fields = new ArrayList<>();
        old_languages = new ArrayList<>();
        old_days = new ArrayList<>();
    }

    public void save() {
        old_days = new ArrayList<String>(days);
        old_languages = new ArrayList<String>(languages);
        old_fields = new ArrayList<String>(fields);
        old_filterDistance = filterDistance;
        applyFilters();
    }

    public void sendRequest(int index) {
        this.users.remove(index);
        this.old_users.remove(index);
    }

    public void applyFilters() {
        users = new ArrayList<UserModel>(old_users);
        for (int i = this.users.size() - 1; i >= 0; i--) {
            UserModel user = this.users.get(i);
            if (!user.passFilter(fields, languages, days, filterDistance)) {
                this.users.remove(i); // remove the user from the ArrayList
                System.out.println("Hello");
            } else {
                System.out.println("Hello World");
            }
        }
    }

    public void cancel() {
        days = new ArrayList<String>(old_days);
        languages = new ArrayList<String>(old_languages);
        fields = new ArrayList<String>(old_fields);
        filterDistance = old_filterDistance;
    }

    public int getFilterDistance() {
        return filterDistance;
    }

    public void setFilterDistance(int filterDistance) {
        this.filterDistance = filterDistance;
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public void setFields(ArrayList<String> fields) {
        this.fields = fields;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<String> getDays() {
        return days;
    }

    public void setDays(ArrayList<String> days) {
        this.days = days;
    }

}