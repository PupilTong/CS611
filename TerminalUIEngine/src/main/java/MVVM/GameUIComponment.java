package MVVM;
import java.beans.PropertyChangeListener;
import java.util.List;

import GameEngine.GameEngine;
import GameEngine.UIEngine;
/**
 * Game UI componment, must have one view, one viewmodel and one model. Use factory to Instantiate this.
 * Use Game UI Engine show this componment.
 * TODO props feature is not finished.
 */
public class GameUIComponment implements UIcomponment {

    private View view;
    private ViewModel viewModel;
    private Model model;
    private List<ModelProperty> props;
    private UIEngine uie;
    private int index;
    @Override
    public void show() {
        this.uie.showUIComponment(this);
    }

    @Override
    public void close() {
        this.uie.closeAll(this);

    }

    @Override
    public int getHeight() {
        return view.getHeight();
    }

    @Override
    public int getWidth() {
        // TODO Auto-generated method stub
        return view.getWidth();
    }

    @Override
    public void setProps(List<ModelProperty> props) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPropsChangeLister(PropertyChangeListener listener) {
        // TODO Auto-generated method stub

    }
    GameUIComponment(Model m,View v, ViewModel vm,List<ModelProperty> props,UIEngine gameEngine){
        this.model = m;
        this.view = v;
        this.viewModel = vm;
        this.props = props;
        this.uie = gameEngine;
    }

    @Override
    public String getApperence() {
        return view.getrenderView();
    }
    @Override
    public String toString() {
        return getApperence();
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public ViewModel getViewModel() {
        return this.viewModel;
    }

    @Override
    public Model getModel() {
        return this.model;
    }
}
