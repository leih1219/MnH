public class Exoskeleton extends Monster {
    public Exoskeleton() {
        super();
    }

    public Exoskeleton(String name) {
        super(name);
    }

    public Exoskeleton(String name, int level, int hp, int damage, int defense, int dodgeChance) {
        super(name, level, hp, damage, defense, dodgeChance);
    }

    public Exoskeleton createMonster(String attributes, int level) {
        Exoskeleton exoskeleton = new Exoskeleton();
        exoskeleton.setAttributes(attributes);
        exoskeleton.setLevel(level);
        return exoskeleton;
    }
}
