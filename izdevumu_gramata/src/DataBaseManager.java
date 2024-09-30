import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {

//data for DB connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/izdevumu_gramata";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "manaparole";

//Method for get local DB adress
    public static String getDbUrl() {
    return DB_URL;
}

//Method for get username
public static String getDbUser() {
    return DB_USER;
}

//Method for get password
public static String getDbPassword() {
    return DB_PASSWORD;
}






}
