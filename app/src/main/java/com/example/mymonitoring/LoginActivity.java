package com.example.mymonitoring;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_login);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.BMenu);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this,
                        MenuActivity.class);
                startActivity(myIntent);
            }
        });
    }


    /* EditText TFLfullname, TFLpass;
     ProgressDialog progressDialog;
     Button BMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initilize the edit text
        TFLfullname = (EditText) findViewById(R.id.TFLfullname);
        TFLpass = (EditText) findViewById(R.id.TFLpass);
        BMenu = (Button) findViewById(R.id.BMenu);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Pleasee wait...");

        BMenu.setOnClickListener(this);


            // code for back previous page
        ImageView closeLoginIv = (ImageView) findViewById(R.id.closeLoginIv);
        closeLoginIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.super.onBackPressed();
            }
        });


        }

        private void userLogin(){
         final String fullname = TFLfullname.getText().toString().trim();
         final String password = TFLpass.getText().toString().trim();

         progressDialog.show();

         // post to website
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    constant.ROOT_URLLOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
                                if(!obj.getBoolean("error")) {
                                    SharePrefManager.getInstance(getApplicationContext())
                                            .userLogin(
                                                   obj.getInt("id"),
                                                    obj.getString("fullname"),
                                                    obj.getString("email")

                                            );
                                   startActivity(new Intent(getApplicationContext(), MenuActivity.class ));
                                   finish();
                                }else{
                                    Toast.makeText(
                                            getApplicationContext(),
                                            obj.getString("message"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(
                            getApplicationContext(),
                            error.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();


                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fullname", fullname);
                    params.put("password", password);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

   @Override
    public void onClick(View view) {
        if( view == BMenu){
            userLogin();
        }
    }*/
}








































        // code for back previous page
        /*ImageView closeLoginIv = (ImageView) findViewById(R.id.closeLoginIv);
        closeLoginIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.super.onBackPressed();
            }
        });*/










