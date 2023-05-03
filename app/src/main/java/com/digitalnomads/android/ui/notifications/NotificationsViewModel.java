package com.digitalnomads.android.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.digitalnomads.android.R;
import com.digitalnomads.android.models.UserModel;

import java.util.ArrayList;

public class NotificationsViewModel extends ViewModel {

    ArrayList<UserModel> users = new ArrayList<UserModel>(6);

    public NotificationsViewModel() {
        users.add(new UserModel("Clara Rodriguez", "today", "Linguistic expert", "Spanish, German, French", R.drawable.clara_rodriguez, 2, "Accepted"));
        users.add(new UserModel("Javier Fernandez", "today", "Physic expert", "Spanish, English", R.drawable.javier_fernandez, 3, "Waiting"));
        users.add(new UserModel("Lucia Smith", "today", "Data Science student", "English, German", R.drawable.lucia_smith, 4, "Waiting"));
        users.add(new UserModel("Maria Gracia", "today", "Politics and Economics", "Spanish, English", R.drawable.maria_gracia, 5, "Expired"));
        users.add(new UserModel("Mark Brown", "today", "UI Design", "English", R.drawable.mark_brown, 3, "Expired"));
        users.add(new UserModel("Monica Rocamura", "today", "Musician", "Spanish, English", R.drawable.monica_rocamura, 4, "Expired"));
    }
}