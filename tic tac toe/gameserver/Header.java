package gameserver;

public class Header 
{
    static String composeHeader(int flag, String data)
    {
        StringBuffer sbuff = new StringBuffer();
        sbuff.append(flag);
        sbuff.append(",");
        sbuff.append(data);
        
        return sbuff.toString();
    }
    
    static int getRequest(String req)
    {
        try
        {
            String arr[] =req.split(";");
            return Integer.parseInt(arr[0]);
        }
        catch(Exception ex)
        {
            return CommunicationFlags.GAME_ERROR;
        }
    }
    
    static String getPlayerName(String req)
    {
        try
        {
            String arr[] =req.split(";");
            return arr[1];
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    
    static String[] getPlayerNameandKey(String req)
    {
        try
        {
            String arr[] =req.split(";");
            return arr[1].split(",");
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    
    static String[] getPlayerNameandKeyandCoords(String req)
    {//3;Shubham@key,0-1
        try
        {
            String arr[] =req.split(";");
            String arr1[] = arr[1].split(",");//Shubham@key,0-1
            String arr2[] = arr1[0].split("@");//Shubham@key
            String arr3[] = arr1[1].split("-");//0-1
            String result[] = new String[arr2.length + arr3.length];
            result[0] = arr2[0];
            result[1] = arr2[1];
            result[2] = arr3[0];
            result[3] = arr3[1];
            return  result;
            
            
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    
}
