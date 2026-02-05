
package SignUpInfo;


public class AuthoritySignupInfo {
    
    private String instType;
    private String instName;
    private String contact;
    private String email;
    private String address;
    private String area;
    
    public AuthoritySignupInfo()
    {
        
    }
    public AuthoritySignupInfo(String type,String name,String contact,String email,String address,String area)
    {
        this.instType=type;
        this.instName=name;
        this.contact=contact;
        this.email=email;
        this.address=address;
        this.area=area;
    }
    
    public void showInfo()
    {
        System.out.println("Institution Type: "+instType);
        System.out.println("Name:"+instName);
        System.out.println("Contact:"+contact);
        System.out.println("Email:"+email);
        System.out.println("Address:"+address);
        System.out.println("Area:"+area);
        
    }
    
}
