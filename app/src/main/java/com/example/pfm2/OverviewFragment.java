package com.example.pfm2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class OverviewFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private TextView totalBalanceTextView;
    private LottieAnimationView lottieAnimationView;

    // Start the animation


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        totalBalanceTextView = view.findViewById(R.id.total_balance);
        lottieAnimationView = view.findViewById(R.id.lottie_animation_view);
        lottieAnimationView.playAnimation();

        // Optionally, you can control the animation with additional methods
        lottieAnimationView.setSpeed(1.5f); // Set animation speed
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);

        // Display total balance
        double totalBalance = dbHelper.getTotalBalance();
        totalBalanceTextView.setText("Total Balance: $" + String.format("%.2f", totalBalance));

        return view;
    }
}
