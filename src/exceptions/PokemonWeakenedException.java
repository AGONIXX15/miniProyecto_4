package exceptions;

public class PokemonWeakenedException extends Exception {
    //Excepcion que se lanza al intentar usar un Pokémon con HP ≤ 0.
    public PokemonWeakenedException(String message) {
        super(message);
    }
}
