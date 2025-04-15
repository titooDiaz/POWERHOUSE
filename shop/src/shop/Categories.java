package shop;

public class Categories {
    //attributes
    private int pk;
    private String name;
    private String productOrService;
    private Service service[];
    private Product product[];

    //builder
    public Categories(int pk, String name, String productOrService) {
        this.pk = pk;
        this.name = name;
        this.productOrService = productOrService;
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


    public String getProductOrService() {
        return productOrService;
    }

    public void setProductOrService(String productOrService) {
        this.productOrService = productOrService;
    }

    //methods
    public void addCategories(){

    }

    public void editCategories(){

    }
    
}
