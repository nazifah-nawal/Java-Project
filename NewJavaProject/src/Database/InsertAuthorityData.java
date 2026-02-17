/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import SignUpInfo.AuthoritySignupInfo;

public class InsertAuthorityData extends InsertData {

    private AuthoritySignupInfo info;

    public InsertAuthorityData(Connection connection,
                               AuthoritySignupInfo info) {
        super(connection);
        this.info = info;
    }

   
    @Override
    public String insert() {

        String sql = "INSERT INTO authority_info " +
                     "(inst_type, inst_name, contact, email, address, area, username, pass) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, info.getInstType());
            ps.setString(2, info.getInstName());
            ps.setString(3, info.getContact());
            ps.setString(4, info.getEmail());
            ps.setString(5, info.getAddress());
            ps.setString(6, info.getArea());
            ps.setString(7, info.getUsername());
            ps.setString(8, info.getPass());

            ps.executeUpdate();
            return "SUCCESS";

        } catch (SQLException e) {
           
            if (e.getErrorCode() == 1062) {   // 1062 = Duplicate entry
               //System.out.println("Username already exists!");
               return e.getMessage();
              }

             e.printStackTrace();
             return "Error";
        }
    }
}

