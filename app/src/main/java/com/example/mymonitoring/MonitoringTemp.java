package com.example.mymonitoring;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.safwan.mymonitoring.R;
import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MonitoringTemp extends Fragment {

    private static final String TAG = "MONITORINGACTIVITY";
    private Button Temperature;
    private Button Humidity;
    private static TextView s1;
    private static TextView t1;
    private TextView t2;
    private static TextView t3;
    private static final String THINGSPEAK_CHANNEL_ID = "584296"; //  584296
    private static final String THINGSPEAK_API_KEY = "HXCLHZZKAIQGH944 "; //GARBAGE KEY    HXCLHZZKAIQGH944
    private static final String THINGSPEAK_API_KEY_STRING = "HXCLHZZKAIQGH944"; //   HXCLHZZKAIQGH944
    /* Be sure to use the correct fields for your own app*/
    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds/last?";


    private ThingSpeakChannel tsChannel;
    private ThingSpeakLineChart tsChart;
    private LineChartView chartView;
    private Handler mHandler;

    public MonitoringTemp() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_temp, container, false);

        t1 = (TextView) v.findViewById(R.id.tempNumber);
        //t2 = (TextView) v.findViewById(R.id.humidNumber);


        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,30000);this.mHandler = new Handler();



        Temperature = (Button) v.findViewById(R.id.btnTemperature);
        //Humidity = (Button) v.findViewById(R.id.btnHumidity);

        ThingSpeakChannel tsChannel = new ThingSpeakChannel(584296);
        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                // Make use of your Channel feed here!
                // Show Channel ID and name on the Action Bar
                final LineChartView chartView = (LineChartView) v.findViewById(R.id.TempChart);
                tsChart = new ThingSpeakLineChart(584296, 1); //   584296
                tsChart.setNumberOfEntries(10);
                // Set value axis labels on 10-unit interval
                tsChart.setValueAxisLabelInterval(10);
                // Set date axis labels on 5-minute interval
                tsChart.setDateAxisLabelInterval(1);
                // Show the line as a cubic spline
                tsChart.useSpline(true);
                // Set the line color
                tsChart.setLineColor(Color.parseColor("#D32F2F"));
                // Set the axis color
                tsChart.setAxisColor(Color.parseColor("#455a64"));
                // Set the starting date (5 minutes ago) for the default viewport of the chart
                //tsChart.setChartStartDate(calendar.getTime());

                tsChart.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
                    @Override
                    public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {

                        //Calendar calendar = Calendar.getInstance();
                        //calendar.add(Calendar.MINUTE, -5);



                        chartView.setLineChartData(lineChartData);
                        chartView.setMaximumViewport(maxViewport);
                        chartView.setCurrentViewport(initialViewport);
                    }
                });
                tsChart.loadChartData();

                try {
                    new FetchThingspeakTempTask().execute();
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage(), e);
                }

            }
        });
        tsChannel.loadChannelFeed();



        /*Temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.findViewById(R.id.TemperatureLayout).setVisibility(View.VISIBLE);
                v.findViewById(R.id.HumidityLayout).setVisibility(View.GONE);

            }
        });*/

        //TextView tipT1,tipT2,tipT3;
        s1 = (TextView) v.findViewById(R.id.s1);


        return v;
    }


    public static class FetchThingspeakTempTask extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            t1.setText("Fetching Data from Server.Please Wait...");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL(THINGSPEAK_CHANNEL_URL + THINGSPEAK_CHANNEL_ID +
                        THINGSPEAK_FEEDS_LAST + THINGSPEAK_API_KEY_STRING + "=" +
                        THINGSPEAK_API_KEY + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if (response == null) {
                //Toast.makeText(MonitoringTemp.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                String field1 = Double.toString(v1);
                t1.setText(field1);
                double DTemp = v1;
                suggestTemp(DTemp);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void showTemp(){ // show data
        try {
            new FetchThingspeakTempTask().execute();


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }
    }



    public static void suggestTemp(Double DTemp){


        if (DTemp > 38.9 && DTemp < 37.5){
            s1.setText("Temperature high, The Egg may die");

        }
        else if (37.5 == DTemp || DTemp == 38.9){
            s1.setText ("Best temperature chicken egg to hatch ");
           // tipT1.setText("No action needed. ");
            //tipT2.setText("The plant can still grow");

        }
        else
        {
            s1.setText("The egg will die");
            //Toast.makeText(MonitoringTemp.super.getContext(), "High Temperature. The egg may die", Toast.LENGTH_LONG).show();
           // tipT1.setText("Consider placing the plant away from direct sunlight");
        }
    }

    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
           showTemp(); // data sensor refresh


           // graphTemp();



            //Toast.makeText(getContext() ,"refreshed",Toast.LENGTH_SHORT).show();

            MonitoringTemp.this.mHandler.postDelayed(m_Runnable, 15000);// time to sensor data refresh
        }

    };//runnable




}











