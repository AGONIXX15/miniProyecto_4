package controllers;

import models.pokemon.PokemonFactory;
import models.trainer.Trainer;
import view.ViewTrainerInterface;
import utils.Pair;

import java.time.Instant;
import java.util.Random;

/**
 * @author Sebastian Devia
 */
public class ControllerTrainer {
    public ViewTrainerInterface viewI;
    public Trainer trainer1, trainer2;
    private static ControllerTrainer instance;
    private Random random;
    private long seed;

    public ControllerTrainer() {
    }

    public void setViewI(ViewTrainerInterface viewI) {
        this.viewI = viewI;
    }

    // con los nombres crea los trainers los cuales recibe desde la vista
    public void introducirTrainers(String nombre1, String nombre2) {
        trainer1 = new Trainer(nombre1, PokemonFactory.loadAvailablePokemons());
        trainer2 = new Trainer(nombre2, PokemonFactory.loadAvailablePokemons());
        seed = Instant.now().toEpochMilli();
        random = new Random(seed);
        trainer1.randomTeam(random);
        trainer2.randomTeam(random);

        this.viewI.mostrarMensaje("Entrenadores introducidos Correctamente.");

    }

    public void MostrarPokemons() {
        if (trainer1 == null || trainer2 == null) {
            viewI.mostrarMensaje("⚠️Primero debes introducir los entrenadores.");
            return;
        }
        viewI.mostrarMensaje("\nEl equipo de " + trainer1.getNameTrainer());
        trainer1.getTeam();
        viewI.mostrarMensaje("\nEl equipo de " + trainer2.getNameTrainer());
        trainer2.getTeam();
    }

    public static ControllerTrainer getInstance() {
        if (instance == null) {
            instance = new ControllerTrainer();
        }
        return instance;
    }

    public Pair<Random, Long> getRandom() {
        return new Pair<>(random, seed);
    }
}
