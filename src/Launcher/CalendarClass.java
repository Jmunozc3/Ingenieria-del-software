package Launcher;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.event.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Item;
import com.mindfusion.scheduling.model.ItemEvent;
import com.mindfusion.scheduling.model.RecurrenceState;
import com.mindfusion.scheduling.standardforms.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.EnumSet;






public class CalendarClass extends JFrame{


    public void CalendarInit(){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{

                    new CalendarClass().setVisible(true);
                }
                catch (Exception exp)
                {

                }
            }
        });
    }
    protected CalendarClass(){



        // Configuración de la ventana principal sin bordes
        setUndecorated(false); // Sin bordes
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setSize(1000, 1000);
        setTitle("Java Swing Scheduling Library: Appointment Forms");

        //AppPanel appPanel = new AppPanel();
        calendar = new Calendar();
        //appPanel.mainPanel.add(calendar, BorderLayout.CENTER);
        getContentPane().add(calendar, BorderLayout.CENTER);

        calendar.beginInit();
        calendar.setCurrentView(CalendarView.WeekRange);
        calendar.getWeekRangeSettings().setHeaderStyle(EnumSet.of(WeekRangeHeaderStyle.Title));
        calendar.setDate(new DateTime(2022, 1, 1));
        calendar.setEndDate(new DateTime(2023, 12, 31));
        calendar.setCurrentTime(new DateTime(2023, 5, 10));
        calendar.getWeekRangeSettings().setDayOfWeekFormat(DayOfWeekFormat.Full);
        calendar.setEnableDragCreate(true);


        calendar.endInit();

        calendar.addCalendarListener(new CalendarAdapter(){

            private void showForm(Item item){

                AppointmentForm form = new AppointmentForm(calendar.getSchedule());
                form.setAppointment((Appointment)item );
                form.setVisible(true);

                form.addWindowListener(new WindowAdapter() {

                    @Override
                    public void windowClosed(WindowEvent we){

                        if(form.getDialogResult()== DialogResult.Remove){

                            if(item.getRecurrenceState() == RecurrenceState.Occurrence || item.getRecurrenceState() == RecurrenceState.Exception){
                                item.getRecurrence().markException(item, true);

                            }else{
                                calendar.getSchedule().getItems().remove(item);
                            }
                        }
                    }

                });

            }

            @Override
            public void itemClick(ItemMouseEvent e){

                showForm(e.getItem());
            }

            @Override
            public  void itemCreated(ItemEvent e){
                calendar.getSelection().reset();
                calendar.getSelection().add(e.getItem().getStartTime(), e.getItem().getEndTime());
                showForm(e.getItem());
            }

        });
    }
    private static final long serialVersionUID = 1L;
    private Calendar calendar;


}
