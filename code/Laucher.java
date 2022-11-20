package code;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import code.curriculum.Curriculum;
import code.curriculum.KnowledgePart;
import code.curriculum.Subject;

public class Laucher {
    public static void main(String[] args) {
        Application application = new Application();
        application.setVisible(true);
    }
}
