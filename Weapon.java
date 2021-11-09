public class Weapon extends RPGItem {
    protected int damage;
    protected int numHands;

    public Weapon() {
        super();
        damage = 0;
        numHands = 1;
    }

    public Weapon(String name, int price, int requiredLevel, int damage, int numHands) {
        super(name, price, requiredLevel);
        this.damage = damage;
        this.numHands = numHands;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                " Name: " + name +
                "  Price: " + price +
                "  Required Level: " + requiredLevel +
                "  Damage: " + damage +
                "  Number of Hands: " + numHands +
                " }";
    }

    public int getDamage() {
        return damage;
    }

    public int getNumHands() {
        return numHands;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setNumHands(int numHands) {
        this.numHands = numHands;
    }
}
