package com.digitalnomads.android.ui.suggested_places;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalnomads.android.R;
import com.digitalnomads.android.databinding.ActivityMainBinding;
import com.digitalnomads.android.models.PlaceModel;
import com.digitalnomads.android.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SuggestedPlacesFragment extends Fragment {

    final int widthInDp = 310;
    private SuggestedPlacesViewModel mViewModel;
    private TextView txtSeats;
    private TextView txtTechFeatures;
    private TextView txtOffers;
    private TextView txtClosestTo;
    private boolean popUpOpen = false;
    private LinearLayout listPlaces;
    private LinearLayout.LayoutParams layoutParams;
    private LayoutInflater inflater;

    public static SuggestedPlacesFragment newInstance() {
        return new SuggestedPlacesFragment();
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

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewClick) {
                View popupView = LayoutInflater.from(viewClick.getContext()).inflate(R.layout.filter_places, null);
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
            }
        });

        return view;
    }

    private void handleFilterButtons(View popupView) {
    }

    private void setTextFilter() {
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
            TextView name = (TextView) card_places.findViewById(R.id.PlaceToWorkName);
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

            // Add swipe up gesture
            int finalI = i;

            listPlaces.addView(card_places);
        }
    }
}