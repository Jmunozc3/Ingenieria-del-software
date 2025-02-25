package Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel bluePanel;
    private JPanel loginPanel;


    public LoginForm(JFrame parent){


        super(parent);

        setTitle("Login");
        setContentPane(loginPanel);
        //setMinimumSize(new Dimension(450, 474));
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        //loginPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setModal(true);
        setLocationRelativeTo(parent);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = getAuthenticatorUser(email, password);

                if(user != null){
                    dispose();
                    CalendarClass calendarClass = new CalendarClass();
                    calendarClass.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this, "Email or Password Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StartForm startForm = new StartForm(null);

            }
        });
        setVisible(true);
    }


    public User user;
    private User getAuthenticatorUser(String email, String password){

        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/MyLogin?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "root";
        //Conectado a la base de datos
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");

            }

            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;

    }


    public static void main(String[] args){
        LoginForm loginForm = new LoginForm(null);

        User user = loginForm.user;


        if (user != null) {
            System.out.println("Successful Authentication of: " + user.name);
            System.out.println("          Email: " + user.email);
            System.out.println("          Phone: " + user.phone);
            System.out.println("          Address: " + user.address);
        }
        else {
            System.out.println("Authentication canceled");
        }
    }
}
