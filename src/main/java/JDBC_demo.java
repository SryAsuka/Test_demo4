import java.sql.*;

public class JDBC_demo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String driver = "jdbc:mysql://127.0.0.1:3306/test_db";
        try {
            Connection connection = DriverManager.getConnection(driver,"root","123456");
            if (connection !=null){
                System.out.println("数据库连接成功！");
                System.out.println(connection);
            }
            //3.建立执行者
            assert connection != null;
            Statement statement = connection.createStatement();
            //4.查询数据库，获得结果集
            ResultSet resultSet = statement.executeQuery("select * from test_db");
            while (resultSet.next()){
                String id = resultSet.getString("Sno");
                String name = resultSet.getString("Sname");
                String sex = resultSet.getString("sex");
                String birthday = resultSet.getString("birthday");
                System.out.print("编号"+id);
                System.out.print(" 编号"+name);
                System.out.print(" 编号"+sex);
                System.out.println(" 编号"+birthday);
            }



            connection.close();
        }catch ( SQLException e){
            throw new RuntimeException(e);
        }



    }
}