package com.esperer.shopshop.models;


import android.os.Parcel;
import android.os.Parcelable;

public class TendingSingle implements Parcelable {

    private String productName;
    private String productDesc;
    private String productImg;
    private int productPrice;
    private int productDiscount;
    private String category;
    private int productId;
    private String brandName;
    private String productVarieties;

    public TendingSingle( ){

    }

    public TendingSingle(String productName, String productDesc, String productImg, int productPrice, int productDiscount, String category, int productId,String brandName,String productVarieties) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImg = productImg;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.category = category;
        this.productId = productId;
        this.brandName = brandName;
        this.productVarieties = productVarieties;
    }


    protected TendingSingle(Parcel in) {
        productName = in.readString();
        productDesc = in.readString();
        productImg = in.readString();
        productPrice = in.readInt();
        productDiscount = in.readInt();
        category = in.readString();
        productId = in.readInt();
        brandName = in.readString();
        productVarieties = in.readString();
    }

    public static final Creator<TendingSingle> CREATOR = new Creator<TendingSingle>() {
        @Override
        public TendingSingle createFromParcel(Parcel in) {
            return new TendingSingle(in);
        }

        @Override
        public TendingSingle[] newArray(int size) {
            return new TendingSingle[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setFoodName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setFoodDisc(String productDisc) {
        this.productDesc = productDesc;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setFoodImg(String productImg) {
        this.productImg = productImg;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int foodPrice) {
        this.productPrice = foodPrice;
    }

    public int getProductDiscount() {
        return productDiscount;
    }

    public void setFoodDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int foodId) {
        this.productId = foodId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductVarieties() {
        return productVarieties;
    }

    public void setProductVarieties(String productVarieties) {
        this.productVarieties = productVarieties;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productDesc);
        dest.writeString(productImg);
        dest.writeInt(productPrice);
        dest.writeInt(productDiscount);
        dest.writeString(category);
        dest.writeInt(productId);
        dest.writeString(brandName);
        dest.writeString(productVarieties);
    }
}
