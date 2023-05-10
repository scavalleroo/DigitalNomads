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
        users.add(new UserModel("Luca Smith", "Friday 17", "UI Design", "English, Russian", R.drawable.lucia_smith, 10, 4, "Accepted"));
        users.add(new UserModel("Mark Brown", "Monday 20", "UI Design", "Spanish", R.drawable.mark_brown, 3, 5, "Expired"));
        users.add(new UserModel("Monica Rocamura", "Monday 20", "Engineering", "Spanish, Russian", R.drawable.monica_rocamura, 4, 3, "Expired"));
    }
}