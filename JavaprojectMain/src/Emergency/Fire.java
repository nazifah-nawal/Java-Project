/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergency;

/**
 *
 * @author Wellcome
 */
public class Fire implements Structure{
    public String to_do()
    {
        
        String FireT=
"1. Please stay calm and raise the alarm immediately if a fire occurs.\n" +
"2. Kindly evacuate using the nearest safe exit and use the stairs instead of elevators.\n" +
"3. Please stay low to avoid smoke and move to a safe assembly point once outside.\n";
        
        return FireT;
    }
    
   
    public String not_to_do(){
        
        String FireNT=
"1. Please do not panic or run without direction during a fire.\n"+
"2. Kindly avoid using elevators, opening hot doors, or returning to collect belongings.\n"+
"3. Please do not use water on electrical or oil fires and keep all exits clear.\n";
        
        return FireNT;
    }
}
