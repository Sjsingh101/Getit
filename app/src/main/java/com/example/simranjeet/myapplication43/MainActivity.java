package com.example.simranjeet.myapplication43;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String arg[] = {"SElect One","A","B","C","D","E"};
    Integer arr[]={0,R.drawable.a,R.drawable.b,
            R.drawable.c,R.drawable.d,
            R.drawable.e};

    private Button b;
    private LocationManager locationManager;
    private LocationListener listener;
    GpsLocation s;
    SharedPreferences sharedPreferences;
    Intent intent;
    ArrayList<String> arrayList;
    Context context;
    SharedPreferences.Editor editor;
    Spinner sp;
    Activity activity;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        b = findViewById(R.id.btn);
        sp = findViewById(R.id.sp);
        myadapt aa = new myadapt(this,arg,arr);
        sp.setAdapter(aa);
       sharedPreferences = getApplicationContext().getSharedPreferences("LoginDetails", getApplicationContext().MODE_PRIVATE);
       // sharedPreferences.edit().putBoolean("User",false).commit();
        context=getApplicationContext();
        activity=this;


        //get authentication
        Auth auth=new Auth(context);
        boolean bool = auth.getAuth();
        Toast.makeText(getApplicationContext(),Boolean.toString(bool),Toast.LENGTH_LONG).show();


        if(!bool) {
            //on button click
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    infoGatherer infogatherer = new infoGatherer(activity);
                    arrayList = infogatherer.infogive();
                    sharedPreferences.edit().putBoolean("User",true).commit();
                    Auth auth=new Auth(context);
                    boolean bool = auth.getAuth();
                    Toast.makeText(getApplicationContext(),Boolean.toString(bool),Toast.LENGTH_LONG).show();
                    intent = new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("info",arrayList);
                    startActivity(intent);
                    finish();

                }
            });

        }else{
          intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
            finish();
        }




    }
}
