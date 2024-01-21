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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {
    Button home_button;
    Button settings_button;
    Button zapiszButton;
    Button kalkulatorButton;
    EditText wyborImienia;
    EditText wyborLimituWody;
    TextView NameSpace;
    Button resetujUstawienia;
    public String imie;
    public String limitWodyMl;
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    TextView format1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_button = findViewById(R.id.settings_button);
        home_button = findViewById(R.id.home_button);
        zapiszButton = findViewById(R.id.zapiszButton);
        kalkulatorButton = findViewById(R.id.kalkulatorButton);
        wyborImienia = findViewById(R.id.wyborImienia);
        wyborLimituWody = findViewById(R.id.wyborLimituWody);
        NameSpace = findViewById(R.id.NameSpace);
        resetujUstawienia = findViewById(R.id.resetujUstawienia);
        format1 = (TextView) findViewById(R.id.format1);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        imie = preferences.getString("imie", "");
        NameSpace.setText("Witaj " + imie);
        format1.setText(dateTime);

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
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            intent.putExtra("imie", imie);
            intent.putExtra("limitWodyMl", limitWodyMl);
            setResult(RESULT_OK, intent);
            startActivity(intent);
            finish();
        });

        settings_button.setOnClickListener(v -> {
            showShortToast("Jesteś tu!");
        });

        kalkulatorButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(SettingsActivity.this, CalculatorActivity.class);
            startActivity(intent2);
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

    @Override
    protected void onStart() {
        super.onStart();

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        format1.setText(dateTime);
    }
}