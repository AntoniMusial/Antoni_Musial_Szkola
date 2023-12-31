package com.example.aquamate;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    Button home_button;
    Button settings_button;
    Button zapiszButton;
    EditText wyborImienia;
    EditText wyborLimituWody;
    TextView NameSpace;
    Button resetujUstawienia;
    public String imie;
    public String limitWodyMl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_button = findViewById(R.id.settings_button);
        home_button = findViewById(R.id.home_button);
        zapiszButton = findViewById(R.id.zapiszButton);
        wyborImienia = findViewById(R.id.wyborImienia);
        wyborLimituWody = findViewById(R.id.wyborLimituWody);
        NameSpace = findViewById(R.id.NameSpace);
        resetujUstawienia = findViewById(R.id.resetujUstawienia);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        imie = preferences.getString("imie", "");
        NameSpace.setText("Witaj " + imie);

        zapiszButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zapisz();
            }
        });

        resetujUstawienia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetujUstawienia();
            }
        });

        home_button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("imie", imie);
            intent.putExtra("limitWodyMl", limitWodyMl);
            setResult(RESULT_OK, intent);
            finish();
        });

        settings_button.setOnClickListener(v -> {
            showShortToast("Jesteś tu!");
        });
    }

    private void showShortToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void zapisz() {
        imie = wyborImienia.getText().toString();
        limitWodyMl = wyborLimituWody.getText().toString();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        if (!imie.equals("")) {
            NameSpace.setText("Witaj " + imie);
            editor.putString("imie", imie);
        }
        if (!limitWodyMl.equals("")) {
            editor.putString("limitWodyMl", limitWodyMl);
        }
        showShortToast("Zapisano!");
        editor.apply();
    }

    private void resetujUstawienia() {
        imie = wyborImienia.getText().toString();
        limitWodyMl = wyborLimituWody.getText().toString();
        imie = "";
        limitWodyMl = "1500";

        showShortToast("Zresetowano!");
        NameSpace.setText("Witaj " + imie);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("imie", imie);
        editor.putString("limitWodyMl", limitWodyMl);
        editor.apply();
    }
}