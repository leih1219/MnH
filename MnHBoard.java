import sun.jvm.hotspot.oops.Mark;

import java.security.PublicKey;
import java.util.Random;

public class MnHBoard extends GameBoard {
    // last position of the hero
    protected int lastI, lastJ;

    public MnHBoard() {
        super();
        init();
    }

    public MnHBoard(int i, int j) {
        super(i, j);
        init();
    }

    @Override
    public void init() {
        super.init();
        int numCells = getBoardCols() * getBoardRows();
        int numImpassable = (int) (numCells * 0.2);
        int numMarket = (int) (numCells * 0.3);
//        int numCommon = (int) (numCells * 0.5);
        int numWild = numCells - numImpassable - numMarket;

        for (int i = 0; i < getBoardRows(); i++) {
            for (int j = 0; j < getBoardCols(); j++) {
                if (i == 0 && j == 0) {
                    cells[i][j] = new WildCell();
                    numWild--;
                } else {
                    double random = Math.random();

                    if (random < 0.2 && numImpassable > 0) {
                        cells[i][j] = new ImpassableCell();
                        numImpassable--;
                    } else if (random < 0.5 && random >= 0.2 && numMarket > 0) {
                        cells[i][j] = new MarketCell();
                        numMarket--;
                    } else if (random >= 0.5 && numWild > 0) {
                        cells[i][j] = new WildCell();
                        numWild--;
                    } else if (numImpassable > 0) {
                        cells[i][j] = new ImpassableCell();
                        numImpassable--;
                    } else if (numMarket > 0) {
                        cells[i][j] = new MarketCell();
                        numMarket--;
                    } else {
                        cells[i][j] = new WildCell();
                        numWild--;
                    }
                }
            }
        }

        // check if player can move
        if (!cells[0][1].isAccessible && !cells[1][0].isAccessible) {
            init();
        }
        movePlayer(0, 0);
    }

    public void movePlayer(int i, int j) {
        if (cells[lastI][lastJ] instanceof ImpassableCell) {
            ((ImpassableCell) cells[lastI][lastJ]).setImpassableCell();
        } else if (cells[lastI][lastJ] instanceof MarketCell) {
            ((MarketCell) cells[lastI][lastJ]).setMarketCell();
        } else if (cells[lastI][lastJ] instanceof WildCell) {
            ((WildCell) cells[lastI][lastJ]).setWildCell();
        }

        cells[i][j].setIcon("P");
        lastI = i;
        lastJ = j;
    }

    public boolean isAvailable(int i, int j) {
        if (i >= 0 && i < getBoardCols() && j >= 0 && j < getBoardCols() && cells[i][j].isAccessible) {
            return true;
        }
        System.out.println("You can't move it here!");
        return false;
    }

}
