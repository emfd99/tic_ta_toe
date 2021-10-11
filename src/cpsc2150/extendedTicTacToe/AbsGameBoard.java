/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

/**
 * INVARIANT(S) & CORRESPONDENCE(S)
 * @invariant n/a
 * @correspondence n/a
 */
public abstract class AbsGameBoard implements IGameBoard
{
    /**
     * METHOD CONTRACT(S)
     * @return a String representation of the game board
     * @pre n/a
     * @post toString() == [a String representation of the game board]
     */
    @Override
    public String toString()
    {
        //Declare {doubleDigit} to represent first, positive double digit number
        //Declare {board} to build String representation of game board
        //Declare {pos} to pass as argument for method whatAtPos(BoardPosition)
        int doubleDigit;
        StringBuilder board;
        BoardPosition pos;

        //Initialize {doubleDigit} to 10
        //Initialize {board} with two empty spaces
        doubleDigit = 10;
        board = new StringBuilder("   ");

        //Build the row that lists column numbers
        for(int i = 0; i < getNumColumns(); i++)
        {
            //Check if column number is double digit
            if(i < doubleDigit)
            {
                board.append(" ").append(i).append("|");
            }
            else
            {
                board.append(i).append("|");
            }
        }

        //Begin new line for {board} String
        board.append("\n");

        for(int i = 0; i < getNumRows(); i++)
        {
            //Check if row number is a double digit
            if(i < doubleDigit)
            {
                board.append(" ").append(i).append("|");
            }
            else
            {
                board.append(i).append("|");
            }

            for(int j = 0; j < getNumColumns(); j++)
            {
                //Append whatever is located at position <i,j>
                pos = new BoardPosition(i, j);

                //Check if position has space or token
                if(whatsAtPos(pos) == ' ')
                {
                    board.append("  ").append("|");
                }
                else
                {
                    board.append(whatsAtPos(pos)).append(" ").append("|");
                }
            }

            //Begin new line for {board} String
            board.append("\n");
        }

        return board.toString();
    }
}
