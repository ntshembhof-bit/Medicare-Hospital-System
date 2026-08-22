
package com.mycompany.medicarehospitalsystem;

import java.util.ArrayList;

public class Hospital {
     private ArrayList<Patient>patients = new ArrayList<Patient>();
     private Ward ward = new Ward();
     
     public void registerPatient(Patient Patient) throws DuplicatePatientIDException{
         for (int i = 0; i < patients.size(); i++){
            if(patients.get(i).getPatientID().equalsIgnoreCase(Patient.getPatientID())) {
                throw new DuplicatePatientIDException("Patient with ID " + Patient.getPatientID() + " already exists.");
            }
         }
         patients.add(Patient);
     }
    
 public Patient searchPatient(String patientID) throws PatientNotFoundException{
     for(int i = 0; i<patients.size(); i++){
         if(patients.get(i).getPatientID().equalsIgnoreCase(patientID)){
             return patients.get(i);
         }
     }
     throw new PatientNotFoundException("No patient found with ID " + patientID + "." );
 }
 
 public void updatePatient(String patientID, String firstName, String lastName, int age, String gender,String medicalcodition )
         throws PatientNotFoundException {
     Patient patient = searchPatient(patientID);
     patient.setFirstName(firstName);
     patient.setLastName(lastName);
     patient.setAge(age);
     patient.setGender(gender);
     patient.setMedicalCondition(medicalcodition);
 }
 public  void deletePatient(String patientID) throws PatientNotFoundException {
     Patient patient = searchPatient(patientID);
     
     if(patient instanceof Inpatient){
        Inpatient inpatient = (Inpatient) patient;
        if(inpatient.getBedNumber()!= null){
            try{
                ward.releaseBedByPatientID(patientID);
            } catch (BedNotAvailableException e){
            }
        }
     }
     patients.remove(patient);
 }
 public String allocateBed(String patientID) throws PatientNotFoundException, BedNotAvailableException, NoBedsAvailableException{
     Patient patient = searchPatient(patientID);
     
     if(!(patient instanceof Inpatient)){
         throw new BedNotAvailableException("Only Inpatients may be allocated a hospital bed");
           }
     Inpatient inpatient = (Inpatient) patient;
     if(inpatient.getBedNumber() != null){
        throw new BedNotAvailableException("Patient " + patientID + "already has bed " + inpatient.getBedNumber() + "allocated.");
     }
    String bedNumber = ward.allocateBed(patientID);
    inpatient.setBedNumber(bedNumber);
    return bedNumber;
 }
 public void releaseBed(String patientID) throws PatientNotFoundException, BedNotAvailableException{
     Patient patient = searchPatient(patientID);
     if(!(patient instanceof Inpatient)){
         throw new BedNotAvailableException("Patient " + patientID + "is  not an Inpatient and has no bed");
     }
     Inpatient inpatient = (Inpatient) patient;
     if (inpatient.getBedNumber() == null){
        throw new BedNotAvailableException("Patient " + patientID + "does not currently have a bed allocated."); 
     }
     ward.releaseBed(inpatient.getBedNumber());
     inpatient.setBedNumber(null);
 }
 public void displayAllPatient(){
     if(patients.isEmpty()){
         System.out.println("No patients are currently registered.");
         return;         
     }
     System.out.println("\n===== Registered patients(" + patients.size() + ") ====");
     for(Patient patient : patients){
         patient.displayDetails();
     }
 }
 public void displayWardLayout(){
     ward.displayWardLayout();
 }
 public void displayOccupiedBeds(){
     ward.displayOccupiedBeds();
 }
 
 public int getTotalRegisteredPatients(){
     return patients.size();
 }
 public int getTotalOccupiedBeds(){
     return ward.countOccupied();     
 }
 public int getTotalAvailableBeds(){
     return ward.countAvailable();
 }
 public double getWardOccupancyPercentage(){
     int totalBeds = ward.countOccupied() + ward.countAvailable();
     return(ward.countOccupied() * 100.0)/ totalBeds;
 }
 
 public Patient[] getPatientsSortedBySurname() {
    Patient[] patientArray = new Patient[patients.size()];
    for (int i = 0; i < patients.size(); i++) {
        patientArray[i] = patients.get(i);
    }

    for (int i = 0; i < patientArray.length - 1; i++) {
        for (int j = 0; j < patientArray.length - 1 - i; j++) {
            if (patientArray[j].getLastName().compareToIgnoreCase(patientArray[j + 1].getLastName()) > 0) {
                Patient temp = patientArray[j];
                patientArray[j] = patientArray[j + 1];
                patientArray[j + 1] = temp;
            }
        }
    }

    return patientArray;
}

public void displayPatientsArray(Patient[] patientArray) {
    if (patientArray.length == 0) {
        System.out.println("No patients to display.");
        return;
    }
    System.out.println("\n===== Patients sorted by surname (" + patientArray.length + ") =====");
    for (int i = 0; i < patientArray.length; i++) {
        patientArray[i].displayDetails();
    }
}
 
}
