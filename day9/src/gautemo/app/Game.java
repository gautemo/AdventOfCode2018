package gautemo.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;

class Game {
    private int[] players;
    private int marbles;
    private ArrayList<Integer> circle = new ArrayList<>();
    private int currentMarbleIndex = 0;

    Game(int players, int marbles){
        this.players = new int[players];
        this.marbles = marbles;
    }

    void playGame(){
        int currentPlayer = 0;
        circle.add(0);
        for(int i = 1; i<=marbles; i++){
            if(i % 23 == 0){
                currentMarbleIndex -= 7;
                if(currentMarbleIndex < 0) currentMarbleIndex = circle.size() + currentMarbleIndex;
                players[currentPlayer] += circle.remove(currentMarbleIndex) + i;
            }else{
                currentMarbleIndex += 2;
                if(currentMarbleIndex >= circle.size()) currentMarbleIndex = currentMarbleIndex - circle.size();
                circle.add(currentMarbleIndex, i);
            }
            currentPlayer++;
            if(currentPlayer >= players.length) currentPlayer = 0;
        }
    }

    void printScore(){
        System.out.println(getScore());
    }

    int getScore(){
        OptionalInt oi = Arrays.stream(players).max();
        return oi.isPresent() ? oi.getAsInt() : 0;
    }
}
