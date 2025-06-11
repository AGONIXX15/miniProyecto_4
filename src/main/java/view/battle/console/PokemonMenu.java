package view.battle.console;

import models.Pokemon;
import models.Attack;
import models.TypeDamage;
import models.TypePokemon;
import view.battle.console.Colors;

import java.util.Scanner;

public class PokemonMenu {

    //mostrar pokemones disponibles
    public static void showAllPokemons(Pokemon[] pokemons) {
        System.out.println(Colors.WHITE_BOLD + "\n📋 Lista de Pokémon disponibles:"+ Colors.RESET);
        for (int i = 0; i < pokemons.length; i++) {
            System.out.println(Colors.RED+(i + 1) + Colors.WHITE_BOLD + ". " + pokemons[i].getName() +  " - Tipo: " + pokemons[i].getType()+Colors.RESET);
        }
    }

    //mostrar pokemones disponibles
    public static void showAllPokemonsAlive(Pokemon[] pokemons) {
        System.out.println(Colors.WHITE_BOLD + "\n📋 Lista de Pokémon disponibles:"+ Colors.RESET);
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i].isAlive()){
                System.out.println(Colors.RED+(i + 1) + Colors.WHITE_BOLD + ". " + pokemons[i].getName() + " - vida: " +pokemons[i].getHealth()  +"/" + pokemons[i].getHealthMax()  +  " - Tipo: " + pokemons[i].getType()+Colors.RESET);
            } else {
                System.out.println(Colors.RED+(i + 1) + Colors.WHITE_BOLD + ". " + pokemons[i].getName() + " - vida: " +pokemons[i].getHealth()  +"/" + pokemons[i].getHealthMax()  +  " - Tipo: " + pokemons[i].getType()+Colors.RESET  + Colors.RED + "☠ (fallecido)");
            }
        }
    }

    //Mostrar los ataques de un Pokémon específico
    public static void showPokemonAttacks(Pokemon pokemon) {
        // imprime con colores
        System.out.println(Colors.WHITE_BOLD + "\n💥 Ataques de " +Colors.GREEN + pokemon.getName() + ":");
        int index = 0;
        for (Attack atk : pokemon.getAttacks()) {
            System.out.println(Colors.RED+(index + 1) + Colors.WHITE_BOLD + ". -" + atk.getName() + " | Poder: " + atk.getPower() + " | Tipo: " + atk.getTypeDamage()+Colors.RESET);
            index++;
        }
    }
}