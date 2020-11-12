package Views;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MVVM.GameView;
import GameEngine.GameEngine;

public class MapView extends GameView {

    public MapView() throws FileNotFoundException {
        super();
    }

    @Override
    protected void initializeTemplate() throws FileNotFoundException {
        File file = new File(GameEngine.resourcesDir + "./MapViewTemplete.txt");
        Scanner s = new Scanner(file);
        while(s.hasNextLine()){
            this.template.add(s.nextLine());
        }
        s.close();

    }
    
}
