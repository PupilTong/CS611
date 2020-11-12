package MVVM;

import java.util.List;

import GameEngine.GameEngine;
import GameEngine.UIEngine;

/**
 * A factory generates GameUIComponment
 */
public class GameUIComponmentFactory {

    public  GameUIComponment generateCUIcomponment(Model model,View view,List<ModelProperty> props,GameEngine gameEngine){
        ViewModel gameViewModel = new GameViewModel(model,view);
        view.setViewModel(gameViewModel);
        GameUIComponment componment = new GameUIComponment(model,view,gameViewModel,props,gameEngine);
        return componment;
    }
}
