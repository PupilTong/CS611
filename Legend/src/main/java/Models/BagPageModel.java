package Models;

import java.util.ArrayList;

import javax.swing.text.html.StyleSheet.ListPainter;

import GameInerfaces.Buyable;
import GameItems.Hero;
import MVVM.ListProperty;
import MVVM.Model;
import MVVM.ModelProperty;

public class BagPageModel implements Model {
    public ListProperty bagitems;
    public BagPageModel(Hero h){
        bagitems = new ListProperty(h.bag);
    }
    public void add(Buyable item){
        bagitems.getList().add(item);
    }
    public void remove(Buyable item){
        bagitems.getList().remove(item);
    }
    public Buyable getItem(int index){

        Buyable item = (Buyable) this.bagitems.getList().get(index);
        return item;
    }
    public Buyable use(int index){
        Buyable item = (Buyable) this.bagitems.getList().get(index);
        remove(item);
        return item;

    }
    public String[] addOptions(ArrayList<String> options){
        options = new ArrayList<>(options);
        for(int i=0;i<this.bagitems.getList().size();i++){
            options.add(""+i);
        }
        return (String[])options.toArray(new String[0]);

    }
}
