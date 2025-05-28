package shop.models;

public class Service {
    // Attributes
    private int pk;
    private String date;
    private float price;
    private int paidMethod;
    private int services;


    // builder
    public Service(int pk, int paidMethod, String date, float price){
        this.pk = pk;
        this.paidMethod = paidMethod;
        this.date = date;
        this.price = price;
    }

    public Service(String date, int paidMethod, int services, float price) {
        this.paidMethod = paidMethod;
        this.date = date;
        this.services = services;
        this.price = price;
    }
    
    //getters and setters
    public int getPk() {
        return pk;
    }
    public void setPk(int pk) {
        this.pk = pk;
    }
    public int getPaidMethod() {
        return paidMethod;
    }
    public void setPaidMethod(int paidMethod) {
        this.paidMethod = paidMethod;
    }
    public int getServices() {
        return paidMethod;
    }
    public void setPaidServices(int paidMethod) {
        this.paidMethod = paidMethod;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}

