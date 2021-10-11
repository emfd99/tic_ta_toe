/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

/**
 * INVARIANT(S) & CORRESPONDENCE(S)
 * @invariant  0 <= openPos <= MAX_ROW * MAX_COlUMN
 * @correspondence Rows == MAX_ROW
 *                 Columns == MAX_COlUMN
 *                 NumToWin == NEEDED_TO_WIN
 *                 AvailablePosition == openPos
 */
public class GameBoard extends AbsGameBoard
{
    //Declare {ROW} to represent maximum number of rows for this instance of <GameBoard>
    //Declare {COLUMN} to represent maximum number of columns on game board
    //Declare {NEEDED_TO_WIN} to represent number of player tokens in a row need to win the game
    private final int MAX_ROW;
    private final int MAX_COLUMN;
    private final int NEEDED_TO_WIN;

    //Declare {openPos} to represent number of open positions
    //Declare {board} to represent a game board
    private int openPos;
    private char[][] board;

    public GameBoard(int row, int column, int numToWin)
    {
        MAX_ROW = row;
        MAX_COLUMN = column;
        NEEDED_TO_WIN = numToWin;
        openPos = MAX_ROW * MAX_COLUMN;
        board = new char[MAX_ROW][MAX_COLUMN];

        //Fill 2D <char> array with blank spaces
        //Loop though rows
        for(int i = 0; i < MAX_ROW; i++)
        {
            //Loop through columns
            for(int j = 0; j < MAX_COLUMN; j++)
            {
                //Assign individual <char> cell with a blank space
                board[i][j] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        //Check if space selected by active player is available
        if(checkSpace(marker))
        {
            //Fill space with player marker
            board[marker.getRow()][marker.getColumn()] = player;
            openPos--;
        }
    }

    public char whatsAtPos(BoardPosition pos)
    {
        return board[pos.getRow()][pos.getColumn()];
    }

    public int getNumRows()
    {
        return MAX_ROW;
    }

    public int getNumColumns()
    {
        return MAX_COLUMN;
    }

    public int getNumToWin()
    {
        return NEEDED_TO_WIN;
    }

    @Override
    public boolean checkForDraw()
    {
        return openPos == 0;
    }
}
