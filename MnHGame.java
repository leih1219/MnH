import javax.swing.plaf.SeparatorUI;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MnHGame extends BoardGame {
    protected MnHBoard board;
    protected final String warriorsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Warriors.txt";
    protected final String sorcerersPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Sorcerers.txt";
    protected final String paladinsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Paladins.txt";
    protected final String weaponryPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Weaponry.txt";
    protected final String armoryPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Armory.txt";
    protected final String potionsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Potions.txt";
    protected final String iceSpellsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "IceSpells.txt";
    protected final String fireSpellsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "FireSpells.txt";
    protected final String lightningSpellsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "LightningSpells.txt";
    protected final String dragonsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Dragons.txt";
    protected final String exoskeletonsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Exoskeletons.txt";
    protected final String spiritsPath = System.getProperty("user.dir") + "/src/ConfigFiles/" + "Spirits.txt";

    protected HeroTeam heroTeam;
    //    protected MonsterTeam monsterTeam;
    protected Scanner sc;

    //default constructor
    public MnHGame(){
        board = new MnHBoard();
        sc = new Scanner(System.in);
    }

    @Override
    public void play() {
        welcome();
        board = new MnHBoard();
        selectRoles();
        System.out.println(heroTeam);
        System.out.println(board);
        while (!gameOver) {
            continuePlay();
        }
        gameIsOver();
    }

    public void selectRoles() {
        System.out.println("Please enter the number of the Heroes you want to play (no more than 3):");
        // todo: add check
        int numHeroes = sc.nextInt();
        heroTeam = new HeroTeam(numHeroes);
        // hero instance
        for (int i = 0; i < numHeroes; i++) {
            System.out.println("What kind of hero do you want to use?\n 1: Warrior\n 2: Sorcerer \n 3: Paladin");
            String specificRole = "";
            int firstChoice = sc.nextInt();
            int secondChoice;
            switch (firstChoice) {
                case 1:
                    Hero warrior = new Warrior();
                    System.out.println("You choose a Warrior! Continue and select one!");
                    readFile(warriorsPath);
                    secondChoice = sc.nextInt();
                    specificRole = getSelectInFile(secondChoice, warriorsPath);
                    warrior.setAttributes(specificRole);
                    heroTeam.setHero(i, warrior);
                    break;
                case 2:
                    Hero sorcerer = new Sorcerer();
                    System.out.println("You choose a Sorcerer! Continue and select one!");
                    readFile(sorcerersPath);
                    secondChoice = sc.nextInt();
                    specificRole = getSelectInFile(secondChoice, sorcerersPath);
                    sorcerer.setAttributes(specificRole);
                    heroTeam.setHero(i, sorcerer);
                    break;
                case 3:
                    Hero paladin = new Paladin();
                    System.out.println("You choose a Paladin! Continue and select one!");
                    readFile(paladinsPath);
                    secondChoice = sc.nextInt();
                    specificRole = getSelectInFile(secondChoice, paladinsPath);
                    paladin.setAttributes(specificRole);
                    heroTeam.setHero(i, paladin);
                    break;
            }
            System.out.println("You chose: " + specificRole);
        }
    }

    public String getSelectInFile(int index, String filePath) {
        File file = new File(filePath);
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Can't read the file!");
        }

        String str = "";
        int idx = 1;
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            if (idx == index) {
                break;
            }
            idx++;
        }
        scanner.close();
        return str;
    }

    public void readFile(String filePath) {
        File file = new File(filePath);
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Can't read the file!");
        }

        String str = "";
        int idx = 1;
        while (scanner.hasNextLine()) {
            str += "[" + idx + "]";
            str += scanner.nextLine();
            str += "\n";
            idx++;
        }

        scanner.close();
        System.out.println(str);
    }

    public void continuePlay() {
        // in case of market, go directly into the market
        Cell standingCell = board.getCell(heroTeam.getI(), heroTeam.getJ());
        if (standingCell instanceof MarketCell) {
            showMarket();
        }
        showGuide();
        checkMove();

    }

    private void checkMove() {
        char order = sc.next().toUpperCase().charAt(0);
        int i = heroTeam.getI();
        int j = heroTeam.getJ();
        switch (order) {
            case 'W':
                if (board.isAvailable(i - 1, j)) {
                    move(i - 1, j);
                } else {
                    System.out.println("Can't move up, input again~");
                    checkMove();
                }
                break;
            case 'A':
                if (board.isAvailable(i, j - 1)) {
                    move(i, j - 1);
                } else {
                    System.out.println("Can't move left, input again~");
                    checkMove();
                }
                break;
            case 'D':
                if (board.isAvailable(i, j + 1)) {
                    move(i, j + 1);
                } else {
                    System.out.println("Can't move right, input again~");
                    checkMove();
                }
                break;
            case 'S':
                if (board.isAvailable(i + 1, j)) {
                    move(i + 1, j);
                } else {
                    System.out.println("Can't move down, input again~");
                    checkMove();
                }
                break;
            case 'E':
                manageInventory();
                break;
            case 'M':
                System.out.println(board);
                break;
            case 'Q':
                gameOver = true;
                break;
            case 'I':
                System.out.println(heroTeam);
                break;
        }

    }

    private void manageInventory() {
        heroTeam.showTeamInventory();
        System.out.println("Whose inventory you want to manage?\n Press -1 to go back to map:");
        for (int i = 0; i < heroTeam.numHeroes(); i++) {
            System.out.println("[" + (i+1) + "]" + heroTeam.getHero(i));
        }
        int heroIdx = sc.nextInt();
        if (heroIdx == -1) {
            return;
        }
        Hero hero = heroTeam.getHero(heroIdx);
        System.out.println("Here is " + hero.getName() + "'s inventory:");
        hero.showInventory();
        System.out.println("What do you want to do? \n (1) Equip weapon/armor\n (2) Use potion");
        int action = sc.nextInt();
        if (action == 1) {
            equip(hero);
        } else if (action == 2) {
            usePotion(hero);
        }
    }

    private void usePotion(Hero hero) {
        System.out.println("What do you want to use?");
        ArrayList<Potion> potions = hero.getInventory().getPotions();
        if (potions.size() > 0) {
            for (int i = 0; i < potions.size(); i++) {
                System.out.println("[" + (i+1) + "]" + potions.get(i));
            }
            int potionIdx = sc.nextInt();
            hero.usePotion(potionIdx - 1);
        } else {
            System.out.println("No potions");
        }
    }

    private void equip(Hero hero) {
        System.out.println("What do you want to equip?\n (1) Weapon \n (2) Armor");
        int input = sc.nextInt();
        if (input == 1) {
            ArrayList<Weapon> weapons = hero.getInventory().getWeapons();
            if (weapons.size() > 0) {
                System.out.println("Choose one weapon");
                for (int i = 0; i < weapons.size(); i++) {
                    System.out.println("[" + (i+1) + "]" + weapons.get(i));
                }
                int weaponIdx = sc.nextInt();
                hero.equipWeapon(weaponIdx - 1);
            } else {

            }
        } else if (input == 2) {
            ArrayList<Armor> armors = hero.getInventory().getArmors();
            if (armors.size() > 0) {
                System.out.println("Choose one armor");
                for (int i = 0; i < armors.size(); i++) {
                    System.out.println("[" + (i+1) + "]" + armors.get(i));
                }
                int armorIdx = sc.nextInt();
                hero.equipArmor(armorIdx - 1);
            } else {
                System.out.println("No armor");
            }
        }
    }

    private void move(int i, int j) {
        heroTeam.moveTeam(i, j);
        board.movePlayer(i, j);
        System.out.println(board);
        if (board.getCell(i, j) instanceof WildCell) {
            fight(i, j);
        }
    }

    private void fight(int i, int j) {
        double probability = Math.random();
        if (probability < (1d / 3)) {
            battle();
        }
    }

    private void battle() {
        showBattleTitle();
        System.out.println(heroTeam);
        int highestLevel = heroTeam.getHighestLevel();
        MonsterTeam monsterTeam = new MonsterTeam(heroTeam.numHeroes());
        for (int i = 0; i < heroTeam.numHeroes(); i++) {
            String filePath = "";
            double kind = Math.random();
            Monster monster;
            if (kind < 1d / 3) {
                filePath = dragonsPath;
                monster = new Dragon();
            } else if (kind < 2d / 3) {
                filePath = spiritsPath;
                monster = new Spirit();
            } else {
                filePath = exoskeletonsPath;
                monster = new Exoskeleton();
            }
            int lines = linesOfFile (filePath);
            int idx = (int) (Math.random() * lines);
            String info = getSelectInFile(idx, filePath);
            monster.setAttributes(info);
            monster.setLevel(highestLevel);
            monsterTeam.setMonster(i, monster);
        }
        System.out.println(monsterTeam);

        while (!monsterTeam.isAllDead() && !heroTeam.isAllDead()) {
            heroTurn(monsterTeam);
            monsterTurn(monsterTeam);
        }

    }

    private void monsterTurn(MonsterTeam monsterTeam) {
        int[] survivorM = monsterTeam.surviveIdx();
        for (int i = 0; i < survivorM.length; i++) {
            int newOpponent = 0;
            int[] survivorH = heroTeam.surviveIdx();
            for (int j = 0; j < survivorH.length; j++) {
                if (Math.abs(survivorM[i] - newOpponent) >= Math.abs(survivorM[i] - survivorH[j])) {
                    newOpponent = survivorH[j];
                }
            }
            Monster currentMonster = monsterTeam.getMonster(survivorM[i]);
            currentMonster.setOpponent(newOpponent);
            monsterHit(currentMonster);
        }
    }

    private void monsterHit(Monster currentMonster) {
        Random rand = new Random();
        Hero opponent = heroTeam.getHero(currentMonster.getOpponent());
        if (rand.nextInt(100) + 1 <= opponent.getDodge()) {
            System.out.println("Didn't hit the hero~");
        } else {
            int damage = currentMonster.getRealDamage();
            opponent.takeDamage(damage);
            System.out.println(currentMonster.getName() + " has hit " + opponent.getName() + " for " + damage);
        }
    }

    private void heroTurn(MonsterTeam monsterTeam) {
        int[] survivorH = heroTeam.surviveIdx();
        int newOpponent = 0;
        for (int i = 0; i < survivorH.length; i++) {
            int[] survivorM = monsterTeam.surviveIdx();
            if (survivorM.length == 0) {
                break;
            }
            Hero currentHero = heroTeam.getHero(survivorH[i]);
            for (int j = 0; j < survivorM.length; j++) {
                if (j == 0) {
                    newOpponent = survivorM[j];
                } else if (Math.abs(survivorH[i] - newOpponent) >= Math.abs(survivorH[i] - survivorM[j])) {
                    newOpponent = survivorM[j];
                }
            }

            currentHero.setOpponent(newOpponent);
            System.out.println(currentHero.getName() + " is targeting -> " + monsterTeam.getMonster(currentHero.getOpponent()).getName());
            System.out.println("What's you next action? \n (1) Attack \n (2) Use a Spell \n (3) Change Equipment \n (4) Use a Potion");
            int choice = sc.nextInt();
            if (choice == 1) {
                heroHit(currentHero, monsterTeam);
            } else if (choice == 2) {
                useSpell(currentHero, monsterTeam);
            } else if (choice == 3) {
                equip(currentHero);
            } else if (choice == 4) {
                usePotion(currentHero);
            }
        }
    }

    public void useSpell(Hero hero, MonsterTeam monsterTeam) {
        Random rand = new Random();
        Monster opponent = monsterTeam.getMonster(hero.getOpponent());

        if (rand.nextInt(100) + 1 <= opponent.getRealDodgeChance()) {
            System.out.println("Didn't hit the monster~");
        } else {
            ArrayList<Spell> spells = hero.getInventory().getSpells();
            if (spells.size() > 0) {
                for (int i = 0; i < spells.size(); i++) {
                    System.out.println("[" + (i+1) + "] " + spells.get(i));
                }
                int choice = sc.nextInt();
                Spell spell = spells.get(choice);
                if (hero.canUseSpell(choice)) {
                    //do the damage on the monster
                    int damage = spell.getDamage();
                    opponent.takeDamage(damage);
                    System.out.println(hero.getName() + " has hit " + opponent.getName() + " for " + damage);
                    if (spell instanceof IceSpell) {
                        opponent.reduceAttributes("damage");
                    } else if (spell instanceof FireSpell) {
                        opponent.reduceAttributes("defense");
                    } else if (spell instanceof LightningSpell) {
                        opponent.reduceAttributes("dodge");
                    }

                }
            } else {
                System.out.println("No spells");
            }
        }
    }

    private void heroHit(Hero currentHero, MonsterTeam monsterTeam) {
        Random rand = new Random();
        //see if the monster dodges the attack
        Monster opponent = monsterTeam.getMonster(currentHero.getOpponent());
        if (rand.nextInt(100) + 1 <= opponent.getRealDodgeChance()) {
            System.out.println("Didn't hit the monster~");
        } else {
            //calculate damage based on equipped weapon
            int damage = currentHero.getWeaponDamage();
            opponent.takeDamage(damage);
            System.out.println(currentHero.getName() + " has hit " + opponent.getName() + " for " + damage);
        }
    }

    public int linesOfFile(String filePath) {
        int lines = 0;
        File file = new File(filePath);
        Scanner scanner = new Scanner(System.in);
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lines++;
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Can't read the file!");
        }
        return lines;
    }

    private void showBattleTitle() {
        String str = " ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀ \n" +
                "▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌          \n" +
                "▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌          ▐░░░░░░░░░░░▌\n" +
                "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀ \n" +
                "▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌          \n" +
                "▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ \n" +
                "▐░░░░░░░░░░▌ ▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀   ▀         ▀       ▀            ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀ ";
        System.out.println(str);
    }

    private void showGuide() {
        String guide = "";
        guide += "***********************************************************************\n";
        guide += " W : move up\n";
        guide += " A : move left\n";
        guide += " S : move right\n";
        guide += " D : move down\n";
        guide += "***********************************************************************\n";
        guide += " I : Show info\n";
        guide += " E : Show inventory\n";
        guide += " M : Show map\n";
        guide += " Q : Quit\n";
        guide += "***********************************************************************\n";
        System.out.println(guide);
    }

    public void showMarket() {
        System.out.println("You stepped into a market, you can buy and sell stuffs here!");
        System.out.println("What do you want to do next? \n (1) Buy / Sell stuffs \n (2) Exit");
        int next = sc.nextInt();
        if (next == 1) {
            showMarketWelcome();
            dealWithHero();
            showMarket();
        } else {
            System.out.println(board);
            return;
        }
    }

    public void dealWithHero() {
        System.out.println("Choose a hero you want to process:");
        for (int i = 0; i < heroTeam.numHeroes(); i++) {
            System.out.println("[" + (i+1) + "]" + heroTeam.getHero(i));
        }
        int idx = sc.nextInt();
        Hero hero = heroTeam.getHero(idx - 1);
        hero.showInventory();
        System.out.println("What do you want? \n (1) Buy \n (2) Sell");
        int second = sc.nextInt();
        if (second == 1) {
            buy(hero);
        } else {
            sell(hero);
        }
    }

    public void sell(Hero hero) {

        hero.showInventory();
        System.out.println("What do you want to sell: \n (1) Weapons \n (2) Armors \n (3) Potions \n (4) Spells");
        int idx = sc.nextInt();
        RPGItem selling = new RPGItem();
        System.out.println("Which one do you want to sell?");
        if (idx == 1) {// weapon
            ArrayList<Weapon> weaponList = hero.getInventory().getWeapons();
            if (weaponList.size() == 0) {
                System.out.println("You don't have any weapons~");
                return;
            }
            for (int i = 0; i < weaponList.size(); i++) {
                System.out.println("[" + (i+1) + "]" + weaponList.get(i));
            }
            int item = sc.nextInt();
            selling = weaponList.get(item - 1);
        } else if (idx == 2) { // armor
            ArrayList<Armor> armorList = hero.getInventory().getArmors();
            if (armorList.size() == 0) {
                System.out.println("You don't have any armors~");
                return;
            }
            for (int i = 0; i < armorList.size(); i++) {
                System.out.println("[" + (i+1) + "]" + armorList.get(i));
            }
            int item = sc.nextInt();
            selling = armorList.get(item - 1);
        } else if (idx == 3) {
            ArrayList<Potion> potionList = hero.getInventory().getPotions();
            if (potionList.size() == 0) {
                System.out.println("You don't have any potions~");
                return;
            }
            for (int i = 0; i < potionList.size(); i++) {
                System.out.println("[" + (i+1) + "]" + potionList.get(i));
            }
            int item = sc.nextInt();
            selling = potionList.get(item - 1);
        } else if (idx == 4) {
            ArrayList<Spell> spellList = hero.getInventory().getSpells();
            if (spellList.size() == 0) {
                System.out.println("You don't have any Spells~");
                return;
            }
            for (int i = 0; i < spellList.size(); i++) {
                System.out.println("[" + (i+1) + "]" + spellList.get(i));
            }
            int item = sc.nextInt();
            selling = spellList.get(item - 1);
        }

        hero.setCoins((int) (hero.getCoins() + selling.getPrice() * 0.5));
        System.out.println("Thanks for selling");

        if (selling instanceof Weapon) {
            hero.getInventory().getWeapons().remove(selling);
        } else if (selling instanceof Armor) {
            hero.getInventory().getArmors().remove(selling);
        } else if (selling instanceof Potion) {
            hero.getInventory().getPotions().remove(selling);
        } else if (selling instanceof Spell) {
            hero.getInventory().getSpells().remove(selling);
        }
    }

    public void buy(Hero hero) {
        System.out.println("What do you want to buy: \n (1) Weapons \n (2) Armors \n (3) Potions \n (4) Spells");
        int idx = sc.nextInt();
        String filePath = "";
        switch (idx) {
            case 1:
                filePath = weaponryPath;
                break;
            case 2:
                filePath = armoryPath;
                break;
            case 3:
                filePath = potionsPath;
                break;
            case 4:
                System.out.println("What kind of spell do you want: \n (1) Ice Spell \n (2) Fire Spell \n (3) Lightning Spell");
                int spll = sc.nextInt();
                if (spll == 1) {
                    filePath = iceSpellsPath;
                } else if (spll == 2) {
                    filePath = fireSpellsPath;
                } else {
                    filePath = lightningSpellsPath;
                }
                break;
//            default:
//                return;
        }
        readFile(filePath);
        int stuff = sc.nextInt();
        String stuffString = getSelectInFile(stuff, filePath);

        RPGItem chosenItem;

        String lstFile = filePath.substring(filePath.length() - 8);
        if (lstFile.equals("onry.txt")) {
            String[] weaponAttr = stuffString.split("\\s+");
            chosenItem = new Weapon(weaponAttr[0], Integer.parseInt(weaponAttr[1]), Integer.parseInt(weaponAttr[2]), Integer.parseInt(weaponAttr[3]), Integer.parseInt(weaponAttr[4]));
        } else if (lstFile.equals("mory.txt")) {
            String[] armorAttr = stuffString.split("\\s+");
            chosenItem = new Armor(armorAttr[0], Integer.parseInt(armorAttr[1]), Integer.parseInt(armorAttr[2]), Integer.parseInt(armorAttr[3]));
        } else if (lstFile.equals("ions.txt")) {
            String[] potionAttr = stuffString.split("\\s+");
            chosenItem = new Potion(potionAttr[0], Integer.parseInt(potionAttr[1]), Integer.parseInt(potionAttr[2]), Integer.parseInt(potionAttr[3]), potionAttr[4]);
        } else if (lstFile.equals("ells.txt")) {
            String spellFile = filePath.substring(filePath.length() - 13);
            if (spellFile.equals("IceSpells.txt")) {
                String[] iceAttr = stuffString.split("\\s+");
                chosenItem = new IceSpell(iceAttr[0], Integer.parseInt(iceAttr[1]), Integer.parseInt(iceAttr[2]), Integer.parseInt(iceAttr[3]), Integer.parseInt(iceAttr[4]));
            } else if (spellFile.equals("ireSpells.txt")) {
                String[] fireAttr = stuffString.split("\\s+");
                chosenItem = new FireSpell(fireAttr[0], Integer.parseInt(fireAttr[1]), Integer.parseInt(fireAttr[2]), Integer.parseInt(fireAttr[3]), Integer.parseInt(fireAttr[4]));
            } else {
                String[] lightningAttr = stuffString.split("\\s+");
                chosenItem = new LightningSpell(lightningAttr[0], Integer.parseInt(lightningAttr[1]), Integer.parseInt(lightningAttr[2]), Integer.parseInt(lightningAttr[3]), Integer.parseInt(lightningAttr[4]));
            }
        } else {
            chosenItem = new Weapon();
        }

        if (hero.getLevel() >= chosenItem.getRequiredLevel() && hero.getCoins() >= chosenItem.getPrice()) {
            if (chosenItem instanceof Weapon) {
                hero.getInventory().getWeapons().add((Weapon) chosenItem);
                hero.setCoins(hero.getCoins() - chosenItem.getPrice());
            } else if (chosenItem instanceof Armor) {
                hero.getInventory().getArmors().add((Armor) chosenItem);
                hero.setCoins(hero.getCoins() - chosenItem.getPrice());
            } else if (chosenItem instanceof Potion) {
                hero.getInventory().getPotions().add((Potion) chosenItem);
                hero.setCoins(hero.getCoins() - chosenItem.getPrice());
            } else if (chosenItem instanceof Spell) {
                hero.getInventory().getSpells().add((Spell) chosenItem);
                hero.setCoins(hero.getCoins() - chosenItem.getPrice());
            }

            System.out.println("Thanks for buying");
        } else {
            System.out.println("You can't buy it");
        }

    }

    public void showMarketWelcome() {
        String welcome = "          .         .                                                                                                 \n" +
                "         ,8.       ,8.                   .8.          8 888888888o.   8 8888     ,88' 8 8888888888 8888888 8888888888 \n" +
                "        ,888.     ,888.                 .888.         8 8888    `88.  8 8888    ,88'  8 8888             8 8888       \n" +
                "       .`8888.   .`8888.               :88888.        8 8888     `88  8 8888   ,88'   8 8888             8 8888       \n" +
                "      ,8.`8888. ,8.`8888.             . `88888.       8 8888     ,88  8 8888  ,88'    8 8888             8 8888       \n" +
                "     ,8'8.`8888,8^8.`8888.           .8. `88888.      8 8888.   ,88'  8 8888 ,88'     8 888888888888     8 8888       \n" +
                "    ,8' `8.`8888' `8.`8888.         .8`8. `88888.     8 888888888P'   8 8888 88'      8 8888             8 8888       \n" +
                "   ,8'   `8.`88'   `8.`8888.       .8' `8. `88888.    8 8888`8b       8 888888<       8 8888             8 8888       \n" +
                "  ,8'     `8.`'     `8.`8888.     .8'   `8. `88888.   8 8888 `8b.     8 8888 `Y8.     8 8888             8 8888       \n" +
                " ,8'       `8        `8.`8888.   .888888888. `88888.  8 8888   `8b.   8 8888   `Y8.   8 8888             8 8888       \n" +
                ",8'         `         `8.`8888. .8'       `8. `88888. 8 8888     `88. 8 8888     `Y8. 8 888888888888     8 8888    ";
        System.out.println(welcome);
    }

    public void gameIsOver() {
        String endGame;
        endGame = " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ \n" +
                "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗\n" +
                "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝\n" +
                "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n" +
                "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n" +
                " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝";
        System.out.println(endGame);
    }

    public void importFile(ArrayList<String> filePath) {

    }

    public void welcome() {
        String welcomeWord;
        welcomeWord = "___       ___                                                                  ____    ____                           \n" +
                "`MMb     dMM'                                                                  `MM'    `MM'                           \n" +
                " MMM.   ,PMM                                /                                   MM      MM                            \n" +
                " M`Mb   d'MM   _____   ___  __     ____    /M       ____   ___  __              MM      MM   ____   ___  __   _____   \n" +
                " M YM. ,P MM  6MMMMMb  `MM 6MMb   6MMMMb\\ /MMMMM   6MMMMb  `MM 6MM              MM      MM  6MMMMb  `MM 6MM  6MMMMMb  \n" +
                " M `Mb d' MM 6M'   `Mb  MMM9 `Mb MM'    `  MM     6M'  `Mb  MM69 \"              MMMMMMMMMM 6M'  `Mb  MM69 \" 6M'   `Mb \n" +
                " M  YM.P  MM MM     MM  MM'   MM YM.       MM     MM    MM  MM'                 MM      MM MM    MM  MM'    MM     MM \n" +
                " M  `Mb'  MM MM     MM  MM    MM  YMMMMb   MM     MMMMMMMM  MM                  MM      MM MMMMMMMM  MM     MM     MM \n" +
                " M   YP   MM MM     MM  MM    MM      `Mb  MM     MM        MM                  MM      MM MM        MM     MM     MM \n" +
                " M   `'   MM YM.   ,M9  MM    MM L    ,MM  YM.  , YM    d9  MM                  MM      MM YM    d9  MM     YM.   ,M9 \n" +
                "_M_      _MM_ YMMMMM9  _MM_  _MM_MYMMMM9    YMMM9  YMMMM9  _MM_                _MM_    _MM_ YMMMM9  _MM_     YMMMMM9 ";
        System.out.println(welcomeWord);
        System.out.println();
        System.out.println("Welcome to the game!!");
    }
    //getters/setters
    public MnHBoard getMnHBoard() {
        return board;
    }
    public void setMnHBoard(MnHBoard mnHBoard) {
        this.board = mnHBoard;
    }

}
