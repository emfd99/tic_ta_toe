/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * INVARIANT(S) & CORRESPONDENCE(S)
 * @invariant  0 <= openPos <= MAX_ROW * MAX_COlUMN
 * @correspondence Rows == MAX_ROW
 *                 Columns == MAX_COlUMN
 *                 NumToWin == NEEDED_TO_WIN
 *                 AvailablePosition == openPos
 */
public class GameBoardMem extends AbsGameBoard
{
    //Declare {MAX_ROW} to represent maximum number of row on game board
    //Declare {MAX_COLUMN} to represent maximum number of columns on game board
    //Declare {NEEDED_TO_WIN} to represent number of player tokens in a row need to win the game
    private final int MAX_ROW;
    private final int MAX_COlUMN;
    private final int NEEDED_TO_WIN;

    //Declare {openPos} to represent number of open positions
    //Declare {board} to represent a game board
    private int openPos;
    private Map<Character, List<BoardPosition>> board;

    public GameBoardMem(int row, int column, int numToWin)
    {
        MAX_ROW = row;
        MAX_COlUMN = column;
        NEEDED_TO_WIN = numToWin;
        openPos = MAX_ROW * MAX_COlUMN;
        board = new HashMap<Character, List<BoardPosition>>();
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        board.computeIfAbsent(player, k -> new ArrayList<BoardPosition>());
        board.get(player).add(marker);
        openPos--;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        char player;
        List<BoardPosition> posList;

        for(Map.Entry<Character, List<BoardPosition>> entry: board.entrySet())
        {
            player = entry.getKey();
            posList = entry.getValue();
            if(posList.contains(pos))
                return player;
        }

        return ' ';
    }

    public int getNumRows()
    {
        return MAX_ROW;
    }

    public int getNumColumns()
    {
        return MAX_COlUMN;
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
