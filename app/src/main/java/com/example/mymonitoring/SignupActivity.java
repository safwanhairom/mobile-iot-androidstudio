package com.example.mymonitoring;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.safwan.mymonitoring.R;
import com.example.mymonitoring.constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static com.example.mymonitoring.constant.Calender;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    EditText TFfullname, TFemail, TFpass, TFcpass;
    ProgressDialog progressDialog;
    Button Bsignup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        //initialize by text field
        TFfullname = (EditText)findViewById(R.id.TFfullname);
        TFemail = (EditText)findViewById(R.id.TFemail);
        TFpass = (EditText)findViewById(R.id.TFpass);
        TFcpass = (EditText)findViewById(R.id.TFcpass);


        //initialize by id
        Bsignup = (Button) findViewById(R.id.Bsignup);
        // button record



        // pop up loading dialog
        progressDialog = new ProgressDialog(this);

        // click function
        Bsignup.setOnClickListener(this);




        //code for back previous page
        ImageView closeSignupIv = (ImageView) findViewById(R.id.closeSignupIv);
        closeSignupIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupActivity.super.onBackPressed();
            }
        });
    }



    private void registerUser()
    {
        final String fullname = TFfullname.getText().toString().trim();
        final String email = TFemail.getText().toString().trim();
        final String password = TFpass.getText().toString().trim();
        final String cpassword = TFcpass.getText().toString().trim();

        progressDialog.setMessage("Register User....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                constant.URL_REGISTER,
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
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("fullname", fullname);
                params.put("email", email);
                params.put("password", password);
                params.put("cpassword", cpassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == Bsignup)
            registerUser();

    }
}
