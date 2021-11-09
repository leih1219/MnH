import com.sun.source.tree.BreakTree;

import java.util.Arrays;

/**
 * basic board class
 */
public class GameBoard {
    protected final int BOARD_SIZE = 8;
    protected Cell[][] cells;

    public GameBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    }

    public GameBoard(int x, int y) {
        cells = new Cell[x][y];
    }

    public void init() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public void setCell(int i, int j, Cell cell) {
        cells[i][j] = cell;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getBoardRows() {
        return cells.length;
    }

    public int getBoardCols() {
        return cells[0].length;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        String str = "";
        // draw the row lines
        String row = "";
        for (int i = 0; i < getBoardCols(); i++) {
            row += "+---";
        }
        row += "+\n";
        for (int j = 0; j < getBoardRows(); j++) {
            str += row;
            for (int k = 0; k < getBoardCols(); k++) {
                str += "| ";
                str += cells[j][k].toString();
                str += " ";
            }
            str += "|\n";
        }
        str += row;
        return str;
    }
}
