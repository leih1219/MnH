/**
 * Used only one time
 * Can add up attributes
 */
public class Potion extends RPGItem {
    protected int increaseAmount;
    protected String effectedAttribute;

    public Potion() {
        super();
    }

    public Potion(String name, int price, int requiredLevel, int increaseAmount, String effectedAttribute) {
        super(name, price, requiredLevel);
        this.increaseAmount = increaseAmount;
        this.effectedAttribute = effectedAttribute;
    }

    @Override
    public String toString() {
        return "Potion{" +
                " Name: " + name +
                "  Price: " + price +
                "  Required Level: " + requiredLevel +
                "  Effective Attribute: " + effectedAttribute +
                "  Increase Amount: " + increaseAmount +
                " }";
    }

    public int getIncreaseAmount() {
        return increaseAmount;
    }

    public String getEffectedAttribute() {
        return effectedAttribute;
    }

    public void setEffectedAttribute(String effectedAttribute) {
        this.effectedAttribute = effectedAttribute;
    }

    public void setIncreaseAmount(int increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

//    public void usePotion(Hero hero) {
//        // can effect more than one attributes at the same time
//        String[] attrs = effectedAttribute.split("/");
//        for (String attr : attrs) {
//            switch (attr) {
//                case "Health":
//                    hero.setHp(hero.getHp() + increaseAmount);
//                    break;
//                case "Mana":
//                    // todo setMana
//            }
//        }
//    }
}
