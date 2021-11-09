import java.util.Arrays;

public class HeroTeam {
    private Hero[] heroes;
    // current position
    private int i, j;

    public HeroTeam(int numHeroes) {
        heroes = new Hero[numHeroes];
        i = 0;
        j = 0;
    }

    public Hero getHero(int idx) {
        return heroes[idx];
    }

    public void setHero(int idx, Hero hero) {
        heroes[idx] = hero;
    }

    public void showTeamInventory() {
        for (Hero hero : heroes) {
            hero.showInventory();
        }
    }

    public int getHighestLevel() {
        int hLevel = 0;
        for (Hero hero : heroes) {
            if (hero.getLevel() > hLevel) {
                hLevel = hero.getLevel();
            }
        }
        return hLevel;
    }

    public void win() {
        for (Hero hero : heroes) {
            if (!hero.isDead()) {
                hero.setExp(hero.getExp() + 2);
                hero.setCoins(hero.getCoins() + getHighestLevel() * 100);
                hero.setHp((int) (hero.getHp() * 1.1));
                hero.setMana((int) (hero.getMana() * 1.1));
            }
        }
    }

    public void levelUp() {
        for (Hero hero : heroes) {
            if (hero.isLevelUp()) {
                hero.levelUp();
            }
        }
    }

    public void revive() { // get half hp back
        for (Hero hero : heroes) {
            if (hero.isDead()) {
                hero.setHp(hero.getLevel() * 50);
            }
        }
    }

    public int numSurvive() {
        int number = 0;
        for (Hero hero : heroes) {
            if (!hero.isDead()) {
                number++;
            }
        }
        return number;
    }

    public int[] surviveIdx() {
        int[] surviveIdx = new int[numSurvive()];
        int idx = 0;
        for (int i = 0; i < heroes.length; i++) {
            if (!heroes[i].isDead()) {
                surviveIdx[idx] = i;
                idx++;
            }
        }
        return surviveIdx;
    }
    public boolean isAllDead() {
        boolean flag = true;
        for (Hero hero : heroes) {
            if (!hero.isDead()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void moveTeam(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "HeroTeam{" +
                "heroes=" + Arrays.toString(heroes) +
                '}';
    }

    public int numHeroes() {
        return heroes.length;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
