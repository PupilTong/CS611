package MVVM;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Boolean type Model property.
 */
public class BooleanProperty implements ModelProperty {

    Boolean value;
    @Override
    public Iterator<String> get() {
        ArrayList<String> r = new ArrayList<>();
        r.add(value.toString());
        return r.iterator();
    }

    @Override
    public void set(String value) {
        value = value.toUpperCase();
        if(value.equals("TRUE")){
            this.value = (Boolean) true;
        }
        else if(value.equals("FALSE")){
            this.value = (Boolean)false;
        }

    }
    BooleanProperty(){

    }
    public BooleanProperty(Boolean v){
        this.value = v;
    }
    public Boolean getValue() {
        return value;
    }
    @Override
    public String toString() {
        return this.value.toString();
    }
}
