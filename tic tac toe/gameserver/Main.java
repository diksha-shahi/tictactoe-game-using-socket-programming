package gameserver;

public class Main {

    public static void main(String[] args) 
    {
        try
        {
            new Server(9000);
        }
        catch(Exception ex)
        {
            System.out.println("Err in Main.main()," + ex);
        }
    }
    
}
