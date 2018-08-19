package com.example.simranjeet.myapplication43;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class infoGatherer {

    Activity context;
    EditText et1,et2;
    Spinner sp;
    myadapt aa;
    GpsLocation s;
    Location location;
    infoGatherer(Activity context){
      this.context=context;
      }

      ArrayList<String> infogive(){
        ArrayList<String> arrayList=new ArrayList<>();
        et1 = context.findViewById(R.id.et1);
        et2 =context.findViewById(R.id.et2);
        sp = context.findViewById(R.id.sp);
        arrayList.add(et1.getText().toString());
        arrayList.add(et2.getText().toString());
        arrayList.add(sp.getSelectedItem().toString());
       /* s = new GpsLocation(context);
        location= s.accessloc();
        arrayList.add(Double.toString(location.getLatitude()));
        arrayList.add(Double.toString(location.getLongitude()));*/
        return arrayList;
      }
}
