package shop.models;

import java.util.LinkedList;

public class Services {
    //pk,name,code,price,date,active
    // attributes
    private int pk;
    private float price;
    private String name;
    private String date;
    private String code;
    private Boolean active;
    private int contServices;
    
    // composition root
    private LinkedList<Service> services;


    // builder

    public Services(String name, String code, float price, String date, Boolean active) {
        this.price = price;
        this.name = name;
        this.active = active;
        this.date  = date;
        this.code = code;
        }

    public int getPk() {
        return pk;
    }
    
    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public float getPrice() {
        return price;
    }
    
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public Boolean isActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
        }
    
    
    public int getContServices() {
        return contServices;
    }
    
        public void setContServices(int contServices) {
        this.contServices = contServices;
    }
    
    
    public LinkedList<Service> getServices() {
        return services;
    }
    
    public void setServices(LinkedList<Service> services) {
        this.services = services;
    }
    
    public void addService(){

    }

    public void deleteService(){

    }

    public void editService(){

    }

    public String[] viewServices(){
        String string[] = new String[5];
        return string ;
    }

    public double percentageService(){
        double a = 0;
        return a*1;
    }
}
