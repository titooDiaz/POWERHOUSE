package shop.models;

import java.util.LinkedList;

public class Products {
    private int pk;
    private String name;
    private String code;
    private float price;
    private LinkedList products;
    private Boolean active;
    private String date;
    private int contProducts;
    
    // builder
    public Products(String name, String code, float price, String date, Boolean
    active) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.date = date;
        this.active = active;
        this.contProducts = 0;
    }
    //getters and setters
    public int getPk() {
        return pk;
    }
    public void setPk(int pk) {
        this.pk = pk;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public int getContProducts() {
        return contProducts;
    }
    public void setContProducts(int contProducts) {
        this.contProducts = contProducts;
    }
                                                    
    //methods
    public void addProduct(){

    }

    public void saleProduct(){

    }

    public void editProducts(){

    }

    public String[] viewProducts(){
        String string[] = new String[5];
        return string ;
    }

    public double percentageProducts(){
        double a = 0;
        return a*1;
    }

}
