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
        String species = splittedOperation.pop().toLowerCase();
        if( !species.equals("terran") && !species.equals("zerg") && !species.equals("protoss")) {
            throw new InvalidDataException("< ERROR 002: Especie incorrecta >");
        }
        assert (species.equals("protoss") || species.equals("terran") || species.equals("zerg"));

        String squadName = splittedOperation.pop().toLowerCase();
        if(squads.containsKey(squadName)) {
            throw new AlreadyExistsException();
        }

        if(species.equals("protoss")) {
            if(splittedOperation.size() != 3) {
                throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
            }
        } else {
            if(splittedOperation.size() != 4) {
                throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
            }
        }
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
        switch(species) {
            case "terran": squads.put(squadName, new TerranSquad(squadName, squadProperties[0], squadProperties[1], squadProperties[2], squadProperties[3])); break;
            case "zerg": squads.put(squadName, new ZergSquad(squadName, squadProperties[0], squadProperties[1], squadProperties[2], squadProperties[3])); break;
            case "protoss": squads.put(squadName, new ProtossSquad(squadName, squadProperties[0], squadProperties[1], squadProperties[2])); break;

        }
        return "<OK: Escuadrón registrado>";
    }

    public String registerBattle(LinkedList<String> splittedOperation) throws InvalidDataException, DoesntExistException
    {
        if(splittedOperation.size() != 2) {
            throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
        }
        String squadName1 = splittedOperation.pop().toLowerCase();
        String squadName2 = splittedOperation.pop().toLowerCase();
        if(!squads.containsKey(squadName1) || !squads.containsKey(squadName2)) {
            throw new DoesntExistException();
        }
        Squad squad1 = squads.get(squadName1);
        Squad squad2 = squads.get(squadName2);
        String squad1Name = squad1.getName(), squad2Name = squad2.getName();
        double squad1Atk = squad1.calculateAtkPower();
        double squad1Def = squad1.calculateDefPower();
        double squad2Atk = squad2.calculateAtkPower();
        double squad2Def = squad2.calculateDefPower();
        double squad1FinalAtk = (squad1Atk - squad2Def) > 0 ? squad1Atk - squad2Atk : 0;
        double squad2FinalAtk = (squad2Atk - squad1Def) > 0 ? squad2Atk - squad1Def : 0;
        int squad1WonRounds = 0, squad2WonRounds = 0;
        StringBuilder output = new StringBuilder("<Inicio batalla...>\n");
        for(int i = 0; i < GameStatics.BATTLE_ROUNDS; i++) {
            int randomNumSquad1 = ThreadLocalRandom.current().nextInt(0, GameStatics.MAX_RANDOM_NUMBER + 1);
            int randomNumSquad2 = ThreadLocalRandom.current().nextInt(0, GameStatics.MAX_RANDOM_NUMBER + 1);
            output.append("Asalto nº ").append(i+1).append("\n");
            output.append("Ataca ").append(squad1Name).append(" - Nº Aleatorio: ").append(randomNumSquad1)
                    .append(" - Valor de su ataque: ").append(squad1FinalAtk + randomNumSquad1).append("\n");
            output.append("Ataca ").append(squad2Name).append(" - Nº Aleatorio: ").append(randomNumSquad2)
                    .append(" - Valor de su ataque: ").append(squad2FinalAtk + randomNumSquad2).append("\n");
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
        output.append("<Fin batalla...>\n");
        if(squad1WonRounds > squad2WonRounds) { // Squad 1 wins
            output.append("<OK: La batalla la ha ganado el escuadron ").append(squad1Name).append(" con ").append(squad1WonRounds).append(" asaltos\n");
            squad1.addVictory();
        } else if(squad1WonRounds < squad2WonRounds) { // Squad 2 wins
            output.append("<OK: La batalla la ha ganado el escuadron ").append(squad2Name).append(" con ").append(squad2WonRounds).append(" asaltos\n");
            squad2.addVictory();
        } else { // Draw
            output.append("<OK: La batalla ha acabado en empate>\n");
        }
        return output.toString();
    }

    public String improveSquad(LinkedList<String> splittedOperation) throws InvalidDataException, DoesntExistException
    {
        if(splittedOperation.size() != 3) {
            throw new InvalidDataException("< ERROR 001: Nº de argumentos inválido >");
        }
        String squadToImproveName = splittedOperation.pop().toLowerCase();
        if( ! squads.containsKey(squadToImproveName)) {
            throw new DoesntExistException();
        }

        Squad squadToImprove = squads.get(squadToImproveName);
        String propertyToImprove = splittedOperation.pop().toLowerCase();
        int newPropertyValue;
        try {
            newPropertyValue = Integer.parseInt(splittedOperation.pop());
            if(newPropertyValue < 0) {
                throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
            }
        } catch (NumberFormatException e) {
            throw new InvalidDataException("< ERROR 003: Dato incorrecto >");
        }
        // TODO: REFACTOR THIS SHIT SO IMPROVE IS A SQUAD METHOD IMPLEMENTED BY EACH SUBCLASS
        if(squadToImprove instanceof TerranSquad) {
            switch (propertyToImprove) {
                case "edificios": ((TerranSquad) squadToImprove).setnBuildings(newPropertyValue); break;
                case "tecnologia": case "tecnología": ((TerranSquad) squadToImprove).setTechLevel(newPropertyValue); break;
                default: throw new InvalidDataException("< ERROR 006: Propiedad incorrecta>");
            }
        } else if(squadToImprove instanceof ZergSquad) {
            switch (propertyToImprove) {
                case "esbirros": ((ZergSquad) squadToImprove).setnMinions(newPropertyValue); break;
                case "overlords": ((ZergSquad) squadToImprove).setnOverlords(newPropertyValue); break;
                default: throw new InvalidDataException("< ERROR 006: Propiedad incorrecta>");
            }
        } else if(squadToImprove instanceof ProtossSquad){
            if(propertyToImprove.equals("pilones")) {
                ((ProtossSquad) squadToImprove).setnPylons(newPropertyValue);
            } else {
                throw new InvalidDataException("< ERROR 006: Propiedad incorrecta>");
            }
        }
        return "<OK: Propiedad mejorada>";
    }

    public String getRanking() {
        if(squads.isEmpty()) {
            return "< CLASIFICACION: No hay escuadrones registrados >";
        }
        List<Squad> ranking = new ArrayList<>(squads.values());
        Collections.sort(ranking);
        StringBuilder output = new StringBuilder("< CLASIFICACION ACTUAL >\n");
        for(int i = 0; i < GameStatics.NUM_SQUADS_RANKING; i++) {
            output.append(ranking.get(i).toString());
        }
        return output.toString();
    }
}
