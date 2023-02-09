package pk;

import java.util.logging.Logger;
import java.util.*;

class Validate {
    protected Validate() {
    }
    static Scanner sc = new Scanner(System.in);
    static char[][] arr;
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    static void assign(int user, int x, int y) {
        if (arr[x - 1][y - 1] == ' ') {
            if (user == 1) {
                arr[x - 1][y - 1] = 'X';
            } else {
                arr[x - 1][y - 1] = 'O';
            }
        } else {
            LOGGER.info("\nThe place is already filled.");
        }
    }

    static int diagonalCheck() {
        int count = 0;
        int count2 = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][i] == arr[i - 1][i - 1] && arr[i][i] != ' ') {
                count++;
            }
            if (arr[i][arr.length - i - 1] == arr[i - 1][arr.length - i] && arr[i][arr.length - i - 1] != ' ') {
                count2++;
            }
        }
        if (count2 == arr.length - 1 || count == arr.length - 1) {
            return 1;
        } else {
            return 0;
        }
    }

    static int horizontalCheck() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 'X') {
                    x++;
                } else if (arr[i][j] == 'O') {
                    y++;
                }
            }
            if (x == arr.length || y == arr.length) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    static int verticalCheck() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i] == 'X') {
                    x++;
                } else if (arr[j][i] == 'O') {
                    y++;
                }
            }
            if (x == arr.length || y == arr.length) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    static int checkDraw() {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == ' ')
                    count++;
            }
        }
        if (count == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    static void createGrid(int dim) {
        arr = new char[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                arr[i][j] = ' ';
            }
        }
    }

    static int checkWin() {
        if (diagonalCheck() == 1 || verticalCheck() == 1 || horizontalCheck() == 1) {
            return 1;
        } else if (checkDraw() == 1) {
            return 2;
        } else {
            return 0;
        }
    }

    static void show() {
        StringBuilder s = new StringBuilder("\n");
        for (int i = 0; i <= arr.length * 2; i++) {
            s.append("-");
        }
        s.append("\n");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                s.append("|" + arr[i][j]);
            }
            s.append("|");
            s.append("\n");
        }
        for (int i = 0; i <= arr.length * 2; i++) {
            s.append("-");
        }
        String str = "" + s;
        LOGGER.info(str);
    }
}

public class TicTacToe extends Validate {
    TicTacToe(){
        super();
    }
    static void dimVal(int dim) {
        while (dim % 2 == 0) {
            LOGGER.info("Enter an odd value: ");
            dim = sc.nextInt();
        }
    }

    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(String[] args) {
        LOGGER.info("Enter dimensions for tic-tac-toe:");
        int dim = sc.nextInt();
        createGrid(dim);
        int st = 0;
        do {
            if (checkWin() == 0) {
                show();
                LOGGER.info("\n-- User1 --\nEnter row number");
                int row = sc.nextInt();
                LOGGER.info("\nEnter column number");
                int col = sc.nextInt();
                assign(1, row, col);
                show();
                if (checkWin() == 0) {
                    LOGGER.info("\n-- User2 --\nEnter row number");
                    row = sc.nextInt();
                    LOGGER.info("\nEnter column number");
                    col = sc.nextInt();
                    assign(2, row, col);
                    show();
                }
                else if(checkWin() == 2){
                    LOGGER.info("Draw");
                    st = 1;
                } 
                else {
                    LOGGER.info("Player 1 wins");
                    st = 1;
                }

            }
            else if(checkWin() == 2){
                LOGGER.info("Draw");
                st = 1;
            }  
            else {
                LOGGER.info("Player 2 wins");
                st = 1;
            }

        } while (st == 0);
    }
}
