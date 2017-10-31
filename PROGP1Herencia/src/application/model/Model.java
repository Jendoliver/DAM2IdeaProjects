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

    public Model() {
        squads = new HashMap<>();
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
        String squadName1 = splittedOperation.pop().toLowerCase(), squadName2 = splittedOperation.pop().toLowerCase();
        if(!squads.containsKey(squadName1) || !squads.containsKey(squadName2)) {
            throw new DoesntExistException();
        }
        // BATTLE DATA INITIALIZATION
        Squad squad1 = squads.get(squadName1), squad2 = squads.get(squadName2);
        String squad1Name = squad1.getName(), squad2Name = squad2.getName();
        double squad1Atk = squad1.calculateAtkPower(), squad2Atk = squad2.calculateAtkPower();
        double squad1Def = squad1.calculateDefPower(), squad2Def = squad2.calculateDefPower();
        double squad1FinalAtk = (squad1Atk - squad2Def) > 0 ? squad1Atk - squad2Atk : 0;
        double squad2FinalAtk = (squad2Atk - squad1Def) > 0 ? squad2Atk - squad1Def : 0;
        int squad1WonRounds = 0, squad2WonRounds = 0;

        /* BATTLE BEGIN */
        StringBuilder output = new StringBuilder("<Inicio batalla...>\n");
        for(int i = 0; i < GameStatics.BATTLE_ROUNDS; i++) {
            int randomNumSquad1 = ThreadLocalRandom.current().nextInt(0, GameStatics.MAX_RANDOM_NUMBER + 1);
            int randomNumSquad2 = ThreadLocalRandom.current().nextInt(0, GameStatics.MAX_RANDOM_NUMBER + 1);
            /* ROUND BEGIN */
            output.append("Asalto nº ").append(i+1).append("\n");
            output.append("Ataca ").append(squad1Name).append(" - Nº Aleatorio: ").append(randomNumSquad1)
                    .append(" - Valor de su ataque: ").append(squad1FinalAtk + randomNumSquad1).append("\n");
            output.append("Ataca ").append(squad2Name).append(" - Nº Aleatorio: ").append(randomNumSquad2)
                    .append(" - Valor de su ataque: ").append(squad2FinalAtk + randomNumSquad2).append("\n");
            /* ROUND END, SUMMARY OF THE ROUND */
            if(squad1FinalAtk + randomNumSquad1 > squad2FinalAtk + randomNumSquad2) { // Squad 1 wins round
                output.append("Ganador del asalto: ").append(squad1Name).append("\n");
                squad1WonRounds++;
            } else if(squad1FinalAtk + randomNumSquad1 < squad2FinalAtk + randomNumSquad2) { // Squad 2 wins round
                output.append("Ganador del asalto: ").append(squad2Name).append("\n");
                squad2WonRounds++;
            } else { // Draw
                output.append("Asalto sin ganador. Ha habido empate.\n");
            }
        }
        /* BATTLE END, SUMMARY OF THE BATTLE */
        output.append("<Fin batalla...>\n");
        if(squad1WonRounds > squad2WonRounds) { // Squad 1 wins battle
            output.append("<OK: La batalla la ha ganado el escuadron ").append(squad1Name).append(" con ").append(squad1WonRounds).append(" asaltos>\n");
            squad1.addVictory();
        } else if(squad1WonRounds < squad2WonRounds) { // Squad 2 wins battle
            output.append("<OK: La batalla la ha ganado el escuadron ").append(squad2Name).append(" con ").append(squad2WonRounds).append(" asaltos>\n");
            squad2.addVictory();
        } else { // Draw
            output.append("<OK: La batalla ha acabado en empate>\n");
        }
        return output.toString();
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
}
