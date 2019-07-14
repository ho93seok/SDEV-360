/* 
 *File Prologue
 *Author: Hoseok Lee
 *CMIS 242
 *Project: Student
 *Purpose: Define student record
 *Date: 12/16/2017
 */

import java.text.DecimalFormat;

public class Student {
    // Variables
    private String name;
    private String major;
    private double totalCredit;
    private double qualityPts;
    protected static DecimalFormat formatter = new DecimalFormat("0.00"); // Format for GPA
    
    // Constructor
    Student (String name, String major) {
        this.name = name;
        this.major = major;
        totalCredit = 0;
        qualityPts = 0;
    }
    
    // courseCompleted
    public void courseCompleted (char grade, int credit) {
        double pts = 0; // pts from class
        totalCredit += credit;
        if (grade == 'A') 
            pts = credit * 4;
        else if (grade == 'B')
            pts = credit * 3;
        else if (grade == 'C')
            pts = credit * 2;
        else if (grade == 'D')
            pts = credit * 1;
        else if (grade == 'F')
            pts = credit * 0;
        qualityPts += pts;
    }
    
    // GPACompute
    public double GPACompute() {
        if (totalCredit == 0)
            return 4.0;
        else
            return qualityPts / totalCredit;
    }
    //toString
    @Override
    public String toString() {
        return "Name: " + name + "\nMajor: " + major + "\nGPA: " + formatter.format(GPACompute()); 
    }
}
