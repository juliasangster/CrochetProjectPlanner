package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColumnFillEditTool extends EditTool {

    public ColumnFillEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("Fill Column");
        button = styleButton(button);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ColumnFillEditToolActionListener());
    }

    private class ColumnFillEditToolActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.COLUMN);
        }
    }
}
