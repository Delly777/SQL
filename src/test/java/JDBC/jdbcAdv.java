package JDBC;

import org.junit.Assert;
import org.junit.Test;
import utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jdbcAdv {

    @Test
    public void dsd() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@url:1521/ORCL",
                "login","password");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");
        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String,Object>> resultData = new ArrayList<>();

        while (resultSet.next()){ //every next raw
            Map<String,Object> rawMap = new HashMap<>();
            for (int i = 1; i <metaData.getColumnCount() ; i++) { // every next column
              rawMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            resultData.add(rawMap);
        }

        System.out.println(resultData.get(0));

        for (int i = 0; i < resultData.size(); i++) {
            if(String.valueOf(resultData.get(i).get("first_name")).equals("TJ")){
                System.out.println(resultData.get(i).get("salary"));
                Assert.assertEquals("$1400",
                        String.valueOf(resultData.get(i).get("salary")));
            }
        }
    }

    @Test
    public void test2(){
        ResultSet resultSet = JdbcUtils.queryDB("select * from employees");
    }
}
