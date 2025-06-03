package controllers;

import models.PokemonFactory;
import models.Trainer;
import view.ViewTrainer;
import view.ViewTrainerInterface;

/**
 * @author Sebastian Devia
 */
public class ControllerTrainer {
    public ViewTrainerInterface viewI;
    public Trainer trainer1, trainer2;
    private static ControllerTrainer instance;

    public ControllerTrainer() {
    }

    public void setViewI(ViewTrainerInterface viewI) {
        this.viewI = viewI;
    }

    // con los nombres crea los trainers los cuales recibe desde la vista
    public void introducirTrainers(String nombre1, String nombre2) {
        trainer1 = new Trainer(nombre1, PokemonFactory.loadAvailablePokemons());
        trainer2 = new Trainer(nombre2, PokemonFactory.loadAvailablePokemons());
        trainer1.randomTeam();
        trainer2.randomTeam();

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
}
