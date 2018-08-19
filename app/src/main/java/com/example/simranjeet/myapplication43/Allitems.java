package com.example.simranjeet.myapplication43;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.Serializable;


public class Allitems implements Serializable {

    public Integer id;
    private String Name;
    private String Brand;
    private String Color;
    private Integer Price;
    private String Image64;
    private String ImageUri;
    private Boolean checked;

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Allitems(){}
    public Allitems(Integer id,String Name,String Brand,String Color,Integer Price,String ImageUri,Boolean checked){
        this.Name=Name;
        this.id=id;
        this.Brand=Brand;
        this.Color=Color;
        this.Price=Price;
        this.ImageUri=ImageUri;
        this.checked=checked;
    }

    public boolean getChecked(){
        return checked;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id=id;
    }

    public String getName() {
        return Name;
    }

    public void setName(@NonNull String name) {
        Name = name;
    }


    public String getBrand() {
        return Brand;
    }

    public void setBrand(@NonNull String brand) {
        Brand = brand;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getImage64() {
        return Image64;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(@NonNull String imageUri) {
        ImageUri = imageUri;
    }

    public void setImage64(@NonNull String image64) {
        Image64 = image64;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(@NonNull Integer price) {
        Price = price;
    }



}
