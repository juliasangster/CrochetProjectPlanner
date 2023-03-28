package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RowFillEditTool extends EditTool {

    public RowFillEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("Fill Row");
        button = styleButton(button);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new RowFillEditToolActionListener());
    }

    private class RowFillEditToolActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.ROW);
        }
    }
}
