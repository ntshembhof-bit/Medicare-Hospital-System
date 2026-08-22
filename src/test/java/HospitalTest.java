
package com.mycompany.medicarehospitalsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalTest {

    private Hospital hospital;

    @BeforeEach
    public void setUp() {
        hospital = new Hospital();
    }

    @Test
    public void testRegisterPatientSuccess() throws Exception {
        hospital.registerPatient(new Patient("P001", "Jane", "Doe", 30, "Female", "Flu", PatientCategory.OUTPATIENT));
        assertEquals(1, hospital.getTotalRegisteredPatients());
    }

    @Test
    public void testRegisterDuplicateIDThrowsException() throws Exception {
        hospital.registerPatient(new Patient("P001", "Jane", "Doe", 30, "Female", "Flu", PatientCategory.OUTPATIENT));

        assertThrows(DuplicatePatientIDException.class, () -> {
            hospital.registerPatient(new Patient("P001", "Fake", "Copy", 99, "Male", "N/A", PatientCategory.OUTPATIENT));
        });
    }

    @Test
    public void testSearchPatientFound() throws Exception {
        hospital.registerPatient(new Patient("P001", "Jane", "Doe", 30, "Female", "Flu", PatientCategory.OUTPATIENT));
        Patient found = hospital.searchPatient("P001");
        assertEquals("Jane", found.getFirstName());
    }

    @Test
    public void testSearchPatientNotFoundThrowsException() {
        assertThrows(PatientNotFoundException.class, () -> {
            hospital.searchPatient("DOES_NOT_EXIST");
        });
    }

    @Test
    public void testAllocateBedSuccess() throws Exception {
        hospital.registerPatient(new Inpatient("P002", "John", "Smith", 45, "Male", "Fracture"));
        String bedNumber = hospital.allocateBed("P002");
        assertNotNull(bedNumber);
        assertEquals(1, hospital.getTotalOccupiedBeds());
    }

    @Test
    public void testAllocateBedTwiceThrowsException() throws Exception {
        hospital.registerPatient(new Inpatient("P002", "John", "Smith", 45, "Male", "Fracture"));
        hospital.allocateBed("P002");

        assertThrows(BedNotAvailableException.class, () -> {
            hospital.allocateBed("P002");
        });
    }

    @Test
    public void testDeletePatientSuccess() throws Exception {
        hospital.registerPatient(new Patient("P001", "Jane", "Doe", 30, "Female", "Flu", PatientCategory.OUTPATIENT));
        hospital.deletePatient("P001");
        assertEquals(0, hospital.getTotalRegisteredPatients());
    }
}
