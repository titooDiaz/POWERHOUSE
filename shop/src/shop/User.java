package shop;

public class User{
    // attribute
    private int pk;
    private String name;
    private String username;
    private String password;
    private String email;
    private String type;
    private Boolean active;
    private String key;

    //builder
    public User(String name, String username, String password, String email){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // methods
    public void Login(){

    } 

    public String viewUser(){
        return "";
    }

    public void logout(){

    }

    public void createUser(){

    }

    public void deleteUser(){
        
    }

    public void editUser(){
        
    }

    public void rememberPassword(){
        
    }

    public void upgratePassword(){
        
    }

    public  String[] generateReport(){
        String vector[] = new String[100];

        return vector;
    }

}
