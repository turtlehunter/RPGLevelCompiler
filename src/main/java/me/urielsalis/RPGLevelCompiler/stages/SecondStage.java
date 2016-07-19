package me.urielsalis.RPGLevelCompiler.stages;

import java.util.ArrayList;

public class SecondStage {
    public static ArrayList<String> parse(ArrayList<String> firstStageResults) {
        ArrayList<String> lines = new ArrayList<String>();
        for(String line: firstStageResults) {
            if(line.startsWith("Import")) {
                String file = line.substring(7);
                System.out.println("    Parsing import: "+file);
                lines.addAll(SecondStage.parse(FirstStage.parse(file)));
            } else {
                lines.add(line);
            }
        }
        return lines;
    }
}
