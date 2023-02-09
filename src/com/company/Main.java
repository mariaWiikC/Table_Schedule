package com.company;
// Imported packages
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
    // Frame
    JFrame frame;
    // Table
    JTable table;
    // Button
    JButton button;

    // Constructor
    Main()
    {
        // Frame initialization
        frame = new JFrame();
        // Frame Title
        frame.setTitle("Schedule");

        // Create new Button and the position/size
        button = new JButton("Export schedule to Text File");
        button.setBounds(100, 250, 250, 30);

        // Column Names
        String[] days = { "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        // Data to be displayed (time + empty cells) - 2D array (rows and columns)
        String[][] timeAndClass = {
                {"08:30", "", "", "", "", ""},
                {"09:15", "", "", "", "", ""},
                {"10:15", "", "", "", "", ""},
                {"11:00", "", "", "", "", ""},
                {"11:45", "", "", "", "", ""},
                {"12:45", "", "", "", "", ""},
                {"13:30", "", "", "", "", ""},
                {"14:30", "", "", "", "", ""},
                {"16:00", "", "", "", "", ""},

        };

        // Structure of the Schedule (Text File)
        // Action when the button is pressed
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    // Create a file in this location with the name "Schedule"
                    File file = new File("C:\\Users\\wiikcandido.maria\\OneDrive - Suomalaisen Yhteiskoulun Osakeyhtiö\\Työpöytä\\Schedule.txt");
                    System.out.println(System.getProperty("user.home"));
                    if (!file.exists())
                        file.createNewFile();
                    // Write character data to file - Creates new              This subclass will write the data and temporarily store it
                    try(FileWriter fw = new FileWriter(file.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw))
                    {
                        // Header with "Time" + days of the week
                        for (int d = 0; d < days.length; d++)
                            bw.write(days[d] + "    ");

                        // writes a line so organize the schedule
                        bw.write("\n__________________________________________________________________\n");

                        // Transfer of table data cell-by-cell to text file
                        for (int l = 0; l < timeAndClass.length; l++)
                        {
                            for (int c = 0; c < timeAndClass[1].length; c++)
                            {
                                int wordLength = table.getModel().getValueAt(l, c).toString().length();
                                if (c == 0)
                                    bw.write(table.getModel().getValueAt(l, c) + "   ");
                                else
                                {
                                    bw.write(table.getModel().getValueAt(l, c) + "   ");
                                    // the if's are there to organize the words in the schedule according to their length
                                    if (table.getModel().getValueAt(l, c).equals(""))
                                        bw.write("        ");
                                    else if (wordLength == 2)
                                        bw.write("       ");
                                    else if (wordLength == 3)
                                        bw.write("      ");
                                    else if (wordLength == 4)
                                        bw.write("     ");
                                    else if (wordLength == 5)
                                        bw.write("    ");
                                    else if (wordLength == 6)
                                        bw.write("   ");
                                    else if (wordLength == 7)
                                        bw.write("  ");
                                    else if (wordLength > 7)
                                        bw.write(" ");
                                }
                            }
                            bw.write("\n__________________________________________________________________\n");
                        }
                        // Creates window with message
                        JOptionPane.showMessageDialog(null, "Schedule Exported");
                    }
                    catch(IOException exc)
                    {
                        exc.printStackTrace();
                        System.out.println("Got exception: " + exc);
                        System.exit(1);
                    }
                }
                catch(Exception ex)
                {
                    // Detects the exact line in which the exception happened
                    ex.printStackTrace();
                    System.out.println("Got exception: " + ex);
                    System.exit(1);
                }
            }
        });

        frame.add(button);

        // Initializing JTable
        table = new JTable(timeAndClass, days);
        // Set bounds to table
        table.setBounds(3, 5, 300, 400);

        // adds table to scrollable panel (JScrollPane)
        JScrollPane pane = new JScrollPane(table);
        // adds scrollable panel to frame
        frame.add(pane);
        // Window Size according to what is displayed
        frame.pack();
        // signal end by clicking "X"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Window Visible = true
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        try(Scanner input = new Scanner(System.in))
        {
            System.out.println("Write 'open' to create your schedule.");
            String resultInput = input.next();
            if (resultInput.equals("open"))
            {
                new Main();
                System.out.println("Fill in the table according to your schedule.");
            }
            else
                System.out.println("Schedule not opened.");
        }
        catch(Exception e)
        {
            System.out.println("Got exception: " + e);
            System.exit(1);
        }
    }
}