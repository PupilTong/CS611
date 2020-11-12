package Models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Game.LegendGame;
import GameItems.Palandin;
import GameItems.Sorcerer;
import GameItems.Warrior;
import MVVM.ListProperty;
import MVVM.Model;

public class HeroSelectPageModel implements Model {
    public ListProperty warList;
    public ListProperty sorList;
    public ListProperty palList;
    public HeroSelectPageModel(List<Warrior> warriors, List<Sorcerer> sorceres,List<Palandin> paldandins){
        warList = new ListProperty(warriors);
        sorList = new ListProperty(sorceres);
        palList = new ListProperty(paldandins);
    }
    
}
