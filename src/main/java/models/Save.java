package models;

import view.utils.Pair;
import view.utils.Triple;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Save implements Serializable {

    final private long seed;
    final private String name1, name2;


    // Integer que va desde [0,2] al final va a ser de maximo 3 de tamaño
    final private LinkedList<Pair<Byte, Byte>> turns = new LinkedList<>();
    final private LinkedList<Byte> attacks = new LinkedList<>();

    public Save(String name1, String name2, long seed) {
        this.name1 = name1;
        this.name2 = name2;
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }

    public void saveTurn(Pair<Byte, Byte> turnPair) {
        this.turns.add(turnPair);
    }

    public void saveAttack(byte attack){
        this.attacks.add(attack);
    }


    public Triple<Trainer, Trainer, Random> getTrainers(){
        // 1. jalar los entrenadores con sus pokemones
        Random rand = new Random(seed);
        Trainer trainer1 = new Trainer(name1, PokemonFactory.loadAvailablePokemons());
        Trainer trainer2 = new Trainer(name2, PokemonFactory.loadAvailablePokemons());
        // asi conseguimos los equipos
        trainer1.randomTeam(rand);
        trainer2.randomTeam(rand);
        // simular el combate
        ListIterator<Byte> iteratorAttack = attacks.listIterator();
        System.out.println(turns.toString());
        System.out.println(attacks.toString());
        for(Pair<Byte, Byte> turnPair: turns){
            Pokemon pokemon1 = trainer1.getTeamArray()[turnPair.first];
            Pokemon pokemon2 = trainer2.getTeamArray()[turnPair.second];
            boolean turn = pokemon1.getSpeed() >= pokemon2.getSpeed();
            while(pokemon1.isAlive() && pokemon2.isAlive() && iteratorAttack.hasNext()){
                    if(turn){
                        pokemon1.makeDamage(pokemon2, pokemon1.getAttacks()[iteratorAttack.next()]);
                    } else {
                        pokemon2.makeDamage(pokemon1, pokemon2.getAttacks()[iteratorAttack.next()]);
                    }
                    turn = !turn;
                }
            }

        return new Triple<Trainer, Trainer, Random>(trainer1, trainer2, rand);
    }

    public static Save loadSave(File saveFile) throws IOException, FileNotFoundException, ClassNotFoundException {
        try{
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(saveFile));
            return (Save)os.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveGame(File saveFile) throws IOException, FileNotFoundException, ClassNotFoundException {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(saveFile));
            os.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
