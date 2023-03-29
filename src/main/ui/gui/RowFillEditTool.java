package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CLASS COMMENT: Class representing the editing tool that processes
//                full row color changes

public class RowFillEditTool extends EditTool {

    // EFFECTS: Constructs the row fill editing tool
    public RowFillEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  creates and styles the button for the fill row
    //           editing tool
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("Fill Row");
        button = styleButton(button);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  associates this button with an action listener that can
    //           process full row color changes
    protected void addListener() {
        button.addActionListener(new RowFillEditToolActionListener());
    }

    // CLASS COMMENT: Action listener fo the row fill editing tool
    private class RowFillEditToolActionListener implements ActionListener {

        @Override
        // MODIFIES: this, ToolPanel
        // EFFECTS:  passes a ROW tool option to the tool panel if button is pressed,
        //           which selects the fill row editing tool
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.ROW);
        }
    }
}
