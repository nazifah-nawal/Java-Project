/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUser {
   
    public static final String URL="jdbc:sqlite:User.db";
    
    public static Connection connect()
    {
        Connection con=null;
        try
        {
            con=DriverManager.getConnection(URL);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return con;
    }
    
}
