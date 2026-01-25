
package Database;
import SignUpInfo.UserSignupInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertUserData {
    
    public void Insert(UserSignupInfo obj){
        
        String sql="INSERT INTO app_data(User_Name,Name,Gender,Age,Phone,Nid,Email,Emergency_Contact,Blood,Allergy) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try(Connection con=DBConnectionUser.connect();
              PreparedStatement ps= con.prepareStatement(sql)  )
        {
            ps.setString(0,obj.getUser_name());
            ps.setString(1,obj.getName());
            ps.setString(2,obj.getGender());
            ps.setString(3,obj.getAge());
            ps.setString(4,obj.getPhone_number());
            ps.setString(5,obj.getNID());
            ps.setString(0,obj.getEmail_id());
            
            ps.executeUpdate();
            System.out.println("Data inserted");
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
    }
}
