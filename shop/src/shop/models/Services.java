package shop.models;

public class Services {
    // attributes
    private int pk;
    private int price;
    private String name;
    private Boolean active;
    private int contServices;
    
    // composition root
    private Service[] services;


    // builder

    public Services(int pk, int price, String name, Boolean active, int contServices) {
        this.pk = pk;
        this.price = price;
        this.name = name;
        this.active = active;
        this.contServices = contServices;
        }

    public int getPk() {
        return pk;
    }
    
    public void setPk(int pk) {
        this.pk = pk;
    }
    
    public int getPrice() {
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
    
    
    public Service[] getServices() {
        return services;
    }
    
    public void setServices(Service[] services) {
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
