package com.example.pfm2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    OverviewFragment overviewFragment = new OverviewFragment();
    TransactionFragment transactionFragment = new TransactionFragment();
    HistoryFragment reportFragment = new HistoryFragment();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView user_email = findViewById(R.id.user_email);

        if (user != null)
            user_email.setText("User: " + user.getEmail());

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize with the OverviewFragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, overviewFragment, "OverviewFragment")
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

                if(itemId == R.id.overview) {
                    Log.d("HomeActivity", "Overview selected");
                    switchFragment(overviewFragment, "OverviewFragment");
                    return true;
                }

                if(itemId == R.id.transaction) {
                    Log.d("HomeActivity", "Transaction selected");
                    switchFragment(transactionFragment, "TransactionFragment");
                    return true;
                }

                if(itemId == R.id.report) {
                    Log.d("HomeActivity", "Report selected");
                    switchFragment(reportFragment, "ReportFragment");
                    return true;
                }
                    return false;
        });
    }

    private void switchFragment(Fragment newFragment, String tag) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (currentFragment != null && currentFragment != newFragment) {
            getSupportFragmentManager().beginTransaction()
                    .detach(currentFragment)
                    .commit();
        }
        if (newFragment.isDetached()) {
            getSupportFragmentManager().beginTransaction()
                    .attach(newFragment)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment, tag)
                    .commit();
        }
    }
}
