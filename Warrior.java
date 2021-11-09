public class Warrior extends Hero {
    public Warrior() {
        super();
    }

    public Warrior(String name) {
        super(name);
    }

    public Warrior(String name, int level, int hp, int mana, int strength, int dexterity, int agility, int exp, int coins) {
        super(name, level, hp, mana, strength, dexterity, agility, exp, coins);
    }

    @Override
    public void levelUp() {
        super.levelUp();

        strength = (int) (strength * 1.1);
        dexterity = (int) (dexterity * 1.05);
        agility = (int) (agility * 1.1);
    }

    public Warrior createHero(String attributes) {
        Warrior warrior = new Warrior();
        warrior.setAttributes(attributes);
        return warrior;
    }
}

