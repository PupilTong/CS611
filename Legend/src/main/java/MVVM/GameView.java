
package MVVM;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * defined essential function for all gameviews
 * except the template loading method.
 */
public abstract class GameView implements View {

    /**
     * key property name value list of property event handler
     */
    protected ViewModel viewModel;
    protected HashMap<String, String> propertyValue;
    protected int height;
    protected int width;
    /**
     * View Templete
     */
    protected List<String> template;

    protected abstract void initializeTemplate() throws FileNotFoundException;
    protected GameView() throws FileNotFoundException {
        this.template = new ArrayList<>();
        this.propertyValue = new HashMap<>();
        this.initializeTemplate();
        this.height = template.size();
        this.width = this.template.get(0).length();
        Pattern regex = Pattern.compile("\\{\\{\\w*\\}\\}");
        for(String line:template){
            Matcher m = regex.matcher(line);
            while (m.find()) {
                String find = m.group();
                // Log.debug("regex:" + find);
                this.propertyValue.put(find, find);
            }
        }
    }

    /**
     * set the viewModel will be binded to this view
     * 
     * @param viewModel
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean Update(String propertyName, String value) {
        propertyName = "{{" + propertyName +"}}";
        if (propertyValue.containsKey(propertyName)) {

            propertyValue.put(propertyName, value);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getPropertySet() {
        return this.propertyValue.keySet();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue(String propertyName) {
        return this.propertyValue.get(propertyName);
    }

    @Override
    public String renderView() {
        String phrased = "";
        for(String line:this.template){

            String pline = new String(line);
            for (String key : this.propertyValue.keySet()) {
                pline = pline.replace(key, this.propertyValue.get(key));
            }
            phrased += pline + "\n";
        }
        return phrased;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public void Modify(String propertyName, String value) {
        this.Update(propertyName, value);
        viewModel.viewModified(propertyName, value);
    }


    
}
