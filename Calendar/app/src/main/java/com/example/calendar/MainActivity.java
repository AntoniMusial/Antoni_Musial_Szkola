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

public class MainActivity extends AppCompatActivity {
    TextView yearHeaderText;
    Calendar calendar;
    LocalDate date;
    LocalDate monthLocalDate;
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
    int numberYear;
    String textYear;
    int numberMonth;
    String month_text;
    int monthCounter = 0;
    int yearCounter = 0;
    int monthLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yearHeaderText = findViewById(R.id.yearHeaderText);
        showDateText = findViewById(R.id.showDateText);
        monthText = findViewById(R.id.monthText);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        date = LocalDate.now();
        monthLocalDate = LocalDate.now();
        time();
        getMonth();
        setMonth();
        getYear();
        setYear();

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "left arrow clicked", Toast.LENGTH_SHORT).show();
                monthCounter --;
                monthLocalDate = date.plusMonths(monthCounter);

                if (monthLocalDate.getMonthValue() == 12) {
                    date = date.plusYears(-1);
                }

                getMonth();
                setMonth();
                getYear();
                setYear();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "right arrow clicked", Toast.LENGTH_SHORT).show();
                monthCounter ++;
                monthLocalDate = date.plusMonths(monthCounter);

                if (monthLocalDate.getMonthValue() == 1) {
                    date = date.plusYears(1);
                }

                getMonth();
                setMonth();
                getYear();
                setYear();
                setCalendarDays();
            }
        });
    }

    @Override
    protected void onStart() {
        date = LocalDate.now();
        monthLocalDate = LocalDate.now();
        getYear();
        setYear();
        getMonth();
        setMonth();
        super.onStart();
    }

    @Override
    protected void onResume() {
        getYear();
        setYear();
        getMonth();

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

    public void getYear() {
        numberYear = date.getYear();
    }

    public void setYear() {
        textYear = Integer.toString(numberYear);
        yearHeaderText.setText(textYear);
    }

    public void getMonth() {
        monthLocalDate.getMonth();
        numberMonth = monthLocalDate.getMonthValue();
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

    public void getMonthLength() {
        monthLength = monthLocalDate.lengthOfMonth();
    }

    public void setCalendarDays() {
        getMonthLength();
        String mounthLength = Integer.toString(monthLength);

        Toast.makeText(this, mounthLength, Toast.LENGTH_SHORT).show();
    }
}