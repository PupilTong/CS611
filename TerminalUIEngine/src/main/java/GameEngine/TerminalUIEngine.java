package GameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import MVVM.UIcomponment;

public class TerminalUIEngine implements UIEngine {
    InputStream input;
    OutputStream output;
    Scanner iScanner;
    Scanner scanner;
    List<UIcomponment> uiStack;
    Timer uiThread;
    TimerTask uiTask;
    HashSet<String> requiredInputs;
    String bottomMessage;
    String inputMessage;
    public static final int messageHoldingTime = 3000;//3s
    public static final int refreshTime = 50;//3s
    int messageTime = 0;

    public TerminalUIEngine(InputStream input, OutputStream output) {
        this.input = input;
        this.iScanner = new Scanner(input);
        this.output = output;
        this.scanner = new Scanner(input);
        this.uiStack = new ArrayList<>();
        this.requiredInputs = new HashSet<>();
        this.bottomMessage = "";
        this.inputMessage = "";
        this.uiTask = new TimerTask() {
            @Override
            public void run() {
                UITaskRun();
            }
        };
        this.uiThread = new Timer();
        this.uiThread.schedule(uiTask, 0, TerminalUIEngine.refreshTime);// 20hz
    }

    private void UITaskRun() {
        uiRefresher();
    }

    private void uiRefresher() {
        System.out.print("\033[H\033[2J");
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.flush();
        String currentComponString = "";
        //clear console
        if(uiStack.size()!=0){
           currentComponString = uiStack.get(uiStack.size() - 1).toString();
        }
        currentComponString += "\n\n" + this.bottomMessage;
        currentComponString += "\n\n" + this.inputMessage;
        System.out.print(currentComponString);
        messageTime -= TerminalUIEngine.refreshTime;
        if(messageTime<0){
            bottomMessage = "";
        }
    }
    public void showUIComponment(UIcomponment c){
        this.uiStack.add(c);
    }
    /**
     * show componment now and remove duplicate componment
     * @param c componment
     */
    public void moveTop(UIcomponment c){
        for(int i=0;i<this.uiStack.size();i++){
            UIcomponment uicStackElement = this.uiStack.get(i);
            if(uicStackElement==c){
                this.uiStack.remove(i);
                if(this.uiStack.get(this.uiStack.size())!=uicStackElement){
                    this.uiStack.add(uicStackElement);
                }
                //move to top
            }
        }
    }

    @Override
    public void closeAll(UIcomponment c) {
        this.uiStack.remove(c);

    }
    public String requestInput(String message,String ... options){
        this.requiredInputs.clear();
        String bottomMessage = message + "(";
        for(String option:options){
            requiredInputs.add(option);
            bottomMessage += option + ",";
        }
        bottomMessage = bottomMessage.substring(0,bottomMessage.length() - 1);
        bottomMessage +=")\n";
        this.inputMessage = bottomMessage;
        String catahedString = "";
        if(options.length!=0){
            while(!requiredInputs.contains(catahedString)){
                catahedString = iScanner.nextLine();
            }
            /**
             * while (true) {
                this.inputMessage = bottomMessage + catahedString;
                char oneKeyinput = '\0';
                try {
                    oneKeyinput = (char) input.read();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    oneKeyinput = '\0';
                }
                if(oneKeyinput=='\n'){
                    if(!requiredInputs.contains(catahedString)){
                        break;
                    }
                }
                else if(oneKeyinput=='\b'){
                    if(catahedString.length()>0){
                        catahedString = catahedString.substring(0,catahedString.length());
                    }
                }
                else if(oneKeyinput=='\0'){
                    //do nothing
                }
                else{
                    catahedString +=oneKeyinput;
                }
            }
             */
        }
        else{
            catahedString = iScanner.nextLine();
        }
        this.inputMessage ="";
        return catahedString;
    }
    public void showMessage(String m){
        this.messageTime = TerminalUIEngine.messageHoldingTime;
        bottomMessage = m;
    }
    public void stop(){
        this.uiTask.cancel();
    }
}
