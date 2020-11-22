package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import GameEngine.Game;
import MVVM.ListProperty;
import MVVM.Model;
import MVVM.ModelProperty;
import MVVM.StringProperty;

public class MapPageModel implements Model {
    ListProperty elements;
    StringProperty property1;
    StringProperty color;
    StringProperty oneline_demo0;
    StringProperty oneline_demo1;
    ListProperty multiline;
    ListProperty multiline_long;
    public MapPageModel(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("element0");
        arr.add("element1");
        arr.add("element2");
        elements = new ListProperty(arr);
        property1 = new StringProperty("this is a string property");
        color = new StringProperty("Green");
        oneline_demo0 = new StringProperty("context_oneline0");
        oneline_demo1 = new StringProperty("context_oneline1");
        multiline = new ListProperty(arr);
        arr = new ArrayList<>(arr);
        arr.add("element3");
        multiline_long = new ListProperty(arr);
    }
    
    
}
