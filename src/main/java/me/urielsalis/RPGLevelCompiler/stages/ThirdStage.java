package me.urielsalis.RPGLevelCompiler.stages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ThirdStage {
    public static ArrayList<String> parse(ArrayList<String> secondStageResults) {
        ArrayList<String> lines = new ArrayList<>();
        HashMap<String, String> constructor = new HashMap<>();
        ArrayList<String> replace = new ArrayList<>();
        ArrayList<String> container = new ArrayList<>();
        String name = "";
        boolean isConstructor = false;
        for(String line: secondStageResults) {
            if(isConstructor) {
                if(!line.equals(")")) {
                    for (int i = 0; i < replace.size(); i++) {
                        line = line.replace(replace.get(i), "_"+name+i);//_name1

                    }
                    container.add(line);
                } else {
                    isConstructor = false;
                    constructor.put(name, toStr(container));
                    replace.clear();
                    container.clear();
                }
            } else if (line.startsWith("ExportConstruct")) {
                name = line.substring(16, line.indexOf("("));
                System.out.println("    Construct: " + name);
                isConstructor = true;
                String parse = line.substring(16+name.length()+1, line.indexOf(")"));
                String[] result = parse.split(" ");
                Collections.addAll(replace, result);
            } else if(line.startsWith("Use")) {
                String nameConstructor = line.substring(4, line.indexOf("("));
                String data = line.substring(4 + nameConstructor.length()+1, line.indexOf(")"));
                String[] vars = data.split(" ");
                String var = constructor.get(nameConstructor);
                for (int i = 0; i < vars.length; i++) {
                    var.replace("_"+nameConstructor+i, vars[i]);
                }
                String[] stringsToAdd = var.split("\n");
                Collections.addAll(lines, stringsToAdd);
            } else {
                lines.add(line);
            }
        }
        return lines;
    }

    private static String toStr(ArrayList<String> container) {
        StringBuilder builder = new StringBuilder();
        for(String str: container) builder.append(str + "\n");
        return builder.toString();
    }
}
