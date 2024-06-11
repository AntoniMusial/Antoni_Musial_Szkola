package com.example.kantor_walut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    static TextView tV_kursZlota;
    static TextView tV_kursEuro;
    static String urlAdress;
    public final int REQUEST_INTERNET_PERMISSION = 1;

    private void action(View v) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA}, REQUEST_INTERNET_PERMISSION);
            return;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_INTERNET_PERMISSION && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Przyznano uprawnienia", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nie przyznano uprawnień", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File dexOutputDir = getCodeCacheDir();
        dexOutputDir.setReadOnly();
        setContentView(R.layout.activity_main);

        tV_kursZlota = findViewById(R.id.tV_kursZlota);
        tV_kursEuro = findViewById(R.id.tV_kursEuro);

        urlAdress = "https://api.nbp.pl/api/cenyzlota/?format=JSON";
        GetZloto(urlAdress);

        urlAdress = "https://api.nbp.pl/api/exchangerates/rates/A/EUR/?format=JSON";
        GetEuro(urlAdress);
    }

    @Override
    protected void onStart() {
        super.onStart();

        urlAdress = "https://api.nbp.pl/api/cenyzlota/?format=JSON";
        GetZloto(urlAdress);

        urlAdress = "https://api.nbp.pl/api/exchangerates/rates/A/EUR/?format=JSON";
        GetEuro(urlAdress);
    }

    private static void GetZloto(String urlAdress) {
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

                    JSONArray zmienna = new JSONArray(result);
                    result = (zmienna.getJSONObject(0).getString("cena")) + "zł, " + (zmienna.getJSONObject(0).getString("data"));

                    int a = 1;
                } catch (IOException e) {
                    Log.e("DownloadTask", "Error downloading data", e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                String finalResult = result;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tV_kursZlota.setText(finalResult);
                    }
                });
            }
        });
    }

    private static void GetEuro(String urlAdress) {
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

                    JSONObject zmienna = new JSONObject(result);
                    result = (zmienna.getJSONArray("rates").getJSONObject(0).getString("mid"));

                    int a = 1;
                } catch (IOException e) {
                    Log.e("DownloadTask", "Error downloading data", e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                String finalResult = result;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tV_kursEuro.setText(finalResult);
                    }
                });
            }
        });
    }
}
