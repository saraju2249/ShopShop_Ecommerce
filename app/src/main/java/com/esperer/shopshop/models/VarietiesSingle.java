package com.esperer.shopshop.models;

public class VarietiesSingle {

    private int varietiesId;
    private String varietiesName;
    private String varietiesImg;


    public VarietiesSingle() {
    }

    public VarietiesSingle( String varietiesName, String varietiesImg) {

        this.varietiesName = varietiesName;
        this.varietiesImg = varietiesImg;
    }

    public int getVarietiesId() {
        return varietiesId;
    }

    public void setVarietiesId(int varietiesId) {
        this.varietiesId = varietiesId;
    }

    public String getVarietiesName() {
        return varietiesName;
    }

    public void setVarietiesName(String varietiesName) {
        this.varietiesName = varietiesName;
    }

    public String getVarietiesImg() {
        return varietiesImg;
    }

    public void setVarietiesImg(String varietiesImg) {
        this.varietiesImg = varietiesImg;
    }
}
