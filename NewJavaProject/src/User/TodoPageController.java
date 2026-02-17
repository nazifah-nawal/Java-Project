/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TodoPageController  {

      @FXML
      private Label TodoLabel;
     @FXML
      private Label NottodoLabel;
      
      
      public void setTextT(String text)
      {
          TodoLabel.setText(text);
      }
      
      public void setTextN(String text)
      {
          NottodoLabel.setText(text);
      }
    
}