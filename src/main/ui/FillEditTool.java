package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FillEditTool extends EditTool {
    public FillEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("Fill");
        button = styleButton(button);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new FillEditToolActionListener());
    }

    private class FillEditToolActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.FILL);
        }
    }
}