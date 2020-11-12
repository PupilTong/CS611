
package Views;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

import GameEngine.GameEngine;
import MVVM.GameView;

public class FightView extends GameView {

    public FightView() throws FileNotFoundException {
        super();
    }

    @Override
    protected void initializeTemplate() throws FileNotFoundException {
        File file = new File(GameEngine.resourcesDir + "./FightPage.txt");
        Scanner s = new Scanner(file);
        while(s.hasNextLine()){
            this.template.add(s.nextLine());
        }
        s.close();

    }
    
}
