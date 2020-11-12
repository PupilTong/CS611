package Views;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MVVM.GameView;
import GameEngine.GameEngine;

public class MainView extends GameView {

    public MainView() throws FileNotFoundException {
        super();
    }

    @Override
    protected void initializeTemplate() throws FileNotFoundException {
        File file = new File(GameEngine.resourcesDir + "./MainPage.txt");
        Scanner s = new Scanner(file);
        while(s.hasNextLine()){
            this.template.add(s.nextLine());
        }
        s.close();

    }
    
}
