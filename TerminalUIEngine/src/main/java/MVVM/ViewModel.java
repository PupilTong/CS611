
package MVVM;

public interface ViewModel {

    /**
     * view uses this method to modify a property
     * @param propertyName property's name
     * @param value value of the property
     */
    void viewModified(String propertyName,String value);
    /**
     * set view of property.
     * @param v view
     */
    void setView(View v);
    /**
     * get property by propertyname
     * @param propertyName propertyname
     * @return null or modelproperty
     */
    ModelProperty getProperty(String propertyName);
    /**
     * eventHandler for property changed
     * @param p
     */
    void propertyChangedHandler(ModelProperty p);
    /**
     * add parameters as property;
     */
    void addProps(String propsName, ModelProperty property);
    
}
