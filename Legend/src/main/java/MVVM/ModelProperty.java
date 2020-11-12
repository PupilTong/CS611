package MVVM;

import java.util.Iterator;
/**
 * Property in model. 
 * This interface is also used by refliection.
 */
public interface ModelProperty {
    Iterator<String> get();
    void set(String value);
}
