package com.esperer.shopshop.models;

import java.io.Serializable;

public class OfferSingle  {


    private String offerImg;
    private String categoryName;
    private int discount;
    private String type;
    private String offerBrandName;


    public OfferSingle( ){

    }

    public OfferSingle(String offerImg, String categoryName, int discount, String type, String offerBrandName) {
        this.offerImg = offerImg;
        this.categoryName = categoryName;
        this.discount = discount;
        this.type = type;
        this.offerBrandName = offerBrandName;
    }

    public String getOfferImg() {
        return offerImg;
    }

    public void setOfferImg(String offerImg) {
        this.offerImg = offerImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOfferBrandName() {
        return offerBrandName;
    }

    public void setOfferBrandName(String offerBrandName) {
        this.offerBrandName = offerBrandName;
    }
}
