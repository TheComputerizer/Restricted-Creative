package mods.thecomputerizer.restrictedcreative;

import java.util.Arrays;
import java.util.List;

public class configHandler {

    public static void clientSync(String s) {
        String[] split = s.split(";");
        if(split[0].matches("e:")) {
            List<String> temp = Arrays.asList(split);
            temp.remove("e:");
            config.exemptPlayers = temp.toArray(new String[0]);
        }
        else if(split[0].matches("c:")) {
            List<String> temp = Arrays.asList(split);
            temp.remove("c:");
            config.badCommands = temp.toArray(new String[0]);
        }
        else if(split[0].matches("i:")) {
            List<String> temp = Arrays.asList(split);
            temp.remove("i:");
            config.badItems = temp.toArray(new String[0]);
        }
        else {
            config.noCheating = Boolean.parseBoolean(s);
        }
    }

    public static String buildExemptPlayers() {
        StringBuilder temp= new StringBuilder("e:");
        for(String f: config.exemptPlayers) {
            temp.append(";").append(f);
        }
        return temp.toString();
    }

    public static String buildBadCommands() {
        StringBuilder temp= new StringBuilder("c:");
        for(String f: config.badCommands) {
            temp.append(";").append(f);
        }
        return temp.toString();
    }

    public static String buildBadItems() {
        StringBuilder temp= new StringBuilder("i:");
        for(String f: config.badItems) {
            temp.append(";").append(f);
        }
        return temp.toString();
    }
}
