package Views;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

import GameEngine.GameEngine;
import MVVM.GameView;

public class HeroDetailPageView extends GameView {

    public HeroDetailPageView() throws FileNotFoundException {
        super();
    }

    @Override
    protected void initializeTemplate() throws FileNotFoundException {
        File file = new File(GameEngine.resourcesDir + "./HeroDetails.txt");
        Scanner s = new Scanner(file);
        while(s.hasNextLine()){
            this.template.add(s.nextLine());
        }
        s.close();

    }
    
}
