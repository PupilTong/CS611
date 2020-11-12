package GameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MVVM.UIcomponment;

public interface UIEngine {
    void showUIComponment(UIcomponment c);
    void moveTop(UIcomponment c);
    void closeAll(UIcomponment c);
}
