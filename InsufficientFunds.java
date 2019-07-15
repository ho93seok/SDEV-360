/* 
 *File Prologue
 *Author: Hoseok Lee
 *CMIS 242
 *Project: InsufficientFunds
 *Purpose: Custom exception class for insufficient funds
 *Date: 11/16/2017
 */

public class InsufficientFunds extends Exception{
    public InsufficientFunds (String msg) {
        super(msg);  
    }
}
