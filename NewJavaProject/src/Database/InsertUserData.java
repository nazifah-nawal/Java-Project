package Database;

import SignUpInfo.UserSignupInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class InsertUserData extends InsertData{
    
    private UserSignupInfo info;
    
    public InsertUserData(Connection connection, UserSignupInfo info)
    {
        super(connection);
        this.info=info;
    }
    
    
    @Override
    public String insert()
    {
        String sql = "INSERT INTO user_info " +
             "(username, name, gender, age, phone_num, nid, email, emergency_contact, blood_group, allergy, address) " +
             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement ps= connection.prepareStatement(sql))
        {
           ps.setString(1, info.getUser_name());
           ps.setString(2, info.getName());
           ps.setString(3, info.getGender());
           ps.setString(4, info.getAge());
           ps.setString(5, info.getPhone_number());
           ps.setString(6, info.getNID());
           ps.setString(7, info.getEmail_id());
           ps.setString(8, info.getEmergency_contact());
           ps.setString(9, info.getBlood_group());
           ps.setString(10, info.getAllergies());
           ps.setString(11, info.getArea());

           ps.executeUpdate();
           return "SUCCESS";
        }
        catch(SQLException e){
            
            if (e.getErrorCode() == 1062) {   // 1062 = Duplicate entry
               
               return e.getMessage();
              }

             e.printStackTrace();
             return "Error";
        }

        
    }
    
    
    
    
}