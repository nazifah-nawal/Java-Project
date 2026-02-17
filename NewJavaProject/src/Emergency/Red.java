
package Emergency;


public class Red implements Structure{
    public String to_do()
    {
        
        String RedT=
"1. Stay calm and move to the nearest safe, open area.\n" +
"2. Keep a safe distance from fire, unstable structures, traffic, or suspicious activity.\n" +
"3. Stay alert and follow official emergency instructions.";
        
        return RedT;
    }
    
   
    public String not_to_do(){
        
        String RedNT=
"1. Do not panic or run blindly.\n" +
"2. Do not enter damaged buildings or unsafe areas.\n" +
"3. Do not spread unverified information or create crowding.\n";
        
        return RedNT;
    }
}
