package com.example.pfm2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private LottieAnimationView lottieAnimationView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        dbHelper = new DatabaseHelper(getActivity());

        EditText amountInput = view.findViewById(R.id.amount_input);
        EditText descriptionInput = view.findViewById(R.id.description_input);
        Button addButton = view.findViewById(R.id.add_transaction_button);
        lottieAnimationView = view.findViewById(R.id.lottie_animation_view);
        lottieAnimationView.playAnimation();

        // Optionally, you can control the animation with additional methods
        lottieAnimationView.setSpeed(1.5f); // Set animation speed
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);

        addButton.setOnClickListener(v -> handleAddTransaction(amountInput, descriptionInput));

        return view;
    }

    private void handleAddTransaction(EditText amountInput, EditText descriptionInput) {
        String amountStr = amountInput.getText().toString();
        String description = descriptionInput.getText().toString();

        if (!amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                if (dbHelper.insertTransaction(amount, currentDate, description, "expense")) {
                    Toast.makeText(getActivity(), "Transaction Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to Add Transaction", Toast.LENGTH_SHORT).show();
                }

                amountInput.setText("");
                descriptionInput.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Invalid amount format", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
        }
    }
}
