package client;
import java.util.Scanner;
public class GamePlayer 
{

    public GamePlayer(int portNo) {
    }
    
    //helper method
    static int getInt()
    {
        
        try
        {
            //System.in.skip(System.in.available());
            //byte arr[] = new byte[20];
            //int n = System.in.read(arr);
            //return Integer.parseInt(new String(arr, 0 , n-2));
            
            Scanner scn = new Scanner(System.in);
            return scn.nextInt();
            
        }
        catch(Exception ex)
        {
            return -1;
        }
        
    }
    
    void menu()
    {
        int ch = 4;
        do
        {
            System.out.println("1. New Game");
            System.out.println("2. Join Game");
            System.out.println("3. Play Move");
            System.out.println("4. Exit");
            System.out.println("Enter Choice ");
            ch = getInt();

            switch(ch)
            {
                case 1:
                    System.out.println("New Game");
                    break;

                case 2:
                    System.out.println("Join Game");
                    break;

                case 3:
                    System.out.println("Play a Move");
                    displayBoard("x, ,o; , ,o; ,x, ");
                    break;

                case 4:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Wrong Choice");
            }
            
        }while(ch != 4);
        
    }
    
    void displayBoard(String brd)
    {
        String rows [] = brd.split(";");
        String cols[];
        for (String r: rows)
        {
            System.out.println();
            cols = r.split(",");
            for(String c : cols)
                System.out.print("[" + c +"]");
        }
        
        System.out.println();
    }
}
