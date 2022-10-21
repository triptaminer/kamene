package sk.tuke.game.kamene.consoleui;

import sk.tuke.game.kamene.core.Field;
import sk.tuke.game.kamene.core.FieldState;
import sk.tuke.game.kamene.core.Tile;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {
    private final Field field;
    private final Controls controls;

    private Scanner scanner = new Scanner(System.in);

    private static final Pattern INPUT_PATTERN = Pattern.compile("[a-zA-Z0-9]");

    public ConsoleUI(Field field) {
        this.field = field;
        controls = new Controls();

    }


    public void play() {
        do {
            print();
            processInput();
        } while (field.getState() == FieldState.PLAYING);
        print();
    }

    private void print() {
        System.out.println(field.getState());

        System.out.println();
        for (int row = 0; row < field.getRowCount(); row++) {
            for (int column = 0; column < field.getColumnCount(); column++) {
                int value = field.getTile(row,column);

                System.out.printf("%3s",value>0?value:" ");

            }
            System.out.println();
        }
        System.out.println();
    }

    private void processInput() {
        System.out.println("Enter command (X - exit, OA1 - open, MB3 - mark: ");
        String line = scanner.nextLine().toLowerCase().trim();
        if ("x".equals(line))
            System.exit(0);
        Matcher matcher = INPUT_PATTERN.matcher(line);
        if (matcher.matches()) {

            int direction= controls.translate(line);

            String emptyTile= field.getEmpty().toString().replace("[","").replace("]","");
            System.out.println(emptyTile);
            int row= Integer.parseInt(emptyTile.split("x")[0]);
            int column= Integer.parseInt(emptyTile.split("x")[1]);

            field.moveTile(row,column,direction);

//            int row = matcher.group(1).charAt(0) - 'A';
//            int column = Integer.parseInt(matcher.group(3)) - 1;

            if (row+1 > field.getRowCount() || column+1 > field.getColumnCount()) {
                System.err.println("Bad input");
            }

        } else
            System.err.println("Bad input");
    }

}
