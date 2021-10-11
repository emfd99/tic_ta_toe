/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

/**
 * INTERFACE SPECIFICATION
 * Initialization Ensures: The user has given input to create a game board.
 * Defines: Rows: Z
 *          Columns: Z
 *          NumToWin: Z
 *          AvailablePositions: Z
 * Constraints: 3 <= Rows <= 100
 *              3 <= Columns <= 100
 *              3 <= NumToWin <= 25 AND NumToWin <= Rows AND NumToWin <= Columns
 *              0 <= AvailablePositions <= Rows * Columns
 */
public interface IGameBoard
{
    public static final int MAX_NUM_PLAYER = 10;
    public static final int MAX_ROW = 100;
    public static final int MAX_COLUMN = 100;
    public static final int MAX_NEEDED_TO_WIN = 25;

    public static final int MIN_NUM_PLAYER = 2;
    public static final int MIN_ROW = 3;
    public static final int MIN_COLUMN = 3;
    public static final int MIN_NEEDED_TO_WIN = 3;

    /**
     * METHOD CONTRACT(S)
     * @return the number of rows in the GameBoard
     * @precondition n/a
     * @postcondition getNumRows() == Rows
     */
    public abstract int getNumRows();

    /**
     * METHOD CONTRACT(S)
     * @return the number of columns in the GameBoard
     * @precondition n/a
     * @postcondition getNumColumns = Columns
     */
    public abstract int getNumColumns();

    /**
     * METHOD CONTRACT(S)
     * @return the number of tokens in a row needed to win the game
     * @precondition n/a
     * @postcondition getNumToWin = NumToWin
     */
    public abstract int getNumToWin();

    /**
     * METHOD CONTRACT(S)
     * Returns true if the position specified in pos is available, false otherwise.
     * If a space is not in bounds, then it is not available
     * @param pos is position of interest
     * @return true if the position specified in pos is available, false otherwise.
     * @precondition (0 <= pos.row <= Rows - 1) AND (0 <= pos.column <= Columns - 1)
     * @postcondition (checkSpace(BoardPosition pos) == true) IFF (board[pos.row][pos.column] == ' ')
     */
    public default boolean checkSpace(BoardPosition pos)
    {
        //Check if {pos} is in bounds
        if(pos.getRow() >= 0 && pos.getRow() <= getNumRows() - 1 &&
        pos.getColumn() >= 0 && pos.getColumn() <= getNumColumns() - 1)
        {
            //Check if space specified by {pos} is empty
            if(whatsAtPos(pos) == ' ')
            {
                return true;
            }
        }

        return false;
    }

    /**
     * METHOD CONTRACT(S)
     * Places the character in marker on the position specified by marker,
     * and should not be called if the space is not available.
     * @param marker is position that the active player has selected
     * @param player is active player
     * @pre (0 <= marker.row <= Rows - 1) AND (0 <= marker.column <= Columns - 1)
     * @pre checkSpace(marker) == true
     * @post (AvailablePosition == #avialablePos - 1)
     */
    public abstract void placeMarker(BoardPosition marker, char player);

