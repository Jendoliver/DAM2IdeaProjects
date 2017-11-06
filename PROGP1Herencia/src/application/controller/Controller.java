package application.controller;

import application.model.Model;
import application.model.exceptions.AlreadyExistsException;
import application.model.exceptions.DoesntExistException;
import application.model.exceptions.InvalidDataException;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Controller: the main game controller, responsible of parsing the user input and delegating it to the model for
 * the proper operations. It also gets the output back from the model and sends it to the view
 */
public class Controller
{
    private LinkedList<String> splittedOperation;
    private Model model;

    private String output;
    private boolean gameLoop;

    public Controller()
    {
        splittedOperation = new LinkedList<>();
        gameLoop = true;
        model = new Model();
    }

    public String getOutput() {
        return output;
    }
    public boolean isGameLooping() {
        return gameLoop;
    }
    public void setOperation(String operation) {
        if(operation != null)
            splittedOperation = new LinkedList<>(Arrays.asList(operation.split("\\s+")));
        else
            splittedOperation = new LinkedList<>();
    }

    public void processOperation() {
        try {
            switch(splittedOperation.pop()) {
                case "A": output = model.addSquad(splittedOperation); break;
                case "R": output = model.registerBattle(splittedOperation); break;
                case "M": output = model.improveSquad(splittedOperation); break;
                case "B": output = model.listBattles(splittedOperation); break;
                case "C": output = model.getRanking(splittedOperation); break;
                case "S":
                    if(splittedOperation.isEmpty()) {
                        output = "Goodbye!";
                        gameLoop = false;
                    } else {
                        output = "< ERROR 001: Nº de argumentos inválido >";
                    } break;
                default: output = "< ERROR 004: Operación incorrecta >\n";
            }
        } catch (AlreadyExistsException | DoesntExistException | InvalidDataException e) {
            output = e.getMessage();
        }
    }
}
