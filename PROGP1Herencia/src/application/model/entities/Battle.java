package application.model.entities;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Battle implements Comparable<Battle>
{
    private Date battleDate;
    private Squad squad1;
    private Squad squad2;
    private Squad winner;
    private String battleLog;

    public Battle(Squad squad1, Squad squad2)
    {
        battleDate = new Date(System.currentTimeMillis());
        this.squad1 = squad1; this.squad2 = squad2;
        fight();
    }

    public Date getBattleDate() {
        return battleDate;
    }
    public Squad getSquad1() {
        return squad1;
    }
    public Squad getSquad2() {
        return squad2;
    }
    public Squad getWinner() {
        return winner;
    }
    public String getBattleLog() {
        return battleLog;
    }

    private void fight()
    {
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
            winner = squad1;
        } else if(squad1WonRounds < squad2WonRounds) { // Squad 2 wins battle
            output.append("<OK: La batalla la ha ganado el escuadron ").append(squad2Name).append(" con ").append(squad2WonRounds).append(" asaltos>\n");
            squad2.addVictory();
            winner = squad2;
        } else { // Draw
            output.append("<OK: La batalla ha acabado en empate>\n");
            winner = null;
        }
        battleLog = output.toString();
    }

    @Override
    public int compareTo(Battle battle) {
        return battleDate.compareTo(battle.battleDate);
    }
}
