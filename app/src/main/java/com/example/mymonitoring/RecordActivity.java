package com.example.mymonitoring;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.safwan.mymonitoring.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecordActivity extends AppCompatActivity  {

    Button BSave;
    EditText TFincubatorid, TFTotal, TFDate;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;

    String server_url = "http//192.168.1.4/Record.php";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_record);
        //initializa alert dialoog
        //builder = new AlertDialog.Builder(RecordActivity.this);

        //initialize by text field
        TFincubatorid = (EditText) findViewById(R.id.TFincubatorid);
        TFTotal = (EditText) findViewById(R.id.TFTotal);
        TFDate = (EditText) findViewById(R.id.TFDate);
        //initialize btn by id
        BSave = (Button) findViewById(R.id.BSave);

        // click function


        // pop up loading dialog
        progressDialog = new ProgressDialog(this);

    }



    public void BSave (View BSave)
    {
        final String incubatorid = TFincubatorid.getText().toString().trim();
        final String total = TFTotal.getText().toString().trim();
        final String calender = TFDate.getText().toString().trim();

        //validation
        if (TextUtils.isEmpty(incubatorid))
        {
            TFincubatorid.setError("Fill in incubator id");
            TFincubatorid.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(total))
        {
            TFTotal.setError("Fill in total succesfull egg hatch");
            TFTotal.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(calender))
        {
            TFDate.setError("Choose date after 21 days");
           TFDate.requestFocus();
            return;
        }


        class Record extends AsyncTask<Void, Void, String>
        {
            @Override
            public void onPreExecute()
            {
                progressDialog = ProgressDialog.show(RecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected String doInBackground(Void... v)
            {
                HashMap<String, String> params = new HashMap<>();
                params.put(constant.TFincubatorid, incubatorid);
                params.put(constant.TFTotal, total);
                params.put(constant.Calender, calender);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(constant.ROOT_URLRECORD, params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                progressDialog.dismiss();

                if (s.equalsIgnoreCase("You have been successfully save the data."))
                {
                    Toast.makeText(RecordActivity.this, s, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent (RecordActivity.this, RecordActivity.class);
                    finish();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RecordActivity.this, s, Toast.LENGTH_LONG).show();
                }
            }
        }

        Record reg = new Record();
        reg.execute();
    }

}





// click function
        /*BSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String incubatorid, total;

                incubatorid = TFincubatorid.getText().toString().trim();
                total = TFTotal.getText().toString().trim();
                // request server url
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("server respone");
                                builder.setMessage("Respones:" + response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //clear text box
                                        TFincubatorid.setText("");
                                        TFTotal.setText("");

                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RecordActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }) {

                    @Override // method to get input
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("incubatorid", incubatorid);
                        params.put("total", total);
                        return params;
                    }
                };

                MySingleton.getInstance(RecordActivity.this).addTorequestQue(stringRequest);
            }
            });
        };
    }*/


          /*private void Record(){

        final String incubatorid = TFincubatorid.getText().toString().trim();
        final String total = TFTotal.getText().toString().trim();
        final String calender = TFDate.getText().toString().trim();

        progressDialog.setMessage("Save data....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                constant.ROOT_URLRECORD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("incubatorid", incubatorid);
                params.put("total", total);
                params.put("calender", calender);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == BSave){
            Record();
        }
    }*/


