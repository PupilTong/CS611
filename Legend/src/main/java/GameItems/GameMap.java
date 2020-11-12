package GameItems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import MVVM.ListProperty;

public class GameMap extends ListProperty {

    public static final String unreachablePlace = "X";
    public static final String marketPlace = "M";
    public static final String spacePlace = " ";
    public static final String player = "o";
    public int player_x = 0;
    public int player_y = 0;

    public ArrayList<ArrayList<String>> gameMap;
    private void generateMap(int height,int width){
        this.gameMap = new ArrayList<>();
        Random random = new Random();
        for(int h=0;h<height;h++){
            ArrayList<String> onerow = new ArrayList<>();
            for(int w=0;w<height;w++){
                int r = random.nextInt(100);
                if(r<20){//unreachable
                    onerow.add(GameMap.unreachablePlace);
                }
                else if(r<50){//MarketPlace
                    onerow.add(GameMap.marketPlace);
                }
                else{//space
                    onerow.add(GameMap.spacePlace);
                    if(player_x==0&&player_y==0){
                        player_x = w;
                        player_y = h;
                    }
                }
            }
            gameMap.add(onerow);
        }

    }
    public GameMap(List<Object> iList) {
        super(null);
        gameMap = new ArrayList<>();
        for(Object o : iList){
            this.gameMap.add((ArrayList<String>) o);
        }
    }
    /**
     * generate game map
     * @param height map height
     * @param width map width
     */
    public GameMap(int height,int width){
        super(null);
        this.generateMap(height, width);
    }
    /**
     * generate game map in default size 5x5
     */
    public GameMap(){
        super(null);
        this.generateMap(5, 5);
    }

    @Override
    public Iterator<String> get() {
        ArrayList<String> r = new ArrayList<>();
        Iterator<Object> it = this.iterator();
        while(it.hasNext()){
            r.add(it.next().toString());
        }
        return r.iterator();
    }

    @Override
    public void set(String value) {

    }

    @Override
    public Iterator<Object> iterator() {
        Iterator<Object> it = new Iterator<Object>() {
            private int index =0;
            @Override
            public boolean hasNext() {
                return index<gameMap.size();
            }
            @Override
            public String next() {
                String top = "";
                String mid = "";
                String bot = "";
                int w = 0;
                for(String e:gameMap.get(index)){
                    //top +="|   |";
                    if(index == player_y&&w == player_x){
                        mid +="| \033[0;35m" + GameMap.player +"\033[0;37m |";
                    }
                    else if(e==GameMap.unreachablePlace){
                        mid +="| \033[0;31m" + e +"\033[0;37m |";
                    }
                    else if(e==GameMap.marketPlace){
                        mid +="| \033[0;34m" + e +"\033[0;37m |";

                    }
                    else{
                        mid +="| " + e +" |";

                    }
                    w++;
                    //bot +="|   |";
                }
                index++;
                return top +"\n" + mid +"\n" + bot + "\n";

            }
        };
        return it;
    }
    public List<Object> getList(){
        return this.list;
    }
    
}
