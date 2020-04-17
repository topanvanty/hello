package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity {

    private static final String TAG = "Dashboard";
    ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();
   private Button btnLog,btnStar,btnCancel;





    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = findViewById(R.id.btnNV);





        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framLay, new homeFragment()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.home:
                        fragment = new homeFragment();
                        break;
                    case R.id.fav:
                        fragment = new favoriteFragment();
                        break;
                    case R.id.Data:
                        fragment = new dataFragment();
                        break;
                    case R.id.set:
                        fragment = new settingsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framLay,fragment).commit();
                return true;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(exampleBroadcastReceiver);
    }

    public void scheduleJob(View view){

        ComponentName componentName = new ComponentName(getApplication(),MyJobService.class);
        JobInfo info = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler scheduler =(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int ResultCode = scheduler.schedule(info);
        if (ResultCode == JobScheduler.RESULT_SUCCESS){
            Log.i(TAG, "scheduleJob: Job Scheduled");
        } else {
            Log.i(TAG, "scheduleJob: Job Scheduling failed");
        }
    }
        public void cancelJob(View view){
            JobScheduler scheduler =(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.cancel(123);
            Log.i(TAG, "cancelJob");
        }
}
