/* 
 *File Prologue
 *Author: Hoseok Lee
 *CMIS 242
 *Project: GUIBuilder
 *Purpose: Build the GUI for the project
 *Date: 12/16/2017
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;  

public class GUIBuilder extends JFrame implements ActionListener {
    private JLabel printId;
    private JLabel printName;
    private JLabel printMajor;
    private JLabel printSelection;
    private JTextField idTf;
    private JTextField nameTf;
    private JTextField majorTf;
    private JButton processBtn;
    private JComboBox selection;
    private String[] selectionList = {"Insert", "Delete", "Find", "Update"};
    private HashMap<Integer,Student> students = new HashMap<Integer,Student>();  
    
    // Constructor
    public GUIBuilder(String title) { 
        // Set GUI
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(150,280);
	setLayout(new FlowLayout());
	setLocationRelativeTo (null);
        
        // Text Fields
        idTf = new JTextField(10);
        nameTf = new JTextField(10);
        majorTf = new JTextField(10);
        
        // JLabels
        printId = new JLabel("ID:");
        printName = new JLabel("Name:");
        printMajor = new JLabel("Major:");
        printSelection = new JLabel("Choose Selection:");
        
        // Button
        processBtn = new JButton("Process Request");
        processBtn.addActionListener(this);
        
        // ComboBox
        selection = new JComboBox(selectionList);
        selection.setSelectedIndex(3);
        
        add(printId);
        add(idTf);
        add(printName);
        add(nameTf);
        add(printMajor);
        add(majorTf);
        add(printSelection);
        add(selection);
        add(processBtn);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Process Request")) {
            try {
                int id = Integer.parseInt(idTf.getText());
                String name = nameTf.getText();
                String major = majorTf.getText();
                Student student = new Student(name, major); // Creating a student

                String selected = (String) selection.getSelectedItem();           
                switch (selected) { //check for a match
                    case "Insert":
                        if (students.get(id) != null) 
                            JOptionPane.showMessageDialog(null, "The Student is already exist in the list.", "Insertion Error", JOptionPane.ERROR_MESSAGE);
                        else {
                            students.put(id, student); // Add student to the Hashmap
                            JOptionPane.showMessageDialog(null, "The Student added to the list.", "Student Insertion",  JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case "Delete":
                        if (students.get(id) != null) {
                            students.remove(id);   
                            JOptionPane.showMessageDialog(null, "The Student deleted from the list.", "Student Deletion",  JOptionPane.PLAIN_MESSAGE);
                        }
                        else 
                            JOptionPane.showMessageDialog(null, "The Student is not exist in the list.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    case "Find":
                        if (students.get(id) != null) 
                            JOptionPane.showMessageDialog(null, students.get(id).toString(), "Student Information",  JOptionPane.PLAIN_MESSAGE);     
                        else 
                            JOptionPane.showMessageDialog(null, "The Student is not exist in the list.", "Find Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    case "Update":
                        if (students.get(id) != null) {
                            // Grade
                            Object[] grades = {'A','B','C','D','F'};
                            char grade = (char)JOptionPane.showInputDialog(null, "Choose Grade: ", "Grade", JOptionPane.QUESTION_MESSAGE, null, grades, 'A');
                            // Credit
                            Object[] credits = {"2", "3", "4", "5", "6"};
                            int credit = Integer.parseInt((String)JOptionPane.showInputDialog(null, "Choose Credits: ", "Credits", JOptionPane.QUESTION_MESSAGE, null, credits, "2"));
                            // Update
                            students.get(id).courseCompleted(grade, credit);
                            JOptionPane.showMessageDialog(null, "The Student information has been updated.", "Student Information Update",  JOptionPane.PLAIN_MESSAGE);
                        }       
                        else 
                            JOptionPane.showMessageDialog(null, "The Student is not exist in the list.", "Update Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            }
            catch (NumberFormatException er) {
                // Check if input is numeric
                JOptionPane.showMessageDialog(null, "Error, not a number. Please try again.");
            }
        }
    }

    public static void main (String[] args) {       
        new GUIBuilder("Project 3").setVisible(true);
    }
}
