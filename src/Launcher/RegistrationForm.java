package Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;


    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        //setMinimumSize(new Dimension(540, 474));
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();


                //Escribir archivo start

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

    private void registerUser(){

        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassworld = String.valueOf(pfConfirmPassword.getPassword());

        if(name.isEmpty() || email.isEmpty()|| phone.isEmpty() || address.isEmpty() || password.isEmpty()){

            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirmPassworld)){

            JOptionPane.showMessageDialog(this, "Confirm password does not match", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDataBase(name, email, phone, address, password);
        if(user !=null){

            dispose();




            //Escritura en texto start
            printfile pe = new printfile();


            String[] opciones = {"1", "2","3", "4","5", "6","7", "8","9", "10","11", "12","13"};
            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "¿Cuantas horas duermes diariamente?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
            if (seleccion < 8 ) {
                pe.EscribirEnText("resources/recomendacionSueño.txt","Debes dormir más, ya que tus horas semanales son menores de 8 diarias de media:\n" +
                        "\n" +
                        "Aquí hay algunas recomendaciones para ayudar a dormir mejor:\n" +
                        "\n" +
                        "Mantén un horario de sueño consistente: Intenta acostarte y levantarte a la misma hora todos los días, incluso los fines de semana.\n" +
                        "\n" +
                        "Crea un ambiente relajante: Mantén tu habitación fresca, oscura y tranquila para promover el sueño. También puedes usar velas, difusores de aceites esenciales o música suave para crear un ambiente relajante.\n" +
                        "\n" +
                        "Limita la exposición a la luz brillante: La exposición a la luz brillante, especialmente antes de acostarte, puede dificultar conciliar el sueño. Trata de limitar la exposición a la luz brillante al menos una hora antes de dormir.\n" +
                        "\n" +
                        "Evita la cafeína y el alcohol: La cafeína y el alcohol pueden interferir en la calidad del sueño. Intenta evitar estas sustancias por la tarde y por la noche.\n" +
                        "\n" +
                        "Haz ejercicio regularmente: El ejercicio puede ayudar a mejorar la calidad del sueño, pero intenta hacerlo al menos unas horas antes de acostarte.\n" +
                        "\n" +
                        "Practica la relajación: Prueba técnicas de relajación, como la meditación o la respiración profunda, para reducir el estrés y promover el sueño.\n" +
                        "\n" +
                        "Evita la tecnología antes de dormir: La luz azul emitida por dispositivos electrónicos, como teléfonos inteligentes, tabletas y computadoras, puede interferir en la calidad del sueño. Trata de evitar usar estos dispositivos antes de acostarte.\n" +
                        "\n" +
                        "¡Espero que estas recomendaciones te ayuden a dormir mejor!");


            } else if (seleccion > 10) {
                pe.EscribirEnText("resources/recomendacionSueño.txt","Dormir más de 10 horas diarias puede tener efectos tanto positivos como negativos en la salud, dependiendo de la persona y de las circunstancias que rodean su sueño. Algunas de las posibles consecuencias de dormir más de 10 horas diarias son:\n" +
                        "\n" +
                        "Fatiga diurna: Si duermes más de lo necesario, es posible que te sientas cansado y somnoliento durante el día.\n" +
                        "\n" +
                        "Problemas de memoria y concentración: Dormir demasiado puede afectar negativamente la memoria y la capacidad de concentración, lo que puede interferir con la productividad y el rendimiento laboral.\n" +
                        "\n" +
                        "Aumento de peso: Algunos estudios han encontrado una asociación entre dormir demasiado y el aumento de peso, posiblemente porque el exceso de sueño reduce la cantidad de tiempo que se dedica a actividades físicas y aumenta la producción de hormonas que aumentan el apetito.\n" +
                        "\n" +
                        "Problemas emocionales: Dormir demasiado puede aumentar el riesgo de depresión y otros problemas emocionales, aunque la relación entre el sueño y el estado de ánimo es compleja y puede variar de una persona a otra.\n" +
                        "\n" +
                        "Sin embargo, es importante tener en cuenta que las necesidades de sueño varían de una persona a otra y que algunas personas necesitan más de 10 horas de sueño por noche para sentirse descansadas y en óptimas condiciones. Si te sientes bien y tienes suficiente energía durante el día, es posible que dormir más de 10 horas diarias no tenga consecuencias negativas para ti. Sin embargo, si experimentas síntomas como fatiga diurna o problemas emocionales, es posible que debas hablar con un profesional de la salud para determinar si el exceso de sueño es un problema para ti.\n" +
                        "\n");


            }  else if (seleccion >= 8 && seleccion <= 10 ) {
                pe.EscribirEnText("resources/recomendacionSueño.txt","Duermes las hora correctas. ¡sigue así!");


            }else {
                System.out.println("No seleccionaste ninguna opción");
            }


            //Texto end

            dispose();
            CalendarClass calendarClass = new CalendarClass();
            calendarClass.setVisible(true);

            //Escritura en texto ends
        }else{
            JOptionPane.showMessageDialog(this, "Failed to register new user", "Try again", JOptionPane.ERROR_MESSAGE);
        }

    }

    public User user;
    private User addUserToDataBase(String name, String email, String phone, String address, String password){
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/MyLogin?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        try{
            //Conexion a base de datos
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, email, phone, address, password)" + "VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);


            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }
            stmt.close();
            conn.close();

        }catch(Exception e){

            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args){
        RegistrationForm myForm = new RegistrationForm(null);
        User user = myForm.user;


        if(user != null){
            System.out.println("Succesfull registration of: " + user.name);
        }else{
            System.out.println("Registration canceled");
        }
    }
}