    /**
     * METHOD CONTRACT(S)
     * This function will check to see if the game has resulted in a tie. A game is tied if there are no free board
     * positions remaining.You do not need to check for any potential wins, because we can assume that the players were
     * checking for win conditions as they played the game.It will return true if the game is tied, and false otherwise.
     * @return true if the game is tied, and false otherwise.
     * @pre n/a
     * @post (checkForDraw() == true) IFF ((AvailablePosition == 0) AND [There is no winner])
     */
    public default boolean checkForDraw()
    {
        BoardPosition pos;

        for(int i = 0; i < getNumRows() - 1; i++)
        {
            for(int j = 0; j < getNumColumns(); j++)
            {
                pos = new BoardPosition(i, j);

                if(whatsAtPos(pos) == ' ')
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * METHOD CONTRACT(S)
     * This function will check to see if the lastPos placed resulted in a winner. It so it will return true,
     * otherwise false. Passing in the last position will help limit the possible places to check for a win condition,
     * since you can assume that any win condition that did not include the most recent play made would have been caught
     * earlier.You may call other methods to complete this method
     * @param lastPos is last position of player
     * @return true if lastPos resulted in five in a row, otherwise false
     * @pre (0 <= lastPos.row <= Rows - 1) AND (0 <= lastPos.column <= Columns - 1)
     * @post (checkForWinner(BoardPosition lastPos) == true) IFF
     * (checkHorizontalWin(BoardPosition lastPos, char player) == true OR
     * checkVerticalWin(BoardPosition lastPos, char player) == true OR
     * checkDiagonalWin(BoardPosition lastPos, char player) == true)
     */
    public default boolean checkForWinner(BoardPosition lastPos)
    {
        //Declare and initialize {player} with the <char> that was placed at the last position
        char player = whatsAtPos(lastPos);

        return checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player) || checkDiagonalWin(lastPos, player);
    }

    /**
     * METHOD CONTRACT(S)
     * Checks to see if the last marker placed resulted in {NumToWin} in a row horizontally.Returns true if it does,
     * otherwise false
     * @param lastPos is last position of player
     * @param player is active player
     * @return true is last position resulted in a horizontal win, otherwise false
     * @precondition (0 <= lastPos.row <= Rows - 1) AND (0 <= lastPos.column <= Columns - 1)
     * @postcondition (checkHorizontalWin(BoardPosition lastPos, char player) == true) IFF
     * [last position resulted in a horizontal win with five tokens]
     */
    public default boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        //Initialize {inARow} to 1
        int inARow = 1;

        //Declare <BoardPosition> variables that will be used to check positions to the left and right of {lastPos}
        BoardPosition checkLeft, checkRight;

        //Loop four positions away from {lastPos}
        for(int i = 1; i < getNumToWin(); i++)
        {
            checkLeft = new BoardPosition(lastPos.getRow(), lastPos.getColumn() - i);
            checkRight = new BoardPosition(lastPos.getRow(), lastPos.getColumn() + i);

            //Check if we are horizontally inbound of the left
            if(checkLeft.getColumn() >= 0)
            {
                //Check if active player has another marker to the left of their {lastPos}
                if(isPlayerAtPos(checkLeft, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }

            //Check if we are horizontally inbound of the right
            if(checkRight.getColumn() <= getNumColumns() - 1)
            {
                //Check if active player has another marker to the right of their {lastPos}
                if(isPlayerAtPos(checkRight, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * METHOD CONTRACT(S)
     * Checks to see if the last marker placed resulted in {NumToWin} in a row vertically. Returns true if it does,
     * otherwise false
     * @param lastPos is last position of player
     * @param player is active player
     * @return true is last position resulted in a vertical win, otherwise false
     * @pre (0 <= lastPos.row <= Rows - 1) AND (0 <= lastPos.column <= Columns - 1)
     * @post (checkVerticalWin(BoardPosition lastPos, char player) == true) IFF
     * [last position resulted in a vertical win with five tokens]
     */
    public default boolean checkVerticalWin(BoardPosition lastPos, char player)
    {
        //Initialize {inARow} to 1
        int inARow = 1;

        //Declare <BoardPosition> variables that will be used to check positions to the top and bottom of {lastPos}
        BoardPosition checkUp, checkDown;

        //Loop four positions away from {lastPos}
        for(int i = 1; i < getNumToWin(); i++)
        {
            checkUp = new BoardPosition(lastPos.getRow() - i, lastPos.getColumn());
            checkDown = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn());

            //Check if we are vertically inbound of the top side
            if(checkUp.getRow() >= 0)
            {
                //Check if active player has another marker above their {lastPos}
                if(isPlayerAtPos(checkUp, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }

            //Check if we are vertically inbound of the bottom side
            if(checkDown.getRow() <= getNumRows() - 1)
            {
                //Check if active player has another marker below their {lastPos}
                if(isPlayerAtPos(checkDown, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * METHOD CONTRACT(S)
     * Checks to see if the last marker placed resulted in {NumToWin} in a row diagonally. Returns true if it does,
     * otherwise false. Note: there are two diagonals to check
     * @param lastPos is last position of player
     * @param player is active player
     * @return true is last position resulted in a diagonal win, otherwise false
     * @pre (0 <= lastPos.row <= Rows - 1) AND (0 <= lastPos.column <= Columns - 1)
     * @post (checkDiagonalWin(BoardPosition lastPos, char player) == true) IFF
     * [last position resulted in a diagonal win with five tokens]
     */
    public default boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {
        //Initialize {inARow} to 1
        int inARow = 1;

        //Declare <BoardPosition> variables that will be used to check positions to the top and bottom of {lastPos}
        BoardPosition checkLeftUp, checkLeftDown, checkRightUp, checkRightDown;

        for(int i = 1; i < getNumToWin(); i++)
        {
            checkLeftUp = new BoardPosition(lastPos.getRow() - i, lastPos.getColumn() - i);
            checkRightDown = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn() + i);

            //Check if we are inbound of the left corner
            if(checkLeftUp.getRow() >= 0 && checkLeftUp.getColumn() >= 0)
            {
                //Check if active player has another marker left, up of their {lastPos}
                if(isPlayerAtPos(checkLeftUp, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }

            //Check if we are inbound of the bottom right corner
            if(checkRightDown.getRow() <= getNumRows() - 1 && checkRightDown.getColumn() <= getNumColumns() - 1)
            {
                //Check if active player has another marker right, down of their {lastPos}
                if(isPlayerAtPos(checkRightDown, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }
        }

        inARow = 1;

        for(int i = 1; i < getNumToWin(); i++)
        {
            checkRightUp = new BoardPosition(lastPos.getRow() - i, lastPos.getColumn() + i);
            checkLeftDown = new BoardPosition(lastPos.getRow() + i, lastPos.getColumn() - i);

            //Check if we are inbound of the upper right corner
            if(checkRightUp.getRow() >= 0 && checkRightUp.getColumn() <= getNumColumns() - 1)
            {
                //Check if active player has another marker right, up of their {lastPos}
                if(isPlayerAtPos(checkRightUp, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }

            //Check if we are inbound of the bottom left corner
            if(checkLeftDown.getRow() <= getNumRows() - 1 && checkLeftDown.getColumn() >= 0)
            {
                //Check if active player has another marker left, down of their {lastPos}
                if(isPlayerAtPos(checkLeftDown, player))
                {
                    //Increment {inARow}
                    inARow++;

                    //Check if active player has five markers in a row
                    if(inARow == getNumToWin())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * METHOD CONTRACT(S)
     * Returns what is in the GameBoard at position pos. If no marker is there it returns a blank space char ‘ ‘
     * @param pos is position of interest
     * @return what is in the GameBoard at position pos
     * @pre (0 <= pos.row <= Rows - 1) AND (0 <= pos.column <= Columns - 1)
     * @post whatsAtPos(BoardPosition pos) == board[pos.row][pos.column]
     */
    public abstract char whatsAtPos(BoardPosition pos);

    /**
     * METHOD CONTRACT(S)
     * Returns true if the player is at pos, otherwise it returns false. Note: this method will be implemented very
     * similarly to whatsAtPos, but it’s asking a different question.
     * end up being radically different.
     * @param pos is position of interest
     * @param player is active player
     * @return true if the player is at pos, otherwise it returns false
     * @pre (0 <= pos.row <= Rows - 1) AND (0 <= pos.column <= Columns - 1)
     * @pre player == 'X' OR player == 'O'
     * @post (isPlayerAtPos(BoardPosition pos, char player) == true) IFF (player == board[pos.row][pos.column])
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        return pos.getRow() >= 0 && pos.getRow() <= getNumRows() - 1 && pos.getColumn() >= 0 &&
                pos.getColumn() <= getNumColumns() - 1 && whatsAtPos(pos) == player;
    }
}
