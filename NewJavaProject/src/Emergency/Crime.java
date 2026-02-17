
package Emergency;


public class Crime implements Structure {
    
    public String to_do()
    {
        
        String CrimeT=
"1. Move to a safe place and stay calm.\n" +
"2. Observe and remember key details (suspect, vehicle, direction).\n" +
"3. Follow police instructions and assist if asked.\n";
        
        return CrimeT;
    }
    
   
    public String not_to_do(){
        
        String CrimeNT=
"1.Do not confront or chase the criminal.\n" +
"2. Avoid touching evidence or risking your safety.\n" +
"3. Do not spread unverified info or panic others.\n";
        
        return CrimeNT;
    }
}
