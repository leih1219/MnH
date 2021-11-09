public class IceSpell extends Spell {

    public IceSpell() {
        super();
    }

    public IceSpell(String name, int price, int requiredLevel, int damage, int manaCost) {
        super(name, price, requiredLevel, damage, manaCost);
    }

    @Override
    public String toString() {
        return "Ice " + super.toString();
    }

    @Override
    public String getAttribute() {
        return "damage";
    }
}
