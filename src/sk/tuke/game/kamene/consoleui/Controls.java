package sk.tuke.game.kamene.consoleui;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class Controls {

    Map<String, Integer> controls = new HashMap<>();

    Controls() {
        controls.put("w",0);
        controls.put("a",1);
        controls.put("s",2);
        controls.put("d",3);
        controls.put("up",0);
        controls.put("left",1);
        controls.put("down",2);
        controls.put("right",3);
    }

    public int translate(String input) {
        if(controls.get(input)!=null)
            return controls.get(input);
        else
            return -1;
    }

}
