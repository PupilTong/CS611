package MVVM;

import java.beans.PropertyChangeListener;
import java.util.List;
/**
 * UIComponment
 * sub componment is hot implemented.
 * It is hard to locate subcomponment without CSS. :(
 */
public interface UIcomponment {
    /**
     * show this ui
     */
    public void show();
    /**
     * close this ui
     */
    public void close();
    /**
     * get the apperence
     * @return rendered view
     */
    public String getApperence();
    /**
     * get height
     * @return height
     */
    public int getHeight();
    /**
     * set width
     * @return width
     */
    public int getWidth();
    /**
     * like `props` in Vue
     * @param props props list
     */
    public void setProps(List<ModelProperty> props);
    /**
     * add a props change lister for all props
     * @param listener listener
     */
    public void setPropsChangeLister(PropertyChangeListener listener);
    public View getView();
    public ViewModel getViewModel();
    public Model getModel();
}
