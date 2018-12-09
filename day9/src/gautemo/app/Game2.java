package gautemo.app;

import java.util.Arrays;
import java.util.OptionalLong;

class Game2 {
    private long[] players;
    private int marbles;
    private Marble current;

    Game2(int players, int marbles){
        this.players = new long[players];
        this.marbles = marbles;
    }

    void playGame(){
        int currentPlayer = 0;
        current = new Marble(0);
        current.next = current;
        current.prev = current;
        for(int i = 1; i<=marbles; i++){
            if(i % 23 == 0){
                current = current.prev.prev.prev.prev.prev.prev.prev;
                players[currentPlayer] += current.number + i;
                link(current.prev, current.next);
                current = current.next;
            }else{
                Marble newCurrent = new Marble(i);
                link(newCurrent, current.next.next);
                link(current.next, newCurrent);
                current = newCurrent;
            }
            currentPlayer++;
            if(currentPlayer >= players.length) currentPlayer = 0;
        }
    }

    void printScore(){
        System.out.println(getScore());
    }

    long getScore(){
        OptionalLong oi = Arrays.stream(players).max();
        return oi.isPresent() ? oi.getAsLong() : 0;
    }

    void link(Marble m1, Marble m2){
        m1.next = m2;
        m2.prev = m1;
    }
}
