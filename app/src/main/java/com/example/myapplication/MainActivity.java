package com.example.myapplication;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {


    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putInt(stateScore, clickerScore);
        super.onSaveInstanceState(outState);
        System.out.println("State saved");

    }

    public int clickerScore = 0;
    public String stateScore = "";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            clickerScore = savedInstanceState.getInt(stateScore);

            
        }
        else {
           System.out.println("[ELSE]");
        }
        
        ImageButton imgBtn = (ImageButton)findViewById(R.id.imageButton2);
        TextView text1 = (TextView)findViewById(R.id.textView2);        //link Java variable to XML TextView
        TextView text2 = (TextView)findViewById(R.id.textView5);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickerScore++;                                                                                 
                System.out.println(clickerScore);
                text2.setText(""+clickerScore);        //change text of XML TextView     + String required, so it can format properly
                
            }
        });

    }






}