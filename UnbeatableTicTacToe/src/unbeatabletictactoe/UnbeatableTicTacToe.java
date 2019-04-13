/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unbeatabletictactoe;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Base extends JFrame implements ActionListener{
    JButton b[]=new JButton[10];
    JPanel pan;
    char board[][]=new char[4][4];
    int i=1;
    char player='x',comp='o';
    char winner='n';
    int buttonnum;
    JLabel wel;
     Base(String title){
        super(title); 
        
        ImageIcon imge=new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\logo1.png");
       setIconImage(imge.getImage()); 
        //panel
        pan=new JPanel(new GridLayout(3,3,5,5));
        pan.setBounds(0,70,300,300);
        pan.setVisible(true);
        add(pan);
        pan.setBackground(Color.black);
        //buttons
        while(i!=10){
            b[i]=new JButton(i+"");
            b[i].addActionListener(this);
            b[i].setVisible(true);
            pan.add(b[i]);
            b[i].setIcon(new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\empty tile.jpg"));
            b[i].setBackground(Color.white);
            i++;
            
        }
         wel=new JLabel(new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\logo.png"));
        wel.setFont(new Font("Georgia",Font.BOLD,20));
        add(wel);
        wel.setBackground(Color.black);
        wel.setVisible(true);
        wel.setBounds(0,0,300,70);
        //initialize board
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
            board[i][j]='n';
        }
        }
        
        setSize(300,400);
        setLayout(null);  
        setVisible(true); 
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       }
       
//action listener        
    @Override
    public void actionPerformed(ActionEvent e){
        //get button number
     String choice=e.getActionCommand();
     int ch=Integer.parseInt(choice);
     //check if button not already chosen
     
     
     if(board[ch%3==0?ch/3:(ch/3)+1][ch%3==0?3:ch%3]=='n'&& winner=='n'){
     //update board arrAy    
     board[ch%3==0?ch/3:(ch/3)+1][ch%3==0?3:ch%3]=player;
     b[ch].setIcon(new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\x1.png"));
  
      winner=winner();//if the players current move led to victory
      
      if(winner=='n'){//if not
          
      compAI obj=new compAI(player,comp);
      Nextmoveval xandy=obj.nextmove(board);//calculate next computer move
      board[xandy.row][xandy.col]=comp;
      
      //calculae equiv button value
      int r=xandy.row,c=xandy.col;
      buttonnum=c;
      while(r>1){
      buttonnum+=3;
      r--;
      }
      b[buttonnum].setIcon(new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\o.png"));
      } 
      //display game result
      winner=winner();
      if(winner!='n') { 
          if(winner=='t') {
             ImageIcon icon = new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\win.png"); 
             JOptionPane.showMessageDialog(null,"It is a TIE!!","still Unbeatable!",JOptionPane.INFORMATION_MESSAGE,icon);}
         else{
              if(winner==player) {JOptionPane.showMessageDialog(null,winner+" won","CONGRAGULATIONS",JOptionPane.PLAIN_MESSAGE); }//impossible
              else{
                  ImageIcon icon = new ImageIcon("C:\\RINKA\\java projects\\tictactoe\\win.png");
                  JOptionPane.showMessageDialog(null,"I won ,you lost!!","ha ha ha!",JOptionPane.INFORMATION_MESSAGE,icon);
              }
      } 
      }        
     }
     else{} 
   }
     //decides the winner
    char winner(){
         
      for(int i=1;i<4;i++){ //checks rows
          if(board[i][1]==board[i][2]&&board[i][3]==board[i][2]){
            if (board[i][1]==player) 
            return player; 
            else if (board[i][1]==comp) 
            return comp; 
          }
      }
        for(int i=1;i<4;i++){ //checks cols
          if(board[1][i]==board[2][i]&&board[3][i]==board[2][i]){
            if (board[1][i]==player) 
            return player; 
            else if (board[1][i]==comp) 
            return comp; 
          }
      }
        //checks diagonals
          if(board[1][1]==board[2][2]&&board[3][3]==board[2][2]){
            if (board[2][2]==player) 
             return player;  
            else if (board[2][2]==comp) 
            return comp; 
          }
            if(board[3][1]==board[2][2]&&board[1][3]==board[2][2]){
            if (board[2][2]==player) 
            return player; 
            else if (board[2][2]==comp) 
            return comp;
            }
           for(int i=1;i<4;i++)
            for(int j=1;j<4;j++)
            if (board[i][j]=='n') 
                return 'n'; 
          return 't'; 
    

    }
}
    class compAI{
    char player,comp;
    compAI(char p,char c){
     player=p;  
     comp=c;
    }
    //evaluates board value
  int evaluate(char board[][],int depth){
      
      for(int i=1;i<4;i++){ //checks rows
          if(board[i][1]==board[i][2]&&board[i][3]==board[i][2]){
            if (board[i][1]==player) 
            return -10; 
            else if (board[i][1]==comp) 
            return 10; 
          }
      }
        for(int i=1;i<4;i++){ //checks cols
          if(board[1][i]==board[2][i]&&board[3][i]==board[2][i]){
            if (board[1][i]==player) 
            return -10; 
            else if (board[1][i]==comp) 
            return 10; 
          }
      }
        //checks diagonals
          if(board[1][1]==board[2][2]&&board[3][3]==board[2][2]){
            if (board[2][2]==player) 
            return -10; 
            else if (board[2][2]==comp) 
            return 10; 
          }
            if(board[3][1]==board[2][2]&&board[1][3]==board[2][2]){
            if (board[2][2]==player) 
            return -10; 
            else if (board[2][2]==comp) 
            return 10;
            }
      
      return 0;//if nobody has won
  }
 boolean areMovesLeft(char board[][]) 
{ 
   for(int i=1;i<4;i++)
            for(int j=1;j<4;j++)
            if (board[i][j]=='n') 
                return true; 
    return false; 
}   
  int minimax(char board[][],int height,boolean maximizer){
      
      int score=evaluate(board,height);
 
         if (score == 10) 
        return score-height; 
         if (score == -10) 
        return score+height;
       if (areMovesLeft(board)==false) 
        return 0; 
    
      if(maximizer){ //computers turn
          int bestmax=-1000;
          for(int i=1;i<4;i++){
            for(int j=1;j<4;j++){
             if(board[i][j]=='n')   {    
                board[i][j]=comp;
                bestmax=Math.max(bestmax,minimax(board,height+1,!maximizer));
                board[i][j]='n';
                     } }} 
            //System.out.println(bestmax);
            return bestmax;
      }  
      else{//players turn
         int best=1000;
          for(int m=1;m<4;m++){
            for(int n=1;n<4;n++){
             if(board[m][n]=='n')   {    
                board[m][n]=player;
                best=Math.min(best,minimax(board,height+1,!maximizer));
                board[m][n]='n'; 
             } }} 
        return best;       
      }
      
  }
  Nextmoveval nextmove(char board[][]){
      Nextmoveval nnn=new Nextmoveval();
      int bestmove=-9999,resultmove;
      for(int i=1;i<4;i++){
            for(int j=1;j<4;j++){
             if(board[i][j]=='n')   {    
                board[i][j]=comp;
                resultmove=minimax(board,0,false);
                board[i][j]='n';
                //System.out.println(i+" "+j+" "+resultmove);
                if(resultmove>bestmove){
                    //System.out.println(i+" "+j+" "+resultmove);
                    nnn.row=i;
                    nnn.col=j;
                    bestmove=resultmove;
                }     
             }
        }
      }
      
      return nnn;
  } 
}
class Nextmoveval{
      int row,col;// acts as a structure
}

public class UnbeatableTicTacToe {
public static void main(String[] args) {
    Base base = new Base("UNBEATABLE ME!!");
    }
    
}
