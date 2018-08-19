package com.example.simranjeet.myapplication43;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static java.lang.Integer.lowestOneBit;
import static java.lang.Integer.parseInt;
import com.facebook.stetho.Stetho;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity  {
    EditText et,et2,et3,et4;
    ImageView imv ;
    String image64,imageUri;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "MainActivity";
    Allitems allitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Stetho.initializeWithDefaults(this);


         allitems = new Allitems();
        final MySqlHelper mySqlHelper = new MySqlHelper(this);


        et = findViewById(R.id.etext);
        et2 = findViewById(R.id.etext2);
        et3 = findViewById(R.id.etext3);
        et4 = findViewById(R.id.etext4);
        Button btn = findViewById(R.id.btn);
        Button btn1 = findViewById(R.id.btn1);
         imv = findViewById(R.id.imv);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });





        final Allitems itm= (Allitems) getIntent().getSerializableExtra("item");
        if(itm==null) {


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    allitems.setName(et.getText().toString());
                    allitems.setBrand(et2.getText().toString());
                    allitems.setColor(et3.getText().toString());
                    allitems.setPrice(parseInt(et4.getText().toString()));
                    allitems.setImageUri(imageUri);
                  //  allitems.setImage64(image64);

                    Log.v(TAG,et4.getText().toString());
                    long a = mySqlHelper.insertData(allitems);
                    Toast.makeText(getApplicationContext(), "id is : " + a, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
            });
        }
        else{

            et.setText(itm.getName());
            et2.setText(itm.getBrand());
            et3.setText(itm.getColor());
            et4.setText(Integer.toString(itm.getPrice()));
            if(itm.getImageUri()!=null) {
                imv.setImageURI(Uri.parse(itm.getImageUri()));
            }

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(getApplicationContext(), "Innnn ", Toast.LENGTH_LONG).show();
                    allitems.setId(itm.getId());
                    allitems.setName(et.getText().toString());
                    allitems.setBrand(et2.getText().toString());
                    allitems.setColor(et3.getText().toString());
                    allitems.setPrice(parseInt(et4.getText().toString()));
                    if(imageUri!=null){
                        allitems.setImageUri(imageUri);}
                    //allitems.setImage64(image64);
                    //Toast.makeText(getApplicationContext(), parseInt(et4.getText().toString()), Toast.LENGTH_LONG).show();
                    long a = mySqlHelper.updateItems(allitems);




                    //CRASHING
                  //String S = Integer.toString(allitems.getId());
                   Toast.makeText(getApplicationContext(), "id is : " + allitems.getId() , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
            });



        }



    }

    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_LONG).show();
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    Log.i(TAG, "Image Path : " + selectedImageUri);
                    imageUri= selectedImageUri.toString();
                   String de= UriToBitmap(selectedImageUri);
                   //image64=de;
                    //Toast.makeText(getApplicationContext(),de,Toast.LENGTH_LONG).show();
                    //Log.i("base64",de);
                     imv.setImageURI(selectedImageUri);
                    // Set the image in ImageView

                    /*byte[] imageBytes = Base64.decode(de, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                   imv.setImageBitmap(decodedImage);
*/

                }
            }
        }
    }

    // Get the real path from the URI
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    private String UriToBitmap(Uri imageUri){
         InputStream imageStream = null;
        try {
            imageStream = getContentResolver().openInputStream(imageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        String encodedImage = encodeImage(selectedImage);
        return encodedImage;
    }
}

