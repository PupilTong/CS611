package GameEngine;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;

import javax.xml.transform.Source;

public class GameEngine {
    UIEngine uiEngine;
    public static String resourcesDir;
    //Singleton
    public void initialUIEngine(InputStream input,OutputStream output){
        if(uiEngine==null){
            this.uiEngine = new TerminalUIEngine(input, output);
        }
    }
    public UIEngine getUiEngine(){
        return this.uiEngine;
    }
    public static void setRecourseDirectory(String path){
        GameEngine.resourcesDir = path;
    }
}
