package com.esperer.shopshop.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteSingle  {

    private int productId;
    private String productName;
    private String productBrand;
    private String productImg;
    private int price;
    private int productDiscount;


    public FavoriteSingle() {
    }

    public FavoriteSingle(int productId, String productName, String productBrand, String productImg, int price, int productDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productImg = productImg;
        this.price = price;
        this.productDiscount = productDiscount;

    }

    protected FavoriteSingle(Parcel in) {
        productId = in.readInt();
        productName = in.readString();
        productBrand = in.readString();
        productImg = in.readString();
        price = in.readInt();
        productDiscount = in.readInt();

    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }






}

