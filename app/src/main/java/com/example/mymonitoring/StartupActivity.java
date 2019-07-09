package com.example.mymonitoring;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.safwan.mymonitoring.R;

public class StartupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }





    public void JoinPressed(View view){
        //create intents to go sign up activity
        Intent signUpIntent = new Intent(StartupActivity.this, SignupActivity.class);
        startActivity(signUpIntent);

        //not created yet

        //Toast.makeText(StartupActivity.this, "Sign up pressed", Toast.LENGTH_SHORT).show();

    }


    public void LoginPressed(View view){
        //create intents to go sign up activity
        Intent LoginIntent = new Intent(StartupActivity.this, LoginActivity.class);
        startActivity(LoginIntent);

        //not created yet

        //Toast.makeText(StartupActivity.this, "Log in pressed", Toast.LENGTH_SHORT).show();
    }



}
