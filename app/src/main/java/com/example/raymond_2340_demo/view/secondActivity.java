package com.example.raymond_2340_demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raymond_2340_demo.R;

public class secondActivity extends AppCompatActivity {

    private final String TAB = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d(TAB, "onCreate called");

        // retrieve the intent that started this activity
        Intent intent = getIntent();
        String message = intent.getStringExtra("KEY");

        // find the textview ui element by id, then set text to message
        TextView textView = findViewById(R.id.text_second_activity);
        textView.setText(message);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAB, "onStart called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAB, "onResume called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAB, "onPause called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAB, "onStop called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAB, "onDestory called");
    }

}