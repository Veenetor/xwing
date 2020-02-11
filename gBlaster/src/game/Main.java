package game;

public class Main {

    public static void main(String[] args) {

        Game game = new Game(40, 10);

        System.out.println(game.getMap().getRows());

        System.out.println(game.getPlayer().getRow());


        game.getPlayer().movePlayer(2);
        System.out.println(game.getPlayer().getRow());
        game.getPlayer().movePlayer(2);
        System.out.println(game.getPlayer().getRow());
        game.getPlayer().movePlayer(-2);
        System.out.println(game.getPlayer().getRow());

        game.getPlayer().movePlayer(2);
        System.out.println(game.getPlayer().getRow());
        game.getPlayer().movePlayer(2);
        System.out.println(game.getPlayer().getRow());


    }

}
