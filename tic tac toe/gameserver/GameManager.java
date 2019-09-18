package gameserver;
import java.util.*;

class GameManager
{
 HashMap <String, TicTacToe> gameMap;
 
 GameManager()
 {
  gameMap = new HashMap<String, TicTacToe>();
 }

//1;Shubham
//2;Bhupinder,key
//3;Shubham@key,0-1
//...
 String Process(String request)
 {
    int reqFlag = Header.getRequest(request);
    String player1Name;
    TicTacToe ref;
    switch(reqFlag)
    {
        case CommunicationFlags.GAME_NEW:
            player1Name = Header.getPlayerName(request);
            if(player1Name != null)
            {
                String key = KeyGenerator.getObject().getKey();
                TicTacToe obj = new TicTacToe(player1Name , key);
                gameMap.put(key, obj);
                return "Game activated, to play let other user join using the key: \"" + key + "\";" + obj.getBoard();
            }
            else
            {//error response
                return "Player name missing, Game not activated!!!";
            }
            
            
        case CommunicationFlags.GAME_JOIN:
            String nK[] = Header.getPlayerNameandKey(request);
            //Bhupinder,key
            ref = gameMap.get(nK[1]);
            if(ref!= null)
            {
                ref.joinGame(nK[0], nK[1]);
                return "Game Joined Successfully;" + ref.getBoard();
            }
            else
                return "Invalid Key";
            
        case CommunicationFlags.GAME_PLAYNOW:
            String result[] = Header.getPlayerNameandKeyandCoords(request);
            //name, key, coord1, coord2
            ref = gameMap.get(result[1]);
            if(ref != null)
            {
                try
                {
                    return ref.play(result[0] + "@" + result[1], Integer.parseInt(result[2]), Integer.parseInt(result[3]));
                }
                catch(Exception ex)
                {
                    return "Invalid Move";
                }
            }
            else
                return "Invalid Key";
        }//switch
        return "Invalid Request";
    }//process
    
}//GameManager

