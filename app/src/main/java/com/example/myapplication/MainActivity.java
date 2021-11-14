package com.example.myapplication;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {



    private TextView text2;           //declare variable in class => can be used in different functions other than onCreate()
    public int clickerScore = 0;
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text2 = (TextView) findViewById(R.id.textView5);      //link Java variable to XML TextView + initialize variable

        ImageButton imgBtn = (ImageButton) findViewById(R.id.imageButton2);
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
        //create SharedPreferences => data stored and saved even in case of onDestroy()
        sharedPreferences = getSharedPreferences("mSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();             //edit SharedPreferences
        editor.putInt("CLICKER_SCORE", Integer.parseInt(text2.getText().toString()));              //add values (key-paired) to be saved
        editor.apply();     //apply changes                                                        // see: https://developer.android.com/reference/android/content/SharedPreferences
        System.out.println("[DEBUG] Data saved");
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
}


