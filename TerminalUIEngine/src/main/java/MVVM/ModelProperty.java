package MVVM;

import java.util.Iterator;
import java.util.UUID;
/**
 * Property in model. 
 * This interface is also used by refliection.
 */
public interface ModelProperty {
    /**
     * get values of property
     * @return Iterator of values
     */
    Iterator<String> get();
    /**
     * set property value
     * @param value value
     */
    void set(String value);
    /**
     * add viewmodel for hooking property changed event;
     * @param vm viewmodel
     */
    void addHook(ViewModel vm);
    /**
     * 
     * @param vm
     * @return
     */
    boolean removeHook(ViewModel vm);
}
