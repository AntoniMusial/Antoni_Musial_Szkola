package com.example.api_antonimusail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    static Spinner listaRozwijanaKategorie;
    static Spinner listaRozwijanaJezyki;
    static Spinner listaRozwijanaFlags;
    static Spinner listaRozwijanaType;
    static String kategoria;
    static String jezyk;
    static String languageTransferedde = "de";
    static String languageTransferedcs = "cs";
    static String languageTransfereden = "en";
    static String languageTransferedes = "es";
    static String languageTransferedfr = "fr";
    static String languageTransferedpt = "pt";
    static String flag;
    static String typ;
    static TextView tV_joke;
    static Button button_generateJoke;
    static Button button_generateRandomJoke;
    static String urlAdress;
    static Switch switchKategorie;
    static Switch switchJezyki;
    static Switch switchFlags;
    static Switch switchType;
    static Boolean switchStateKategorie;
    static Boolean switchStateJezyki;
    static Boolean switchStateFlags;
    static Boolean switchStateType;
    static TextView textViewCategory;
    static TextView textViewLanguage;
    static TextView textViewFlags;
    static TextView textViewType;
    static Boolean switchesState;
    static Boolean switchesStateDifferent;
    public static final int REQUEST_CAMERA_PERMISSION = 1;

    private static void GetData(String urlAdress) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String result = "";
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(urlAdress);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                } catch (IOException e) {
                    Log.e("DownloadTask", "Error downloading data", e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                String finalResult = result;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tV_joke.setText(finalResult);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaRozwijanaKategorie = findViewById(R.id.listaRozwijanaKategorie);
        listaRozwijanaJezyki = findViewById(R.id.listaRozwijanaJezyki);
        listaRozwijanaFlags = findViewById(R.id.listaRozwijanaFlags);
        listaRozwijanaType = findViewById(R.id.listaRozwijanaType);
        tV_joke = findViewById(R.id.tV_joke);
        button_generateJoke = findViewById(R.id.button_generateJoke);
        button_generateRandomJoke = findViewById(R.id.button_generateRandomJoke);
        switchKategorie = findViewById(R.id.switchKategorie);
        switchJezyki = findViewById(R.id.switchJezyki);
        switchFlags = findViewById(R.id.switchFlags);
        switchType = findViewById(R.id.switchType);
        textViewCategory = findViewById(R.id.textViewCategory);
        textViewFlags = findViewById(R.id.textViewFlags);
        textViewLanguage = findViewById(R.id.textViewLanguage);
        textViewType = findViewById(R.id.textViewType);

        switchStateKategorie = switchKategorie.isChecked();
        switchStateJezyki = switchJezyki.isChecked();
        switchStateFlags = switchFlags.isChecked();
        switchStateType = switchType.isChecked();

        switchKategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSwitchStateKategorie();
                checkSwitchStateJezyki();
                checkSwitchStateFlags();
                checkSwitchStateType();
                checkSwitches();
                setSwitches();
            }
        });

        switchJezyki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSwitchStateKategorie();
                checkSwitchStateJezyki();
                checkSwitchStateFlags();
                checkSwitchStateType();
                checkSwitches();
                setSwitches();
            }
        });

        switchFlags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSwitchStateKategorie();
                checkSwitchStateJezyki();
                checkSwitchStateFlags();
                checkSwitchStateType();
                checkSwitches();
                setSwitches();
            }
        });

        switchType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSwitchStateKategorie();
                checkSwitchStateJezyki();
                checkSwitchStateFlags();
                checkSwitchStateType();
                checkSwitches();
                setSwitches();
            }
        });

        button_generateRandomJoke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                urlAdress = "https://v2.jokeapi.dev/joke/Any?format=txt";
                GetData(urlAdress);
            }
        });

        button_generateJoke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                generateCustomLink();
                GetData(urlAdress);
            }
        });

        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerCategories,
                R.layout.spinner

        );
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaRozwijanaKategorie.setAdapter(adapterCategory);

        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerLanguages,
                R.layout.spinner
        );
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaRozwijanaJezyki.setAdapter(adapterLanguage);

        ArrayAdapter<CharSequence> adapterFlags = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerFlags,
                R.layout.spinner
        );
        adapterFlags.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaRozwijanaFlags.setAdapter(adapterFlags);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerType,
                R.layout.spinner
        );
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaRozwijanaType.setAdapter(adapterType);

        setSwitches();
    }

    @Override
    protected void onStart() {
        super.onStart();

        setSwitches();
    }

    public static void checkSwitchStateKategorie() {
        switchStateKategorie = switchKategorie.isChecked();

        if (switchStateKategorie == false) {
            textViewCategory.setTextColor(Color.GRAY);
        } else {
            textViewCategory.setTextColor(Color.parseColor("#b20091"));
        }
    }

    public static void checkSwitchStateJezyki() {
        switchStateJezyki = switchJezyki.isChecked();

        if (switchStateJezyki == false) {
            textViewLanguage.setTextColor(Color.GRAY);
        } else {
            textViewLanguage.setTextColor(Color.parseColor("#b20091"));
        }
    }

    public static void checkSwitchStateFlags() {
        switchStateFlags = switchFlags.isChecked();

        if (switchStateFlags == false) {
            textViewFlags.setTextColor(Color.GRAY);
        } else {
            textViewFlags.setTextColor(Color.parseColor("#b20091"));
        }
    }

    public static void checkSwitchStateType() {
        switchStateType = switchType.isChecked();

        if (switchStateType == false) {
            textViewType.setTextColor(Color.GRAY);
        } else {
            textViewType.setTextColor(Color.parseColor("#b20091"));
        }
    }

    public static void checkSwitches() {
        checkSwitchStateJezyki();
        checkSwitchStateKategorie();
        checkSwitchStateFlags();
        checkSwitchStateType();

        if (switchStateKategorie == false && switchStateJezyki == false && switchStateFlags == false && switchStateType == false) {
            switchesState = false;
            switchesStateDifferent = false;
        } else if (switchStateKategorie == true && switchStateJezyki == true && switchStateFlags == true && switchStateType == true) {
            switchesState = true;
            switchesStateDifferent = false;
        } else {
            switchesStateDifferent = true;
            switchesState = true;
        }
        if (switchesState == true && switchesStateDifferent == false) {
            Log.i("laa", "true");
        } else if (switchesState == false && switchesStateDifferent == false) {
            Log.i("laa", "false");
        } else if (switchesStateDifferent == true) {
            Log.i("laa", "different");

        }
    }

    public static void setSwitches() {
        checkSwitches();

        if (switchesState == false && switchesStateDifferent == false) {
            button_generateJoke.setEnabled(false);
            button_generateJoke.setBackgroundColor(Color.DKGRAY);
            button_generateJoke.setTextColor(Color.GRAY);

        } else if (switchesState == true && switchesStateDifferent == false) {
            button_generateJoke.setEnabled(true);
            button_generateJoke.setBackgroundColor(Color.parseColor("#fea100"));
            button_generateJoke.setTextColor(Color.parseColor("#b20091"));

        } else if (switchesStateDifferent == true) {
            button_generateJoke.setEnabled(true);
            button_generateJoke.setBackgroundColor(Color.parseColor("#fea100"));
            button_generateJoke.setTextColor(Color.parseColor("#b20091"));

        }
    }

    public static void getListyRozwijalne() {
        kategoria = listaRozwijanaKategorie.getSelectedItem().toString();
        jezyk = listaRozwijanaJezyki.getSelectedItem().toString();
        flag = listaRozwijanaFlags.getSelectedItem().toString();
        typ = listaRozwijanaType.getSelectedItem().toString();
    }

    public static void generateCustomLink() {
        checkSwitches();
        getListyRozwijalne();
        transferLaunguage();

        if (switchStateKategorie == false) {
            kategoria = "Any";
        } else if (switchStateJezyki == false) {
            jezyk = "";
        } else if (switchStateType == false) {
            typ = "";
        } else if (switchStateFlags == false) {
            flag = "";
        }

        urlAdress = "https://v2.jokeapi.dev/joke/" + kategoria + "?lang=" + jezyk + "&blacklistFlags=" + flag + "&format=txt&type=" + typ;
    }

    public static void transferLaunguage() {
        getListyRozwijalne();

        if (jezyk.equals("en - English")) {
            jezyk = languageTransfereden;
        } else if (jezyk.equals("cs - Czech")) {
            jezyk = languageTransferedcs;
        } else if (jezyk.equals("de - German")) {
            jezyk = languageTransferedde;
        } else if (jezyk.equals("es - Spanish")) {
            jezyk = languageTransferedes;
        } else if (jezyk.equals("fr - French")) {
            jezyk = languageTransferedfr;
        } else if (jezyk.equals("pt - Portuguese")) {
            jezyk = languageTransferedpt;
        }
    }
}