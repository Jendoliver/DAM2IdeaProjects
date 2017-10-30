package application.view;

import application.controller.Controller;
import com.jendoliver.io.InputAsker;

/**
 * View: the view of the game, a terminal on which the user can provide input
 * and recieve output from the controller
 */
public class View
{
    public static void main(String[] args)
    {
        Controller gameController = new Controller();
        do {
            gameController.setOperation(InputAsker.askString(""));
            gameController.processOperation();
            System.out.println(gameController.getOutput());
        } while (gameController.isGameLooping());
    }
}
