package com.example.cs_student.gridviewtest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ImageButton man;
    MediaPlayer sound;
    MediaPlayer sound2;
    boolean StartStop = false;
    boolean StartStop2 = false;

   // ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, );

    // ListView listView = (ListView) findViewById(R.id.imageButton);
    // GridView.setAdapter(adapter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);

        sound = MediaPlayer.create(this, R.raw.bigenough);
        sound2 = MediaPlayer.create(this, R.raw.theway);



    }

    public void playSound(View v){


        StartStop = !StartStop;

        if (StartStop) {
            sound.start();
        }
        else {
            sound.pause();
            sound.seekTo(0);

        }

    }

    public void playSound2(View v){


        StartStop2 = !StartStop2;

        if (StartStop2) {
            sound2.start();
        }
        else {
            sound2.pause();
            sound2.seekTo(0);

        }




    }
}
