package com.digitalnomads.android.ui.networking;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalnomads.android.R;
import com.digitalnomads.android.models.UserModel;
import com.google.android.material.button.MaterialButton;


import java.util.ArrayList;

import kotlin.jvm.JvmStatic;

public class NetworkingFragment extends Fragment {

    final int widthInDp = 350;

    private NetworkingViewModel mViewModel;

    public static NetworkingFragment newInstance() {
        return new NetworkingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(NetworkingViewModel.class);

        View view = inflater.inflate(R.layout.fragment_netwroking, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.list_working_people);

        // Creation of the cards
        float scale = getResources().getDisplayMetrics().density;
        int widthInPixels = (int) (widthInDp * scale + 0.5f);

        for (int i = 0; i < mViewModel.users.size(); i++) {
            UserModel user = mViewModel.users.get(i);

            View card_working_person = inflater.inflate(R.layout.card_working_person, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    widthInPixels, // width in pixels or dp
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            card_working_person.setLayoutParams(layoutParams);

            // Setting the image of the card
            ImageView image = (ImageView) card_working_person.findViewById(R.id.imageWorkingPartner);
            image.setImageDrawable(getResources().getDrawable(user.getIdImage(), null));

            // Setting the name of the card
            TextView name = (TextView) card_working_person.findViewById(R.id.workingPartnerFullName);
            name.setText(user.getFullName());

            // Setting the availability of the card
            TextView availability = (TextView) card_working_person.findViewById(R.id.availability);
            availability.setText("Available " + user.getAvailability());

            // Setting the name of the card
            TextView field = (TextView) card_working_person.findViewById(R.id.field);
            field.setText(user.getField());

            // Setting the years of experience of the card
            TextView year_of_experience = (TextView) card_working_person.findViewById(R.id.year_of_experience);
            year_of_experience.setText(user.getYearsOfExperience() + " years of experience");

            // Setting the years of experience of the card
            TextView languages = (TextView) card_working_person.findViewById(R.id.languages);
            languages.setText("Languages " + user.getLanguages());

            linearLayout.addView(card_working_person);
        }

        // Reference the UI element using findViewById()
        Button filter = (Button) view.findViewById(R.id.button_filter_networking);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = LayoutInflater.from(view.getContext()).inflate(R.layout.filter_popup, null);
                ImageView closeButton = popupView.findViewById(R.id.close_popup);

                handleFilterButtons(popupView);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(popupView);
                AlertDialog filterPopup = builder.create();

                // Set an OnClickListener on the close button to dismiss the popup
                closeButton.setOnClickListener(v -> filterPopup.dismiss());

                filterPopup.show();
            }
        });

        return view;
    }

    public void handleFilterButtons(View popupView) {
        ((MaterialButton) popupView.findViewById(R.id.ui_design_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.ui_design_button)), isButtonSelected(R.id.ui_design_button));
        ((MaterialButton) popupView.findViewById(R.id.data_science_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.data_science_button)), isButtonSelected(R.id.data_science_button));
        ((MaterialButton) popupView.findViewById(R.id.physics_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.physics_button)), isButtonSelected(R.id.physics_button));
        ((MaterialButton) popupView.findViewById(R.id.engineering_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.engineering_button)), isButtonSelected(R.id.engineering_button));
        ((MaterialButton) popupView.findViewById(R.id.eng_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.eng_button)), isButtonSelected(R.id.eng_button));
        ((MaterialButton) popupView.findViewById(R.id.spa_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.spa_button)), isButtonSelected(R.id.spa_button));
        ((MaterialButton) popupView.findViewById(R.id.gre_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.gre_button)), isButtonSelected(R.id.gre_button));
        ((MaterialButton) popupView.findViewById(R.id.rus_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.rus_button)), isButtonSelected(R.id.rus_button));
        ((MaterialButton) popupView.findViewById(R.id.fri_17)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.fri_17)), isButtonSelected(R.id.fri_17));
        ((MaterialButton) popupView.findViewById(R.id.sat_18)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.sat_18)), isButtonSelected(R.id.sat_18));
        ((MaterialButton) popupView.findViewById(R.id.sun_19)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.sun_19)), isButtonSelected(R.id.sun_19));
        ((MaterialButton) popupView.findViewById(R.id.mon_20)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.mon_20)), isButtonSelected(R.id.mon_20));
    }

    private boolean isButtonSelected(int idButton) {
        switch (idButton) {
            case R.id.ui_design_button:
                return mViewModel.getFields().contains("UI Design");
            case R.id.data_science_button:
                return mViewModel.getFields().contains("Data Science");
            case R.id.physics_button:
                return mViewModel.getFields().contains("Physics");
            case R.id.engineering_button:
                return mViewModel.getFields().contains("Engineering");
            case R.id.eng_button:
                return mViewModel.getLanguages().contains("English");
            case R.id.spa_button:
                return mViewModel.getLanguages().contains("Spanish");
            case R.id.gre_button:
                return mViewModel.getLanguages().contains("Greek");
            case R.id.rus_button:
                return mViewModel.getLanguages().contains("Russian");
            case R.id.fri_17:
                return mViewModel.getDays().contains("Friday 17");
            case R.id.sat_18:
                return mViewModel.getDays().contains("Saturday 18");
            case R.id.sun_19:
                return mViewModel.getDays().contains("Sunday 19");
            case R.id.mon_20:
                return mViewModel.getDays().contains("Monday 20");
        }
        return false;
    }

    public void initializeButtons(View view, MaterialButton btn, boolean selected) {
        if(selected) {
            int newColor = ContextCompat.getColor(view.getContext(), R.color.ocean_blue);
            btn.setBackgroundTintList(ColorStateList.valueOf(newColor));
            btn.setTextColor(Color.WHITE);
        } else {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            btn.setTextColor(Color.BLACK);
        }
    }


    public void onClickBtnFilter(View view) {
        MaterialButton btn = (MaterialButton) view;

        // True if the user wants to select it now
        boolean selected = btn.getTextColors().getDefaultColor() == Color.BLACK;

        if(selected) {
            int newColor = ContextCompat.getColor(view.getContext(), R.color.ocean_blue);
            btn.setBackgroundTintList(ColorStateList.valueOf(newColor));
            btn.setTextColor(Color.WHITE);
        } else {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            btn.setTextColor(Color.BLACK);
        }

        int filterDistance = mViewModel.getFilterDistance();
        ArrayList<String> fields = mViewModel.getFields();
        ArrayList<String> languages = mViewModel.getLanguages();
        ArrayList<String> days = mViewModel.getDays();
        boolean result;

        switch (view.getId()) {
            case R.id.ui_design_button:
                result = selected ? fields.add("UI Design") : fields.remove("UI Design");
                break;
            case R.id.data_science_button:
                result = selected ? fields.add("Data Science") : fields.remove("Data Science");
                break;
            case R.id.physics_button:
                result = selected ? fields.add("Physics") : fields.remove("Physics");
                break;
            case R.id.engineering_button:
                result = selected ? fields.add("Engineering") : fields.remove("Engineering");
                break;
            case R.id.eng_button:
                result = selected ? languages.add("English") : languages.remove("English");
                break;
            case R.id.spa_button:
                result = selected ? languages.add("Spanish") : languages.remove("Spanish");
                break;
            case R.id.gre_button:
                result = selected ? languages.add("Greek") : languages.remove("Greek");
                break;
            case R.id.rus_button:
                result = selected ? languages.add("Russian") : languages.remove("Russian");
                break;
            case R.id.fri_17:
                result = selected ? days.add("Friday 17") : days.remove("Friday 17");
                break;
            case R.id.sat_18:
                result = selected ? days.add("Saturday 18") : days.remove("Saturday 18");
                break;
            case R.id.sun_19:
                result = selected ? days.add("Sunday 19") : days.remove("Sunday 19");
                break;
            case R.id.mon_20:
                result = selected ? days.add("Monday 20") : days.remove("Monday 20");
        }

        mViewModel.setFields(fields);
        mViewModel.setFilterDistance(filterDistance);
        mViewModel.setLanguages(languages);
        mViewModel.setDays(days);
    }

}