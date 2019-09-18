
package client;

public class Main 
{

    public static void main(String[] args) 
    {
        
        try
        {
            new GamePlayer(9001).menu();
        }
        catch(Exception ex)
        {
            System.out.println("Err in client Main.main()," + ex);
        }
    }
    
}
