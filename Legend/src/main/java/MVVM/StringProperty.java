package MVVM;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * {@inhertDoc}
 */
public class StringProperty implements ModelProperty {

    private String value;

    @Override
    public Iterator<String> get() {
        ArrayList<String> r = new ArrayList<>();
        r.add(this.value.toString());
        return r.iterator();
    }

    @Override
    public void set(String value) {
        this.value = value;

    }

    @Override
    public String toString() {
        return value;
    }
    public StringProperty(){
        this.value="";
    }
    public StringProperty(String s){
        this.value =s;
    }
    
}
