package MVVM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 */
public class NumberProperty implements ModelProperty {

    private Number value;

    @Override
    public Iterator<String> get() {
        ArrayList<String> r = new ArrayList<>();
        r.add(this.value.toString());
        return r.iterator();
    }

    @Override
    public void set(String value) {
        try{
            this.value = Integer.parseInt(value);
        }
        catch(Exception e){
            try{
                this.value = Double.parseDouble(value);
            }
            catch(Exception ee){

            }
        }
        notifyHandler();

    }
    public void set(Number value){
        this.value = value;
        notifyHandler();

    }
    public Number getValue(){
        return this.value;
    }
    @Override
    public String toString() {
        return value.toString();
    }
    public NumberProperty(){

    }
    public NumberProperty(Number num){
        this.value = num;
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
