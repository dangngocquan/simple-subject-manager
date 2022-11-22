package code.dialog;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import java.awt.Component;

public class DialogList {
    private Component parentComponent;
    private String message;
    private String title;
    private int messageType = JOptionPane.PLAIN_MESSAGE;
    private Icon icon = null;
    private Object[] selectionValues;
    private Object initialValue;

    public DialogList(Component parentComponent,
            String message, String title, Object[] selectionValues, Object initalValue) {
        this.parentComponent = parentComponent;
        this.message = message;
        this.title = title;
        this.selectionValues = selectionValues;
        this.initialValue = initalValue;
    }

    public String run() {
        return (String) JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues,
                initialValue);
    }
}
