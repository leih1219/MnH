public class FireSpell extends Spell {
    public FireSpell() {
        super();
    }

    public FireSpell(String name, int price, int requiredLevel, int damage, int manaCost) {
        super(name, price, requiredLevel, damage, manaCost);
    }

    @Override
    public String toString() {
        return "Fire "+ super.toString();
    }

    @Override
    public String getAttribute() {
        return "defense";
    }
}
