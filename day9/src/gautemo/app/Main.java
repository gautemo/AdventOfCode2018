package gautemo.app;

public class Main {

    public static void main(String[] args) {
        Game g = new Game(491, 71058);
        g.playGame();
        g.printScore();

        Game2 g2 = new Game2(491, 71058 * 100);
        g2.playGame();
        g2.printScore();
    }

}
