package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Calendar calendar;
    LocalDate date;
    SimpleDateFormat simpleDateFormat;
    Format f;
    TextView showDateText;
    String time;
    Handler handler = new Handler();
    Runnable runnable;
    Button leftArrow;
    Button rightArrow;
    TextView monthText;
    int delay = 100;
    String month;
    int numberMonth;
    String month_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDateText = findViewById(R.id.showDateText);
        monthText = findViewById(R.id.monthText);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        date = LocalDate.parse("2018-11-27");
        time();
        getMonth();
        setMonth();

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberMonth = date.getMonthValue();
                Toast.makeText(MainActivity.this, "left arrow clicked", Toast.LENGTH_SHORT).show();

            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "right arrow clicked", Toast.LENGTH_SHORT).show();
                date.plusMonths(1);
                getMonth();
                setMonth();
            }
        });
    }

    @Override
    protected void onStart() {
        getMonth();
        setMonth();
        super.onStart();
    }

    @Override
    protected void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                time();
                handler.postDelayed(runnable, delay);
            }
        }, delay);
        super.onResume();
    }

    public void time() {
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        time = simpleDateFormat.format(calendar.getTime()).toString();

        showDateText.setText(time);
    }

    public void getMonth() {
        numberMonth = date.getMonthValue();
    }

    public void setMonth() {
        if (numberMonth == 1) {
            month_text = "Styczeń";
        } else if (numberMonth == 2) {
            month_text = "Luty";
        } else if (numberMonth == 3) {
            month_text = "Marzec";
        } else if (numberMonth == 4) {
            month_text = "Kwiecień";
        } else if (numberMonth == 5) {
            month_text = "Maj";
        } else if (numberMonth == 6) {
            month_text = "Czerwiec";
        } else if (numberMonth == 7) {
            month_text = "Lipiec";
        } else if (numberMonth == 8) {
            month_text = "Sierpień";
        } else if (numberMonth == 9) {
            month_text = "Wrzesień";
        } else if (numberMonth == 10) {
            month_text = "Październik";
        } else if (numberMonth == 11) {
            month_text = "Listopad";
        } else if (numberMonth == 12) {
            month_text = "Grudzień";
        } else {
            month_text = "Błąd";
        }

        monthText.setText(month_text);
    }
}