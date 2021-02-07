package StudMan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SMSForm {
    private JFrame ref;
    private JPanel loginPanel;
    private JLabel login;
    private JLabel userLabel;
    private JTextField textField1;
    private JButton button1;
    private JLabel PassLabel;
    private JPasswordField passwordField1;

    public SMSForm(){
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char pass[] = new char[] {'1','2','3','4'};
                if(textField1.getText().equals("RamBorade") && Arrays.equals(pass,passwordField1.getPassword()))
                {
                    ref.dispose();
                    AddRecord addRecord = new AddRecord();
                }
                else{
                    JOptionPane.showMessageDialog(ref,"" +
                            "Enter correct Credentials");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame logFrame = new JFrame("Login");
        logFrame.setResizable(false);
        SMSForm smsForm = new SMSForm();
        smsForm.ref = logFrame;
        logFrame.setContentPane(smsForm.loginPanel);
        logFrame.pack();
        logFrame.setSize(500, 500);
        logFrame.setVisible(true);
        logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
