package com.example.simranjeet.myapplication43;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapt extends ArrayAdapter<String> {

   Context context;
    MySqlHelper mySqlHelper;
    Allitems allitemsArray[];




    public ListAdapt(Context context,String arr[]) {
        super(context,R.layout.btnlist,arr);
        mySqlHelper = new MySqlHelper(context);
        List<Allitems> allitemsList = mySqlHelper.getAllItems();
        allitemsArray = allitemsList.toArray(new Allitems[allitemsList.size()]);
        this.context=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View rowview = inflater.inflate(R.layout.btnlist,null,true);

        TextView titleText = (TextView) rowview.findViewById(R.id.tv1);
        ImageView imageView = (ImageView) rowview.findViewById(R.id.img_id);


        titleText.setText(allitemsArray[position].getName());

        imageView.setImageURI(Uri.parse(allitemsArray[position].getImageUri()));


        return rowview;


    }
}
