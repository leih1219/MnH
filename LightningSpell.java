public class LightningSpell extends Spell {
    public LightningSpell() {
        super();
    }

    public LightningSpell(String name, int price, int requiredLevel, int damage, int manaCost) {
        super(name, price, requiredLevel, damage, manaCost);
    }

    @Override
    public String toString() {
        return "Lightning " + super.toString();
    }

    @Override
    public String getAttribute() {
        return "dodgeChange";
    }


}
