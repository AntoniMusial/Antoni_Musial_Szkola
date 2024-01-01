package com.example.aquamate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextGoal;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextName = findViewById(R.id.editTextName);
        editTextGoal = findViewById(R.id.editTextGoal);
        btnSave = findViewById(R.id.btnSave);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        editTextName.setText(preferences.getString("userName", ""));
        editTextGoal.setText(String.valueOf(preferences.getInt("dailyGoal", 1500)));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationSettings);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    Intent mainIntent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                } else if (item.getItemId() == R.id.action_settings) {
                    item.setChecked(true);
                }
                return true;
            }
        });

        // Dodanie nasłuchiwacza zmian do SharedPreferences
        SharedPreferences.OnSharedPreferenceChangeListener listener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        if (key.equals("userName")) {
                            updateActionBarTitle();
                        }
                    }
                };

        SharedPreferences defaultSharedPreferences = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    private void saveSettings() {
        String name = editTextName.getText().toString();
        int goal = Integer.parseInt(editTextGoal.getText().toString());

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userName", name);
        editor.putInt("dailyGoal", goal);
        editor.apply();

        // Wysyłamy również broadcast, aby poinformować MainActivity o zmianie celu
        Intent intent = new Intent("update_goal");
        intent.putExtra("newGoal", goal);
        sendBroadcast(intent);

        updateActionBarTitle();

        Intent mainIntent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    // Metoda do aktualizacji tytułu w pasku akcji
    private void updateActionBarTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(editTextName.getText().toString());
        }
    }
}