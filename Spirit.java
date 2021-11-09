import sun.security.provider.ConfigFile;

public class Spirit extends Monster {
    public Spirit() {
        super();
    }

    public Spirit(String name) {
        super(name);
    }

    public Spirit(String name, int level, int hp, int damage, int defense, int dodgeChance) {
        super(name, level, hp, damage, defense, dodgeChance);
    }

    public Spirit createMonster(String attributes, int level) {
        Spirit spirit = new Spirit();
        spirit.setAttributes(attributes);
        spirit.setLevel(level);
        return spirit;
    }
}
