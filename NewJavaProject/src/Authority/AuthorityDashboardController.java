/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Authority;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



/**
 * FXML Controller class
 *
 * @author Nazifah
 */
public class AuthorityDashboardController {

     @FXML
     private Label InstituteNameLabel; 
     
     public void setInstituteName(String name)
     {
         InstituteNameLabel.setText(name);
     }
    
}
