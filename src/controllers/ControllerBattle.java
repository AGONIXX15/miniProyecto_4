package controllers;

import exceptions.NotInBattleException;
import models.Pokemon;
import models.Save;
import models.Trainer;
import view.battle.ViewBattle;
import models.Combat;
import view.utils.Pair;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ControllerBattle {
    public Trainer trainer1, trainer2;
    private ViewBattle viewBattle;
    private boolean isInBattle = false;
    private Combat combat;
    private Random random;
    private Save save;

    // constructor del controlador
    public ControllerBattle(Trainer trainer1, Trainer trainer2, Pair<Random, Long> pairRandom) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.random = pairRandom.first;
        save = new Save(trainer1.getNameTrainer(), trainer2.getNameTrainer(), pairRandom.second);
    }

    public ControllerBattle(Trainer trainer1, Trainer trainer2,Random rand, Save save) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.random = rand;
    }

    public void setViewBattle(ViewBattle viewBattle) {
        this.viewBattle = viewBattle;
    }

    public void sendMessage(String message){
        viewBattle.sendMessage(message);
    }

    public void startBattle(){
        if (trainer1 == null || trainer2 == null) {
            System.out.println("Por favor, Completa todos los campos");
        }else {
            System.out.println("¡Batalla iniciada!");
            viewBattle.start();
        }

    }

    /**
     * @param index1 indice del pokemon del primer entrenador
     * @param index2 indice del pokemon del segundo entrenador
     * crea el objeto combate el cual es el que se va a encargar de administrar los turnos
     * y ataques de los pokemones
     */
    public void startCombat(int index1, int index2) {
        Pokemon pokemon1 = trainer1.getTeamArray()[index1];
        Pokemon pokemon2 = trainer2.getTeamArray()[index2];
        this.combat = new Combat(pokemon1,pokemon2, this);
        isInBattle = true;
    }

    /**
     * @param index indice del ataque que ha sido escodigo
     *  deja que el combate se encargue de procesar el ataque ya que el maneja toda la logica
     *  sabe de quien es el turno por lo tanto procesara tal cual como se espera
     */
    public void processAttack(byte index){
        save.saveAttack(index);
        combat.makeAttack(index);
    }


    //retorna el pokemon del entrenador 1
    public Pokemon getPokemon1(){
        if(isInBattle){
            return combat.getPokemon1();
        }
        throw new NotInBattleException("No están en batalla");
    }

    // retorna el pokemon del entrenador 2
    public Pokemon getPokemon2(){
        if(isInBattle){
            return combat.getPokemon2();
        }
        throw new NotInBattleException("No están en batalla");
    }

    // retorna el turno actual del combate
    public boolean getTurn(){
        if(isInBattle){
            return combat.getTurn();
        }
        throw new NotInBattleException("No están en batalla!");
    }

    // pregunta si el combate ya ha finalizado
    public boolean hasFinish(){
        return combat.hasFinish();
    }

    public void saveTurn(Pair<Byte, Byte> turn){
        save.saveTurn(turn);
    }

    public void saveGame(File saveFile) throws IOException, ClassNotFoundException {
        save.saveGame(saveFile);
    }
}