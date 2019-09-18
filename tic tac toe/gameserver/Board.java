package gameserver;

class Board //extends Object
{
 char board[][];

 Board()
 {
   //create a board
   board = new char[3][3];
   
   //init the board with ' '
   int i, j;
   for(i =0; i < board.length; i++)//per row
    for(j =0; j < board[i].length; j++)//cols per row
      board[i][j] = ' ';//' ' == EMPTY
 
 }

 boolean isFull()
 {
  int i, j;
  for(i =0; i < board.length; i++)//per row
   for(j =0; j < board[i].length; j++)//cols per row
     if(board[i][j] == ' ')//' ' == EMPTY
       return false;
  
  return true;  
 } 

 boolean checkWins(char symbol)
 {
   int i;

   //row check
   for(i =0 ; i < board.length; i++)
     if(board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
       return true;

   //col check
   for(i =0 ; i < board.length; i++)
     if(board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
       return true;

   //diagonal check
   if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
     return true;

   //rev. diagonal check
   if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
     return true;

   return false; 
 }
 
 boolean updateBoard(int r, int c, char symbol)
 {
   if(r < 0 || r >= board.length)
    return false;

   if(c < 0 || c >= board.length)
    return false;

   if(board[r][c] != ' ')// ' ' == EMPTY
    return false;
  
   board[r][c] = symbol;
   return true;
 }

 //override
 public String toString()
 {
  //matrix ---> string "x, ,o;  o; x "
  StringBuffer sbuff = new StringBuffer();
  int i, j;
  for(i =0; i < board.length; i++)//per row
  {
   for(j =0; j < board[i].length; j++)//cols per row
   {
    sbuff.append(board[i][j]);
    if(!(j == board[i].length -1))
     sbuff.append(',');
   }
   if(!(i == board.length -1))
     sbuff.append(';');

  }
  return sbuff.toString();
 }

}//Board