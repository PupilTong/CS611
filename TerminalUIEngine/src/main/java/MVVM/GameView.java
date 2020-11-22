
package MVVM;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GameEngine.TerminalColors;

/**
 * defined essential function for all gameviews except the template loading
 * method.
 */
public class GameView implements View {

    /**
     * key property name value list of property event handler
     */
    protected ViewModel viewModel;
    protected ArrayList<String> renderedView;
    protected int height;
    protected int width;
    /**
     * View Templete
     */
    protected List<String> template = new ArrayList<>();



    public GameView(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while(s.hasNextLine()){
            this.template.add(s.nextLine());
        }
        s.close();
        this.height = template.size();
        this.width = this.template.get(0).length();
    }

    /**
     * set the viewModel will be binded to this view
     * 
     * @param viewModel
     */
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public String getrenderView() {
        Iterator<String> it = getrenderViewIterator();
        String view = "";
        while(it.hasNext()){
            view += it.next() +"\n";
        }
        return view;
    }
    @Override
    public Iterator<String> getrenderViewIterator() {
        return this.renderedView.iterator();
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
        viewModel.viewModified(propertyName, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updated() {
        this.renderedView = this.renderViewLines();
    }

    /**
     * get view rendered by lines
     * @return lines
     */
    private ArrayList<String> renderViewLines() {
        Pattern ontimePropertyPattern = Pattern.compile("\\(\\w*\\)");
        ArrayList<String> finalResult = new ArrayList<>();
        // replace all replaceable braces
        ArrayList<String> intermid_result = new ArrayList<>();
        for (String line : this.template) {
            // matchs {{}}
            Matcher m = ontimePropertyPattern.matcher(line);

            while (m.find()) {
                String propertyName = m.group();
                ModelProperty property = this.viewModel.getProperty(propertyName.substring(1,propertyName.length()-1));
                if (property != null) {
                    Iterator<String> propertyGotIterator = property.get();
                    if (propertyGotIterator.hasNext()) {
                        line = line.replace(propertyName, propertyGotIterator.next());
                    }
                    else{
                        line = line.replace(propertyName, "");
                    }
                }
            }
            intermid_result.add(line);
        }

        //process {{}} - block text
        Pattern braces = Pattern.compile("\\{\\{\\w*\\}\\}");
        ArrayList<String> intermid_result2 = new ArrayList<>();
        for (String line : intermid_result) {
            // matchs {{}}
            Matcher m = braces.matcher(line);
            ArrayList<String> tobeReplacedElement = new ArrayList<>();
            ArrayList<Iterator<String>> replacingToken = new ArrayList<>();
            ArrayList<Boolean> replaceable = new ArrayList<>();

            while (m.find()) {
                String propertyName = m.group();
                ModelProperty property = this.viewModel.getProperty(propertyName.substring(2,propertyName.length()-2));
                if (property != null) {
                    Iterator<String> propertyGotIterator = property.get();
                    replaceable.add(true);
                    tobeReplacedElement.add(propertyName);
                    replacingToken.add(propertyGotIterator);
                }
            }


            if(replaceable.size()==0){//didn't found
                intermid_result2.add(line);

            }
            Boolean replaceableMaped = true;
            while(replaceableMaped){
                String newLine = new String(line);
                for(int i=0;i<tobeReplacedElement.size();i++){
                    String currentElement = tobeReplacedElement.get(i);
                    Iterator<String> currentToken = replacingToken.get(i);
                    if(currentToken.hasNext()){
                        newLine = newLine.replace(currentElement, currentToken.next());
                    }
                    else{
                        newLine = newLine.replace(currentElement, "");
                        replaceable.set(i, false);
                    }
                }
                replaceableMaped = replaceable.stream().reduce(false,(x,y)->x|y);
                if(replaceableMaped)
                    intermid_result2.add(newLine);
            }
        }

        Iterator<String> loopExpanded = this.processFor(intermid_result2.iterator(), null, null);


        Pattern angleBrackets = Pattern.compile("<(\\w|,|=| )*>");
        while (loopExpanded.hasNext()) {
            String line = loopExpanded.next();
            Matcher m = angleBrackets.matcher(line);
            while (m.find()) {
                String foundElement = m.group();
                foundElement = foundElement.substring(1,foundElement.length()-1);
                String[] angleElements = foundElement.split(",");
                String length = Integer.toString(foundElement.length());
                String[] setting = { null,null , null };// color,max,min
                for (int i = 1; i < angleElements.length; i++) {
                    String[] kv = angleElements[i].toUpperCase().split("=");
                    int index = Integer.MAX_VALUE;
                    if (kv[0].equals("COLOR")) {
                        index = 0;
                    } else if (kv[0].equals("MAX")) {
                        index = 1;
                    } else if (kv[0].equals("MIN")) {
                        index = 2;
                    }
                    String value = length;
                    if (index < setting.length) {
                        if (kv.length == 2) {
                            value = kv[1];
                        }
                    }
                    setting[index] = value;
                }
                String color;
                // get color
                try {
                    color = (String) TerminalColors.class.getField(setting[0]).get(null);
                } catch (Exception e) {
                    color = TerminalColors.Default;//default
                }
                if(setting[1]!=null){
                    //max
                    if(angleElements[0].length()>Integer.parseInt(setting[1])){
                        angleElements[0] = angleElements[0].substring(0, Integer.parseInt(setting[1]));
                    }
                }
                if(setting[2]!=null){
                    //min
                    while(angleElements[0].length()<Integer.parseInt(setting[2])){
                        angleElements[0] +=" ";
                    }
                }
                String finalString = color + angleElements[0] + TerminalColors.Default ;
                line = line.replace("<"+ foundElement +">", finalString);
            }
            finalResult.add(line);
        }
        return finalResult;
    }

    /**
     * [e:element]
     * 
     * @param intermid_result   [e:element]
     * @param iteratingProperty element
     * @param iteratorName      e
     * @return
     * @throws Exception
     */
    private Iterator<String> processFor(Iterator<String> intermid_result, Iterator<String> iteratingPropertyValue,
            String iteratorName)  {
        Pattern brackets = Pattern.compile("\\[(\\w|\\:)*\\]");
        ArrayList<String> loopResult = new ArrayList<>();
        while(intermid_result.hasNext()){
            String line = intermid_result.next();
            //everythings except the first [] will be ignored
            Matcher m = brackets.matcher(line);
            if(m.find()){
                String bracketElement = m.group();
                if(bracketElement.equals("[endfor]")){
                    break;
                }
                else{
                    String[] forSetting = bracketElement.substring(1,bracketElement.length()-1).split(":");
                    if(forSetting.length==2){
                        ModelProperty nextLeveProperty = viewModel.getProperty(forSetting[1]);
                        Iterator<String> nextLevelPropertyIterator;
                        if(nextLeveProperty!=null){
                            nextLevelPropertyIterator = nextLeveProperty.get();
                        }
                        else{
                            ArrayList<String> emptyList = new ArrayList<>();
                            nextLevelPropertyIterator = emptyList.iterator();
                        }
                        String nextLevelIteratorName = forSetting[0];
                        Iterator<String> it = processFor(intermid_result,nextLevelPropertyIterator,nextLevelIteratorName);
                        while(it.hasNext()){
                            loopResult.add(it.next());
                        }

                    }
                    else{
                        loopResult.add("[e:element] illegal. [e:e:?]");
                        //split ':' result should be length = 2;
                    }
                }

            }
            else{
                loopResult.add(line);
            }
        }

        if(iteratingPropertyValue==null){
            //no loop
            return loopResult.iterator();
        }
        else{
            //iterating the result
            ArrayList<String> result = new ArrayList<>();
            String forElementValue="";
            while(iteratingPropertyValue.hasNext()){
                forElementValue = iteratingPropertyValue.next();
                for(String line:loopResult){
                    result.add(line.replace("("+ iteratorName +")", forElementValue));
                }
            }
            return result.iterator();
        }

    }

    
}
