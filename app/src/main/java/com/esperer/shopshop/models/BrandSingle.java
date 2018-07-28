package com.esperer.shopshop.models;

public class BrandSingle {

    private String brandName;

    public BrandSingle( )
    {

    }

    public BrandSingle(String brandName, String brandImg, int brandId) {
        this.brandName = brandName;
        this.brandImg = brandImg;
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    private String brandImg;
    private int brandId;


}
