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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
        }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
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
