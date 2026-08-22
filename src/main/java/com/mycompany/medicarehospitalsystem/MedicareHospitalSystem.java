
package com.mycompany.medicarehospitalsystem;

import java.util.Scanner;

public class MedicareHospitalSystem {

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
do  {
    System.out.println("\n==== MediCare Hospital Patient Admission System ====");
    System.out.println("1. Register patient");
    System.out.println("2. Search Patient");
    System.out.println("3. Update Patient");
    System.out.println("4. Delete Patient");
    System.out.println("5. Display All Patient");
    System.out.println("6. Allocate Bed");
    System.out.println("7. Release Bed");
    System.out.println("8. Display Ward Layout");
    System.out.println("9. Reports");
    System.out.println("0. Exit");
    System.out.println("10. Display Patients Sorted by surname");
    System.out.println("Enter your choice: ");
    
    choice = scanner.nextInt();
    scanner.nextLine();
    
    switch (choice){
        case 1: 
            System.out.println("Enter category (1 = Inpatient, 2 = Outpatient, 3 = Emergency): ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Enter patient ID: ");
            String newID = scanner.nextLine();
            
            System.out.println("Enter First Name: ");
            String newFirstName = scanner.nextLine();
            
            System.out.println("Enter Last Name: ");
            String newLastName = scanner.nextLine();
            
            System.out.println("Enter Age: ");
            int newAge = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Enter Gender: ");
            String newGender = scanner.nextLine();
            
            System.out.println("Enter Medical Condition: ");
            String newCondition = scanner.nextLine();
            
            try{
                if (categoryChoice == 1){
                    hospital.registerPatient(new Inpatient(newID, newFirstName, newLastName, newAge, newGender, newCondition));
                }else if (categoryChoice == 2)  {
                    hospital.registerPatient(new Patient(newID, newFirstName, newLastName, newAge, newGender, newCondition, PatientCategory.OUTPATIENT));
                }else if ( categoryChoice == 3) {
                    hospital.registerPatient(new Patient(newID, newFirstName, newLastName, newAge, newGender, newCondition, PatientCategory.EMERGENCY));
                }else{
                    System.out.println("Invalid category choice.");
                    break;
                }
                System.out.println("Patient registered successfully!");
            } catch (DuplicatePatientIDException e){
                System.out.println("Error: " + e.getMessage());
            }
        break;
        
        case 2:
           System.out.println("Enter Patient ID to search: ");
           String searchID = scanner.nextLine();
           
           try{
               Patient foundPatient = hospital.searchPatient(searchID);
               System.out.println("Patient found: ");
               foundPatient.displayDetails();
           } catch (PatientNotFoundException e){
               System.out.println("Error: " + e.getMessage());
           }
           break;
           
        case 3:
            System.out.print("Enter Patient ID to update: ");
            String updateID = scanner.nextLine();
             
            System.out.print("Enter new First Name: ");
            String updateFirstName = scanner.nextLine();
            
            System.out.println("Enter new Last Name: ");
            String updateLastName = scanner.nextLine();
            
            System.out.println("Enter new Age: ");
            int updateAge = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("Enter new Gender: ");
            String updateGender =scanner.nextLine();
            
            System.out.println("Enter new Medical Condition: ");
            String updateCondition = scanner.nextLine();
            
         try{ 
             hospital.updatePatient(updateID, updateFirstName, updateLastName, updateAge, updateGender, updateCondition);
             System.out.println("Patient updated successfully!");
         } catch (PatientNotFoundException e){
             System.out.println("Error: " + e.getMessage());
         }   
          break;
          
        case 4:
            System.out.print("Enter  Patient ID to  delete: ");
            String deleteID = scanner.nextLine();
            
            try{
                hospital.deletePatient(deleteID);
                System.out.println("Patient deleted sucessfully!");
            } catch (PatientNotFoundException e){
                System.out.println("Error: " + e.getMessage());
            }
            break;
                 
        case 5:
            hospital.displayAllPatient();
            break;
            
        case 6:
            System.out.println("Enter Patient ID to allocate a bed");
            String allocateID = scanner.nextLine();
            
            try{
                String bedNum = hospital.allocateBed(allocateID);
                System.out.println("Bed allocated: " + bedNum);
            }catch(PatientNotFoundException | BedNotAvailableException | NoBedsAvailableException e){
               System.out.println("Error: " + e.getMessage()); 
            }
            break;
            
        case 7:
            System.out.println("Enter Patient ID to release bed: ");
            String releaseID = scanner.nextLine();
            
         try{ 
             hospital.releaseBed(releaseID);
             System.out.println("Bed releases successfully!");
           }catch(PatientNotFoundException | BedNotAvailableException e){
             System.out.println("Error: " + e.getMessage());  
                       
         }
         break;
         
        case 8:
            hospital.displayWardLayout();
            break;
            
        case 9:
            System.out.println("\n--- Report ---");
            System.out.println("Total  registered patients: "  + hospital.getTotalRegisteredPatients());
            System.out.println("Total occupied beds: " + hospital.getTotalOccupiedBeds());
            System.out.println("Total available beds: " + hospital.getTotalAvailableBeds());
            System.out.printf("Ward occupancy: %.2f%%\n", hospital.getWardOccupancyPercentage());
            break;
            
        case 10:
            Patient[] sortedPatients = hospital.getPatientsSortedBySurname();
            hospital.displayPatientsArray(sortedPatients);
            break;
            
         case 0:
            System.out.println("Exiting system. Goodbye!");
            break;
            
        default:
            System.out.println("Invalid choice, please try again.");
         }
    } while (choice != 0);
       scanner.close();
}
}

