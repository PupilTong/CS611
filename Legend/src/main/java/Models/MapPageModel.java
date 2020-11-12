package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import Game.LegendGame;
import GameEngine.Game;
import GameItems.GameMap;
import MVVM.ListProperty;
import MVVM.Model;
import MVVM.ModelProperty;
import MVVM.StringProperty;

public class MapPageModel implements Model {
    GameMap map;
    StringProperty space;
    StringProperty unreachable;
    StringProperty market;
    StringProperty player;
    public StringProperty playername;
    public MapPageModel(){
        map = new GameMap(LegendGame.mapHeight,LegendGame.mapWidth);
        space = new StringProperty(GameMap.spacePlace);
        unreachable = new StringProperty("\033[0;31m" + GameMap.unreachablePlace + "\033[0;37m |");
        market = new StringProperty("\033[0;34m" + GameMap.unreachablePlace + "\033[0;37m |");
        player = new StringProperty(GameMap.player);
        playername = new StringProperty();
    }
    public int playerMove(String d){
        if(d.equals("w")){
            map.player_y--;
        }
        else if(d.equals("s")){
            map.player_y++;
        }
        else if(d.equals("d")){
            map.player_x++;
        }
        else if(d.equals("a")){
            map.player_x--;
        }
        if(this.map.gameMap.get(map.player_y).get(map.player_x).equals(GameMap.spacePlace)){
            Random random = new Random();
            return random.nextInt(100);
        }
        return -1;
        
    }
    public String[] addOptions(ArrayList<String> ret){
        ret = new ArrayList<>(ret);
        if(map.player_y<map.gameMap.size()-1&&!map.gameMap.get(map.player_y+1).get(map.player_x).equals(GameMap.unreachablePlace)){
            ret.add("s");
        }
        if(map.player_y>0&&!map.gameMap.get(map.player_y-1).get(map.player_x).equals(GameMap.unreachablePlace)){
            ret.add("w");
        }
        if(map.player_x<map.gameMap.get(0).size()-1&&!map.gameMap.get(map.player_y).get(map.player_x+1).equals(GameMap.unreachablePlace)){
            ret.add("d");
        }
        if(map.player_x>0&&!map.gameMap.get(map.player_y).get(map.player_x-1).equals(GameMap.unreachablePlace)){
            ret.add("a");
        }
        if(map.gameMap.get(map.player_y).get(map.player_x).equals(GameMap.marketPlace)){
            ret.add("m");
        }
        return (String[])ret.toArray(new String[0]);
    }
    
}
