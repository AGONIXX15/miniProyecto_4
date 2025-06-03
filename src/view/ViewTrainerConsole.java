package view;

import controllers.ControllerBattle;
import controllers.ControllerTrainer;
import models.Trainer;
import view.battle.console.BattlePokemonConsole;

import java.util.Scanner;

public class ViewTrainerConsole implements ViewTrainerInterface {
    Scanner sc = new Scanner(System.in);
    public ControllerTrainer controllerTrainer;
    ViewTrainer viewTrainer;

    public ViewTrainerConsole(ControllerTrainer controllerTrainer) {
        this.controllerTrainer = controllerTrainer;
    }

    @Override
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ ===");
            System.out.println("1. Ingresar Entrenadores");
            System.out.println("2.Ver Equipo");
            System.out.println("3. Iniciar Batalla");
            System.out.println("4.cambiar de vista");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // para limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el nombre del entrenador 1: ");
                    String nombre1 = sc.nextLine();
                    System.out.print("Ingresa el nombre del entrenador 2: ");
                    String nombre2 = sc.nextLine();
                    this.controllerTrainer.introducirTrainers(nombre1, nombre2);
                    break;
                case 2:
                    ControllerTrainer.getInstance().MostrarPokemons();
                    break;
                case 3:

                    Trainer trainer1 = ControllerTrainer.getInstance().trainer1;
                    Trainer trainer2 = ControllerTrainer.getInstance().trainer2;
                    ControllerBattle controller = new ControllerBattle(trainer1, trainer2);
                    BattlePokemonConsole view = new BattlePokemonConsole(controller);
                    controller.setViewBattle(view);
                    controller.startBattle();
                    break;
                case 4:
                    System.out.println("cambiando de vista");
                    ViewTrainer nuevaView = new ViewTrainer();
                    ControllerTrainer.getInstance().setViewI(nuevaView);
                    nuevaView.mostrarMenu();
                    return;
                case 5:
                    System.out.println("saliendo");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
