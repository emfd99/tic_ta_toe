/**
 * @author Ejhazz Milford
 * @date 06/17/2020
 * @Section 001
 * @Assignment Project 5: Adding a GUI
 */

package cpsc2150.extendedTicTacToe;

/**
 * This class exists to declare our model, view, and controller objects, connect them, and start the game
 *
 * No changes are necessary
 */
public final class TicTacToeGame {

    /**
     * This is the entry point for our tic tac toe game
     * @param args ignored in this program
     */
    public static void main(String[] args)
    {

        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);


    }
}
