package MVVM;

import java.util.List;

import GameEngine.GameEngine;
import GameEngine.UIEngine;

/**
 * A factory generates GameUIComponment
 */
public class GameUIComponmentFactory {

    public  GameUIComponment generateUIcomponment(Model model,View view,List<ModelProperty> props,UIEngine gameEngine){
        ViewModel gameViewModel = new GameViewModel(model,view);
        view.setViewModel(gameViewModel);
        GameUIComponment componment = new GameUIComponment(model,view,gameViewModel,props,gameEngine);
        view.updated();
        return componment;
    }
}
