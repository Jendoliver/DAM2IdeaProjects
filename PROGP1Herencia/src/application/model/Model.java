package application.model;

import application.model.entities.*;
import application.model.exceptions.AlreadyExistsException;
import application.model.exceptions.DoesntExistException;
import application.model.exceptions.InvalidDataException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Model: the class responsible for accessing and modifying data
 * depending on the operations sent by the controller
 */
public class Model
{
    private Map<String, Squad> squads;
    private List<Battle> battles;

    public Model() {
        squads = new HashMap<>();
        battles = new ArrayList<>();
    }

    public String addSquad(LinkedList<String> splittedOperation) throws InvalidDataException, AlreadyExistsException
    {
        // Check that the species is a valid one
        String species = splittedOperation.pop().toLowerCase();
        if( !species.equals("terran") && !species.equals("zerg") && !species.equals("protoss")) {
            throw new InvalidDataException("< ERROR 002: Especie incorrecta >");
        }
        // Does the squad already exist in squads?
        String squadName = splittedOperation.pop().toLowerCase();
        if(squads.containsKey(squadName)) {
            throw new AlreadyExistsException();
        }
        // Check the number of arguments depending on the type of Squad
        if(species.equals("protoss")) {
            if(splittedOperation.size() != 3) {
                throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
            }
        } else {
            if(splittedOperation.size() != 4) {
                throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
            }
        }
        // Get the properties into the array
        int[] squadProperties = new int[4];
        for(int i = 0; i < splittedOperation.size(); i++) {
            try {
                int data = Integer.parseInt(splittedOperation.get(i));
                if (data < 0) { // Check for ints to be positive
                    throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
                }
                squadProperties[i] = data;
            } catch (NumberFormatException e) {
                throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
            }
        }
        // Construct the appropiate type of squad and put it into squads
        switch(species) {
            case "terran": squads.put(squadName, new TerranSquad(squadName, squadProperties[0], squadProperties[1], squadProperties[2], squadProperties[3])); break;
            case "zerg": squads.put(squadName, new ZergSquad(squadName, squadProperties[0], squadProperties[1], squadProperties[2], squadProperties[3])); break;
            case "protoss": squads.put(squadName, new ProtossSquad(squadName, squadProperties[0], squadProperties[1], squadProperties[2])); break;

        }
        return "<OK: Escuadrón registrado>";
    }

    public String registerBattle(LinkedList<String> splittedOperation) throws InvalidDataException, DoesntExistException
    {
        // Check if the number of arguments provided is valid
        if(splittedOperation.size() != 2) {
            throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
        }
        // Check if squads contains the specified squads to battle
        String squad1Name = splittedOperation.pop().toLowerCase(), squad2Name = splittedOperation.pop().toLowerCase();
        if(!squads.containsKey(squad1Name) || !squads.containsKey(squad2Name)) {
            throw new DoesntExistException();
        }
        // BATTLE DATA INITIALIZATION
        Squad squad1 = squads.get(squad1Name), squad2 = squads.get(squad2Name);
        Battle battle = new Battle(squad1, squad2);
        battles.add(battle);
        return battle.getBattleLog();
    }

    public String improveSquad(LinkedList<String> splittedOperation) throws InvalidDataException, DoesntExistException
    {
        // Check if the number of arguments provided is valid
        if(splittedOperation.size() != 3) {
            throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
        }
        // Check if squads contains the specified squad to improve
        String squadToImproveName = splittedOperation.pop().toLowerCase();
        if( ! squads.containsKey(squadToImproveName)) {
            throw new DoesntExistException();
        }
        Squad squadToImprove = squads.get(squadToImproveName);
        String propertyToImprove = splittedOperation.pop().toLowerCase();
        int newPropertyValue;
        // Check that newPropertyValue is actually an int
        try {
            newPropertyValue = Integer.parseInt(splittedOperation.pop());
            if(newPropertyValue < 0) {
                throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
            }
        } catch (NumberFormatException e) {
            throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
        }
        // Improve the squad depending on its type (delegates the improvement to Squad and its subclasses, who will check if the property is correct)
        squadToImprove.improve(propertyToImprove, newPropertyValue);
        return "<OK: Propiedad mejorada>";
    }

    public String getRanking() {
        if(squads.isEmpty()) {
            return "< CLASIFICACION: No hay escuadrones registrados >";
        }
        List<Squad> ranking = new ArrayList<>(squads.values());
        Collections.sort(ranking);
        StringBuilder output = new StringBuilder("< CLASIFICACION ACTUAL >\n");
        for(int i = 0; i < ((squads.size() < GameStatics.NUM_SQUADS_RANKING) ? squads.size() : GameStatics.NUM_SQUADS_RANKING); i++) {
            output.append(ranking.get(i).toString());
        }
        return output.toString();
    }

    public String listBattles(LinkedList<String> splittedOperation) throws InvalidDataException {
        if(battles.isEmpty()) {
            return "< BATALLAS: No hay batallas registradas >";
        }
        if(splittedOperation.size() > 1) {
            throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
        }
        Collections.sort(battles);
        StringBuilder output = new StringBuilder();
        // Used to display the existing battles
        if(splittedOperation.size() == 0) {
            output.append("< BATALLAS REGISTRADAS >\n");
            for (int i = 0; i < battles.size(); i++) {
                Battle battle = battles.get(i);
                Squad squad1 = battle.getSquad1(), squad2 = battle.getSquad2();
                output.append("--- BATALLA ").append(i+1).append(" ---\n Escuadron 1: ").append(squad1.toString())
                        .append("\t\tVS\n").append("Escuadron 2: ").append(squad2.toString()).append("\n")
                        .append("Ganador: ").append(battle.getWinner().getName()).append("\n\n");
            }
        } else { // Used to display the details of one battle
            int battleIndex;
            try {
                battleIndex = Integer.parseInt(splittedOperation.pop());
                if(battleIndex <= 0) {
                    throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
                }
            } catch (NumberFormatException e) {
                throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
            }
            if(battleIndex > battles.size()) {
                return "< ERROR 008: La batalla indicada no existe >";
            }
            Battle battle = battles.get(battleIndex - 1);
            Squad squad1 = battle.getSquad1(), squad2 = battle.getSquad2();
            output.append("< MOSTRANDO EL REGISTRO DE BATALLA DE ").append(squad1.getName()).append(" VS ")
                    .append(squad2.getName()).append(" (").append(battle.getBattleDate().toString()).append(") >\n\n")
                    .append(battle.getBattleLog());
        }
        return output.toString();
    }
}
