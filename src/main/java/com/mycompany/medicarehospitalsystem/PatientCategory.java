
package com.mycompany.medicarehospitalsystem;


public enum PatientCategory {
    INPATIENT,
    OUTPATIENT,
    EMERGENCY;
    
    @Override
    public String toString(){
        String lower = name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
    
}
