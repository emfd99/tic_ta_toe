/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

/**
 * INVARIANT(S) & CORRESPONDENCE(S)
 * @invariant Row AND Column
 * @correspondence n/a
 */
public class BoardPosition
{
    private int row, column;

    public BoardPosition(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    /**
     * METHOD CONTRACT(S)
     * @return the row
     * @pre n/a
     * @post getRow() = Row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * METHOD CONTRACT(S)
     * @return the column
     * @pre n/a
     * @post getColumn() = Column
     */
    public int getColumn()
    {
        return column;
    }

    /**
     * METHOD CONTRACT(S)
     * @param object is object to be compared to THIS object
     * @return true if rows and columns are equal, otherwise return false
     * @pre [parameter must be reference type]
     * @post equals(Object object) == true IFF this.Row == object.Row AND this.Column == object.Column
     */
    @Override
    public boolean equals(Object object)
    {
        return (object instanceof BoardPosition) &&
                (((BoardPosition) object).row == row && ((BoardPosition) object).column == column);
    }

    /**
     * METHOD CONTRACT(S)
     * @return a string that represents the board position in the format <row>,<column>
     * @pre n/a
     * @post toString() == "<row>,<column>"
     */
    @Override
    public String toString()
    {
        return "<" + row + ">,<" + column + ">";
    }
}
