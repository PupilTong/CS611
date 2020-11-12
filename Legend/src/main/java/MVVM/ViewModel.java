
package MVVM;

public interface ViewModel {

    String refresh();
    void viewModified(String propertyName,String value);
    void setView(View v);
    
}
