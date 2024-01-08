package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    static Statement statement;
    static Connection connection;
    static ResultSet resultSet;
    private static Statement establishConnection(){
        try {
            connection = DriverManager.getConnection(getProp("connection_string"), getProp("username"),
                    getProp("password"));
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException ex ){
            throw new RuntimeException("Could not connect to DB");
        }

        return statement;


    }
    private static String getProp(String key) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/database.properties"));
        }catch(IOException ex) {
            throw new RuntimeException("Could not find properties file");
        }
        return properties.getProperty(key);
    }

    public static ResultSet queryDB(String query){
        Statement statement1 = statement;
        try {
            resultSet = statement1.executeQuery(query);
        }catch (SQLException ex){
            throw new RuntimeException("Failed to execute query");
        } finally {
            closeConnection();
        }
       return  resultSet;
    }

    private static void closeConnection(){

        try {

            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
