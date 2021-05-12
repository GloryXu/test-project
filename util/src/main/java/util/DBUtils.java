package util;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xuguangrong
 * @description 数据库工具类
 * @date Created at 18:35 2019/5/6
 */
public class DBUtils {

    /**
     * 执行mysql sql
     * @param url
     * @param user
     * @param password
     * @param sql
     * @return
     */
    public static Set<String> executeSql(String url, String user, String password, String sql) {
        Connection con;
        String driver = "com.mysql.jdbc.Driver";
        ResultSet resultSet;
        Set<String> result = new HashSet<>();
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(resultSet.getString("code"));
            }
            resultSet.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
        return result;
    }
}
