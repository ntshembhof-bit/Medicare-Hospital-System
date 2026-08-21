
package com.mycompany.medicarehospitalsystem;

public class Inpatient extends Patient{
    private static final String WARD_NUMBER = "Ward 1";
    private String bedNumber;
    
    public Inpatient(String patientID, String firstName, String lastName, int age,String gender, String medicalCondition){
        super(patientID, firstName, lastName, age, gender, medicalCondition, PatientCategory.INPATIENT);
        this.bedNumber = null;
    }
    public String getWardNumber(){
        return WARD_NUMBER;
    }
    public String getBedNumber(){
        return bedNumber;
    }
    
    public void setBedNumber(String bedNumber){
        this.bedNumber = bedNumber;
    }
    public boolean hasBedAllocated(){
        return null != bedNumber;
    }
    
    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Ward Number   :" + WARD_NUMBER);
        System.out.println("Bed Number    :" + bedNumber);
    }
    
}
