package com.example.aquamate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static int DAILY_GOAL = 1500; // Dzienne zapotrzebowanie na wodę w ml
    private int currentProgress;

    private TextView textViewProgress;
    private ProgressBar progressBar;
    private Button btnAddWater;
    private EditText etCustomGoal;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewProgress = findViewById(R.id.textViewProgress);
        progressBar = findViewById(R.id.progressBar);
        btnAddWater = findViewById(R.id.btnAddWater);
        btnReset = findViewById(R.id.btnReset);

        // Odczytaj bieżący postęp z SharedPreferences
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        currentProgress = preferences.getInt("currentProgress", 0);

        updateProgressText();

        // Odczytaj imię z SharedPreferences
        String userName = preferences.getString("userName", "");

        // Ustawienie imienia w lewym górnym rogu
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(userName);
        }

        btnAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWater(250); // Dodaj 250 ml wody po kliknięciu przycisku
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Zresetuj wszystkie ustawienia
                resetSettings();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.action_home) {
                    // Tutaj możesz dodać kod, który przenosi do innej aktywności (jeśli jest to potrzebne)
                } else if (itemId == R.id.action_settings) {
                    Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                }

                return true;
            }
        });

        // Dodaj ten kod do istniejącego kodu w onCreate w MainActivity
        IntentFilter intentFilter = new IntentFilter("update_goal");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null && intent.getAction().equals("update_goal")) {
                    int newGoal = intent.getIntExtra("newGoal", DAILY_GOAL);
                    DAILY_GOAL = newGoal;
                    updateProgressText();
                }
            }
        };
        registerReceiver(receiver, intentFilter);
    }

    private void updateProgressText() {
        textViewProgress.setText("Dzienne zapotrzebowanie wody: " + currentProgress + "/" + DAILY_GOAL + " ml");
        progressBar.setMax(DAILY_GOAL);
        progressBar.setProgress(currentProgress);
    }

    private void addWater(int amount) {
        currentProgress += amount;

        // Zapisz bieżący postęp w SharedPreferences
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("currentProgress", currentProgress);
        editor.apply();

        updateProgressText();
    }

    private void resetSettings() {
        // Zresetuj wszystkie ustawienia
        currentProgress = 0;
        DAILY_GOAL = 1500;

        // Wyczyść SharedPreferences
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        updateProgressText();
    }
}