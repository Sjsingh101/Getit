package com.example.simranjeet.myapplication43;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    ArrayList<Allitems> allitemsList;
    ItemsListAdapter myItemsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Stetho.initializeWithDefaults(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        MySqlHelper mySqlHelper=new MySqlHelper(this);
       allitemsList = mySqlHelper.getAllItems();
        String arr[] = new String[allitemsList.size()];
        final Allitems[] allitemsArray = allitemsList.toArray(new Allitems[allitemsList.size()]);
        for (int i=0;i < allitemsList.size();i++)
        {
            arr[i]= allitemsArray[i].getName();
        }

       // ListAdapt listAdapt = new ListAdapt(this,arr);
       //lv.setAdapter(listAdapt);


        lv = findViewById(R.id.lv);


  // to convert between list by long pres any item
/*        ListAdapt listAdapt = new ListAdapt(this,arr);
        lv.setAdapter(listAdapt);

lv.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {

        myItemsListAdapter = new ItemsListAdapter(getApplicationContext(), allitemsList);
        lv.setAdapter(myItemsListAdapter);
        return false;
    }
});*/
        myItemsListAdapter = new ItemsListAdapter(Main2Activity.this, allitemsList);
        lv.setAdapter(myItemsListAdapter);


      lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent = new Intent(Main2Activity.this,DetailActivity.class);
              intent.putExtra("item",allitemsArray[i]);
              Toast.makeText(getApplicationContext(),allitemsArray[i].getImageUri(),Toast.LENGTH_LONG).show();
              startActivity(intent);
          }
      });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"hello fab",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main2Activity.this,EditActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_del)
        {
            Toast.makeText(getApplicationContext(),"ddee",Toast.LENGTH_LONG).show();

            for (int i=0; i<allitemsList.size(); i++){
                if (myItemsListAdapter.isChecked(i)){
                    MySqlHelper helper = new MySqlHelper(getApplicationContext());
                    helper.deleteItem(allitemsList.get(i));
                    allitemsList.remove(i);
                    i--;
                    myItemsListAdapter.notifyDataSetChanged();


                }

            }



        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
