package JDBC;

import org.junit.Test;

import java.sql.*;

public class jdbcIntro {
    @Test
    public void ConnectionTest() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@URL:1521/ORCL",
                "username","password");
        Statement statement=   connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

       //print next row
        resultSet.next();
        System.out.println(resultSet.getString(1));

       //print every row until false !!will start from 2 raw because we used .next previously!!
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
        }
    }


    @Test
    public void practice1() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@url:1521/ORCL",
                "login","password");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();

        while(resultSet.next()){
            System.out.println("|");
            for(int i =1; i<=metaData.getColumnCount();i++){
                System.out.print(resultSet.getObject(i + " |"));
            }
            System.out.println();

        }
    }

















}
