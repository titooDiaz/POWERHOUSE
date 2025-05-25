package shop.models;

import java.util.Date;

public class Product {
    // attributes
    private int pk;
    private float purchase_price;
    private Date date;
    private float price;
    private Boolean purchased;
    private String paidMethod;

    // builder
    public Product(int pk, float purchase_price, float price, Boolean purchased) {
        this.pk = pk;
        this.purchase_price = purchase_price;
        this.price = price;
        this.purchased = purchased;
    }

    public Product(int pk, float purchase_price, float price, Boolean purchased, String paidMethod) {
        this.pk = pk;
        this.purchase_price = purchase_price;
        this.price = price;
        this.purchased = purchased;
        this.paidMethod = paidMethod;
    }

    // getters and setters
    public int getPk() {
    return pk;
    }

    public void setPk(int pk) {
    this.pk = pk;
    }

    public String getPaidMethod() {
    return paidMethod;
    }

    public void setPaidMethod(int paidMethod) {
    this.paidMethod = paidMethod;
    }

    public float getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }
    
    // methods
    public String viewProduct(){
        String a = "";
        return a;
    }
}
