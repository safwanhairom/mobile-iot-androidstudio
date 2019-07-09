package com.example.mymonitoring;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.safwan.mymonitoring.R;

public class MenuActivity extends AppCompatActivity {
    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle; //To display humbuger menu icon

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharePrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // find toolbar by id
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //humbuger icon
        drawerLayout = (DrawerLayout)findViewById(R.id.drawermenu);
        //initialize
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);




        // fragment transaction
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.profile_container, new ProfileFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Profile Fragment");

        //handle click event according navigation view
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.profile:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new ProfileFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Profile");
                        item.setChecked(true);

                        break;


                    case R.id.monitoringTemp:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new MonitoringTemp());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Monitoring Temperature");
                        item.setChecked(true);
                        //drawerLayout.closeDrawer();
                        break;

                    case R.id.monitoringHumid:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new MonitoringHumid());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Monitoring Humidity");
                        item.setChecked(true);
                        //drawerLayout.closeDrawer();
                        break;


                    case R.id.record:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new RecordFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Record Successfull Egg Hatch");
                        item.setChecked(true);

                        break;


                    case R.id.data:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.profile_container, new DataFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Data");
                        item.setChecked(true);
                        //drawerLayout.closeDrawer();
                        break;
                        

                }



                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}


