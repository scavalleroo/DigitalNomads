package com.digitalnomads.android.ui.networking;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.digitalnomads.android.R;

import java.util.Arrays;
import java.util.List;

public class NetworkingFragment extends Fragment {

    private NetworkingViewModel mViewModel;

    public static NetworkingFragment newInstance() {
        return new NetworkingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_netwroking, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.list_working_people);

        List<Integer> ids = Arrays.asList(R.drawable.clara_rodriguez, R.drawable.maria_gracia, R.drawable.monica_rocamura, R.drawable.javier_fernandez, R.drawable.lucia_smith, R.drawable.mark_brown);

        int widthInDp = 350;
        float scale = getResources().getDisplayMetrics().density;
        int widthInPixels = (int) (widthInDp * scale + 0.5f);

        for (int i = 0; i < ids.size(); i++) {
            View card_working_person = inflater.inflate(R.layout.card_working_person, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    widthInPixels, // width in pixels or dp
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            card_working_person.setLayoutParams(layoutParams);

            ImageView image = (ImageView) card_working_person.findViewById(R.id.imageWorkingPartner);
            image.setImageDrawable(getResources().getDrawable(ids.get(i), null));
            linearLayout.addView(card_working_person);
        }

        // Reference the UI element using findViewById()
        Button filter = (Button) view.findViewById(R.id.button_filter_networking);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View popupView = LayoutInflater.from(view.getContext()).inflate(R.layout.filter_popup, null);
                ImageView closeButton = popupView.findViewById(R.id.close_popup);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(popupView);
                AlertDialog filterPopup = builder.create();

                // Set an OnClickListener on the close button to dismiss the popup
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterPopup.dismiss();
                    }
                });

                filterPopup.show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NetworkingViewModel.class);
        // TODO: Use the ViewModel
    }

}