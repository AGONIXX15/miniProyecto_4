package battle;

import models.trainer.Trainer;
import models.pokemon.Pokemon;

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
