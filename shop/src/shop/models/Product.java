package shop.models;

public class Product {
    // attributes
    private int pk;
    private float purchase_price;
    private String dateBuy;
    private String dateSell;
    private float price;
    private Boolean purchased;
    private int paidMethod;
    private int products;

    // builder
    public Product(float price, String dateBuy, int paidMethod, int products) {
        this.price = price;
        this.paidMethod = paidMethod;
        this.dateBuy = dateBuy;
        this.dateSell = "";
        this.products = products;
    }

    public Product(int pk, float purchase_price, float price, Boolean purchased, int paidMethod) {
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

    public int getPaidMethod() {
        return paidMethod;
    }

    public void setPaidMethod(int paidMethod) {
        this.paidMethod = paidMethod;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    public float getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
    }
    public String getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(String dateBuy) {
        this.dateBuy = dateBuy;
    }
    public String getDateSell() {
        return dateSell;
    }
    public void setDateSell(String dateSell) {
        this.dateSell = dateSell;
    }
    public Boolean isPurchased() {
        return purchased;
    }
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
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
