import java.util.Arrays;

public class MonsterTeam {
    private Monster[] monsters;

    public MonsterTeam(int numMonsters) {
        monsters = new Monster[numMonsters];
    }

    public int numSurvive() {
        int number = 0;
        for (Monster monster : monsters) {
            if (!monster.isDead()) {
                number++;
            }
        }
        return number;
    }

    public int[] surviveIdx() {
        int[] surviveIdx = new int[numSurvive()];
        int idx = 0;
        for (int i = 0; i < monsters.length; i++) {
            if (!monsters[i].isDead()) {
                surviveIdx[idx] = i;
                idx++;
            }
        }
        return surviveIdx;
    }
    public boolean isAllDead() {
        boolean flag = true;
        for (Monster monster : monsters) {
            if (!monster.isDead()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public int numMonsters() {
        return monsters.length;
    }

    public Monster getMonster(int idx) {
        return monsters[idx];
    }

    public void setMonster(int idx, Monster monster) {
        monsters[idx] = monster;
    }

    @Override
    public String toString() {
        String output = "";
        output += "Monsters still alive:\n";
        for (Monster monster : monsters) {
            if (!monster.isDead()) {
                output += monster.toString() + "\n";
            }
        }
        return output;
    }
}
