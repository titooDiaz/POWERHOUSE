package shop.models;

import java.util.LinkedList;

public class Products {
    private int pk;
    private String name;
    private String code;
    private float price;
    public LinkedList<Product> products;
    private Boolean active;
    private String date;
    
    // builder
    public Products(String name, String code, float price, String date, Boolean
    active) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.date = date;
        this.active = active;
        this.products = new LinkedList<Product>();
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
                                                    
    //methods
    public void addProduct(Product p){
        products.add(p);
    }

    public int cantProducts(){
        return products.size();
    }
}
