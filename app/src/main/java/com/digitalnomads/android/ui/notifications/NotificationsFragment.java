package com.digitalnomads.android.ui.notifications;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.digitalnomads.android.R;
import com.digitalnomads.android.databinding.FragmentNotificationsBinding;
import com.digitalnomads.android.models.UserModel;
import com.digitalnomads.android.ui.networking.NetworkingViewModel;

public class NotificationsFragment extends Fragment {

    final int widthInDp = 310;
    private NotificationsViewModel nViewModel;
    private LinearLayout listNotificationPeople;
    private LinearLayout.LayoutParams layoutParams;
    private LayoutInflater inflater;
    private boolean popUpOpen = false;


    public View onCreateView(@NonNull LayoutInflater inflater_param,
                             ViewGroup container, Bundle savedInstanceState) {
        nViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        this.inflater = inflater_param;
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        listNotificationPeople = (LinearLayout) view.findViewById(R.id.list_notifications);

        float scale = getResources().getDisplayMetrics().density;
        int widthInPixels = (int) (widthInDp * scale + 0.5f);
        layoutParams = new LinearLayout.LayoutParams(
                widthInPixels, // width in pixels or dp
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        buildCards();

        Button filter = (Button) view.findViewById(R.id.button_see_all_notifications);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewClick) {
                Toast.makeText(view.getContext(), R.string.this_page_is_under_construction, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void buildCards() {
        listNotificationPeople.removeAllViews();
        for (int i = 0; i < nViewModel.users.size(); i++) {
            View card_working_person = inflater.inflate(R.layout.card_notification_person, null);
            card_working_person.setLayoutParams(layoutParams);

            UserModel user = nViewModel.users.get(i);

            // Setting the image of the card
            ImageView image = (ImageView) card_working_person.findViewById(R.id.imageWorkingPartner);
            image.setImageDrawable(getResources().getDrawable(user.getIdImage(), null));

            // Setting the name of the card
            TextView name = (TextView) card_working_person.findViewById(R.id.workingPartnerFullName);
            name.setText(user.getFullName());

            // Setting the name of the card
            TextView field = (TextView) card_working_person.findViewById(R.id.field);
            field.setText(user.getField());

            // Setting the years of experience of the card
            TextView year_of_experience = (TextView) card_working_person.findViewById(R.id.year_of_experience);
            year_of_experience.setText(user.getYearsOfExperience() + " years of experience");

            // Setting the years of experience of the card
            TextView languages = (TextView) card_working_person.findViewById(R.id.languages);
            languages.setText("Languages " + user.getLanguages());

            TextView requestStatus = (TextView) card_working_person.findViewById(R.id.request_status);
            requestStatus.setText(user.getRequestStatus());

            // Add swipe up gesture
            int finalI = i;
            card_working_person.setOnTouchListener(new View.OnTouchListener() {
                private float startY;
                float distance;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    float endY;
                    float distance;
                    if(!popUpOpen) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                // Record the starting position of the touch
                                startY = event.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                // Calculate the distance of the swipe as the user's finger moves
                                float currentY = event.getY();
                                distance = currentY - startY;

                                // If the swipe distance is greater than a threshold, show the pop up
                                if (distance < -200) {
                                    // sendRequest(v.getContext(), user, finalI);
                                }
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                // Calculate the final distance of the swipe when the user lifts their finger
                                endY = event.getY();
                                distance = endY - startY;

                                // If the swipe distance is greater than a threshold, show the pop up
                                if (distance < -200) {
                                    //sendRequest(v.getContext(), user, finalI);
                                }
                                break;
                        }
                    }
                    return true;
                }
            });

            listNotificationPeople.addView(card_working_person);
        }
    }

}