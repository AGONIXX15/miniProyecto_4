package view.battle.console;

import battle.BattleTrainer;
import controllers.ControllerBattle;
import models.Pokemon;
import models.Trainer;
import view.battle.ViewBattle;

import java.util.Scanner;

public class BattlePokemonConsole implements ViewBattle {
    private final Scanner scanner = new Scanner(System.in);
    private ControllerBattle controller;
    public BattlePokemonConsole(ControllerBattle controller) {
        this.controller = controller;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    public int choosePokemon(Trainer trainer) {
        Scanner sc = new Scanner(System.in);
        boolean condition;
        int index;
        do {
            PokemonMenu.showAllPokemonsAlive(trainer.team);
            System.out.println(trainer.getNameTrainer() + " Escoje un pokemon de tu equipo para comenzar a luchar: "+ Colors.RESET);
            String line = sc.nextLine();
            if (!line.isEmpty()){
                index = Integer.parseInt(line)-1;
            } else {
                index = -1;
            }

            condition = !(index >= 0 && index < trainer.team.length && trainer.team[index].isAlive());
            if(index >= 0 && !trainer.team[index].isAlive()){
                System.out.println("⚠️Por Favor, escoje un pokemon que este vivo!");
            }
        }while(condition);
        return index;
    }

    @Override
    public void start(){
        do {
            int indexPokemon1 = choosePokemon(controller.trainer1);
            int indexPokemon2 = choosePokemon(controller.trainer2);
            controller.startCombat(indexPokemon1, indexPokemon2);
            do {
                int index = (controller.getTurn()) ? selectAttack(controller.getPokemon1()): selectAttack(controller.getPokemon2());
                controller.processAttack(index);
            }while(!controller.hasFinish());
        }while(!(BattleTrainer.trainerHasLost(controller.trainer1) ||  BattleTrainer.trainerHasLost(controller.trainer2)));

        if(BattleTrainer.trainerHasLost(controller.trainer1)) {
            System.out.printf("gano %s", controller.trainer2.getNameTrainer());
        } else {
            System.out.printf("gano %s", controller.trainer1.getNameTrainer());
        }
    }

    public int selectAttack(Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);
        boolean condition = false;
        int index;
        System.out.printf(Colors.WHITE_BOLD + "\nTurno de %s%n" + Colors.RESET, controller.getTurn() ? controller.trainer1.getNameTrainer() : controller.trainer2.getNameTrainer());
        do {
            PokemonMenu.showPokemonAttacks(pokemon);
            index = Integer.parseInt(sc.nextLine())-1;
            condition = !(index >= 0 && index < pokemon.getAttacks().length);
            if(condition){
                System.out.println(Colors.RED+"⚠️Por favor ingresa uno de los ataques disponibles!"+Colors.RESET);
            }
        }while(condition);
        return index;
    }
}
