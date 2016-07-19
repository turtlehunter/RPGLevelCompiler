package me.urielsalis.RPGLevelCompiler.stages;

import java.io.*;
import java.util.ArrayList;

public class FirstStage {

    public static ArrayList<String> parse(String arg) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(arg);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if(!line.equals(""))
                    lines.add(removeComments(line.trim()) + "\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file to parse " + arg);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Error parsing file " + arg);
            System.exit(-1);
        }
        return lines;
    }

    private static String removeComments(String str) {
        if(str.contains("//")) {
            str = str.substring(0, str.indexOf("//"));
        }
        return str;

    }
}
