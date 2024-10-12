package com.example.raymond_2340_demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.raymond_2340_demo.BR;
import com.example.raymond_2340_demo.R;
import com.example.raymond_2340_demo.databinding.ActivityMainBinding;
import com.example.raymond_2340_demo.viewmodel.MainViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAB = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // use data binding to inflate the layout
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAB, "onCreate called");

        // create viewModel
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // bind viewmodel to layout
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this);

        // find button by id
        Button openButton = findViewById(R.id.button_open);

        // set an onclicklistener to button
        openButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // create an instant to start seconactive
                Intent intent = new Intent(MainActivity.this, secondActivity.class);

                // add extra data to the intent
                intent.putExtra("KEY", "hello from Main Activity");

                // start the second activity
                startActivity(intent);

            }
        });

        //find the chart creation button to create the graph
        Button countGraphButton = findViewById(R.id.button_countergraph);
        //draw the bar chart when click
        countGraphButton.setOnClickListener((l) -> drawGraph(viewModel));
    }

    public void drawGraph(MainViewModel viewModel){
        //prepare the data for the chart
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, viewModel.getCounter().getValue()));

        //create a bardataset from the data entries
        BarDataSet dataSet = new BarDataSet(entries, "Counter Data");
        BarChart barChart = findViewById(R.id.barChart);
        barChart.setData(new BarData(dataSet));

        //optional chart customization
        YAxis rightAxis = barChart.getAxisRight();
        //hiding the right axis
        rightAxis.setEnabled(false);
        //hiding the description label
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        //Redraw the chart
        barChart.invalidate(); //Refresh the chart

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