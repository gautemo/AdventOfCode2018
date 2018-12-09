package gautemo.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Game2Test {

    @org.junit.jupiter.api.Test
    void test1(){
        Game2 game = new Game2(9, 25);
        game.playGame();
        assertEquals(32, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test2(){
        Game2 game = new Game2(10, 1618);
        game.playGame();
        assertEquals(8317, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test3(){
        Game2 game = new Game2(13, 7999);
        game.playGame();
        assertEquals(146373, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test4(){
        Game2 game = new Game2(17, 1104);
        game.playGame();
        assertEquals(2764, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test5(){
        Game2 game = new Game2(21, 6111);
        game.playGame();
        assertEquals(54718, game.getScore());
    }

    @org.junit.jupiter.api.Test
    void test6(){
        Game2 game = new Game2(30, 5807);
        game.playGame();
        assertEquals(37305, game.getScore());
    }
}