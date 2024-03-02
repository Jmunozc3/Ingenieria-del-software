package Launcher;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileInputStream;

public class printfile {

    public void EscribirEnText(String prueba,String tex){


        FileWriter fichero = null;
        PrintWriter pw = null;

        try
        {
            fichero = new FileWriter(prueba,false);
            pw = new PrintWriter(fichero);
            pw.flush();
            //System.out.println("Que quiere hacer?");
            pw.println(tex);
            //pw.println("hola");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }


    }
}