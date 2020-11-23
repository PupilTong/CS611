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


import java.util.Iterator;

/**
 * ViewModel processes Two-way binding
 * View->>Model is not tested since it's not supported in terminal.
 */
public class GameViewModel implements ViewModel {

    protected HashMap<String,ModelProperty> properties = new HashMap<>();
    protected HashMap<ModelProperty,String> propertiesName = new HashMap<>();
    protected Model model;
    protected View view;

    @Override
    public void setView(View v) {
        this.view = v;
    }
    @Override
    public void viewModified(String propertyName,String value){
        this.properties.get(propertyName).set(value);
    }
    /**
     * using Reflection
     * iterating all fields in Model and model's superclass
     * Get all modelspropertys and their names
     */
    GameViewModel(Model model,View v){
        this.model = model;
        this.view = v;
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
                        this.properties.put(f.getName(), property);
                        this.propertiesName.put(property, f.getName());
                        property.addHook(this);
                    }
                }
                catch(Exception e){
                    //Log.debug(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelProperty getProperty(String propertyName) {
        return this.properties.get(propertyName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChangedHandler(ModelProperty p) {
        if(propertiesName.containsKey(p)){
            view.updated();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProps(String propsName, ModelProperty property) {
        this.properties.put(propsName, property);
        this.propertiesName.put(property, propsName);
        property.addHook(this);
    }
    
}
