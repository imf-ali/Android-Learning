package com.learning.frenchteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button blackBtn, greenBtn, purpleBtn, redBtn, yellowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blackBtn = findViewById(R.id.blackBtn);
        greenBtn = findViewById(R.id.greenBtn);
        purpleBtn = findViewById(R.id.purpleBtn);
        redBtn = findViewById(R.id.redBtn);
        yellowBtn = findViewById(R.id.yellowBtn);

        blackBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        purpleBtn.setOnClickListener(this);
        redBtn.setOnClickListener(this);
        yellowBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int clickedBtnId = view.getId();
        if(clickedBtnId == R.id.blackBtn) {
            playSound(R.raw.black);
        } else if(clickedBtnId == R.id.greenBtn) {
            playSound(R.raw.green);
        } else if(clickedBtnId == R.id.purpleBtn) {
            playSound(R.raw.purple);
        } else if(clickedBtnId == R.id.redBtn) {
            playSound(R.raw.red);
        } else {
            playSound(R.raw.yellow);
        }
    }

    public void playSound(int id) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, id);
        mediaPlayer.start();
    }
}