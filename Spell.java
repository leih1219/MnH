public abstract class Spell extends RPGItem {
    protected int damage;
    protected int manaCost;

    public Spell() {
        super();
        damage = 0;
        manaCost = 0;
    }

    public Spell(String name, int price, int requiredLevel, int damage, int manaCost) {
        super(name, price, requiredLevel);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    @Override
    public String toString() {
        return "Spell{" +
                " Name: " + name +
                "  Price: " + price +
                "  Required Level: " + requiredLevel +
                "  Damage: " + damage +
                "  Mana Cost: " + manaCost +
                " }";
    }

    public abstract String getAttribute();
}
