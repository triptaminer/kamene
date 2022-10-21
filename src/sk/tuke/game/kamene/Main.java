package sk.tuke.game.kamene;

import sk.tuke.game.kamene.consoleui.ConsoleUI;
import sk.tuke.game.kamene.core.Field;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(8, 8, 10);
        ConsoleUI ui = new ConsoleUI(field);
        ui.play();
    }
}
