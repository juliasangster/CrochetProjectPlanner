package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneSquareEditTool extends EditTool {


    public OneSquareEditTool(ToolPanel toolPanel) {
        super(toolPanel);
    }

    @Override
    protected void createButton(ToolPanel toolPanel) {
        button = new JButton("One Square");
        button = styleButton(button);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new OneSquareToolActionListener());
    }

    private class OneSquareToolActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            toolPanel.setActiveTool(ToolPanel.ToolOptions.ONE_SQUARE);
        }
    }
}
