public class Dragon extends Monster {
    public Dragon() {
        super();
    }

    public Dragon(String name) {
        super(name);
    }

    public Dragon(String name, int level, int hp, int damage, int defense, int dodgeChance) {
        super(name, level, hp, damage, defense, dodgeChance);
    }

    public Dragon createMonster(String attributes, int level) {
        Dragon dragon = new Dragon();
        dragon.setAttributes(attributes);
        dragon.setLevel(level);
        return dragon;
    }

}
