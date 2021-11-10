package com.example.myapplication;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {

    private int clickerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button);
        TextView text1 = (TextView)findViewById(R.id.textView2);        //link Java variable to XML TextView
        //ImageButton imgBtn = (ImageButton)findViewById(R.id.imageButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickerScore++;                                                                                 
                System.out.println(clickerScore);
                text1.setText("Times clicked: " + clickerScore);        //change text of XML TextView
            }
        });

    }


}