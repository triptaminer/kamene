package sk.tuke.game.kamene.core;

public class Tile {

    private final int value;

    protected Tile(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
