package com.example.aquamate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    ImageButton backButton;
    Button obliczButton;
    Button ustawLimitMl;
    EditText twojaWaga;
    TextView twojeZapotrzebowanieWodyText;
    String twojaWagatxt;
    int twojawagal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        backButton = findViewById(R.id.backButton);
        twojeZapotrzebowanieWodyText = findViewById(R.id.twojeZapotrzebowanieWodyText);
        obliczButton = findViewById(R.id.obliczButton);
        twojaWaga = findViewById(R.id.twojaWaga);
        ustawLimitMl = findViewById(R.id.ustawLimitMl);

        obliczButton.setOnClickListener(v -> {
            twojaWagatxt = twojaWaga.getText().toString();
            twojawagal = Integer.parseInt(twojaWagatxt);

            twojawagal *= 35;

            twojeZapotrzebowanieWodyText.setText("Twoje zapotrzebowanie wody: " + twojawagal);
            ustawLimitMl.setVisibility(View.VISIBLE);
        });

        ustawLimitMl.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            String limitWodyMl = String.valueOf(twojawagal);
            editor.putString("limitWodyMl", limitWodyMl);
            editor.apply();

            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(intent);
        });



        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
