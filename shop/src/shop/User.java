package shop;

public class User{
    private int pk;
    private String name;
    private String username;
    private String password;
    private String email;
    private String type;
    private Boolean active;
    private String key;

    
    public User(String name, String username, String password, String email){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void Login(){

    } 

    public String viewUser(){
        return "";
    }




}
