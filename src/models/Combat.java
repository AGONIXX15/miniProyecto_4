package models;

import controllers.ControllerBattle;

public class Combat {
    // si turno es verdadera ataque el primer pokemon si no el segundo pokemon
    private boolean turn;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private ControllerBattle controller;

    public Combat(Pokemon pokemon1, Pokemon pokemon2, ControllerBattle controller) {
        this.controller = controller;
        turn = pokemon1.getSpeed() >= pokemon2.getSpeed();
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    public boolean getTurn() {
        return turn;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }




    public void changeTurn(){
        turn = !turn;
    }

    /**
     *
     * @param index indice del ataque que se lanzq
     * con el turno sabe cual es el pokemon el que ataque
     */
    public void makeAttack(int index){
        if(turn){
            attack(pokemon1, index, pokemon2, controller.trainer1.getNameTrainer());
        } else {
            attack(pokemon2, index, pokemon1, controller.trainer2.getNameTrainer());
        }
        changeTurn();
    }

    /**
     *
     * @param pokemon1 pokemon puede variar de ser del entrenador 1 o 2
     * @param index indice del ataque
     * @param pokemon2 pokemon puede variar de ser del entrenador 1 o 2
     * @param trainerName nombre del entrenador que realizo el ataque
     *  realiza los cambios de vida en los modelos y tambien manda mensajes hacia la vista desde el controlador
     */
    public void attack(Pokemon pokemon1,int index, Pokemon pokemon2, String trainerName) {
        Attack attack = pokemon1.getAttacks()[index];
        float advantage = (pokemon1.hasAdvantage(pokemon2)) ? 1.3f : 1;
        if(advantage > 1){
            controller.sendMessage("El ataque ha sido efectivo!!");
        }
        int damage = (int) (advantage * attack.getPower());
        // le decimos al controlador que mande un mensaje hacia la interfaz
        controller.sendMessage(String.format("%s realizo %s hacia %s con un daño de %d\n",trainerName, attack.getName(), pokemon2.getName(), damage));
        pokemon2.takeDamage(damage);
    }

    // condicion booleana que permite saber si el combate termino
    public boolean hasFinish(){
        return !(pokemon1.isAlive() && pokemon2.isAlive());
    }


}
