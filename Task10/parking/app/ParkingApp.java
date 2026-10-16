package parking.app;

import parking.model.ParkingSlot;
import parking.service.ParkingService;

public class ParkingApp {
    public static void main(String[] args) {
        ParkingSlot slot = new ParkingSlot(5, 90);
        ParkingService service = new ParkingService();

        System.out.println("Slot Number: " + slot.getSlotNumber());
        System.out.println("Duration: " + slot.getDurationMinutes()
                + " minutes");
        System.out.println("Parking Fee: Rs "
                + service.calculateFee(slot));

        Integer slotA = 5;
        Integer slotB = 5;

        System.out.println("Same slot: "
                + service.isSameSlot(slotA, slotB));

        try {
            slot.setDurationMinutes(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation: " + e.getMessage());
        }

        try {
            slot.setSlotNumber(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation: " + e.getMessage());
        }
    }
}