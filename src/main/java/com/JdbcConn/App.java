package com.JdbcConn;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws SQLException {
        createConnection();
    }
    private static final Logger logger= LoggerFactory.getLogger("FILE");
    public static  void createConnection() throws SQLException {

        logger.info("Creating connection");
       

        String jdbcUrl = "jdbc:mysql://172.17.0.2:3306/users";
        String username = "root";
        String password = "1234";


         try{
             Class.forName("com.mysql.cj.jdbc.Driver");

           Connection  conn= DriverManager.getConnection(jdbcUrl, username, password);
             User user=new User(4, "dawton", "kdfni839rhb=349-34-");

             String sql ="insert into user values(?,?,?)";

             PreparedStatement ps = conn.prepareStatement(sql);
             ps.setInt(1, user.getId());
             ps.setString(2, user.getName());
             ps.setString(3, user.getPassword());
             int i = ps.executeUpdate();
             if(i>0){
                 System.out.println("user insrted succesfully");
             }
             else  System.out.println("user inserted failed");

             conn.close();

         } catch (SQLException e) {
          System.out.println(e.getMessage());
         }catch (ClassNotFoundException e){
             System.out.println(e.getClass());
         }

    }
}
