package view.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastian Rubio
 * se encarga de guardar los indices de los id de los pokemones
 * para cargar las imagenes de la APi
 */
public class Pokedex {
    public static Map<String, Integer> pokedex = Map.ofEntries(
            Map.entry("Charmander", 4),
            Map.entry("Squirtle", 7),
            Map.entry("Bulbasaur", 1),
            Map.entry("Pikachu", 25),
            Map.entry("Arcanine", 59),
            Map.entry("Scyther", 123),
            Map.entry("Electabuzz", 125),
            Map.entry("Zapdos", 145),
            Map.entry("Pidgeotto", 17),
            Map.entry("Fearow", 22),
            Map.entry("Moltres", 146),
            Map.entry("Vulpix", 37),
            Map.entry("Magmar", 126),
            Map.entry("Goldeen", 118),
            Map.entry("Seaking", 119),
            Map.entry("Bellsprout", 69),
            Map.entry("Weepinbell", 70),
            Map.entry("Jolteon", 135),
            Map.entry("Seel", 86),
            Map.entry("Doduo", 84),
            Map.entry("Magnemite", 81),
            Map.entry("Farfetch'd", 83),
            Map.entry("Psyduck", 54),
            Map.entry("Exeggcute", 102)
    );


}
