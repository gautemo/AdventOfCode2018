package gautemo.app;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Guard {
    private int[] minutes = new int[60];

    public void addSleep(int from, int to){
        IntStream.range(from, to).forEach(i -> minutes[i]++);
    }

    public int totalSleep(){
        return Arrays.stream(minutes).sum();
    }

    public int mostSleep(){
        int minute = 0;
        int max = 0;
        for(int i = 0; i<minutes.length; i++) {
            if (minutes[i] > max) {
                minute = i;
                max = minutes[i];
            }
        }
        return minute;
    }

    public int mostSleepMinute(){
        return Arrays.stream(minutes).max().orElse(0);
    }
}
