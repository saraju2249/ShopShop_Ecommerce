package com.esperer.shopshop.models;

public class OrderSingle {

    private int productId;
    private String productName;
    private String productBrand;
    private String productImg;
    private int quantity;
    private int price;

    public OrderSingle() {
    }

    public OrderSingle(int productId, String productName, String productBrand, String productImg, int quantity, int price) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productImg = productImg;
        this.quantity = quantity;
        this.price = price;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

