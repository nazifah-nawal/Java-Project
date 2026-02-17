/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

/**
 *
 * @author Wellcome
 */
public class Accident  {
    public String to_do()
    {
        
        String AccidentT=
"1. Please stay calm and breathe slowly.\n" +
"2. Kindly call for help or ask someone nearby to contact emergency services.\n" +
"3. Please try to stay still and avoid moving too much.\n";
        
        return AccidentT;
    }
    
   
    public String not_to_do(){
        
        String AccidentNT=
"1. Please do not panic or try to walk.\n" +
"2. Kindly avoid taking food, drink, or medication.\n" +
"3. Please do not ignore your injuries or delay getting help.\n";
        
        return AccidentNT;
    }
}
