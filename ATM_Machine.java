/* 
 *File Prologue
 *Author: Hoseok Lee
 *CMIS 242
 *Project: ATM_Machine
 *Purpose: GUI version of ATM 
 *Date: 11/16/2017
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class ATM_Machine extends JFrame implements ActionListener {
    
    // Variables
    private JTextField amount; // Text field for amount of money
    private JRadioButton checkingBtn;
    private JRadioButton savingBtn;
    Account checking = new Account(100); // Checking Account
    Account saving = new Account(50); // Saving Account
    
    public ATM_Machine (String title) {
        //Set GUI
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(400,150);
	setLayout(new FlowLayout());
	setLocationRelativeTo (null);
        
        // Buttons
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.addActionListener(this);
        JButton depositBtn = new JButton("Deposit");
        depositBtn.addActionListener(this);
        JButton transferBtn = new JButton("Transfer to");
        transferBtn.addActionListener(this);
        JButton balanceBtn = new JButton("Balance");
        balanceBtn.addActionListener(this);
        checkingBtn = new JRadioButton("Checking");
        checkingBtn.setSelected(true);
        checkingBtn.addActionListener(this);
        savingBtn = new JRadioButton("Saving");
        savingBtn.addActionListener(this);
        ButtonGroup group = new ButtonGroup();
        group.add(checkingBtn);
        group.add(savingBtn);
        
        // Text Field
        amount = new JTextField(25);
        
        // Add
        add(withdrawBtn);
        add(depositBtn);
        add(transferBtn);
        add(balanceBtn);
        add(checkingBtn);
        add(savingBtn);
        add(amount);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Withdraw"))  {
            try {
                double numAmount;
                String text = amount.getText();
                numAmount = Double.parseDouble(text);
                if (numAmount % 20 !=0)
                    JOptionPane.showMessageDialog(null, "Error, not increments of $20 . Please try again."); // Check $20 increment
                else if (checkingBtn.isSelected()) {
                    checking.withdraw(numAmount);
                    JOptionPane.showMessageDialog(null, "Withdraw Succeeded.");
                }
                else {
                    saving.withdraw(numAmount);
                    JOptionPane.showMessageDialog(null, "Withdraw Succeeded.");
                }
            } 
            catch (NumberFormatException er) {
                // Check if input is numeric
                JOptionPane.showMessageDialog(null, "Error, not a number. Please try again.");
            }
            catch (InsufficientFunds ex) {
                JOptionPane.showMessageDialog(null, "Insufficient funds in the account");
            }
        }
        else if (e.getActionCommand().equals("Deposit")) {
            try {
                double numAmount;
                String text = amount.getText();
                numAmount = Double.parseDouble(text);
                if (checkingBtn.isSelected()) 
                    checking.deposit(numAmount);
                else 
                    saving.deposit(numAmount);
                JOptionPane.showMessageDialog(null, "Deposit Succeeded.");
            } 
            catch (NumberFormatException er) {
                // Check if input is numeric
                JOptionPane.showMessageDialog(null, "Error, not a number. Please try again.");
            }
        }
        else if (e.getActionCommand().equals("Transfer to")) {
            try {
                double numAmount;
                String text = amount.getText();
                numAmount = Double.parseDouble(text);
                if (checkingBtn.isSelected()) 
                    checking.transfer(numAmount,saving);
                else 
                    saving.transfer(numAmount,checking);
                JOptionPane.showMessageDialog(null, "Transfer Succeeded.");
            } 
            catch (NumberFormatException er) {
                // Check if input is numeric
                JOptionPane.showMessageDialog(null, "Error, not a number. Please try again.");
            }
            catch (InsufficientFunds ex) {
                JOptionPane.showMessageDialog(null, "Insufficient funds in the account");
            }
        }
        else if (e.getActionCommand().equals("Balance")) {
            if (checkingBtn.isSelected()) 
                JOptionPane.showMessageDialog(null, "Checking account balance is $" + checking.getBalance());
            else 
                JOptionPane.showMessageDialog(null, "Saving account balance is $" + saving.getBalance());             
        }
    }
    public static void main (String[] args) {       
        new ATM_Machine("ATM Machine").setVisible(true);
    }
}
