package gautemo.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        lines.sort(Comparator.comparing(Main::getDate));

        HashMap<String, Guard> map = new HashMap();
        populateMap(lines, map);

        printTask1(map);
        printTask2(map);
    }

    private static String getGuardId(String line){
        Matcher matcher = Pattern.compile("#\\d+").matcher(line);
        matcher.find();
        return matcher.group().replace("#", "");
    }

    private static LocalDateTime getDate(String line) {
        Matcher matcher = Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}").matcher(line);
        matcher.find();
        return LocalDateTime.parse(matcher.group(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private static void populateMap(List<String> lines, HashMap<String, Guard> map){
        String currentGuard = "";
        int startSleep = 0;
        for(String line : lines){
            if(line.contains("begins shift")) {
                String id = getGuardId(line);
                currentGuard = id;
                if (!map.containsKey(id)) {
                    map.put(id, new Guard());
                }
            }else if(line.contains("asleep")){
                startSleep = getDate(line).getMinute();
            }else{
                int stop = getDate(line).getMinute();
                map.get(currentGuard).addSleep(startSleep, stop);
            }
        }
    }

    private static void printTask1(HashMap<String, Guard> map){
        Map.Entry<String, Guard> sleeper = map.entrySet().stream().max((o1, o2) -> {
            Integer sleep1 = o1.getValue().totalSleep();
            Integer sleep2 = o2.getValue().totalSleep();
            return sleep1.compareTo(sleep2);
        }).orElse(null);
        System.out.println("Guard #" + sleeper.getKey() + " slept most in minute " + sleeper.getValue().mostSleep() + ". Code id * minute: " + Integer.parseInt(sleeper.getKey()) * sleeper.getValue().mostSleep());
    }

    private static void printTask2(HashMap<String, Guard> map){
        Map.Entry<String, Guard> sleeper = map.entrySet().stream().max((o1, o2) -> {
            Integer sleep1 = o1.getValue().mostSleepMinute();
            Integer sleep2 = o2.getValue().mostSleepMinute();
            return sleep1.compareTo(sleep2);
        }).orElse(null);
        System.out.println("Guard #" + sleeper.getKey() + " slept most in minute " + sleeper.getValue().mostSleep() + ". Code id * minute: " + Integer.parseInt(sleeper.getKey()) * sleeper.getValue().mostSleep());
    }
}
