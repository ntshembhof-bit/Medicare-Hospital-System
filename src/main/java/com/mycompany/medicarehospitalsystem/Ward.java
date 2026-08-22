
package com.mycompany.medicarehospitalsystem;

public class Ward {

    private Bed[][] beds;

    public Ward() {
        beds = new Bed[4][5];
        int bedCounter = 1;
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                String bedNumber = String.format("B%02d", bedCounter);
                beds[row][col] = new Bed(bedNumber);
                bedCounter++;
            }
        }
    }

    private Bed findBedByNumber(String bedNumber) {
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (beds[row][col].getBedNumber().equalsIgnoreCase(bedNumber)) {
                    return beds[row][col];
                }
            }
        }
        return null;
    }

    public String allocateBed(String patientID) throws NoBedsAvailableException {
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (!beds[row][col].isOccupied()) {
                    beds[row][col].occupy(patientID);
                    return beds[row][col].getBedNumber();
                }
            }
        }
        throw new NoBedsAvailableException("No beds are available in the ward.");
    }

    public void releaseBed(String bedNumber) throws BedNotAvailableException {
        Bed bed = findBedByNumber(bedNumber);
        if (bed == null) {
            throw new BedNotAvailableException("Bed " + bedNumber + " does not exist.");
        }
        bed.release();
    }

    public void releaseBedByPatientID(String patientID) throws BedNotAvailableException {
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (beds[row][col].isOccupied() && beds[row][col].getPatientID().equalsIgnoreCase(patientID)) {
                    beds[row][col].release();
                    return;
                }
            }
        }
        throw new BedNotAvailableException("No bed found allocated to patient " + patientID);
    }

    public void displayWardLayout() {
        System.out.println("\n--- Ward Layout ---");
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                Bed bed = beds[row][col];
                if (bed.isOccupied()) {
                    System.out.print(bed.getBedNumber() + "[X] ");
                } else {
                    System.out.print(bed.getBedNumber() + "[ ] ");
                }
            }
            System.out.println();
        }
    }

    public void displayAvailableBeds() {
        System.out.println("\n--- Available Beds ---");
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (!beds[row][col].isOccupied()) {
                    System.out.println(beds[row][col].getBedNumber());
                }
            }
        }
    }

    public void displayOccupiedBeds() {
        System.out.println("\n--- Occupied Beds ---");
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (beds[row][col].isOccupied()) {
                    System.out.println(beds[row][col].getBedNumber() + " - Patient ID: " + beds[row][col].getPatientID());
                }
            }
        }
    }

    public int countOccupied() {
        int count = 0;
        for (int row = 0; row < beds.length; row++) {
            for (int col = 0; col < beds[row].length; col++) {
                if (beds[row][col].isOccupied()) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countAvailable() {
        int totalBeds = beds.length * beds[0].length;
        return totalBeds - countOccupied();
    }
}