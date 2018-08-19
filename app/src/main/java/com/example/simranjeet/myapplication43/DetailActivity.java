package com.example.simranjeet.myapplication43;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

public class DetailActivity extends AppCompatActivity {
    TextView tv1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_detail);
        tv1 = findViewById(R.id.textdetail);
        imageView = findViewById(R.id.imv1);



        final Allitems allitems= (Allitems) getIntent().getSerializableExtra("item");
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        String title = allitems.getName();
        getSupportActionBar().setTitle(title);

        tv1.setText(allitems.getColor());
        if(allitems.getImageUri()!=null) {
            imageView.setImageURI(Uri.parse(allitems.getImageUri()));
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(DetailActivity.this, EditActivity.class);
                intent.putExtra("item",allitems);
                startActivity(intent);

            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
