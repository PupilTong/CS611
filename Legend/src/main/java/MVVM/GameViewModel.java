package MVVM;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;

import jdk.internal.org.jline.utils.Log;

import java.util.Iterator;

/**
 * ViewModel processes Two-way binding
 * View->>Model is not tested since it's not supported in terminal.
 */
public class GameViewModel implements ViewModel {

    protected HashMap<ModelProperty,String> propertiesName;
    protected HashMap<String,ModelProperty> propertiesBook;
    protected Model model;
    protected View view;

    @Override
    public void setView(View v) {
        this.view = v;
    }
    @Override
    public String refresh() {
        Set<ModelProperty> propertySet = propertiesName.keySet();
        for(ModelProperty m:propertySet){
            if(m!=null){

                String v="";
                try{

                    Iterator<String> it = m.get();
                    if(it!=null)
                        while(it.hasNext()){
                            v+=it.next()+"\n";
                        }
                    //remove the last new line
                    if(v.length()>0)
                        v=v.substring(0,v.length()-1);
                }
                catch(Exception e){
                    
                }
                view.Update(propertiesName.get(m), v);
            }
        }
        return view.renderView();

    }
    @Override
    public void viewModified(String propertyName,String value){
        this.propertiesBook.get(propertyName).set(value);
    }
    GameViewModel(Model model,View v){
        this.model = model;
        this.view = v;
        this.propertiesBook = new HashMap<>();
        this.propertiesName = new HashMap<>();
        ArrayList<Field> fields = new ArrayList<>();
        Class tempClass = model.getClass();
        while (tempClass != null) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); 
        }
        for(Field f : fields){
            if(ModelProperty.class.isAssignableFrom(f.getType())){
                try{
                    f.setAccessible(true);
                    ModelProperty property = (ModelProperty) f.get(model);
                    if(property!=null){
                        this.propertiesBook.put(f.getName(), property);
                        this.propertiesName.put(property, f.getName());

                    }
                }
                catch(Exception e){
                    //Log.debug(e);
                    e.printStackTrace();
                }
            }
        }
    }
    
}
