import java.sql.*;

public class CreatAndSelect {
    Connection connection= null;
    public static void main(String[] args) {
        CreatAndSelect creatAndSelect = new CreatAndSelect();
        creatAndSelect.initConnection();
//        creatAndSelect.showAllData();
//        creatAndSelect.add(1,"张三","男","1997/11/2","开发部");
//        creatAndSelect.add(2,"大强","男","1989/6/11","营销部");
//        creatAndSelect.add(3,"小王","男","1993/7/10","财务部");
//        creatAndSelect.add(4,"小胖","女","1998/7/10","开发部");
//        creatAndSelect.add(5,"李怡","女","2000/5/18","人事部");
        creatAndSelect.showAllData();


        creatAndSelect.closeConnection();
    }


    public void initConnection(){
        try{
            connection = DruidFactory.getConnection();
            Statement statement = connection.createStatement();
            if (statement != null){
                System.out.println("Creat Statement successfully");
            }
            //Create table tb_emp
//            String sqlStr = "create table tb_emp(id int primary key ," +
//                    "name varchar(100)," +
//                    "sex char(2)," +
//                    "birth date," +
//                    "dept varchar(20));";
//            assert statement != null;
//            statement.execute(sqlStr);
//            System.out.println("Creat table successfully");

        }catch (Exception e){
            //System.out.println("table creation Failed"+e);
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(int id,String name,String sex,String birthday,String dept){
        //Add,Submit,Modify,and Select table data
        try {
            String sql = "insert into tb_emp values (?,?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,sex);
            preparedStatement.setString(4,birthday);
            preparedStatement.setString(5,dept);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void showAllData(){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tb_emp");
            while (resultSet.next()){
                System.out.print(" 编号:"+resultSet.getString("id"));
                System.out.print(" 姓名:"+resultSet.getString("name"));
                System.out.print(" 性别:"+resultSet.getString("sex"));
                System.out.print(" 生日:"+resultSet.getString("birth"));
                System.out.println(" 部门:"+resultSet.getString("dept"));

            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
