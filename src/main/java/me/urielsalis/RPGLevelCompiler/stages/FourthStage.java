package me.urielsalis.RPGLevelCompiler.stages;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FourthStage {
    public static ArrayList<String> parse(ArrayList<String> thirdStageResult) {
        ArrayList<String> lines = new ArrayList<>();
        HashMap<String, String> variables = new HashMap<>();
        for(String str: thirdStageResult) {
            if(str.startsWith("Export")) {
                String[] strs = str.split(" ");
                variables.put(strs[1], runExpresions(strs[2]));
                System.out.println("    Variable: "+strs[1]);
            } else {
                for(Map.Entry<String, String> entry: variables.entrySet()) {
                    str = str.replace(entry.getKey(), entry.getValue());
                }
                lines.add(str);
            }
        }
        return lines;
    }

    private static String runExpresions(String str) {
        return new DoubleEvaluator().evaluate(str).toString();
    }
}
