package com.example.aquamate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    SeekBar Suwak_ml;
    TextView Wyswietl_dodawane_ml;
    ProgressBar LicznikWody_wykres;
    TextView LicznikWody_text;
    ImageView ImgView;
    Button resetButton;
    Button home_button;
    Button settings_button;
    TextView NameSpace;
    TextView Cel_wody;
    public String imie;
    public String limitWodyMl;
    int suwak_ml;
    int wypiteMl;
    int limitWodyMl_l;
    static final int USTAWIENIA_REQUEST = 1;
    String dateTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    TextView format1;
    int oldDay;
    int newDay;
    int oldSecond;
    int newSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread myThread = null;
        Runnable myRunnableThread = new CountDownRunner();
        myThread= new Thread(myRunnableThread);
        myThread.start();

        Wyswietl_dodawane_ml = findViewById(R.id.Wyswietl_dodawane_ml);
        Suwak_ml = findViewById(R.id.Suwak_ml);
        LicznikWody_wykres = findViewById(R.id.LicznikWody_wykres);
        LicznikWody_text = findViewById(R.id.LicznikWody_text);
        ImgView = findViewById(R.id.ImgView);
        resetButton = findViewById(R.id.resetButton);
        settings_button = findViewById(R.id.settings_button);
        home_button = findViewById(R.id.home_button);
        NameSpace = findViewById(R.id.NameSpace);
        Cel_wody = findViewById(R.id.Cel_wody);
        format1 = (TextView) findViewById(R.id.format1);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        dateTime = simpleDateFormat.format(calendar.getTime()).toString();
        oldDay = calendar.get(Calendar.DAY_OF_MONTH);
        timeUpdate();

        NameSpace.setText("Witaj " + imie);
        Cel_wody.setText("Twój dzisiejszy cel 1500ml");
        LicznikWody_text.setText(wypiteMl + "/1500ml");
        LicznikWody_wykres.setProgress(0);
        LicznikWody_wykres.setMax(1500);
        Wyswietl_dodawane_ml.setText("Dodaj " + 250 + "ml");
        Suwak_ml.setProgress(250);
        Suwak_ml.setMax(1001);
        format1.setText(dateTime);

        home_button.setOnClickListener(v -> {
            showShortToast("Jesteś tu!");
        });

        settings_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(intent, USTAWIENIA_REQUEST);
        });

        Suwak_ml.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressWykres = LicznikWody_wykres.getProgress();

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (int i = 0; i < 1001; i = i + 50) {
                    if (progress > i && progress < (i * 2)) {
                        suwak_ml = (i);
                        Wyswietl_dodawane_ml.setText("Dodaj " + suwak_ml + "ml");
                    }
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMl(suwak_ml);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        wypiteMl = preferences.getInt("wypiteMl", 0);
        imie = preferences.getString("imie", "");
        limitWodyMl = preferences.getString("limitWodyMl", "");

        if (limitWodyMl != null && !limitWodyMl.isEmpty() && limitWodyMl != "") {
            limitWodyMl_l = Integer.parseInt(limitWodyMl);
            LicznikWody_text.setText(wypiteMl + "/" + limitWodyMl_l + "ml");
            LicznikWody_wykres.setProgress(wypiteMl);
            LicznikWody_wykres.setMax(limitWodyMl_l);
        } else {
            LicznikWody_text.setText(wypiteMl + "/1500ml");
            LicznikWody_wykres.setProgress(wypiteMl);
            LicznikWody_wykres.setMax(1500);
        }

        NameSpace.setText("Witaj " + imie);
        Wyswietl_dodawane_ml.setText("Dodaj " + suwak_ml + "ml");
        Cel_wody.setText("Twój dzisiejszy cel " + limitWodyMl_l + "ml");
        Suwak_ml.setProgress(251);
        Suwak_ml.setMax(1001);

        dailyUpdate();
        timeUpdate();
    }

    public void addMl(int progressSuwak) {
        wypiteMl += progressSuwak;
        LicznikWody_wykres.setProgress(wypiteMl);
        LicznikWody_text.setText(wypiteMl + "/" + limitWodyMl_l + "ml");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("wypiteMl", wypiteMl);
        editor.apply();
    }

    public void reset() {
        wypiteMl = 0;
        LicznikWody_wykres.setProgress(wypiteMl);
        LicznikWody_text.setText(wypiteMl + "/" + limitWodyMl_l + "ml");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("wypiteMl", wypiteMl);
        editor.apply();
    }

    private void showShortToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        imie = preferences.getString("imie", "");
        limitWodyMl = preferences.getString("limitWodyMl", "");
        if (limitWodyMl != null && !limitWodyMl.isEmpty() && limitWodyMl != "") {
            limitWodyMl_l = Integer.parseInt(limitWodyMl);
            Cel_wody.setText("Twój dzisiejszy cel " + limitWodyMl_l + "ml");
            LicznikWody_wykres.setMax(limitWodyMl_l);
            LicznikWody_text.setText(wypiteMl + "/" + limitWodyMl_l + "ml");
            LicznikWody_wykres.setProgress(wypiteMl);
        }
        if (imie != null) {
            NameSpace.setText("Witaj " + imie);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("limitWodyMl", limitWodyMl);
        editor.apply();

        dailyUpdate();
        timeUpdate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void dailyUpdate() {
        newDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (oldDay != newDay) {
            wypiteMl = 0;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("wypiteMl", wypiteMl);
            editor.apply();
            LicznikWody_text.setText(wypiteMl + "/" + limitWodyMl_l + "ml");
            LicznikWody_wykres.setProgress(wypiteMl);
            oldDay = newDay;
        }
    }

    private void timeUpdate() {
        newSecond = calendar.get(Calendar.SECOND);
        if (oldSecond != newSecond) {
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            dateTime = simpleDateFormat.format(calendar.getTime()).toString();

            oldSecond = newSecond;
        }
    }

    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    Date dt = new Date();
                    int days = calendar.get(Calendar.DAY_OF_MONTH);
                    int months = calendar.get(Calendar.MONTH);
                    int years = calendar.get(Calendar.YEAR);
                    int hours = dt.getHours();
                    int minutes = dt.getMinutes();
                    int seconds = dt.getSeconds();

                    if (seconds < 10) {
                        s
                    }
                    String curTime = hours + ":" + minutes + ":" + seconds;
                    format1.setText(days + "." + months + "." + years + "r." + " | " + hours + ":" + minutes + ":" + seconds);
                }catch (Exception e) {}
            }
        });
    }

    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }
}