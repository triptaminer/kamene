package sk.tuke.game.kamene.core;

import java.util.Random;

public class Field {
    private final int rowCount;

    private final int columnCount;

    private FieldState state = FieldState.PLAYING;

    private final Tile[][] tiles;

    private int openCount;

    public Field(int rowCount, int columnCount) {

        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tiles = new Tile[rowCount][columnCount];
        generate();
    }

    private void generate() {

        Random random = new Random();
        for (int i = 0; i < rowCount*columnCount-1;i++ ) {
            int row = random.nextInt(rowCount);
            int column = random.nextInt(columnCount);
            if (tiles[row][column] == null) {
                tiles[row][column] = new Tile(i+1);
                i++;
            }
        }
        tiles[rowCount][columnCount] = new Tile(0);
    }

    private int countNeighbourMines(int r, int c) {
        int count = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < rowCount && j >= 0 && j < columnCount) {
                    if (tiles[i][j] instanceof Mine) {
                        count++;
                    }
                }
            }

        }
        return count;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public FieldState getState() {
        return state;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    public void openTile(int row, int column) {
        Tile tile = getTile(row, column);
        if (tile.getState() == TileState.CLOSED) {
            tile.setState(TileState.OPEN);
            openCount++;

            if (tile instanceof Mine) {
                state = FieldState.FAILED;
                return;
            }
            Clue c= (Clue) getTile(row,column);
            if (c.getValue()==0){
                openNeighborTiles(row,column);
            }

            if (isSolved())
                state = FieldState.SOLVED;
        }
    }
    private void openNeighborTiles(int r, int c){
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < rowCount && j >= 0 && j < columnCount) {
                    openTile(i,j);
                }
            }
        }
    }

    private boolean isSolved() {
        return rowCount * columnCount - mineCount == openCount;
    }
}
