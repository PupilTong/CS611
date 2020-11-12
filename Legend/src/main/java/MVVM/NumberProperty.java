package MVVM;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 */
public class NumberProperty implements ModelProperty {

    protected Number value;

    @Override
    public Iterator<String> get() {
        ArrayList<String> r = new ArrayList<>();
        r.add(this.value.toString());
        return r.iterator();
    }

    @Override
    public void set(String value) {
        try{
            this.value = Integer.parseInt(value);
        }
        catch(Exception e){
            try{
                this.value = Double.parseDouble(value);
            }
            catch(Exception ee){

            }
        }

    }
    public void set(Number value){
        this.value = value;
    }
    public Number getValue(){
        return this.value;
    }
    @Override
    public String toString() {
        return value.toString();
    }
    public NumberProperty(){

    }
    public NumberProperty(Number num){
        this.value = num;
    }
    
}
