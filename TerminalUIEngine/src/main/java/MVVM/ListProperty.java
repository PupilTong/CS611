package MVVM;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * ModelProperty for list property.
 */
public class ListProperty implements Iterable, ModelProperty {

    protected List<Object> list;
    @Override
    public Iterator<String> get() {
        ArrayList<String> r = new ArrayList<>();
        Iterator<Object> it = this.iterator();
        int index =0;
        while(it.hasNext()){
            //r.add("" + index + "." +it.next().toString());
            r.add(it.next().toString());
            index++;
        }
        return r.iterator();
    }

    @Override
    public void set(String value) {
        notifyHandler();
    }
    public void set(List<Object> list){
        this.list = list;
        notifyHandler();
    }

    @Override
    public Iterator<Object> iterator() {
        return list.iterator();
    }
    public List<Object> getList(){
        return this.list;
    }
    public ListProperty(List<?> iList){
        this.list = (List<Object>) iList;
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
