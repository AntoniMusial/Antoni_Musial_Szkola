package com.example.sensors_antonimusial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Spinner listaRozwijanaYtVideoChoice;
    VideoView videoView2;
    static String pickedVideo;
    Button buttonPlay;
    Button buttonReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaRozwijanaYtVideoChoice = findViewById(R.id.listaRozwijanaYtVideoChoice);
        videoView2 = findViewById(R.id.videoView2);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonReset = findViewById(R.id.buttonReset);

        videoView2.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.wtf);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView2);
        videoView2.setMediaController(mediaController);
        videoView2.start();

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickedVideo = listaRozwijanaYtVideoChoice.getSelectedItem().toString();
//znajdz inne filme i je zaaplikuj
                if (pickedVideo.equals("weird")) {
                    videoView2.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.wtf);
                } else if (pickedVideo.equals("scary")) {

                } else if (pickedVideo.equals("wtf")) {

                } else {
                    Toast.makeText(MainActivity.this, "wtf japierrdole cos sie spierdolilo w liscie rozwijalnej kurwa. mac...!", Toast.LENGTH_SHORT).show();
                }

                videoView2.start(); // sprawdz czy to odtwarza film od nowa
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView2.pause(); // sprawdz jak zresetowac film
            }
        });

        ArrayAdapter<CharSequence> adapterYtVideo = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerYtVideo,
                R.layout.spinner

        );
        adapterYtVideo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaRozwijanaYtVideoChoice.setAdapter(adapterYtVideo);
    }


}