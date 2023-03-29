package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CLASS COMMENT: Class representing the editing tool that processes
//                entire-graphghan color changes

public class FillEditTool extends EditTool {

    // EFFECTS: Constructs the fill editing tool
    public FillEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  creates and styles the button for the fill
    //           editing tool
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("Fill");
        button = styleButton(button);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  associates this button with an action listener that
    //           can process entire-graphghan color changes
    protected void addListener() {
        button.addActionListener(new FillEditToolActionListener());
    }

    // CLASS COMMENT: Action listener for the fill editing tool
    private class FillEditToolActionListener implements ActionListener {

        @Override
        // MODIFIES: this, toolPanel
        // EFFECTS:  passes a FILL tool option to the tool panel if button is
        //           pressed, which selects the fill editing tool
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.FILL);
        }
    }
}