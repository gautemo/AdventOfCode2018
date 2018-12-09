package gautemo.app;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @org.junit.jupiter.api.Test
    void test1(){
        Game game = new Game(9, 25);
        game.playGame();
        assertEquals(32, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test2(){
        Game game = new Game(10, 1618);
        game.playGame();
        assertEquals(8317, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test3(){
        Game game = new Game(13, 7999);
        game.playGame();
        assertEquals(146373, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test4(){
        Game game = new Game(17, 1104);
        game.playGame();
        assertEquals(2764, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test5(){
        Game game = new Game(21, 6111);
        game.playGame();
        assertEquals(54718, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test6(){
        Game game = new Game(30, 5807);
        game.playGame();
        assertEquals(37305, game.getScore());
    }
}