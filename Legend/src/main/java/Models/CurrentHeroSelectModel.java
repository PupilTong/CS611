package Models;

import java.util.List;

import GameItems.Hero;
import MVVM.ListProperty;
import MVVM.Model;

public class CurrentHeroSelectModel implements Model {
    public ListProperty heroList;
    public CurrentHeroSelectModel(List<Hero> list){
        heroList = new ListProperty(list);
    }
}
