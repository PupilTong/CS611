package Models;

import java.util.ArrayList;
import java.util.List;

import GameItems.Hero;
import GameItems.Monster;
import MVVM.*;

public class FightPageModel implements Model {
    ListProperty herolist;
    ListProperty enemylist;
    int index=0;
    public FightPageModel(List<Hero> heroTeam, List<Monster> monsterTeam){
        herolist  = new ListProperty(heroTeam);
        ArrayList<Monster> mons = new ArrayList<>();
        for(Hero hero:heroTeam){

            for(Monster monster:monsterTeam){
                if(hero.getLevel()<=monster.getLevel()){
                    mons.add(monster);
                    break;
                }
            }
        }
        enemylist = new ListProperty(mons);
    }
    public ListProperty getEnemylist() {
        return enemylist;
    }
}
