package com.example.mymonitoring;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.safwan.mymonitoring.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.os.Build.VERSION_CODES.M;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment implements View.OnClickListener {
    Button BSave;
    EditText TFincubatorid, TFTotal, TFDate;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    //String server_url = "http//192.168.1.4/Record.php";

    // calender view code
    private static final String TAG = "RecordFragment";
    private TextView mDisplayDate;
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public RecordFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record, container, false);
        //initialize by text field
        TFincubatorid = (EditText) v.findViewById(R.id.TFincubatorid);
        TFTotal = (EditText) v.findViewById(R.id.TFTotal);
        TFDate = (EditText) v.findViewById(R.id.TFDate);
        //initialize btn by id


        mDisplayDate = (TextView) v.findViewById(R.id.TFDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = "" + String.valueOf(year) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth);
                        mDisplayDate.setText(date);
                        // tfDate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        mDateSetListener = new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: date:" + year + "/" + month + "/" + dayOfMonth);
            }
        };

        // pop up loading dialog
        progressDialog = new ProgressDialog(getContext());

        BSave = (Button) v.findViewById(R.id.BSave);

        // click function
        BSave.setOnClickListener(this);



return v;
    }



          private void Record()
          {

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

                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG);
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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == BSave){
            Record();
        }
    }
    }









 /*public void BSave (View BSave)
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
                progressDialog = ProgressDialog.show(getContext(), "Loading Data", null, true, true);
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
                    Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent (getActivity(), RecordFragment.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                }
            }
        }

        Record reg = new Record();
        reg.execute();
    }*/




    /*BSave = (Button) v.findViewById(R.id.BSave);
        BSave.setOnClickListener(new View.OnClickListener() {
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
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        //This indicates that the reuest has either time out or there is no connection
                    } else if (error instanceof AuthFailureError) {
                        //Error indicating that there was an Authentication Failure while performing the request
                    } else if (error instanceof ServerError) {
                        //Indicates that the server responded with a error response
                    } else if (error instanceof NetworkError) {
                        //Indicates that there was network error while performing the request
                    } else if (error instanceof ParseError) {
                        // Indicates that the server response could not be parsed
                    }
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

            MySingleton.getInstance(getActivity()).addTorequestQue(stringRequest);
        }
    });
        return v;
};*/






