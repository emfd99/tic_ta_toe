/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard
{
    /**
     * This method is creating an indirection for the constructor call. In all of our test cases in this
     * file, instead of calling the constructor for GameBoard, we’ll call MakeAGameBoard. This
     * accomplishes the same thing, but it means the only reference to GameBoard will be
     * in the MakeAGameBoard method. This means when we want to test GameBoardMem, we just
     * need to change the constructor call inside of MakeAGameBoard, and no other piece of code
     * needs to change.
     * @return a time-efficient implementation of IGameBoard
     * @post MakeAGameBoard(int row, int column, int numToWin) == new GameBoard(row, column, numToWin)
     */
    private IGameBoard MakeAGameBoard(int row, int column, int numToWin)
    {
        return new GameBoard(row, column, numToWin);
    }

    /**
     * METHOD CONTRACT(S)
     * @param board is a game board
     * @return a formatted game board
     * @pre [board is initialized]
     * @post printExpected(char[][] board) == [a formatted game board]
     */
    private String printExpected(char[][] board)
    {
        //Declare {doubleDigit} to represent first, positive double digit number
        //Declare {board} to build String representation of game board
        int doubleDigit;
        StringBuilder expected;

        doubleDigit = 10;
        expected = new StringBuilder("   ");

        //Build the row that lists column numbers
        for(int i = 0; i < board[0].length; i++)
        {
            //Check if column number is double digit
            if(i < doubleDigit)
            {
                expected.append(" ").append(i).append("|");
            }
            else
            {
                expected.append(i).append("|");
            }
        }

        //Begin new line for {board} String
        expected.append("\n");

        for(int i = 0; i < board.length; i++)
        {
            //Check if row number is a double digit
            if(i < doubleDigit)
            {
                expected.append(" ").append(i).append("|");
            }
            else
            {
                expected.append(i).append("|");
            }

            for(int j = 0; j < board[0].length; j++)
            {
                //Check if position has space or token
                if(board[i][j] == ' ')
                {
                    expected.append("  ").append("|");
                }
                else
                {
                    expected.append(board[i][j]).append(" ").append("|");
                }
            }

            //Begin new line for {board} String
            expected.append("\n");
        }

        return expected.toString();
    }

    /**
     * ROUTINE CASE TEST
     */
    @Test
    public void testGameBoard_row50_col50_win10()
    {
        int row = 50;
        int col = 50;
        int win = 10;
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.toString().equals(printExpected(expected)));
    }

    /**
     * BOUNDARY CASE TEST
     */
    @Test
    public void testGameBoard_row3_col3_win3()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.toString().equals(printExpected(expected)));
    }

    /**
     * BOUNDARY CASE TEST
     */
    @Test
    public void testGameBoard_row100_col100_win25()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.toString().equals(printExpected(expected)));
    }

    /**
     * CHALLENGING TEST CASE
     */
    @Test
    public void testGameBoard_row3_col100_win3()
    {
        int row = 3;
        int col = 100;
        int win = 3;
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.toString().equals(printExpected(expected)));
    }

    /**
     * ROUTINE TEST CASE
     */
    @Test
    public void testCheckSpace_middle()
    {
        int row = 100;
        int col = 100;
        int win = 25;

        BoardPosition pos = new BoardPosition(49, 49);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.checkSpace(pos));
    }

    /**
     * BOUNDARY TEST CASE
     */
    @Test
    public void testCheckSpace_topLeft()
    {
        int row = 100;
        int col = 100;
        int win = 25;

        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.checkSpace(pos));
    }

    /**
     * BOUNDARY TEST CASE
     */
    @Test
    public void testCheckSpace_bottomRight()
    {
        int row = 100;
        int col = 100;
        int win = 25;

        BoardPosition pos = new BoardPosition(99, 99);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        assertTrue(gb.checkSpace(pos));
    }

    @Test
    public void testHorizontalWin_row3_col3_win3_top()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == 0)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkHorizontalWin(pos, token));
    }

    @Test
    public void testHorizontalWin_row3_col3_win3_bottom()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == row - 1)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkHorizontalWin(pos, token));
    }

    @Test
    public void testHorizontalWin_row100_col100_win25_top()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'X';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == 0)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkHorizontalWin(pos, token));
    }

    @Test
    public void testHorizontalWin_row100_col100_win25_bottom()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'Y';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == row - 1)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkHorizontalWin(pos, token));
    }

    @Test
    public void testCheckVerticalWin_row3_col3_win3_left()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(j == 0)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkVerticalWin(pos, token));
    }

    @Test
    public void testCheckVerticalWin_row3_col3_win3_right()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(j == col - 1)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkVerticalWin(pos, token));
    }

    @Test
    public void testCheckVerticalWin_row100_col100_win25_left()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'X';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(j == 0)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkVerticalWin(pos, token));
    }

    @Test
    public void testCheckVerticalWin_row100_col100_win25_right()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'Y';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(j == col - 1)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkVerticalWin(pos, token));
    }

    @Test
    public void testCheckDiagonalWin_row3_col3_win3_topLeftToBottomRight()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == j)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkDiagonalWin(pos, token));
    }

    @Test
    public void testCheckDiagonalWin_row3_col3_win3_topRightToBottomLeft()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        int diagDown = col - 1;
        char token = 'Y';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(j - i == diagDown)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                    diagDown -= 2;
                }
            }
        }
        assertTrue(gb.checkDiagonalWin(pos, token));
    }

    @Test
    public void testCheckDiagonalWin_row100_col100_win25_topLeftToBottomRight()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'X';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == j)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                }
            }
        }
        assertTrue(gb.checkDiagonalWin(pos, token));
    }

    @Test
    public void testCheckDiagonalWin_row100_col100_win25_topRightToBottomLeft()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        int diagDown = col - 1;
        char token = 'Y';
        BoardPosition pos = null;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(j - i == diagDown)
                {
                    pos = new BoardPosition(i, j);
                    gb.placeMarker(pos, token);
                    diagDown -= 2;
                }
            }
        }
        assertTrue(gb.checkDiagonalWin(pos, token));
    }

    @Test
    public void testCheckDiagonalWin_row3_col3_win3_lastPosInMiddle()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(new BoardPosition(0,0), token);
        gb.placeMarker(new BoardPosition(2,2), token);
        gb.placeMarker(new BoardPosition(1,1), token);
        assertTrue(gb.checkDiagonalWin(new BoardPosition(1, 1), token));
    }

    @Test
    public void testCheckDiagonalWin_row3_col3_win3_lastPosInBack()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(new BoardPosition(1,1), token);
        gb.placeMarker(new BoardPosition(2,2), token);
        gb.placeMarker(new BoardPosition(0,0), token);
        assertTrue(gb.checkDiagonalWin(new BoardPosition(0, 0), token));
    }

    @Test
    public void testCheckDiagonalWin_row3_col3_win3_playerBlock()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(1,1), 'Y');
        gb.placeMarker(new BoardPosition(2,2), 'X');
        assertFalse(gb.checkVerticalWin(new BoardPosition(2, 2), 'X'));
    }

    @Test
    public void testCheckForDraw_fullBoard_tokenX()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                pos = new BoardPosition(i, j);
                gb.placeMarker(pos, token);
            }
        }
        assertTrue(gb.checkForDraw());
    }

    @Test
    public void testCheckForDraw_fullBoard_tokenY()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                pos = new BoardPosition(i, j);
                gb.placeMarker(pos, token);
            }
        }
        assertTrue(gb.checkForDraw());
    }

    @Test
    public void testCheckForDraw_fullBoard_mixTokens()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        BoardPosition pos;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                pos = new BoardPosition(i, j);
                if(j % 2 == 0)
                {
                    gb.placeMarker(pos, 'X');
                }
                else
                {
                    gb.placeMarker(pos, 'Y');
                }
            }
        }

        assertTrue(gb.checkForDraw());
    }

    @Test
    public void testCheckForDraw_emptyBoard()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        IGameBoard gb = MakeAGameBoard(row, col, win);
        assertFalse(gb.checkForDraw());
    }

    @Test
    public void testWhatsAtPos_pos0_col0_tokenX()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos, token);
        assertEquals(gb.whatsAtPos(pos), token);
    }

    @Test
    public void testWhatsAtPos_pos2_col2_tokenX()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos, token);
        assertEquals(gb.whatsAtPos(pos), token);
    }

    @Test
    public void testWhatsAtPos_posRow1_posCol1_multipleTokens()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos , token);
        token = 'Y';
        pos = new BoardPosition(1, 1);
        gb.placeMarker(pos, token);
        assertEquals(gb.whatsAtPos(pos), 'Y');
    }

    @Test
    public void testWhatsAtPos_posRow1_posCol1_tokenYMistakenForTokenY()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = new BoardPosition(1, 1);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos , token);
        assertFalse(gb.whatsAtPos(pos) == 'Y');
    }

    @Test
    public void testWhatsAtPos_posRow1_posCol1_tokenYMistakenForTokenX()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos = new BoardPosition(1, 1);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos , token);
        assertFalse(gb.whatsAtPos(pos) == 'X');
    }

    @Test
    public void testIsPlayerAtPos_pos0_col0_tokenX()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(new BoardPosition(0,0), token);
        assertTrue(gb.isPlayerAtPos(pos, token));
    }

    @Test
    public void testIsPlayerAtPos_posRow2_posCol2_tokenY()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos = new BoardPosition(2, 2);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos, token);
        assertTrue(gb.isPlayerAtPos(pos, token));
    }

    @Test
    public void testIsPlayerAtPos_posRow1_posCol1_multipleTokens()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos , token);
        token = 'Y';
        pos = new BoardPosition(1, 1);
        gb.placeMarker(pos, token);
        assertTrue(gb.isPlayerAtPos(pos, token));
    }

    @Test
    public void testIsPlayerAtPos_posRow1_posCol1_tokenXMistakenForTokenY()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        BoardPosition pos = new BoardPosition(1, 1);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos, token);
        token = 'Y';
        assertFalse(gb.isPlayerAtPos(pos, token));
    }

    @Test
    public void testIsPlayerAtPos_posRow1_posCol1_tokenYMistakenForTokenX()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'Y';
        BoardPosition pos = new BoardPosition(1, 1);
        IGameBoard gb = MakeAGameBoard(row, col, win);
        gb.placeMarker(pos, token);
        token = 'X';
        assertFalse(gb.isPlayerAtPos(pos, token));
    }

    @Test
    public void testPlaceMarker_row3_col3_topLeft()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        expected[0][0] = 'X';

        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        gb.placeMarker(pos, token);

        assertEquals(gb.toString(), printExpected(expected));
    }

    @Test
    public void testPlaceMarker_row3_col3_bottomRight()
    {
        int row = 3;
        int col = 3;
        int win = 3;
        char token = 'X';
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        expected[2][2] = 'X';

        BoardPosition pos = new BoardPosition(2, 2);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        gb.placeMarker(pos, token);

        assertEquals(gb.toString(), printExpected(expected));
    }

    @Test
    public void testPlaceMarker_row100_col100_topLeft()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'X';
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        expected[0][0] = 'X';

        BoardPosition pos = new BoardPosition(0, 0);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        gb.placeMarker(pos, token);

        assertEquals(gb.toString(), printExpected(expected));
    }

    @Test
    public void testPlaceMarker_row100_col100_bottomRight()
    {
        int row = 100;
        int col = 100;
        int win = 25;
        char token = 'X';
        char[][] expected = new char[row][col];

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                expected[i][j] = ' ';
            }
        }

        expected[99][99] = 'X';

        BoardPosition pos = new BoardPosition(99, 99);
        IGameBoard gb = MakeAGameBoard(row, col, win);

        gb.placeMarker(pos, token);

        assertEquals(gb.toString(), printExpected(expected));
    }

    @Test
    public void testPlaceMarker_row10_row10_fullRow()
    {
        int row = 10;
        int col = 10;
        int win = 10;
        char token = 'X';
        char[][] expected = new char[row][col];
        IGameBoard gb = MakeAGameBoard(row, col, win);

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(i == 4)
                {
                    expected[i][j] = token;
                    gb.placeMarker(new BoardPosition(i, j), token);
                }
                else
                {
                    expected[i][j] = ' ';
                }
            }
        }

        assertEquals(gb.toString(), printExpected(expected));
    }
}
