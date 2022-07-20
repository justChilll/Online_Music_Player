package com.applikation.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = findViewById(R.id.b1);
        Button pause = findViewById(R.id.b2);
        seekBar = findViewById(R.id.seekBar);

//        mediaPlayer = MediaPlayer.create(this,R.raw.on);
//        mediaPlayer.start();

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://pagaliworld.com/files/download/id/2045");    // If song isn't being played, verify the link...
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "Good to go...", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser)
                            mediaPlayer.seekTo(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });
        mediaPlayer.prepareAsync();

        play.setOnClickListener(view -> mediaPlayer.start());
        pause.setOnClickListener(view -> mediaPlayer.pause());
    }
}