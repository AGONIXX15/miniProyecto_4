package models;

/**
 * @author Simon Tarazona
 */
public class Attack {
    private String name;          // Nombre del ataque
    private short power;            // Poder del ataque
    private TypeDamage typeDamage; // Tipo de daño (FISICO o ESPECIAL)




    /**
     * Constructor para inicializar un ataque con nombre, poder y tipo de daño.
     *
     * @param name       Nombre del ataque.
     * @param power      Poder del ataque.
     * @param typeDamage Tipo de daño (FÍSICO o ESPECIAL).
     */
    public Attack(String name, short power, TypeDamage typeDamage) {
        this.name = name;
        this.power = power;
        this.typeDamage = typeDamage;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPower() {
        return power;
    }

    public void setPower(short power) {
        this.power = power;
    }

    public TypeDamage getTypeDamage() {
        return typeDamage;
    }

    public void setTypeDamage(TypeDamage typeDamage) {
        this.typeDamage = typeDamage;
    }

    @Override
    public String toString() {
        return name + " (" + power + " de poder, tipo: " + typeDamage + ")";
    }
}

