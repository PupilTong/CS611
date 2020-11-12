import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import Game.LegendGame;
import GameEngine.*;
import MVVM.*;
import Models.MarketPageModel;
import Views.MarketView;

public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        LegendGame game = new LegendGame();
        try {
            game.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            game.uiEngine.stop();
            e.printStackTrace();
        }
        /*
        try {
            GameEngine.setRecourseDirectory("/home/ubuntu/CS611/hw4/Legend/resources/");
            GameEngine g = new GameEngine();
            g.initialUIEngine(System.in, System.out);
            TerminalUIEngine uie = (TerminalUIEngine) g.getUiEngine();
            MarketView marketView = new MarketView();
            MarketPageModel market = new MarketPageModel();
            GameUIComponmentFactory factory = new GameUIComponmentFactory();
            GameUIComponment marketComponment = factory.generateCUIcomponment(market, marketView, null, g);
            marketComponment.show();
            
            market.first.set(uie.requestInput("request input", "x","y","z"));
        } catch (Exception e) {
            File log = new File("/home/ubuntu/CS611/hw4/Legend/resources/" + "log.log");
            PrintStream ps;
            try {
                ps = new PrintStream(log);
                e.printStackTrace(ps);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }*/
    }
}
