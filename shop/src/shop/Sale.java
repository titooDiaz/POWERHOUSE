package shop;
import java.util.Date;
import java.time.LocalDateTime;

public class Sale {
    // attributes
    private int pk;
    private Date date;
    private PaidMethod paidMethod;
    private LocalDateTime time;
    private String user_pk;

    //builder
    public Sale(int pk, Date date, PaidMethod paidMethod, LocalDateTime time, String user_pk){
        this.pk = pk;
        this.date = date;
        this.paidMethod = paidMethod;
        this.time = time;
        this.user_pk = user_pk;
    }

    //getters and setters
    public int getPk() {
        return pk;
    }
    public void setPk(int pk) {
        this.pk = pk;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public PaidMethod getPaidMethod() {
        return paidMethod;
    }
    public void setPaidMethod(PaidMethod paidMethod) {
        this.paidMethod = paidMethod;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public String getUser_pk() {
        return user_pk;
    }
    public void setUser_pk(String user_pk) {
        this.user_pk = user_pk;
        }
    // methods
    public void saleInventory(){

    }

    public Product[] viewSales(){
        Product sales[] = new Product[100];
    return sales;    
    }
}
