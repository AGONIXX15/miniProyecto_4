package models;

import view.battle.gui.BattlePokemonGUI;

public class Pokemon {

    private String name;
    private TypePokemon type;
    private Attack[] attacks;
    private int healthMax;
    private int health;
    private int defense;
    private int specialDefense;
    private int speed;
    public String imagenUrl;




    /**
     * constructor de la clase abstracta pokemon
     *
     * @param name    nombre del pokemon
     * @param healthMax  salud maxima del pokemon
     * @param type    tipo del pokemon
     * @param attacks ataques del pokemon
     */
    public Pokemon(String name, int healthMax, TypePokemon type, Attack[] attacks,
                   String imagenUrl, int defense, int specialDefense, int speed) {
        this.name = name;
        this.healthMax = healthMax;
        this.health = healthMax;
        this.type = type;
        this.attacks = attacks;
        this.imagenUrl = imagenUrl;
        this.defense = defense;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getName() {
        return name;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(int healthMax) {
        this.healthMax = healthMax;
    }

    public int getHealth() {
        return health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypePokemon getType() {
        return type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Attack[] getAttacks() {
        return attacks;
    }


    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }

    public void setType(TypePokemon type) {
        this.type = type;
    }
    public String showInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre = ").append(name)
                .append(", Tipo = ").append(type)
                .append(", Salud = ").append(health)
            .append(", Velocidad = ").append(speed);
        return sb.toString();
    }
    public void cure(int health){
        this.health = Math.min(this.health + health, this.healthMax);
        System.out.printf("%s" + " Ha sido curado hasta %d\n", this.name, this.health);
    }

    // metodo de recibir daño del pokemon
    public void takeDamage(int damage) {
        health = Math.max(0, health-damage);
    }

    public boolean hasAdvantage(Pokemon enemy){
        for(String type: this.type.strong){
            if(type.equals(enemy.type.toString())){
                return true;
            }
        }
        return false;
    }

    public boolean isAlive(){
        return health > 0;
    }

    // metodo para realizar daño
    public void makeDamage(Pokemon enemy, Attack attack){
        float advantage = (hasAdvantage(enemy)) ? 1.3f : 1;
        if(advantage > 1){
            BattlePokemonGUI.getInstance().sendMessage("El ataque ha sido efectivo!!");
        }
        int damage = (int) (advantage * attack.getPower());
        BattlePokemonGUI.getInstance().sendMessage(String.format("%s realizo %s hacia %s con un daño de %d\n",name, attack.getName(), enemy.getName(), damage));
        enemy.takeDamage(damage);
    }

    @Override
    public String toString() {
        return
                "Nombre = " + name +
                        ", Tipo = " + type +
                        ", Salud = " + health ;
    }

    // clonar en memoria el pokemon
    public Pokemon clonar(){
        return new Pokemon(name, healthMax, type, attacks,imagenUrl,defense, specialDefense, speed);
    }

    public int getSpeed() {
        return speed;
    }
}






