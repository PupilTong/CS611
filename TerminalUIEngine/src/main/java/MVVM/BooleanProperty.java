package MVVM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Boolean type Model property.
 */
public class BooleanProperty implements ModelProperty {

    private Boolean value;
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
        for(ViewModel vm:vmSet){
            vm.propertyChangedHandler(this);
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

    HashSet<ViewModel> vmSet = new HashSet<>();
    @Override
    public void addHook(ViewModel vm) {
        vmSet.add(vm);
    }

    @Override
    public boolean removeHook(ViewModel vm) {
        if(vmSet.contains(vm)){
            vmSet.remove(vm);
            return true;
        }
        return false;
    }
}
