package MVVM;

import java.util.ArrayList;
import java.util.HashSet;
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
        notifyHandler();

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
    private void notifyHandler(){
        for(ViewModel vm:vmSet){
            vm.propertyChangedHandler(this);
        }
    }
    
}
