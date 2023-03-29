package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CLASS COMMENT: Class representing the editing tool that processes
//                full column color changes

public class ColumnFillEditTool extends EditTool {

    // EFFECTS: Constructs the column fill editing tool
    public ColumnFillEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  creates and styles the button for the fill column
    //           editing tool
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("Fill Column");
        button = styleButton(button);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  associates this button with an action listener that can process
    //           full column color changes
    protected void addListener() {
        button.addActionListener(new ColumnFillEditToolActionListener());
    }

    // CLASS COMMENT: Action listener for the column fill editing tool
    private class ColumnFillEditToolActionListener implements ActionListener {

        @Override
        // MODIFIES: this, toolPanel
        // EFFECTS:  passes a COLUMN tool option to the tool panel if button is pressed,
        //           which selects the fill column editing tool
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.COLUMN);
        }
    }
}
