package view.battle.console;

import battle.BattleTrainer;
import controllers.ControllerBattle;
import exceptions.InvalidAttackSelectionException;
import exceptions.InvalidPokemonSelectionException;
import exceptions.NotInBattleException;
import exceptions.PokemonWeakenedException;
import datos.HistoryData;
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
        int index =-1;
        do {
            PokemonMenu.showAllPokemonsAlive(trainer.team);
            System.out.println(Colors.WHITE_BOLD + trainer.getNameTrainer() + " Escoje un pokemon de tu equipo para comenzar a luchar: "+ Colors.RESET);
            String line = sc.nextLine();


            try {
                if (!line.isEmpty()){
                    index = Integer.parseInt(line)-1;
                } else {
                    index = -1;

                }
                if (index < 0 || index >= trainer.team.length) {
                    throw new InvalidPokemonSelectionException("⚠️ Índice fuera de rango. Ingresa un número válido.");
                }

                if(index >= 0 && !trainer.team[index].isAlive()){
                    throw new PokemonWeakenedException("⚠️ Por favor, escoge un Pokémon que esté vivo!");
                }
            } catch (PokemonWeakenedException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Colors.RED + "⚠️ Entrada inválida. Ingresa solo números." + Colors.RESET);
            } catch (InvalidPokemonSelectionException e) {
                System.out.print(Colors.RED + e.getMessage() + Colors.RESET);
            }

            condition = !(index >= 0 && index < trainer.team.length && trainer.team[index].isAlive());

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
            System.out.printf(Colors.CYAN_BOLD + "Ha ganado el entrenador %s!!", controller.trainer2.getNameTrainer() + Colors.RESET);
        } else {
            System.out.printf(Colors.CYAN_BOLD + "Ha ganado el entrenador %s!!", controller.trainer1.getNameTrainer() + Colors.RESET);
        }

        System.out.println("\nQuieres ver el hististorial completo de la batalla?(s/n)");
        String respuesta = scanner.nextLine();
        if(respuesta.equals("s")){
            System.out.println(controller.getHistory().ShowAllPila());}
    }

    public int selectAttack(Pokemon pokemon) {
        Scanner sc = new Scanner(System.in);
        boolean condition = false;
        int index = -1;
        System.out.printf(Colors.WHITE_BOLD + "\nTurno de %s%n" + Colors.RESET, controller.getTurn() ? controller.trainer1.getNameTrainer() : controller.trainer2.getNameTrainer());
        do {
            PokemonMenu.showPokemonAttacks(pokemon);

            try{
                index = Integer.parseInt(sc.nextLine())-1;
                condition = !(index >= 0 && index < pokemon.getAttacks().length);
                if(condition){
                    throw new InvalidAttackSelectionException("⚠️Por favor ingresa uno de los ataques disponibles!");
                }
            } catch (NumberFormatException e){
                System.out.println(Colors.RED + "⚠️ Entrada inválida. Ingresa solo números." + Colors.RESET);
                condition = true;
            } catch (InvalidAttackSelectionException e){
                System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
                condition = true;
            }


        }while(condition);
        return index;
    }
}
