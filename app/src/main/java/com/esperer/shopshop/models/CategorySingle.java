package com.esperer.shopshop.models;

public class CategorySingle {

    private String category;
    private String categoryName;
    private String categoryImg;

    public CategorySingle( )
    {


    }

    public CategorySingle(String category, String categoryName, String categoryImg) {
        this.category = category;
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }
}
