public class Sorcerer extends Hero {
    public Sorcerer() {
        super();
    }

    public Sorcerer(String name) {
        super(name);
    }

    public Sorcerer(String name, int level, int hp, int mana, int strength, int dexterity, int agility, int exp, int coins) {
        super(name, level, hp, mana, strength, dexterity, agility, exp, coins);
    }

    @Override
    public void levelUp() {
        super.levelUp();

        strength = (int) (strength * 1.05);
        dexterity = (int) (dexterity * 1.1);
        agility = (int) (agility * 1.1);
    }

    public Sorcerer createHero(String attributes) {
        Sorcerer sorcerer = new Sorcerer();
        sorcerer.setAttributes(attributes);
        return sorcerer;
    }

}
