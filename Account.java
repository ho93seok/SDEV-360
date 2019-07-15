/* 
 *File Prologue
 *Author: Hoseok Lee
 *CMIS 242
 *Project: Account
 *Purpose: Create an account
 *Date: 11/16/2017
 */
public class Account {
    // Variables
    double balance;
    private int numOfWithdraw = 0;
    
    // Constructor
    Account(double balance) {
        this.balance = balance;
    }
    
    // Withdraw method
    public void withdraw(double amount) throws InsufficientFunds {
        if (balance < amount)
            throw new InsufficientFunds("Insufficient funds in the account");
        else {
            balance -= amount;
            numOfWithdraw++;
        }
        if (numOfWithdraw >= 4)
            balance -= 1.5;
    }
    
    // Deposit method
    public void deposit(double amount) {
        balance += amount;
    }
    
    // Transfer method
    public void transfer(double amount, Account another) throws InsufficientFunds  {
        if (another.balance < amount)
            throw new InsufficientFunds("Insufficient funds in the account");
        else {
            balance += amount;
            another.balance -= amount;
        }
    }
    
    // getBalance method
    public double getBalance() {
        return balance;
    }
}
