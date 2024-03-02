package Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartForm extends JDialog{
    private JButton loginButton;
    private JButton registerButton;
    private JPanel startParent;
    private JButton closePlannerButton;

    public StartForm(JFrame parent) {

        super(parent);
        setTitle("Login");
        setContentPane(startParent);
        //setMinimumSize(new Dimension(450, 474));
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("login");
                dispose();
                LoginForm loginForm = new LoginForm(null);

                //loginForm.show();



            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("register");
                dispose();
                RegistrationForm registrationForm = new RegistrationForm(null);
            }
        });

        closePlannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        StartForm startForm = new StartForm(null);
    }
}
