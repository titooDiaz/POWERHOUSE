package shop;

import java.util.Date;

public class Product {
    // attributes
    private int pk;
    private float purchase_price;
    private Date date;
    private float price;
    private Boolean purchased;

    // builder
    public Product(int pk, float purchase_price, Date date, float price, Boolean purchased) {
        this.pk = pk;
        this.purchase_price = purchase_price;
        this.date = date;
        this.price = price;
        this.purchased = purchased;
    }

    // getters and setters
    public int getPk() {
    return pk;
    }

    public void setPk(int pk) {
    this.pk = pk;
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
