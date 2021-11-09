import java.time.chrono.MinguoDate;
import java.util.ArrayList;

public class Inventory {
    protected ArrayList<Weapon> weapons;
    protected ArrayList<Armor> armors;
    protected ArrayList<Potion> potions;
    protected ArrayList<Spell> spells;

    public Inventory() {
        this.weapons = new ArrayList<Weapon>();
        this.armors = new ArrayList<Armor>();
        this.potions = new ArrayList<Potion>();
        this.spells = new ArrayList<Spell>();
    }

    public void addInventory(RPGItem item) {
        if (item instanceof Weapon) {
            weapons.add((Weapon) item);
        } else if (item instanceof Armor) {
            armors.add((Armor) item);
        } else if (item instanceof Potion) {
            potions.add((Potion) item);
        } else if (item instanceof Spell) {
            spells.add((Spell) item);
        }
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }


    // remove the item
    public Weapon removeWeapon(int idx) {
        return this.weapons.remove(idx);
    }

    public Armor removeArmor(int idx) {
        return this.armors.remove(idx);
    }

    public Potion removePotion(int idx) {
        return this.potions.remove(idx);
    }

    public Spell removeSpell(int idx) {
        return this.spells.remove(idx);
    }

    // get the selected item
    public Weapon getWeapon(int idx) {
        return weapons.get(idx);
    }

    public Armor getArmor(int idx) {
        return armors.get(idx);
    }

    public Potion getPotion(int idx) {
        return potions.get(idx);
    }

    public Spell getSpell(int idx) {
        return spells.get(idx);
    }

    public void showInventory() {
        System.out.println("Weapons: ");
        if(weapons.size() > 0){
            for(Weapon weapon: weapons){
                System.out.println(weapon.toString());
            }
        }
        System.out.println("Armor: ");
        if(armors.size() > 0){
            for(Armor armor: armors){
                System.out.println(armor.toString());
            }
        }
        System.out.println("Potions: ");
        if(potions.size() > 0){
            for(Potion potion: potions){
                System.out.println(potion.toString());
            }
        }
        System.out.println("Spells: ");
        if(spells.size() > 0){
            for(Spell spell: spells){
                System.out.println(spell.toString());
            }
        }
    }


}
