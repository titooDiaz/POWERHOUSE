package shop.models;

public class PaidMethod {
    private int pk;
    private String name;

    //builder
    public PaidMethod(int pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    //getter and setter
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

}
