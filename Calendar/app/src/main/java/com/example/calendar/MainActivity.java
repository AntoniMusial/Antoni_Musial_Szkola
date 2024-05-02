package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
    TextView leftArrow;
    TextView rightArrow;
    TextView monthText;
    int delay = 100;
    int numberYear;
    String textYear;
    int numberMonth;
    String month_text;
    int monthCounter = 0;
    int yearCounter = 0;
    int monthLength;
    int i;
    TextView cell1;
    TextView cell2;
    TextView cell3;
    TextView cell4;
    TextView cell5;
    TextView cell6;
    TextView cell7;
    TextView cell8;
    TextView cell9;
    TextView cell10;
    TextView cell11;
    TextView cell12;
    TextView cell13;
    TextView cell14;
    TextView cell15;
    TextView cell16;
    TextView cell17;
    TextView cell18;
    TextView cell19;
    TextView cell20;
    TextView cell21;
    TextView cell22;
    TextView cell23;
    TextView cell24;
    TextView cell25;
    TextView cell26;
    TextView cell27;
    TextView cell28;
    TextView cell29;
    TextView cell30;
    TextView cell31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yearHeaderText = findViewById(R.id.yearHeaderText);
        showDateText = findViewById(R.id.showDateText);
        monthText = findViewById(R.id.monthText);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        cell1 = findViewById(R.id.cell1);
        cell2 = findViewById(R.id.cell2);
        cell3 = findViewById(R.id.cell3);
        cell4 = findViewById(R.id.cell4);
        cell5 = findViewById(R.id.cell5);
        cell6 = findViewById(R.id.cell6);
        cell7 = findViewById(R.id.cell7);
        cell8 = findViewById(R.id.cell8);
        cell9 = findViewById(R.id.cell9);
        cell10 = findViewById(R.id.cell10);
        cell11 = findViewById(R.id.cell11);
        cell12 = findViewById(R.id.cell12);
        cell13 = findViewById(R.id.cell13);
        cell14 = findViewById(R.id.cell14);
        cell15 = findViewById(R.id.cell15);
        cell16 = findViewById(R.id.cell16);
        cell17 = findViewById(R.id.cell17);
        cell18 = findViewById(R.id.cell18);
        cell19 = findViewById(R.id.cell19);
        cell20 = findViewById(R.id.cell20);
        cell21 = findViewById(R.id.cell21);
        cell22 = findViewById(R.id.cell22);
        cell23 = findViewById(R.id.cell23);
        cell24 = findViewById(R.id.cell24);
        cell25 = findViewById(R.id.cell25);
        cell26 = findViewById(R.id.cell26);
        cell27 = findViewById(R.id.cell27);
        cell28 = findViewById(R.id.cell28);
        cell29 = findViewById(R.id.cell29);
        cell30 = findViewById(R.id.cell30);
        cell31 = findViewById(R.id.cell31);

        cell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell1.setBackgroundResource(R.drawable.border_clicked);
                cell1.setTypeface(null, Typeface.BOLD);
            }
        });
        cell2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell2.setBackgroundResource(R.drawable.border_clicked);
                cell2.setTypeface(null, Typeface.BOLD);
            }
        });
        cell3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell3.setBackgroundResource(R.drawable.border_clicked);
                cell3.setTypeface(null, Typeface.BOLD);
            }
        });
        cell4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell4.setBackgroundResource(R.drawable.border_clicked);
                cell4.setTypeface(null, Typeface.BOLD);
            }
        });
        cell5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell5.setBackgroundResource(R.drawable.border_clicked);
                cell5.setTypeface(null, Typeface.BOLD);
            }
        });
        cell6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell6.setBackgroundResource(R.drawable.border_clicked);
                cell6.setTypeface(null, Typeface.BOLD);
            }
        });
        cell7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell7.setBackgroundResource(R.drawable.border_clicked);
                cell7.setTypeface(null, Typeface.BOLD);
            }
        });
        cell8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell8.setBackgroundResource(R.drawable.border_clicked);
                cell8.setTypeface(null, Typeface.BOLD);
            }
        });
        cell9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell9.setBackgroundResource(R.drawable.border_clicked);
                cell9.setTypeface(null, Typeface.BOLD);
            }
        });
        cell10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell10.setBackgroundResource(R.drawable.border_clicked);
                cell10.setTypeface(null, Typeface.BOLD);
            }
        });
        cell11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell11.setBackgroundResource(R.drawable.border_clicked);
                cell11.setTypeface(null, Typeface.BOLD);
            }
        });
        cell12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell12.setBackgroundResource(R.drawable.border_clicked);
                cell12.setTypeface(null, Typeface.BOLD);
            }
        });
        cell13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell13.setBackgroundResource(R.drawable.border_clicked);
                cell13.setTypeface(null, Typeface.BOLD);
            }
        });
        cell14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell14.setBackgroundResource(R.drawable.border_clicked);
                cell14.setTypeface(null, Typeface.BOLD);
            }
        });
        cell15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell15.setBackgroundResource(R.drawable.border_clicked);
                cell15.setTypeface(null, Typeface.BOLD);
            }
        });
        cell16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell16.setBackgroundResource(R.drawable.border_clicked);
                cell16.setTypeface(null, Typeface.BOLD);
            }
        });
        cell17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell17.setBackgroundResource(R.drawable.border_clicked);
                cell17.setTypeface(null, Typeface.BOLD);
            }
        });
        cell18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell18.setBackgroundResource(R.drawable.border_clicked);
                cell18.setTypeface(null, Typeface.BOLD);
            }
        });
        cell19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell19.setBackgroundResource(R.drawable.border_clicked);
                cell19.setTypeface(null, Typeface.BOLD);
            }
        });
        cell20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell20.setBackgroundResource(R.drawable.border_clicked);
                cell20.setTypeface(null, Typeface.BOLD);
            }
        });
        cell21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell21.setBackgroundResource(R.drawable.border_clicked);
                cell21.setTypeface(null, Typeface.BOLD);
            }
        });
        cell22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell22.setBackgroundResource(R.drawable.border_clicked);
                cell22.setTypeface(null, Typeface.BOLD);
            }
        });
        cell23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell23.setBackgroundResource(R.drawable.border_clicked);
                cell23.setTypeface(null, Typeface.BOLD);
            }
        });
        cell24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell24.setBackgroundResource(R.drawable.border_clicked);
                cell24.setTypeface(null, Typeface.BOLD);
            }
        });
        cell25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell25.setBackgroundResource(R.drawable.border_clicked);
                cell25.setTypeface(null, Typeface.BOLD);
            }
        });
        cell26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell26.setBackgroundResource(R.drawable.border_clicked);
                cell26.setTypeface(null, Typeface.BOLD);
            }
        });
        cell27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell27.setBackgroundResource(R.drawable.border_clicked);
                cell27.setTypeface(null, Typeface.BOLD);
            }
        });
        cell28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell28.setBackgroundResource(R.drawable.border_clicked);
                cell28.setTypeface(null, Typeface.BOLD);
            }
        });
        cell29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell29.setBackgroundResource(R.drawable.border_clicked);
                cell29.setTypeface(null, Typeface.BOLD);
            }
        });
        cell30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell30.setBackgroundResource(R.drawable.border_clicked);
                cell30.setTypeface(null, Typeface.BOLD);
            }
        });
        cell31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearClickedDays();
                cell31.setBackgroundResource(R.drawable.border_clicked);
                cell31.setTypeface(null, Typeface.BOLD);
            }
        });

        date = LocalDate.now();
        monthLocalDate = LocalDate.now();
        time();
        getMonth();
        setMonth();
        getYear();
        setYear();
        setCalendarDays();

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "left arrow clicked", Toast.LENGTH_SHORT).show();
                monthCounter--;
                monthLocalDate = date.plusMonths(monthCounter);

                if (monthLocalDate.getMonthValue() == 12) {
                    date = date.plusYears(-1);
                }

                getMonth();
                setMonth();
                getYear();
                setYear();
                setCalendarDays();
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "right arrow clicked", Toast.LENGTH_SHORT).show();
                monthCounter++;
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
        setCalendarDays();
        super.onStart();
    }

    @Override
    protected void onResume() {
        getYear();
        setYear();
        getMonth();
        setCalendarDays();

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

        TextView[] cells = new TextView[31];
        cells[0] = cell1;
        cells[1] = cell2;
        cells[2] = cell3;
        cells[3] = cell4;
        cells[4] = cell5;
        cells[5] = cell6;
        cells[6] = cell7;
        cells[7] = cell8;
        cells[8] = cell9;
        cells[9] = cell10;
        cells[10] = cell11;
        cells[11] = cell12;
        cells[12] = cell13;
        cells[13] = cell14;
        cells[14] = cell15;
        cells[15] = cell16;
        cells[16] = cell17;
        cells[17] = cell18;
        cells[18] = cell19;
        cells[19] = cell20;
        cells[20] = cell21;
        cells[21] = cell22;
        cells[22] = cell23;
        cells[23] = cell24;
        cells[24] = cell25;
        cells[25] = cell26;
        cells[26] = cell27;
        cells[27] = cell28;
        cells[28] = cell29;
        cells[29] = cell30;
        cells[30] = cell31;

        for (i = 0; i < cells.length; i++) {
            cells[i].setVisibility(View.INVISIBLE);
        }

        for (i = 0; i < monthLength; i++) {
            cells[i].setVisibility(View.VISIBLE);
        }
    }

    public void clearClickedDays() {
        cell1.setBackgroundResource(R.drawable.border);
        cell2.setBackgroundResource(R.drawable.border);
        cell3.setBackgroundResource(R.drawable.border);
        cell4.setBackgroundResource(R.drawable.border);
        cell5.setBackgroundResource(R.drawable.border);
        cell6.setBackgroundResource(R.drawable.border);
        cell7.setBackgroundResource(R.drawable.border);
        cell8.setBackgroundResource(R.drawable.border);
        cell9.setBackgroundResource(R.drawable.border);
        cell10.setBackgroundResource(R.drawable.border);
        cell11.setBackgroundResource(R.drawable.border);
        cell12.setBackgroundResource(R.drawable.border);
        cell13.setBackgroundResource(R.drawable.border);
        cell14.setBackgroundResource(R.drawable.border);
        cell15.setBackgroundResource(R.drawable.border);
        cell16.setBackgroundResource(R.drawable.border);
        cell17.setBackgroundResource(R.drawable.border);
        cell18.setBackgroundResource(R.drawable.border);
        cell19.setBackgroundResource(R.drawable.border);
        cell20.setBackgroundResource(R.drawable.border);
        cell21.setBackgroundResource(R.drawable.border);
        cell22.setBackgroundResource(R.drawable.border);
        cell23.setBackgroundResource(R.drawable.border);
        cell24.setBackgroundResource(R.drawable.border);
        cell25.setBackgroundResource(R.drawable.border);
        cell26.setBackgroundResource(R.drawable.border);
        cell27.setBackgroundResource(R.drawable.border);
        cell28.setBackgroundResource(R.drawable.border);
        cell29.setBackgroundResource(R.drawable.border);
        cell30.setBackgroundResource(R.drawable.border);
        cell31.setBackgroundResource(R.drawable.border);

        cell1.setTypeface(null, Typeface.NORMAL);
        cell2.setTypeface(null, Typeface.NORMAL);
        cell3.setTypeface(null, Typeface.NORMAL);
        cell4.setTypeface(null, Typeface.NORMAL);
        cell5.setTypeface(null, Typeface.NORMAL);
        cell6.setTypeface(null, Typeface.NORMAL);
        cell7.setTypeface(null, Typeface.NORMAL);
        cell8.setTypeface(null, Typeface.NORMAL);
        cell9.setTypeface(null, Typeface.NORMAL);
        cell10.setTypeface(null, Typeface.NORMAL);
        cell11.setTypeface(null, Typeface.NORMAL);
        cell12.setTypeface(null, Typeface.NORMAL);
        cell13.setTypeface(null, Typeface.NORMAL);
        cell14.setTypeface(null, Typeface.NORMAL);
        cell15.setTypeface(null, Typeface.NORMAL);
        cell16.setTypeface(null, Typeface.NORMAL);
        cell17.setTypeface(null, Typeface.NORMAL);
        cell18.setTypeface(null, Typeface.NORMAL);
        cell19.setTypeface(null, Typeface.NORMAL);
        cell20.setTypeface(null, Typeface.NORMAL);
        cell21.setTypeface(null, Typeface.NORMAL);
        cell22.setTypeface(null, Typeface.NORMAL);
        cell23.setTypeface(null, Typeface.NORMAL);
        cell24.setTypeface(null, Typeface.NORMAL);
        cell25.setTypeface(null, Typeface.NORMAL);
        cell26.setTypeface(null, Typeface.NORMAL);
        cell27.setTypeface(null, Typeface.NORMAL);
        cell28.setTypeface(null, Typeface.NORMAL);
        cell29.setTypeface(null, Typeface.NORMAL);
        cell30.setTypeface(null, Typeface.NORMAL);
        cell31.setTypeface(null, Typeface.NORMAL);
    }
}