package com.example.simranjeet.myapplication43;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myadapt extends ArrayAdapter<String> {


    Activity activity;
    String arg[];
    Integer arr[];




    public myadapt(Activity context, String arg[], Integer arr[]) {
        super(context,R.layout.mylist,arg);

        this.activity=context;
        this.arg = arg;
        this.arr = arr;



    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View rowview = inflater.inflate(R.layout.mylist,null,true);

        TextView titleText = (TextView) rowview.findViewById(R.id.tv);
        ImageView imageView = (ImageView) rowview.findViewById(R.id.iv);


        titleText.setText(arg[position]);
        imageView.setImageResource(arr[position]);


        return rowview;


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position,convertView,parent);

    }
}
