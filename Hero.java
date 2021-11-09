import java.util.ArrayList;
import java.util.Scanner;

/**
 * Extends from Role
 * Adding opponent, mana, strength, dexterity, agility
 * Hero can also hold weapons, armors, potions, spells
 * Hero has coins and need exp to level up
 */

public class Hero extends Role {
    // attributes
    protected int opponent;
    protected int mana;
    protected int strength;
    protected int dexterity;
    protected int agility;
    protected int exp;
    protected int coins;

    // inventories
    protected Inventory inventory;

    // equipments
    protected Weapon equippedWeapon; // in single hand or double hand, can be extended in the feature
    protected Armor equippedArmor;

    public Hero() {
        super();
        mana = 0;
        strength = 0;
        dexterity = 0;
        agility = 0;
        exp = 0;
        coins = 0;
        inventory = new Inventory();
    }

    public Hero(String name) {
        super(name);
        inventory = new Inventory();
    }

    public Hero(String name, int level, int hp) {
        super(name, level, hp);
        inventory = new Inventory();
    }

    public Hero(String name, int level, int hp, int mana, int strength, int dexterity, int agility, int exp, int coins) {
        super(name, level, hp);
        this.mana = mana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.exp = exp;
        this.coins = coins;
        inventory = new Inventory();
    }

    public void equipWeapon(int idx) {
        equippedWeapon = inventory.getWeapon(idx);
        System.out.println("Hero " + name +" is equipped with weapon: " + inventory.getWeapon(idx).getName());
    }

    public void equipArmor(int idx) {
        equippedArmor = inventory.getArmor(idx);
        System.out.println("Hero " + name +" is equipped with armor: " + inventory.getArmor(idx).getName());
    }

    public void usePotion(int idx) {
        Potion potion = inventory.getPotion(idx);
        System.out.println("Hero " + name +" has used potion: " + potion.getName());
//        inventory.getPotion(idx).usePotion(this);
        int amount = potion.getIncreaseAmount();
        String[] attrs = potion.getEffectedAttribute().split("/");
        for (String attr : attrs) {
            switch (attr) {
                case "Health":
                    hp += amount;
                    break;
                case "Mana":
                    mana += amount;
                    break;
                case "Strength":
                    strength += amount;
                    break;
                case "Dexterity":
                    dexterity += amount;
                    break;
                case "Agility":
                    agility += amount;
                    break;
            }
        }
        inventory.potions.remove(idx);
    }

    public void useSpell(int idx) {
        // todo
        if (canUseSpell(idx)) {
            this.mana = this.mana - inventory.getSpell(idx).getManaCost();
        } else {
            System.out.println("Can't use the spell, hero doesn't have enough mana!");
        }
    }

    public boolean canUseSpell(int idx) {
        if (mana >= inventory.getSpell(idx).getManaCost()) {
            return true;
        }
        return false;
    }

    public void showInventory() {
        System.out.println("Hero " + name + "'s inventory is: ");
        inventory.showInventory();
    }

    @Override
    public String toString() {
        String status = isDead()? "Dead" : "Alive";
        return "Hero{" +
                " Name: '" + name +
                "  Status: " + status +
                "  HP: " + hp +
                "  Level: " + level +
                "  Opponent: '" + opponent +
                "  Mana: " + mana +
                "  Strength: " + strength +
                "  Dexterity: " + dexterity +
                "  Agility: " + agility +
                "  Exp: " + exp +
                "  Coins: " + coins +
                "  Inventory: " + inventory +
                "  EquippedWeapon: " + equippedWeapon +
                "  EquippedArmor: " + equippedArmor +
                " }";
    }

    public void setAttributes(String fileSetting) {
        level = 1;
        hp = level * 100;

        String[] attributes = fileSetting.split("\\s+");
        name = attributes[0];
        mana = Integer.parseInt(attributes[1]);
        strength = Integer.parseInt(attributes[2]);
        agility = Integer.parseInt(attributes[3]);
        dexterity = Integer.parseInt(attributes[4]);
        coins = Integer.parseInt(attributes[5]);
        exp = Integer.parseInt(attributes[6]);

        inventory = new Inventory();
        equippedWeapon = new Weapon();
        equippedArmor = new Armor();
    }

    public boolean isLevelUp() {
        if (exp >= level * 10) {
            return true;
        }
        return false;
    }

    public void levelUp() {
        level++;
        // update all related attributes
        hp = level * 100;
        mana = (int) (mana *= 1.1);
    }

    public void changeWeapon(int idx) {
        inventory.showInventory();
        System.out.println("Please input the index of the weapon you want to equip:");
        Scanner sc = new Scanner(System.in);
        ArrayList<Weapon> weapons = inventory.getWeapons();
        String weaponIndex;
        while (true) {
            weaponIndex = sc.nextLine();
            int index = Integer.parseInt(weaponIndex);
            if (weaponIndex.matches("\\d+")) {
                if (index > 0 && index < weapons.size()) {
                    equippedWeapon = weapons.get(index - 1);
                    System.out.println("Hero " + name + " changes weapon to: " + equippedWeapon.getName());
                    break;
                } else {
                    System.out.println("Please choose a weapon!");
                }
            } else {
                System.out.println("Please choose a weapon!");
            }
        }
    }

    public void changeArmor(int idx) {
        inventory.showInventory();
        System.out.println("Please input the index of the armor you want to equip:");
        Scanner sc = new Scanner(System.in);
        ArrayList<Armor> armors = inventory.getArmors();
        String armorIndex;
        while (true) {
            armorIndex = sc.nextLine();
            int index = Integer.parseInt(armorIndex);
            if (armorIndex.matches("\\d+")) {
                if (index > 0 && index < armors.size()) {
                    equippedArmor = armors.get(index - 1);
                    System.out.println("Hero " + name + " changes armor to: " + equippedArmor.getName());
                    break;
                } else {
                    System.out.println("Please choose an armor!");
                }
            } else {
                System.out.println("Please choose an armor!");
            }
        }
    }

    // revive with half hp
    public void revive(){
        hp = level * 50;
    }

    // get reward
    public void getReward(int coins, int exp) {
        this.coins += coins;
        this.exp += exp;
        if (this.exp > this.level * 10) {
            // should we keep the exp? or set it to 0
            this.levelUp();
        }
    }

    public int getWeaponDamage() {
        double damage = (strength + equippedWeapon.getDamage()) * 0.05;
        return (int) damage;
    }

    public int getSpellDamage(Spell spell) {
        double damage = (spell.getDamage() * (dexterity / 10000 + 1));
        return (int) damage;
    }

    // does hero have attributes of dodge and defense?
    public int getDefense() {
        return (int) (equippedArmor.getDamageReduce() * 0.1);
    }

    public void takeDamage(int damage) {
        hp -= (damage - getDefense());
    }

    public int getDodge() {
        return (int) (agility * 0.002);
    }

    public Hero createHero(String attributes) {
        Hero hero = new Hero();
        hero.setAttributes(attributes);
        return hero;
    }

    // getter
    public int getOpponent() {
        return opponent;
    }



    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public int getExp() {
        return exp;
    }

    public int getCoins() {
        return coins;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    // setter
    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }
}
