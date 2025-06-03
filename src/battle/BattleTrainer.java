package battle;

import models.Trainer;
import models.Pokemon;
import view.battle.gui.BattlePokemonGUI;

// clase para hacer la comprobacion si un entrenador ha perdido
public class BattleTrainer {

    public static boolean trainerHasLost(Trainer trainer) {
        for(Pokemon pokemon: trainer.getTeamArray()){
            if(pokemon.isAlive()){
                return false;
            }
        }
        return true;
    }

}
