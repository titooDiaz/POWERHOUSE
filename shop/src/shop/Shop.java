package shop;

// import CSV class
import shop.CSVwriter.CSVWriter;

public class Shop {
    public static void main(String[] args) {
        System.out.println("hola mundo");

        // create user
        User user1 = new User("Name", "UserName", "Password", "Email@example.com");
        CSVWriter.appendUserToCSV(user1);
    }
}
