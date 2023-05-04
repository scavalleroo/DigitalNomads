package com.digitalnomads.android.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.digitalnomads.android.MainActivity;
import com.digitalnomads.android.R;
import com.digitalnomads.android.ui.networking.NetworkingFragment;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout swipe_left = view.findViewById(R.id.layout_swipe_left);
        LinearLayout swipe_right = view.findViewById(R.id.layout_swipe_right);

        // Attach the gesture detector to the linear layout views
        swipe_left.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        swipe_right.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        return view;
    }

    final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                System.out.println("Hello 101");
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    System.out.println("Hello 1");
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        System.out.println("Hello 2");
                        if (diffX > 0) {
                            System.out.println("Hello 3");
                            // Swipe right
                            result = true;
                            // Handle swipe right action
                            handleSwipeRight();
                        } else {
                            System.out.println("Hello 4");
                            // Swipe left
                            result = true;
                            // Handle swipe left action
                            handleSwipeLeft();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    });

    // Define the swipe left and swipe right handler methods
    private void handleSwipeLeft() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.fragment_home, true)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        navHostFragment.getNavController().navigate(R.id.fragment_networking, null, navOptions);
    }

    private void handleSwipeRight() {
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.fragment_home, true)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main);
        navHostFragment.getNavController().navigate(R.id.fragment_places, null, navOptions);
    }
}