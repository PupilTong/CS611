package MVVM;

import java.util.ArrayList;
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
            r.add("" + index + "." +it.next().toString());
            index++;
        }
        return r.iterator();
    }

    @Override
    public void set(String value) {

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
}
