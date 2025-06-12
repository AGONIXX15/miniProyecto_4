package models.pokemon;


// se encarga de crear los pokemones que pueden ser utilizados
public class PokemonFactory {

    //metodo que crea y devuelve todos los pokemones predefinidos disponibles
    public static Pokemon[] loadAvailablePokemons() {
        return new Pokemon[]{
                new Pokemon("Charmander", 100, TypePokemon.FUEGO, new Attack[]{
                        AttackFactory.FLAMETHROWER, AttackFactory.FIRE_BLAST, AttackFactory.TACKLE, AttackFactory.DOUBLE_KICK
                },  35, 30, 45),

                new Pokemon("Squirtle", 100, TypePokemon.AGUA, new Attack[]{
                        AttackFactory.WATER_GUN, AttackFactory.TACKLE, AttackFactory.DOUBLE_KICK, AttackFactory.QUICK_ATTACK
                },  50, 45, 43),

                new Pokemon("Bulbasaur", 100, TypePokemon.PLANTA, new Attack[]{
                        AttackFactory.VINE_WHIP, AttackFactory.SOLAR_BEAM, AttackFactory.RAZOR_LEAF, AttackFactory.TACKLE
                },  49, 65, 45),

                new Pokemon("Pikachu", 90, TypePokemon.ELECTRICO, new Attack[]{
                        AttackFactory.THUNDERBOLT, AttackFactory.THUNDER, AttackFactory.SHOCK_WAVE, AttackFactory.QUICK_ATTACK
                },  40, 50, 90),

                new Pokemon("Arcanine", 110, TypePokemon.FUEGO, new Attack[]{
                        AttackFactory.FIRE_BLAST, AttackFactory.FLAMETHROWER, AttackFactory.QUICK_ATTACK, AttackFactory.TACKLE
                },  80, 80, 95),

                new Pokemon("Scyther", 100, TypePokemon.PLANTA, new Attack[]{
                        AttackFactory.LEAF_BLADE, AttackFactory.SLASH, AttackFactory.DOUBLE_KICK, AttackFactory.TACKLE
                },  70, 55, 105),

                new Pokemon("Electabuzz", 95, TypePokemon.ELECTRICO, new Attack[]{
                        AttackFactory.THUNDERBOLT, AttackFactory.THUNDER, AttackFactory.SHOCK_WAVE, AttackFactory.QUICK_ATTACK
                },  57, 85, 105),

                new Pokemon("Vulpix", 90, TypePokemon.FUEGO, new Attack[]{
                        AttackFactory.FLAMETHROWER, AttackFactory.FIRE_BLAST, AttackFactory.TACKLE, AttackFactory.QUICK_ATTACK
                },  40, 65, 65),

                new Pokemon("Magmar", 105, TypePokemon.FUEGO, new Attack[]{
                        AttackFactory.FIRE_BLAST, AttackFactory.FLAMETHROWER, AttackFactory.SLASH, AttackFactory.QUICK_ATTACK
                },  57, 85, 93),

                new Pokemon("Psyduck", 95, TypePokemon.AGUA, new Attack[]{
                        AttackFactory.WATER_GUN, AttackFactory.QUICK_ATTACK, AttackFactory.TACKLE, AttackFactory.DOUBLE_KICK
                },  48, 50, 55),

                new Pokemon("Seel", 100, TypePokemon.AGUA, new Attack[]{
                        AttackFactory.WATER_GUN, AttackFactory.SLASH, AttackFactory.QUICK_ATTACK, AttackFactory.TACKLE
                },  55, 70, 45),

                new Pokemon("Bellsprout", 85, TypePokemon.PLANTA, new Attack[]{
                        AttackFactory.VINE_WHIP, AttackFactory.SOLAR_BEAM, AttackFactory.TACKLE, AttackFactory.RAZOR_LEAF
                },  35, 30, 40),

                new Pokemon("Exeggcute", 95, TypePokemon.PLANTA, new Attack[]{
                        AttackFactory.RAZOR_LEAF, AttackFactory.VINE_WHIP, AttackFactory.SOLAR_BEAM, AttackFactory.TACKLE
                },  80, 60, 40),

                new Pokemon("Magnemite", 90, TypePokemon.ELECTRICO, new Attack[]{
                        AttackFactory.THUNDERBOLT, AttackFactory.THUNDER, AttackFactory.SHOCK_WAVE, AttackFactory.TACKLE
                },  70, 95, 45),

                new Pokemon("Jolteon", 110, TypePokemon.ELECTRICO, new Attack[]{
                        AttackFactory.THUNDER, AttackFactory.SHOCK_WAVE, AttackFactory.QUICK_ATTACK, AttackFactory.SLASH
                },  60, 95, 130),

                new Pokemon("Doduo", 90, TypePokemon.VOLADOR, new Attack[]{
                        AttackFactory.SLASH, AttackFactory.QUICK_ATTACK, AttackFactory.TACKLE, AttackFactory.DOUBLE_KICK
                },  45, 35, 75),

                new Pokemon("Farfetch'd", 95, TypePokemon.VOLADOR, new Attack[]{
                        AttackFactory.SLASH, AttackFactory.LEAF_BLADE, AttackFactory.QUICK_ATTACK, AttackFactory.TACKLE
                },  55, 62, 60)};
    }
}



