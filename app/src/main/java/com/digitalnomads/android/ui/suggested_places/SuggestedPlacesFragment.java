package com.digitalnomads.android.ui.suggested_places;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Range;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalnomads.android.R;
import com.digitalnomads.android.models.PlaceModel;
import com.digitalnomads.android.models.UserModel;
import com.digitalnomads.android.ui.notifications.NotificationsFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;

public class SuggestedPlacesFragment extends Fragment {

    final int widthInDp = 310;
    private SuggestedPlacesViewModel mViewModel;
    private TextView txtSeats;
    private TextView txtRating;
    private TextView txtTechFeatures;
    private TextView txtOffers;
    private TextView txtClosestTo;
    private boolean popUpOpen = false;
    private LinearLayout listPlaces;
    private LinearLayout.LayoutParams layoutParams;
    private LayoutInflater inflater;

    private UserModel userSelected;

    public static SuggestedPlacesFragment newInstance() {
        return new SuggestedPlacesFragment();
    }

    public SuggestedPlacesFragment() {}

    public SuggestedPlacesFragment(UserModel userSelected) {
        this.userSelected = userSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater_param, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SuggestedPlacesViewModel.class);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Suggest Place");
        }

        this.inflater = inflater_param;
        View view = inflater.inflate(R.layout.fragment_suggested_places, container, false);

        listPlaces = (LinearLayout) view.findViewById(R.id.list_places);
        txtSeats = (TextView) view.findViewById(R.id.txt_seats);
        txtRating = (TextView) view.findViewById(R.id.txt_ratings);
        txtTechFeatures = (TextView) view.findViewById(R.id.txt_features);
        txtOffers = (TextView) view.findViewById(R.id.txt_offers);
        txtClosestTo = (TextView) view.findViewById(R.id.txt_closerTo);

        // Creation of the cards
        float scale = getResources().getDisplayMetrics().density;
        int widthInPixels = (int) (widthInDp * scale + 0.5f);
        layoutParams = new LinearLayout.LayoutParams(
                widthInPixels, // width in pixels or dp
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        buildCards();

        // Reference the UI element using findViewById()
        Button filter = (Button) view.findViewById(R.id.button_filter_places);

        filter.setOnClickListener(viewClick -> {
            View popupView = LayoutInflater.from(viewClick.getContext()).inflate(R.layout.filter_places, null);

            RangeSlider rangeNumberSeats = (RangeSlider) popupView.findViewById(R.id.slider_seats);
            if(mViewModel.getNumberSeats() > 0) {
                rangeNumberSeats.setValues((float) mViewModel.getNumberSeats());
            }
            rangeNumberSeats.addOnChangeListener(new RangeSlider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                    mViewModel.setNumberSeats((int) value);
                }
            });

            ImageView closeButton = popupView.findViewById(R.id.close_popup);
            Button btnSave = popupView.findViewById(R.id.save);

            handleFilterButtons(popupView);

            AlertDialog.Builder builder = new AlertDialog.Builder(viewClick.getContext());
            builder.setView(popupView);
            AlertDialog filterPopup = builder.create();

            // Set an OnClickListener on the close button to dismiss the popup
            closeButton.setOnClickListener(v -> {
                filterPopup.dismiss();
                mViewModel.cancel();
                setTextFilter();
            });

            btnSave.setOnClickListener(v -> {
                filterPopup.dismiss();
                mViewModel.save();
                buildCards();
                setTextFilter();
            });

            filterPopup.show();
        });

        return view;
    }

    private void setTextFilter() {
        txtClosestTo.setText(!mViewModel.getDistance().isEmpty() ? String.join(", ", mViewModel.getDistance().toArray(new String[0])) : "");
        txtSeats.setText(mViewModel.getNumberSeats() != 0 ? mViewModel.getNumberSeats()  + " seats" : "");
        txtRating.setText(!mViewModel.getRating().isEmpty()
                ? String.join(", ", mViewModel.getRating().stream()
                .map(Object::toString)
                .map(s -> s + "+")
                .toArray(String[]::new))
                : "");
        txtOffers.setText(!mViewModel.getOffers().isEmpty() ? String.join(", ", mViewModel.getOffers().toArray(new String[0]))  : "");
        txtTechFeatures.setText(!mViewModel.getFeatures().isEmpty() ? String.join(", ", mViewModel.getFeatures().toArray(new String[0]))  : "");
    }

    private void buildCards() {
        listPlaces.removeAllViews();
        for (int i = 0; i < mViewModel.places.size(); i++) {
            View card_places = inflater.inflate(R.layout.card_places, null);
            card_places.setLayoutParams(layoutParams);

            PlaceModel place = mViewModel.places.get(i);

            // Setting the main image of the card
            ImageView image = (ImageView) card_places.findViewById(R.id.imagePlaceToWork);
            image.setImageDrawable(getResources().getDrawable(place.getIdMainImage(), null));

            // Setting the name of the card
            TextView name = (TextView) card_places.findViewById(R.id.placeToWorkName);
            name.setText(place.getName());

            // Setting the address of the card
            TextView address = (TextView) card_places.findViewById(R.id.address);
            address.setText(place.getAddress());

            // Setting the reviews of the card
            TextView reviews = (TextView) card_places.findViewById(R.id.reviews);
            reviews.setText(place.getStars() + " out of 5");

            // Setting the extra information of the card
            TextView extra = (TextView) card_places.findViewById(R.id.additional_info);
            extra.setText(place.getExtra());

            // Setting the opening hours of the card
            TextView opening_hours = (TextView) card_places.findViewById(R.id.opening_hours);
            opening_hours.setText(place.getOpeningsHours());

            // Setting the secondary images of the card
            ArrayList<Integer> images = place.getIdsImage();

            ImageView imageView1 = (ImageView) card_places.findViewById(R.id.secondaryImage1);
            ImageView imageView2 = (ImageView) card_places.findViewById(R.id.secondaryImage2);
            ImageView imageView3 = (ImageView) card_places.findViewById(R.id.secondaryImage3);

            imageView1.setImageResource(images.get(0));
            imageView2.setImageResource(images.get(1));
            imageView3.setImageResource(images.get(2));

            ((TextView) card_places.findViewById(R.id.view_map)).setOnClickListener(viewClick -> {
                Toast.makeText(getContext(), R.string.this_page_is_under_construction, Toast.LENGTH_SHORT).show();
            });

            // Add swipe up gesture
            int finalI = i;

            card_places.setOnTouchListener(new View.OnTouchListener() {
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
                                    sendSuggestion(v.getContext(), place, finalI);
                                }
                                break;
                            case MotionEvent.ACTION_CANCEL:
                            case MotionEvent.ACTION_UP:
                                // Calculate the final distance of the swipe when the user lifts their finger
                                endY = event.getY();
                                distance = endY - startY;

                                // If the swipe distance is greater than a threshold, show the pop up
                                if (distance < -200) {
                                    sendSuggestion(v.getContext(), place, finalI);
                                }
                                break;
                        }
                    }
                    return true;
                }
            });

            listPlaces.addView(card_places);
        }
    }

    private void sendSuggestion(Context context, PlaceModel place, int index) {
        // Define the text for the pop up
        this.popUpOpen = true;
        String message = "Do you want to suggest " + place.getName() + " in " + place.getAddress() + "?";

        // Build the alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Yes button
                        // Do something here
                        //mViewModel.sendRequest(index);
                        buildCards();
                        dialog.cancel();
                        Toast.makeText(context, "Suggestion of " + place.getName() + " sent!", Toast.LENGTH_SHORT).show();
                        popUpOpen = false;


                        NotificationsFragment notificationsFragment = new NotificationsFragment(true, userSelected, place);
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, notificationsFragment, "NotificationFragment");
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setReorderingAllowed(true);
                        fragmentTransaction.commit();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked Cancel button
                        dialog.cancel();
                        popUpOpen = false;
                    }
                });

        // Show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void handleFilterButtons(View popupView) {
        ((MaterialButton) popupView.findViewById(R.id.WiFi_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.WiFi_button)), isButtonSelected(R.id.WiFi_button));
        ((MaterialButton) popupView.findViewById(R.id.usb_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.usb_button)), isButtonSelected(R.id.usb_button));
        ((MaterialButton) popupView.findViewById(R.id.sockets_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.sockets_button)), isButtonSelected(R.id.sockets_button));
        ((MaterialButton) popupView.findViewById(R.id.charger_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.charger_button)), isButtonSelected(R.id.charger_button));
        ((MaterialButton) popupView.findViewById(R.id.rating1_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.rating1_button)), isButtonSelected(R.id.rating1_button));
        ((MaterialButton) popupView.findViewById(R.id.rating2_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.rating2_button)), isButtonSelected(R.id.rating2_button));
        ((MaterialButton) popupView.findViewById(R.id.rating3_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.rating3_button)), isButtonSelected(R.id.rating3_button));
        ((MaterialButton) popupView.findViewById(R.id.rating4_button)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.rating4_button)), isButtonSelected(R.id.rating4_button));
        ((MaterialButton) popupView.findViewById(R.id.offers_digitalNomads)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.offers_digitalNomads)), isButtonSelected(R.id.offers_digitalNomads));
        ((MaterialButton) popupView.findViewById(R.id.offers_families)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.offers_families)), isButtonSelected(R.id.offers_families));
        ((MaterialButton) popupView.findViewById(R.id.closest_you)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.closest_you)), isButtonSelected(R.id.closest_you));
        ((MaterialButton) popupView.findViewById(R.id.closest_both)).setOnClickListener(this::onClickBtnFilter);
        initializeButtons(popupView, ((MaterialButton) popupView.findViewById(R.id.closest_both)), isButtonSelected(R.id.closest_both));
    }

    private boolean isButtonSelected(int idButton) {
        switch (idButton) {
            case R.id.WiFi_button:
                return mViewModel.getFeatures().contains("WiFi");
            case R.id.usb_button:
                return mViewModel.getFeatures().contains("USB Charger");
            case R.id.sockets_button:
                return mViewModel.getFeatures().contains("Sockets");
            case R.id.charger_button:
                return mViewModel.getFeatures().contains("Wireless Charger");
            case R.id.rating1_button:
                return mViewModel.getRating().contains(1);
            case R.id.rating2_button:
                return mViewModel.getRating().contains(2);
            case R.id.rating3_button:
                return mViewModel.getRating().contains(3);
            case R.id.rating4_button:
                return mViewModel.getRating().contains(4);
            case R.id.offers_digitalNomads:
                return mViewModel.getOffers().contains("Digital Nomads");
            case R.id.offers_families:
                return mViewModel.getOffers().contains("Families");
            case R.id.closest_you:
                return mViewModel.getDistance().contains("Closest to you");
            case R.id.closest_both:
                return mViewModel.getDistance().contains("Closest to both");
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

        initializeButtons(view, btn, selected);

        int filterSeats = mViewModel.getNumberSeats();
        ArrayList<String> features = mViewModel.getFeatures();
        ArrayList<Integer> ratings = mViewModel.getRating();
        ArrayList<String> offers = mViewModel.getOffers();
        ArrayList<String> distance = mViewModel.getDistance();
        boolean result;

        switch (view.getId()) {
            case R.id.WiFi_button:
                result = selected ? features.add("WiFi") : features.remove("WiFi");
                break;
            case R.id.usb_button:
                result = selected ? features.add("USB Charger") : features.remove("USB Charger");
                break;
            case R.id.sockets_button:
                result = selected ? features.add("Sockets") : features.remove("Sockets");
                break;
            case R.id.charger_button:
                result = selected ? features.add("Wireless Charger") : features.remove("Wireless Charger");
                break;
            case R.id.rating1_button:
                result = selected ? ratings.add(1) : ratings.remove((Integer) 1);
                break;
            case R.id.rating2_button:
                result = selected ? ratings.add(2) : ratings.remove((Integer) 2);
                break;
            case R.id.rating3_button:
                result = selected ? ratings.add(3) : ratings.remove((Integer) 3);
                break;
            case R.id.rating4_button:
                result = selected ? ratings.add(4) : ratings.remove((Integer) 4);
                break;
            case R.id.offers_digitalNomads:
                result = selected ? offers.add("Digital Nomads") : offers.remove("Digital Nomads");
                break;
            case R.id.offers_families:
                result = selected ? offers.add("Families") : offers.remove("Families");
                break;
            case R.id.closest_you:
                result = selected ? distance.add("Closest to you") : distance.remove("Closest to you");
                break;
            case R.id.closest_both:
                result = selected ? distance.add("Closest to both") : distance.remove("Closest to both");
        }

        mViewModel.setNumberSeats(filterSeats);
        mViewModel.setFeatures(features);
        mViewModel.setRating(ratings);
        mViewModel.setOffers(offers);
        mViewModel.setDistance(distance);
    }
}