package MVVM;

import java.util.Set;
/**
 * View
 */
public interface View {
    /**
     * set the viewModel will be binded to this view
     * 
     * @param viewModel
     */
    public void setViewModel(ViewModel viewModel) ;
    /**
     * update a property by a ViewModel
     * @param propertyName property name
     * @param value property value
     * @return isSuccess
     */
    boolean Update(String propertyName, String value);
    /**
     * a property modified by UI management process
     * @param propertyName property name
     * @param value value
     */
    void Modify(String propertyName, String value);
    /**
     * get property's current value;
     * @param propertyName
     * @return value
     */
    String getValue(String propertyName);
    /**
     * get View source code
     * @return
     */
    //String getView();
    /**
     * get view phrased
     * @return view rendered 
     */
    String renderView();
    /**
     * get the View height
     * @return height
     */
    int getHeight();
    /**
     * get a set of property
     * @return
     */
    Set<String> getPropertySet();
    /**
     * get the View width
     * @return width
     */
    int getWidth();
}
