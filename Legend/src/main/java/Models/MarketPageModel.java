package Models;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import GameInerfaces.Buyable;
import MVVM.ListProperty;
import MVVM.Model;
import MVVM.ModelProperty;
import MVVM.NumberProperty;
import MVVM.StringProperty;

public class MarketPageModel implements Model{

    public ListProperty itemList;
    StringProperty marketName;
    public NumberProperty money = new NumberProperty(0);
    public MarketPageModel(ArrayList<?> itemListinput,String name){
        ArrayList<?> iList = new ArrayList<>(itemListinput);
        itemList = new ListProperty(iList);
        marketName = new StringProperty(name);
    }
    public void add(Buyable item){
        itemList.getList().add(item);
    }
    public void remove(Buyable item){
        itemList.getList().remove(item);
    }
    public Buyable getItem(int index){

        Buyable item = (Buyable) this.itemList.getList().get(index);
        return item;
    }
    public Buyable buy(int index){
        Buyable item = (Buyable) this.itemList.getList().get(index);
        remove(item);
        return item;

    }
    public String[] addOptions(ArrayList<String> options){
        options = new ArrayList<>(options);
        for(int i=0;i<this.itemList.getList().size();i++){
            options.add(""+i);
        }
        return (String[])options.toArray(new String[0]);

    }
    
    
}
