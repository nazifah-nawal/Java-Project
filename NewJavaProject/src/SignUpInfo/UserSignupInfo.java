
package SignUpInfo;

/**
 *
 * @author Lenovo
 */
public class UserSignupInfo extends SignupInfo {
    
   

    public String getUser_name() {
        return username;
    }

    public void setUser_name(String user_name) {
        this.username = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone_number() {
        return phone;
    }

    public void setPhone_number(String phone_number) {
        this.phone = phone_number;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getEmail_id() {
        return email;
    }

    public void setEmail_id(String email_id) {
        this.email = email_id;
    }

    public String getEmergency_contact() {
        return emergency_contact;
    }

    public void setEmergency_contact(String emergency_contact) {
        this.emergency_contact = emergency_contact;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getAllergies() {
        return Allergies;
    }

    public void setAllergies(String Allergies) {
        this.Allergies = Allergies;
    }
    
    private String gender;
    private String age;
    private String NID;
    private String emergency_contact;
    private String blood_group;
    public String Allergies;
    
    
    
    public UserSignupInfo(){
        
    }
    public UserSignupInfo(String user_name, String name, String gender, String age, String phone_number, String nid, String email_id, String emergency_contact, String area, String blood_group,String allergies)
    {
        this.username = user_name;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone_number;
        this.NID = nid;
        this.email = email_id;
        this.emergency_contact = emergency_contact;
        this.area = area;
        this.blood_group = blood_group;
        this.Allergies = allergies;
    }
    
    public void showInfo()
    {
        System.out.println("user details:");
        System.out.println("User_name: "+username);
        System.out.println("Name: "+name);
        System.out.println("Gender :"+gender);
        System.out.println("Age :"+age);
        System.out.println("Phone number: "+phone);
        System.out.println("NID: "+NID);
        System.out.println("Email ID :"+email);
        System.out.println("Emergengy contact: "+emergency_contact);
        System.out.println("City/Area: "+area);
        System.out.println("Blood group: "+blood_group);
        System.out.println("Allergies: "+Allergies);
    }
    
    
}
