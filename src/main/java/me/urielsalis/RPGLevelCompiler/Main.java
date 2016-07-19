package me.urielsalis.RPGLevelCompiler;

import me.urielsalis.RPGLevelCompiler.stages.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    public static long started;
    public static String stage = null;

    public static void main(String[] args) {
        startStage("First");
        ArrayList<String> firstStageResults = FirstStage.parse(args[0]);
        saveFile(args[0]+".stage1", firstStageResults);
        startStage("Second");
        ArrayList<String> secondStageResults = SecondStage.parse(firstStageResults);
        saveFile(args[0]+".stage2", firstStageResults);
        startStage("Third");
        ArrayList<String> thirdStageResult = ThirdStage.parse(secondStageResults);
        saveFile(args[0]+".stage3", firstStageResults);
        startStage("Fourth");
        ArrayList<String> fourthStageResult = FourthStage.parse(thirdStageResult);
        saveFile(args[0]+".stage4", firstStageResults);
        startStage("Fifth");
        ArrayList<String> fifthStageResult = FifthStage.parse(fourthStageResult);
        saveFile(args[0]+".stage5", firstStageResults);
    }

    private static void saveFile(String name, ArrayList<String> data) {
        StringBuilder builder = new StringBuilder();
        for(String str: data) {
            builder.append(str + "\n");
        }
        try {
            FileWriter writer = new FileWriter(name);
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatTime(long millis) {
        return String.format("%02d min, %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
    }

    public static void startStage(String st) {
        if(stage!=null) {
            long now = System.nanoTime();
            System.out.println("Finished " + stage + " Stage in " + formatTime(now-started));
        }
        stage = st;
        System.out.println("Starting " + stage + " Stage");
        started = System.nanoTime();
    }
}
