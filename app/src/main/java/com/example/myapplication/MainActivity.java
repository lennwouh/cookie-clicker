package com.example.myapplication;

import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

//TODO mention icons8.com => icons
                                                 

public class MainActivity extends AppCompatActivity {

    

    private TextView text2;           //declare variable in class => can be used in different functions other than onCreate()
    public int clickerScore = 0;
    public SharedPreferences sharedPreferences;
    private View view1;
    ImageButton imgBtn3;
    public MediaPlayer mp;
    public TextView text3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text2 = (TextView) findViewById(R.id.textView5);      //link Java variable to XML TextView + initialize variable
        view1 = (View) findViewById(R.id.view);
        view1.setVisibility(View.GONE);
        imgBtn3 = (ImageButton) findViewById(R.id.closeSettings);
        imgBtn3.setVisibility(View.GONE);
        ImageButton imgBtn = (ImageButton) findViewById(R.id.imageButton2);
        text3 = (TextView) findViewById(R.id.settingsLabel);
        text3.setVisibility(View.GONE);
        mp = MediaPlayer.create(this, R.raw.honk_sound);        //initialize in onCreate() => otherwise error

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickerScore++;
                System.out.println(clickerScore);
                text2.setText("" + clickerScore);        //change text of XML TextView + String required => format properly


            if(clickerScore % 2 == 0){                          //modulo
                imgBtn.setImageResource(R.drawable.h9m39vv630h52);       //change image if clickerScore is even
            }
            else{
                imgBtn.setImageResource(R.drawable.h9m39vv630h51);      //change image if clickerScore is odd
            }

            if(clickerScore % 100 == 0){
                mp.start();
            }

        }
        });

        //show settings UI if button pressed

        ImageButton imgBtn2 = (ImageButton) findViewById(R.id.settings);
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.VISIBLE);
                imgBtn3.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
            }
        });

        //hide settings UI if close button pressed

        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.GONE);
                imgBtn3.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
            }
        });
    }

    //Android Lifecycle

    public void onStop(){
        super.onStop();
        System.out.println("onStop");

        System.out.println("clickerScore: " + clickerScore);
    }

    public void onPause(){
        super.onPause();
        System.out.println("onPause");
        saveData();
    }

    public void onStart(){
        super.onStart();
        System.out.println("onStart");

    }

    public void onResume(){
        super.onResume();
        System.out.println("onResume");
        if(sharedPreferences == null) {
            SharedPreferences sP = getSharedPreferences("mSharedPreferences", MODE_PRIVATE);
            int CLICKER = sP.getInt("CLICKER_SCORE", 0);                                 //retrieve data from SharedPreferences
            text2.setText("" + CLICKER);                                    //update value of TextView with stored data
            clickerScore = CLICKER;                     //adapting clickerScore to value of CLICKER when setOnClickListener
            System.out.println("[DEBUG] Data retrieved");
            new Timer().scheduleAtFixedRate(new TimerTask(){
                @Override
                public void run(){
                    saveData();
                    runOnUiThread(new Runnable() {                      //link Toast.makeText to main thread ; see: https://newbedev.com/can-t-toast-on-a-thread-that-has-not-called-looper-prepare
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    
                }
            },0,120000);            //save game state every 120 seconds
        }
    }

    public void onDestroy(){
        super.onDestroy();
        System.out.println("onDestroy");

    }

    public void onRestart(){
        super.onRestart();
        System.out.println("onRestart");
    }

    public void saveData(){
        sharedPreferences = getSharedPreferences("mSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();             //edit SharedPreferences
        editor.putInt("CLICKER_SCORE", Integer.parseInt(text2.getText().toString()));              //add values (key-paired) to be saved
        editor.apply();     //apply changes                                                        // see: https://developer.android.com/reference/android/content/SharedPreferences
        System.out.println("[DEBUG] Data saved");

    }


    }
