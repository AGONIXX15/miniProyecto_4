package models;

public enum TypePokemon {
    ELECTRICO(new String[]{"AGUA","VOLADOR"}),
    FUEGO(new String[]{"PLANTA"}),
    AGUA(new String[]{"FUEGO"}),
    PLANTA(new String[]{"PLANTA"}),
    VOLADOR(new String[]{"PLANTA"})
    ;

    public String[] strong;
    // hacemos que tenga una lista a los que son fuertes para hacer la comprobacion más rapido
    TypePokemon(String[] strong) {
        this.strong = strong;
    }

    String[] getStrong() {
        return strong;
    }
}