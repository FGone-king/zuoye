package study;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class jdbc {
    private Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURl="jdbc:mysql://localhost:3306/hnb11?useSSL=false&serverTimezone=UTC";
//				System.out.println("创建驱动成功！");
            try {
                Connection connection=DriverManager.getConnection(dbURl, "root", "675563316");
                return connection;
//					System.out.println("创建链接成功！");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
//					System.out.println("链接失败");
                e.printStackTrace();

            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
//				System.out.println("驱动链接失败！");
            e.printStackTrace();
        }
        return null;
    }
    private void getcloseConnection(){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void testInsertDate(String name,String  publishers,String author){
//	    	1.创建数据库连接
        try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//					try {
//							Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Class?useSSL=false&serverTimezone=UTC","root","SQLMM0724");
            Connection connection=getConnection();


//	    	2.构建添加数据的SQL语句
            String sql="insert into test ( book_name,book_publishers,book_author)" +" values('"+name+"','"+ publishers+"','"+author+"')";
//	    	3.执行SQL语句.
            Statement statement =connection.createStatement();

//	    	4.得到执行后的结果，确定是否添加成功
            int rows=statement.executeUpdate(sql);
            if(rows>=1){
                System.out.println("添加成功!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            this.getcloseConnection();
        }

    }
    //	    } catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	    	}
    private void testDeleteDate(int id){

//	    	1.创建数据库连接
        try {
//				Class.forName("com.mysql.jdbc.Driver");
//				try {
//					Connection connection=DriverManager.getConnection("jdbc:mysql//localhost:3306/class11?serverTimezone=GMT%2B8&amp;useSSL=false","root","SQLMM0724");
            Connection connection=getConnection();
//					2.构建删除数据的sql语句
            String sql="delete from account where id="+id;
//			    	3.执行语句
            Statement statement=connection.createStatement();

//			    	4.得到执行结果，确定添加成功
            int rows=statement.executeUpdate(sql);
            if(rows>=1){
                System.out.println("删除成功!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.getcloseConnection();
        }

//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

    }
    private void testUpdateDate(int id,String name,String password){
//	    	1.创建数据库连接
        try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				try {
//					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Class?useSSL=false&serverTimezone=UTC","root","SQLMM0724");
            Connection connection=getConnection();
            //			    	2.创建update sql语句
            String sql="update account set name='"+name+"',password='"+password+"' where id=" + id;
            //			    	3.执行语句
            Statement statement=connection.createStatement();
//			    	4查看结果，是否成功
            int rows=statement.executeUpdate(sql);
            if(rows>=1){
                System.out.println("成功!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            this.getcloseConnection();
        }
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    }
    private  void findAllData(){

//	    	1.获取数据库连接
        Connection connection=getConnection();
//	    	2.构建sql语句
        String sql="select * from test";

//	    	3.执行sql语句
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
//		    	4.遍历结果
            StringBuffer buff= new StringBuffer();
            buff.append("=================================================================="+System.lineSeparator());
            buff.append("\tid\t|\tbook_name \t|\tbook_publishers\t book_author"+System.lineSeparator());

            buff.append("=================================================================="+System.lineSeparator());
            while(resultSet.next()){

                int id=resultSet.getInt("id");
                String name=resultSet.getString("book_name");
                String password =resultSet.getString("book_publishers");
                String  author = resultSet.getString("book_author");
                buff.append("\t|"+id+"\t|\t"+name+" \t|\t  "+password+System.lineSeparator());
            }
            System.out.println(buff);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.getcloseConnection();
        }
    }
    private void findAccountDataById(int id){
//	    	1.建立数据库连接
        Connection connection=getConnection();
//	    	2.构建sql语句
        String sql="select * from test where id="+id;
//	    	3.执行语句
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
//			4.遍历结果
            StringBuffer buffer=new StringBuffer();
            buffer.append("=================================================================="+System.lineSeparator());
            buffer.append("\tid\t|\tbook_name \t|\tbook_publishers\t book_author"+System.lineSeparator());
            buffer.append("=================================================================="+System.lineSeparator());
            while(resultSet.next()){
                int id1=resultSet.getInt("id");
                String name=resultSet.getString("book_name");
                String password =resultSet.getString("book_publishers");
                String  author = resultSet.getString("book_author");
                buffer.append("\t|"+id1+"\t|\t"+name+" \t|\t  "+password+System.lineSeparator());
            }
            System.out.println(buffer);
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.getcloseConnection();
        }
    }
    private void findAccountDateLikeKeyWord(String KeyWord){
//	    	1.创建数据库连接
        Connection connection=getConnection();
//	    	2.构建sql语句
        String sql="select * from test where book_name like '%"+KeyWord+"%' or book_anthor like '%"+KeyWord+"' or id like '%"+Integer.parseInt(KeyWord)+"%';";
//	    	3.执行SQL语句

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
//			4.遍历结果
            StringBuffer buffer1=new StringBuffer();
            buffer1.append("=================================================================="+System.lineSeparator());
            buffer1.append("\tid\t|\tbook_name \t|\tbook_publishers\t book_author"+System.lineSeparator());
            buffer1.append("=================================================================="+System.lineSeparator());
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("book_name");
                String password =resultSet.getString("book_publishers");
                String  author = resultSet.getString("book_author");
                buffer1.append("\t|"+id+"\t|\t"+name+" \t|\t  "+password+System.lineSeparator());
            }
            System.out.println(buffer1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            this.getcloseConnection();
        }


    }

    public  static  void main(String[] args){
        jdbc jdbc=new jdbc();
        while(true){
            Scanner scanner=new Scanner(System.in);
            System.out.println("============================================================================");
            System.out.println("|=======*****=============欢迎使用班级管理系统==================*****==========|");
            System.out.println("|===1.添加数据	2.删除数据	3.修改数据	4.查询数据	-1.退出 =====|");
            System.out.println("============================================================================");
            System.out.println("=============================请输入操作：======================================");
            int select;
            int reselect;
            select= scanner.nextInt();
            while((select>4||select<1)&&select!=-1){
                System.out.println("操作有误，请重新输入操作：");
                select=scanner.nextInt();
            }
            String value=null;

            if(select == 1){//添加
                System.out.println("请输入要添加的书名、出版社、作者，用逗号分隔，举例：金秋最帅,人大出版社，贝加尔湖");
                value=scanner.next();
                String[] values=value.split(",");
                jdbc.testInsertDate(values[0],values[1],values[2]);


            }
            else if(select==2){//删除
                int id=0;
                System.out.println("请输入要删除的记录id：");
                id=scanner.nextInt();
                jdbc.testDeleteDate(id);
            }
            else if(select == 3){//修改数据  update
                System.out.println("请输入要修改的记录id：");
                value=scanner.next();
                String[] values=value.split(",");
                jdbc.testUpdateDate(Integer.parseInt((values[0])),values[1],values[2]);
            }
            else if(select==4){

                System.out.println("请输入查询方式：1.查询所有 2.按id查询 3.按关键字查询");
                reselect=scanner.nextInt();
                if(reselect==1){
                    System.out.println("====================查询结果如下=====================");
                    jdbc.findAllData();

                }
                else if(reselect==2){
                    System.out.println("请输入要查询的id：");
                    int choose=scanner.nextInt();
                    System.out.println("====================查询结果如下=====================");
                    jdbc.findAccountDataById(choose);
                }
                else if(reselect==3){
                    System.out.println("请输入关键字：");
                    value=scanner.next();
                    System.out.println("====================查询结果如下=====================");
                    jdbc.findAccountDateLikeKeyWord(value);
                }
            }
            if(select== -1){
                System.out.println("金秋最帅，你可以走了~");
                System.exit(-1);
            }

        }
    }

}
