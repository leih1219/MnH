/**
 * Extends from Role
 * Adding opponent, dodge_chance, defense_stat, base_damage
 */
public class Monster extends Role{
    protected int damage;
    protected int defense;
    protected int dodgeChance;
    public int opponent;

    public Monster() {
        super();
    }

    public Monster(String name) {
        super(name);
    }

    public Monster(String name, int level, int hp, int damage, int defense, int dodgeChance) {
        super(name, level, hp);
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    public void setAttributes(String fileSetting) {
        String[] attributes = fileSetting.split("\\s+");
        name = attributes[0];
        level = Integer.parseInt(attributes[1]);
        damage = Integer.parseInt(attributes[2]);
        defense = Integer.parseInt(attributes[3]);
        dodgeChance = Integer.parseInt(attributes[4]);
        hp = level * 10;
    }

    public Monster createMonster(String attributes, int level) {
        Monster monster = new Monster();
        monster.setAttributes(attributes);
        monster.setLevel(level);

        return monster;
    }

    @Override
    public String toString() {
        return "Monster{" +
                " Name: '" + name +
                "  HP: " + hp +
                "  Level: " + level +
                "  Damage: '" + damage +
                "  Defense: " + defense +
                "  Dodge Chance: " + dodgeChance +
                " }";
    }

    public void reduceAttributes(String attribute) {
        if (attribute.equals("damage")) {
            damage *= 0.9;
        } else if (attribute.equals("defense")) {
            defense *= 0.9;
        } else if (attribute.equals("dodge")) {
            dodgeChance *= 0.9;
        }
    }

    public void setLevel(int level) {
        int origin = this.level;
        this.level = level;
        this.hp = level * 100;
        this.damage = damage * level / origin;
        this.dodgeChance = dodgeChance * level / origin;
    }

    public boolean isDodge(){
        return Math.random() < this.dodgeChance * 0.01;
    }

    public int getRealDamage() {
        return (int) (damage * 0.15);
    }

    public int getRealDodgeChance() {
        return (int) (dodgeChance * 0.01);
    }

    public void takeDamage(int damage) {
        hp -= (damage - defense * 0.01);
    }

    // getter
    public int getDamage() {
        return damage;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public int getDefense() {
        return defense;
    }

    // setter
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public int getOpponent() {
        return opponent;
    }

    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }
}
