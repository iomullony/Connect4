import java.util.Scanner;

public class Connect4 {

    private int height = 6;
    private int width = 7;
    private String[][] board = new String[height+1][width+1];

    public void drawGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != "X" && board[i][j] != "O") {
                    board[i][j] = "-";
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
        for (int i = 1; i <= width; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public void getPosition(int player) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Player " + player + " - Enter position: ");  
        String str= sc.nextLine();

        try {
            int pos = Integer.parseInt(str);

            if (pos > width || pos < 1) {
                System.out.println("Invalid input. Try again.");
                getPosition(player);
            }

            for (int i = height-1; i >= 0; i--) {
                if (board[i][pos-1] == "-") {
                    if (player == 1) board[i][pos-1] = "X";
                    if (player == 2) board[i][pos-1] = "O";
                    break;
                }
            }
            drawGrid();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
            getPosition(player);
        }
    }

    public int checkStatus(int player) {
        int result = 0;
        int count1 = 0;
        int count2 = 0;
        int countd = 0;

        // check rows
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // checks if player 1 has a row
                if ((board[i][j] == board[i][j+1]) && (board[i][j] == "X")) count1++;
                else count1=0;
                
                // checks if player 2 has a row
                if ((board[i][j] == board[i][j+1]) && (board[i][j] == "O")) count2++;
                else count2=0;

                if (count1 == 3)  return 1;
                else if (count2 == 3) return 2;
            }        
        }

        // check columns
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                // checks if player 1 has a columnn
                if ((board[i][j] == board[i+1][j]) && (board[i][j] == "X")) count1++;
                else count1=0;
                
                // checks if player 2 has a column
                if ((board[i][j] == board[i+1][j]) && (board[i][j] == "O")) count2++;
                else count2=0;

                if (count1 == 3) return 1;
                else if (count2 == 3) return 2;
            }        
        }
        
        // checks diagonals to the right-up
        for (int i = height-1; i >= 1; i--) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != "-" && i >= 3 && j <= width-4) {
                    if ((board[i][j] == board[i-1][j+1]) && (board[i-1][j+1] == board[i-2][j+2]) && 
                    (board[i-2][j+2] == board[i-3][j+3]) && (board[i][j] == "X")){ return 1;}

                    if ((board[i][j] == board[i-1][j+1]) && (board[i-1][j+1] == board[i-2][j+2]) && 
                    (board[i-2][j+2] == board[i-3][j+3]) && (board[i][j] == "O")) return 2;
                }
                if (count1 == 3) return 1;
            }
        }

        // checks diagonals to the right-down
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != "-" && i <= height-4 && j <= width-4) {
                    if ((board[i][j] == board[i+1][j+1]) && (board[i+1][j+1] == board[i+2][j+2]) && 
                    (board[i+2][j+2] == board[i+3][j+3]) && (board[i][j] == "X")){ return 1;}

                    if ((board[i][j] == board[i+1][j+1]) && (board[i+1][j+1] == board[i-2][j+2]) && 
                    (board[i+2][j+2] == board[i+3][j+3]) && (board[i][j] == "O")) return 2;
                }
                if (count1 == 3) return 1;
            }
        }

        //checks for a draw
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((board[i][j]!= "-")) countd++;
                else countd=0;
                
                if (countd==height*width) return 3;
            }        
        }

        return result; // when there's nothing
    }

    public void game() {
        int player = 1;
        boolean play = true;

        System.out.println("Welcome to Connect 4!");
        drawGrid();

        while (play) {
            getPosition(player);

            if (checkStatus(player) == 1) {
                System.out.println("Player 1 wins!\n");
                play = false;
            } else if (checkStatus(player) == 2) {
                System.out.println("Player 2 wins!\n");
                play = false;
            } else if (checkStatus(player) == 3) {
                System.out.println("It's a draw!\n");
                play = false;
            }

            // to change turns
            if (player == 1) player++;
            else player--;
        }
    }

    public static void main(String[] args) throws Exception {
        Connect4 c4 = new Connect4();
        c4.game();
    }
}