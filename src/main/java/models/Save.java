package models;

import controllers.ControllerBattle;
import datos.HistoryData;
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

    public LinkedList<Pair<Byte, Byte>> getTurns(){
        return turns;
    }

    public void saveTurn(Pair<Byte, Byte> turnPair) {
        this.turns.add(turnPair);
    }

    public void saveAttack(byte attack){
        this.attacks.add(attack);
    }


    public ControllerBattle getControllerBattle() {
        Random rand = new Random(seed);
        Trainer trainer1 = new Trainer(name1, PokemonFactory.loadAvailablePokemons());
        Trainer trainer2 = new Trainer(name2, PokemonFactory.loadAvailablePokemons());
        HistoryData history = new HistoryData();
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
                    byte attack = iteratorAttack.next();
                    String message = "";
                    if(turn){
                        int damage = pokemon1.makeDamage(pokemon2, pokemon1.getAttacks()[attack]);
                        message = String.format("%s realizo %s hacia %s con un daño de %d\n",trainer1.getNameTrainer(), pokemon1.getAttacks()[attack].getName(), pokemon2.getName(), damage);
                    } else {
                        int damage = pokemon2.makeDamage(pokemon1, pokemon2.getAttacks()[attack]);
                        message = String.format("%s realizo %s hacia %s con un daño de %d\n",trainer2.getNameTrainer(), pokemon2.getAttacks()[attack].getName(), pokemon1.getName(), damage);
                    }
                history.setPila(message);
                    turn = !turn;
                }
        }
        return new ControllerBattle(trainer1,trainer2,rand,this, history);
    }

    public Pair<LinkedList<Integer>[], LinkedList<Integer>[]> simulationDamage(){
        int n = turns.size();
        LinkedList<Integer>[] damageTrainer1 = new LinkedList[n];
        LinkedList<Integer>[] damageTrainer2 = new LinkedList[n];

        Random rand = new Random(seed);
        Trainer trainer1 = new Trainer(name1, PokemonFactory.loadAvailablePokemons());
        Trainer trainer2 = new Trainer(name2, PokemonFactory.loadAvailablePokemons());
        // asi conseguimos los equipos
        trainer1.randomTeam(rand);
        trainer2.randomTeam(rand);
        // simular el combate
        ListIterator<Byte> iteratorAttack = attacks.listIterator();
        int index = 0;
        for(Pair<Byte, Byte> turnPair: turns){
            Pokemon pokemon1 = trainer1.getTeamArray()[turnPair.first];
            Pokemon pokemon2 = trainer2.getTeamArray()[turnPair.second];
            LinkedList<Integer> damagePokemon1 = new LinkedList<>();
            LinkedList<Integer> damagePokemon2 = new LinkedList<>();
            boolean turn = pokemon1.getSpeed() >= pokemon2.getSpeed();
            while(pokemon1.isAlive() && pokemon2.isAlive() && iteratorAttack.hasNext()){
                byte attack = iteratorAttack.next();
                if(turn){
                    int damage = pokemon1.makeDamage(pokemon2, pokemon1.getAttacks()[attack]);
                    damagePokemon1.push(damage);
                } else {
                    int damage = pokemon2.makeDamage(pokemon1, pokemon2.getAttacks()[attack]);
                    damagePokemon2.push(damage);
                }
                turn = !turn;
            }
            damageTrainer1[index] = damagePokemon1;
            damageTrainer2[index] = damagePokemon2;
            index++;
        }
        return new Pair<LinkedList<Integer>[], LinkedList<Integer>[]>(damageTrainer1, damageTrainer2);
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
