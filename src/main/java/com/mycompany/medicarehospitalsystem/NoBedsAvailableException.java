
package com.mycompany.medicarehospitalsystem;


public class NoBedsAvailableException extends Exception {
    public NoBedsAvailableException(String message){
        super(message);
    }
}
