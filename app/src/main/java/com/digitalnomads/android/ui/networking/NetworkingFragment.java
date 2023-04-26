package com.digitalnomads.android.ui.networking;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.digitalnomads.android.R;

public class NetworkingFragment extends Fragment {

    private NetworkingViewModel mViewModel;

    public static NetworkingFragment newInstance() {
        return new NetworkingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_netwroking, container, false);
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