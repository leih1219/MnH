public class Armor extends RPGItem {
    protected int damageReduce;

    public Armor() {
        super();
        damageReduce = 0;
    }

    public Armor(String name, int price, int requiredLevel, int damageReduce) {
        super(name, price, requiredLevel);
        this.damageReduce = damageReduce;
    }

    @Override
    public String toString() {
        return "Armor{" +
                " Name: " + name +
                "  Price: " + price +
                "  Required Level: " + requiredLevel +
                "  Damage Reduce: " + damageReduce +
                " }";
    }

    public int getDamageReduce() {
        return damageReduce;
    }

    public void setDamageReduce(int damageReduce) {
        this.damageReduce = damageReduce;
    }
}
