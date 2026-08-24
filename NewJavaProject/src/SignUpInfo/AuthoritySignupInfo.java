
package SignUpInfo;


public class AuthoritySignupInfo extends SignupInfo{
    
    private String instType;
    private String address;
    private String pass;
    
    public AuthoritySignupInfo()
    {
        
    }
    public AuthoritySignupInfo(String type,String name,String contact,String email,String address,String area,String username,String pass)
    {
        this.instType=type;
        this.name=name;
        this.phone=contact;
        this.email=email;
        this.address=address;
        this.area=area;
        this.username=username;
        this.pass=pass;
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getInstName() {
        return name;
    }

    public void setInstName(String instName) {
        this.name = instName;
    }

    public String getContact() {
        return phone;
    }

    public void setContact(String contact) {
        this.phone = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void showInfo()
    {
        System.out.println("Institution Type: "+instType);
        System.out.println("Name:"+name);
        System.out.println("Contact:"+phone);
        System.out.println("Email:"+email);
        System.out.println("Address:"+address);
        System.out.println("Area:"+area);
        System.out.println("Username:"+username);
        System.out.println("Pass:"+pass);
        
    }
    
}
