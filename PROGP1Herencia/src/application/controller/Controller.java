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
    private String operation;
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
        this.operation = operation;
        splittedOperation = new LinkedList<>(Arrays.asList(operation.split("\\s+")));
    }

    public void processOperation() {
        switch(splittedOperation.pop()) {
            case "A":
                try {
                    output = model.addSquad(splittedOperation);
                } catch (InvalidDataException | AlreadyExistsException e) {
                    output = e.getMessage();
                } break;
            case "R":
                try {
                    output = model.registerBattle(splittedOperation);
                } catch (InvalidDataException | DoesntExistException e) {
                    output = e.getMessage();
                } break;
            case "M":
                try {
                    output = model.improveSquad(splittedOperation);
                } catch (InvalidDataException | DoesntExistException e) {
                    output = e.getMessage();
                } break;
            case "C": output = model.getRanking(); break;
            case "S": output = "Goodbye!"; gameLoop = false; break;
            default: output = "< ERROR 004: Operación incorrecta >\n";
        }
    }
}
