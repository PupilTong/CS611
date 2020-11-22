import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import GameEngine.*;
import MVVM.*;
import Models.MapPageModel;
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try {
            GameEngine.setRecourseDirectory("/home/ubuntu/CS611/group/Legend/resources/");
            GameEngine g = new GameEngine();
            g.initialUIEngine(System.in, System.out);
            TerminalUIEngine uie = (TerminalUIEngine) g.getUiEngine();
            GameView mapView = new GameView(new File("/home/ubuntu/CS611/group/Legend/resources/MapViewTemplete.txt"));
            MapPageModel mapPageModel = new MapPageModel();
            GameUIComponmentFactory factory = new GameUIComponmentFactory();
            GameUIComponment mapPage = factory.generateUIcomponment(mapPageModel, mapView, null, uie);
            mapPage.show();
            uie.requestInput("test for an input", "a","b","c");
        } catch (Exception e) {
            File log = new File("/home/ubuntu/CS611/group/Legend/resources/" + "log.log");
            PrintStream ps;
            try {
                ps = new PrintStream(log);
                e.printStackTrace(ps);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
