/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController
{
    private int active;
    private boolean gameOver;
    private List<Character> player;
    private IGameBoard curGame;
    private TicTacToeView screen;

    /**
     * METHOD CONTRACT(S)
     * @param model the board implementation
     * @param view the screen that is shown
     * @param np The number of players for the game
     * @post the controller will respond to actions on the view using the model.
     */
    TicTacToeController(IGameBoard model, TicTacToeView view, int np)
    {
        int ascii = 65;
        this.active = 0;
        this.gameOver = false;
        this.player = new ArrayList<Character>();
        this.curGame = model;
        this.screen = view;
        for(int i = 0; i < np; i++)
        {
            player.add((char)(ascii + i));
        }
    }

    /**
     * METHOD CONTRACT(S)
     * @param row the row of the activated button
     * @param col the column of the activated button
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition pos = new BoardPosition(row, col);

        //Check if game is done
        if(!gameOver)
        {
            //Check if space is available
            if(curGame.checkSpace(pos))
            {
                //Place marker
                curGame.placeMarker(pos, player.get(active));
                screen.setMarker(row,col, player.get(active));

                //Check for win or draw event
                winOrDraw(active, player, pos, curGame);

                //Check if game is done before alternating players again
                if(!gameOver)
                {
                    //Alternate active player
                    active = alternatePlayer(active, player);
                    screen.setMessage("It is player " + player.get(active) + "'s turn.");
                }
            }
            else
            {
                //Print error message to screen
                screen.setMessage("That space is unavailable, please pick again");
            }
        }
        else
        {
            //Start new game
            newGame();
        }
    }

    /**
     * METHOD CONTRACT(S)
     * This method will change the index representing the active player
     * @param active is index number of active player
     * @param player is list of players
     * @return an index representing the active player
     * @pre [all parameters must have been initialized]
     * @post alternatePlayer(int active, List<Character> player) == [0...NumberOfPlayers - 1]
     */
    private int alternatePlayer(int active, List<Character> player)
    {
        if(active == player.size() - 1)
        {
            return 0;
        }
        return active + 1;
    }

    /**
     * METHOD CONTRACT(S)
     * This method will check if there was a win or draw.
     * @param active is index number of active player
     * @param player is list of players
     * @param pos is most recent
     * @param board is the game board
     * @return true if there is a win or draw, otherwise return false
     * @pre [all parameters must have been initialized]
     * @post winOrDraw(int active, List<Character> player, BoardPosition lastPos, IGameBoard board) == true IFF
     * [there is a win or draw]
     */
    private void winOrDraw(int active, List<Character> player, BoardPosition pos, IGameBoard board)
    {
        if(board.checkForDraw())
        {
            screen.setMessage("It's a draw! Click any button to start a new game");
            gameOver = true;
        }
        else if(board.checkForWinner(pos))
        {
            screen.setMessage("Player " + player.get(active) + " wins!");
            gameOver = true;
        }
    }

    /**
     * METHOD CONTRACT(S)
     * This method will terminate the current game screen,
     * and prompt the user(s) to set up a new game screen
     */
    private void newGame()
    {
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}
