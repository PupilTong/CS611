
package Views;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

import GameEngine.GameEngine;
import MVVM.GameView;

public class BagView extends GameView {

    public BagView() throws FileNotFoundException {
        super();
    }

    @Override
    protected void initializeTemplate() throws FileNotFoundException {
        File file = new File(GameEngine.resourcesDir + "./Bag.txt");
        Scanner s = new Scanner(file);
        while(s.hasNextLine()){
            this.template.add(s.nextLine());
        }
        s.close();

    }
    
}
