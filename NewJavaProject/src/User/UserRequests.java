/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;

/**
 *
 * @author Nazifah
 */
public class UserRequests {
    
    private int id;
    private String username;
    private String name;

    public UserRequests(int id, String username,String name) {
        this.id = id;
        this.username = username;
        this.name=name;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getName(){ return name;}
    
}
