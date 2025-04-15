package shop;

public class Products {
    private int pk;
    private String name;
    private String code;
    private float price;
    private float purchase_price;
    private Product[] products;
    private Boolean active;
    private int contProducts;
    
    // builder
    public Products(int pk, String name, String code, float price, float purchase_price, Boolean
    active) {
        this.pk = pk;
        this.name = name;
        this.code = code;
        this.price = price;
        this.purchase_price = purchase_price;
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
    public float getPurchase_price() {
        return purchase_price;
    }
    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
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
