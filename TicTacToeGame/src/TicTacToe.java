import java.util.Scanner;

public class TicTacToe {

	public static void main(String[] args) {
		
		int winsX = 0;
	    int winsO = 0; 
	    int draws = 0;
	    boolean rematch = true;    
	    
	    while(rematch){
	    	
	    	Scanner in = new Scanner(System.in); 
	    	
	    	int currentPlayer = -1;
	        boolean gameStatus = true;
	        String message = "";

	        char[] board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9' };
	        
	        while(gameStatus){
	            
	            //Apparently works only on windows or outside eclipse
	            System.out.print("\033[H\033[2J");  
                System.out.flush();  
	            
	            startingInstructions();
	            currentPlayer = getPlayer(currentPlayer);
	            drawBoard(board);
	            playersMove(currentPlayer, board, in);
	            
	            if(isGameDraw(board)){
	                gameStatus = false;
	                draws++;
	               message = "Game ends in a draw!";
	               
	            } else if(isWinner(board)){
	                gameStatus = false;
	                char winner = playerSign(currentPlayer);
	                //message = String.format("Winner is player - %c", winner);
	                message = "Winner is player - " + winner;
	                    
	                if (currentPlayer == 1){
	                    winsX++;
	                } else{
	                    winsO++;
	                }
	            }
	        }
	        //Apparently works only on windows or outside eclipse
	        System.out.print("\033[H\033[2J");  
            System.out.flush();   
	        
	        startingInstructions();
	        drawBoard(board);
		    
		    System.out.println(message);
		    
		    System.out.printf("Player X won %s times, Player O won %s times, while there are %s draws!", winsX, winsO, draws);
		    System.out.println("\n");
		    System.out.println("If you want a rematch type [rematch] otherwise just press [Enter]");
		    
		    
	    	String input = in.nextLine();
		    
	    	
		    if(!(input.equals("rematch"))){
		        rematch = false;
		    }  
	    }
		
	}
	
    static void startingInstructions(){
    	StringBuilder sb = new StringBuilder();
    	sb
    		.append("Welcome! Tic Tac Toe game starting!" + "\n")
    		.append("First player marker is X!" + "\n")
    		.append("Second player marker is O!" + "\n")
    		.append("Welcome! Tic Tac Toe game starting!" + "\n")
    		.append("The first player to have three pieces in a row (up, down, across, or diagonally) wins the game!" + "\n")
    		.append("When all 9 squares are full with no winner the game ends in a draw!" + "\n");
    
    	System.out.println(sb.toString());
    }
    
    static void drawBoard(char[] board){

        System.out.printf(" %s | %s | %s", board[0], board[1], board[2] + "\n");
        System.out.println("---+---+---");
        System.out.printf(" %s | %s | %s", board[3], board[4], board[5] + "\n");
        System.out.println("---+---+---");
        System.out.printf(" %s | %s | %s", board[6], board[7], board[8] + "\n");
        System.out.println("\n");
    }
    
    static int getPlayer(int player){
    	
        if (player == 1){
            return 2;
        } 
        return 1;
    }
    
    static boolean validateInput(String input){
    	
        if (input == null || input.isEmpty()) {
            return false;
        }
        
        if (input.length() > 1) {
            return false;
        }
        else if (!isNumeric(input)) {
             return false;   
        }
        
        return true;
    }
    
    static boolean isNumeric(String str) { 
    	
        try {  
            Integer.parseInt(str);  
            return true;
            } catch(NumberFormatException e){  
            return false;  
        } 
    }  
    
    static boolean isValidZone(int n, char[] board){
    	
        if(n < 1 || n > 9){
            return false;
        }
        
        if (board[n -1] == 'X' || board[n -1] == 'O') {
            return false;
        }
        
        return true;
    }
    
    static boolean isGameDraw(char[] board){
    	
        boolean isDraw = false;
        
        for (char zone : board) {
            
            if (zone == 'X' || zone == 'O') {
                isDraw = true;
            } else{
                isDraw = false;
                break;
            }
        }
        
        return isDraw;
    }
    
    static boolean areEqual(char[] board, int zone1, int zone2, int zone3){
        
        return board[zone1] == board[zone2] && board[zone2] == board[zone3];
    }

    static boolean isWinner(char[] board){
    	
        if (areEqual(board, 0, 1 ,2)) {
            return true;
        }
        else if (areEqual(board, 3, 4, 5)){
            return true;
        }
        else if (areEqual(board, 6, 7, 8)) {
            return true;
        }
        else if (areEqual(board, 0, 3, 6)){
            return true;
        }
        else if (areEqual(board, 1, 4, 7)) {
            return true;
        }
        else if (areEqual(board, 2, 5, 8)){
            return true;
        }
        else if (areEqual(board, 0, 4, 8)) {
            return true;
        }
        else if (areEqual(board, 6, 4, 2)) {
            return true;
        }
        return false;
    }
    
    static char playerSign(int playerNumber){
    	
        char player = ' ';
        
        if (playerNumber == 1){
            player = 'X';
        }
        else if (playerNumber == 2){
            player = 'O';
        } 
        
        return player;
    }
    
    static void playersMove(int playerNumber, char[] board, Scanner in){
        
        char player = playerSign(playerNumber);
        
        System.out.printf("Player %s's move! Select zone from 1 to 9.", player); 
		String input = in.nextLine();
		
		while(!validateInput(input)){
		    
		    System.out.println("Invalid input! Please choose an integer from 1 to 9.");
		    input = in.nextLine();
		}
		
		int index = Integer.parseInt(input);
		
		while(!isValidZone(index, board)){
		    
		    System.out.println("Invalid zone! Choose one which is both free and in the game board [from 1 to 9].");
		    
		    input = in.nextLine();
		    
		    while(!validateInput(input)){
		       System.out.println("Invalid input! Please choose an integer from 1 to 9.");
		       
		       input = in.nextLine();
		    }
		    index = Integer.parseInt(input);
		}
		
		board[index - 1] = player;

	}

}
