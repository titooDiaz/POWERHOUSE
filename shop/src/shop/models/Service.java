package shop.models;
import java.util.Date;

public class Service {
    // attributes
    private int pk;
    private float purchase_price;
    private PaidMethod paidMethod;
    private Date date;
    private float price;


    // builder
    public Service(int pk, float purchase_price, PaidMethod paidMethod, Date date, float price){
        this.pk = pk;
        this.purchase_price = purchase_price;
        this.paidMethod = paidMethod;
        this.date = date;
        this.price = price;
    }
    
    //getters and setters
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
    public PaidMethod getPaidMethod() {
        return paidMethod;
    }
    public void setPaidMethod(PaidMethod paidMethod) {
        this.paidMethod = paidMethod;
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

    //methods
    public String viewService(){
        String a = "";
        return a;
    }
}
