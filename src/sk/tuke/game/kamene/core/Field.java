package sk.tuke.game.kamene.core;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private final int rowCount;

    private final int columnCount;

    private FieldState state = FieldState.PLAYING;

    private final Map<String,Integer> tiles;

    private int openCount;

    public Field(int rowCount, int columnCount) {

        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tiles=new HashMap<String, Integer>();
        //tiles.put(rowCount+"x"+columnCount,new Tile());
        generate();
    }

    private void generate() {

        Random random = new Random();
        int value=1;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                //tiles[i][j] = new Tile(value);
                if (value!=rowCount*columnCount) {
                    tiles.put(i + "x" + j, value);
                    value++;
                }
            }
        }
        tiles.put((rowCount-1)+"x"+(columnCount-1),0);
    }

    private int countNeighbourMines(int r, int c) {
        int count = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < rowCount && j >= 0 && j < columnCount) {
                    count++;
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

    public int getTile(int row, int column) {
        return tiles.get(row+"x"+column);
    }

    public void moveTile(int row, int column,int direction) {
       // Tile tile = getTile(row, column);

        swapTiles();


        if (isSolved())
            state = FieldState.SOLVED;

    }
    private void swapTiles(int r1,int c1,int r2,int c2){
        int tempValue=getTile(r1,c1);
        tiles.put(r1 + "x" + c1, getTile(r2,c2));
        tiles.put(r2 + "x" + c2, tempValue);

    }

    private boolean isSolved() {
        int totalCount = 1;
        for (int i = 0; i < rowCount ; i++) {
            for (int j = 0 ; j < columnCount ; j++) {
                    if (tiles.get(i+"x"+j) == totalCount) {
                        totalCount++;
                }
            }
        }
        System.out.println(totalCount+"="+rowCount+"*"+columnCount);
        return totalCount == rowCount * columnCount;
    }
    public Set<String> getEmpty(){
        return getKeysByValue(tiles,0);
    }
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
