package StudMan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionForm implements ActionListener {
    private JLabel usenameLabel;
    private JPanel selectPanel;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addRecButton;
    private JButton displyButton;
    private JFrame optF;

    public SelectionForm(String username){
        JFrame optionFrame = new JFrame("Option");
        optF = optionFrame;
        optionFrame.setContentPane(selectPanel);
        optionFrame.pack();
        optionFrame.setResizable(false);
        usenameLabel.setText("Hello, "+username);
        optionFrame.setSize(800,800);
        optionFrame.setVisible(true);
        optionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        updateButton.addActionListener(this);
        updateButton.setActionCommand("update");

        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("delete");

        addRecButton.addActionListener(this);
        addRecButton.setActionCommand("add");

        displyButton.addActionListener(this);
        displyButton.setActionCommand("display");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String res = (String) e.getActionCommand();

        switch (res){
//            case "add" : new AddRecord(optF);break;
            case "display" : break;
            case "update" : break;
            case "delete" : break;
        }
    }
}
