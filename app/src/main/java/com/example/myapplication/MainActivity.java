package com.example.myapplication;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {



    private TextView text2;           //declare variable in class => can be used in different functions other than onCreate()
    public int clickerScore = 0;

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
        SharedPreferences sharedPreferences = getSharedPreferences("mSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();             //edit SharedPreference
        editor.putInt("CLICKER_SCORE", Integer.parseInt(text2.getText().toString()));              //add values (key-paired) to be saved
        editor.apply();     //apply changes                                                       // see: https://developer.android.com/reference/android/content/SharedPreferences
    }

    public void onStart(){
        super.onStart();
        System.out.println("onStart");
        
    }

    public void onResume(){
        super.onResume();
        System.out.println("onResume");
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


