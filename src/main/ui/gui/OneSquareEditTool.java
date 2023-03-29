package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CLASS COMMENT: Class representing the editing tool that processes
//                single-square color changes

public class OneSquareEditTool extends EditTool {

    // EFFECTS: Constructs the one square editing tool
    public OneSquareEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  creates and styles the button for the one-square
    //           editing tool
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("One Square");
        button = styleButton(button);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  associates this button with an action listener that can
    //           process single-color changes
    protected void addListener() {
        button.addActionListener(new OneSquareToolActionListener());
    }

    // CLASS COMMENT: Action listener for the one-square editing tool
    private class OneSquareToolActionListener implements ActionListener {

        @Override
        // MODIFIES: this, toolPanel
        // EFFECTS:  passes a ONE_SQUARE tool option to the tool panel if button is
        //           pressed, which selects the one-square tool
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.ONE_SQUARE);
        }
    }
}
