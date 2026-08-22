 
package com.mycompany.medicarehospitalsystem;

public class DuplicatePatientIDException extends Exception{
    public DuplicatePatientIDException(String message){
        super(message);
    }
}
