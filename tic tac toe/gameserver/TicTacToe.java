package gameserver;

class TicTacToe
{
    Board b;
    String player1, player2;
    int gameFlag;
    String current;

    TicTacToe(String player, String key)
    {
        b = new Board();
        player1  = player + "@" + key;
        this.gameFlag = -1; 
        current = player1;
    }

    boolean joinGame(String  player, String key)
    {
        if(gameFlag == -1)
        {
            player2 = player + "@" + key;
            if(player1.equalsIgnoreCase(player2))
                return false;
            gameFlag = 0;
            return true;
        }
        return false;
    }
  

    String play(String player, int r, int c)
    {
        String remark;
        char symbol;
        
        if(gameFlag == 0 )
        {
            if(current.equals(player))
            {
                if(current.equals(player1))
                    symbol = 'X';
                else
                    symbol = 'O';
                
                if(!b.isFull())
                {
                    if(b.updateBoard(r, c, symbol))
                    {
                        if(b.checkWins(symbol))
                        {
                            gameFlag = 1;
                            remark = b.toString() + "~" + current + "(" + symbol+ ") WINS!!!";
                        }  
                        else
                        {
                            if(current.equals(player1))
                            {
                                current = player2;
                                remark = b.toString() + "~" + player2 + " to play now";
                            }
                            else
                            {
                                current = player1;
                                remark = b.toString() + "~" + player1 + " to play now";
                            }
                        }
                    }
                    else
                        remark = "Invalid Move";
                }
                else
                {
                    remark = "Game Draw";
                    gameFlag = 1;
                }
            }
            else
                remark = "Kindly wait for your turn";
        }
        else
            remark = "Let another user join the game or start a new game";
        return remark; 
    }
    
    String getBoard()
    {
        return b.toString();
    }
}
