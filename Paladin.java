public class Paladin extends Hero {
    public Paladin() {
        super();
    }

    public Paladin(String name) {
        super(name);
    }

    public Paladin(String name, int level, int hp, int mana, int strength, int dexterity, int agility, int exp, int coins) {
        super(name, level, hp, mana, strength, dexterity, agility, exp, coins);
    }

    @Override
    public void levelUp() {
        super.levelUp();

        strength = (int) (strength * 1.1);
        dexterity = (int) (dexterity * 1.1);
        agility = (int) (agility * 1.05);
    }

    public Paladin createHero(String attributes) {
        Paladin paladin = new Paladin();
        paladin.setAttributes(attributes);
        return paladin;
    }
}
