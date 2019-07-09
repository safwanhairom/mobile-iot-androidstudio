package com.example.mymonitoring;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.example.safwan.mymonitoring.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    BarChart barChart;


    public DataFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_data, container, false);

        View v = inflater.inflate(R.layout.fragment_data, container, false);

        BarChart barChart = (BarChart) v.findViewById(R.id.barGraph);


        //barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList <BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(44f, 0));
        barEntries.add(new BarEntry(88f, 1));
        barEntries.add(new BarEntry(66f, 2));
        barEntries.add(new BarEntry(12f, 3));
        barEntries.add(new BarEntry(19f, 4));
        barEntries.add(new BarEntry(91f, 5));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        ArrayList <String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");
        theDates.add("Jun");
        theDates.add("July");
        theDates.add("August");
        theDates.add("September");


        BarData theData = new BarData(theDates, barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);



        return v;
    }
}

