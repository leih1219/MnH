/**
 * Basic Role class, extends by Hero and Monster
 * Having name, HP, level
 * mana, strength, dexterity, agility ... will not be included
 */

public class Role {
    protected String name;
    protected int hp;
    protected int level;

    public Role() {
        this.name = "Game Role";
        this.hp = 100;
        this.level = 1;
    }

    public Role(String name) {
        this();
        this.name = name;
    }

    public Role(String name, int level, int hp) {
        this(name);
        this.level = level;
        this.hp = hp;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDead() {
        if (hp <= 0) {
            return true;
        }
        return false;
    }

    public void getHurt(int damage) {
        hp -= damage;
    }

    public void getHealed(int health) {
        hp += health;
    }

    public void levelUp(int addLevel) {
        level += addLevel;
    }
}
