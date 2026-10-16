
import java.util.Arrays;

// Abstract parent class
abstract class Vehicle {
    private final String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract int getHourlyRate();

    public abstract String getType();
}

// Inheritance and polymorphism
class Bike extends Vehicle {
    public Bike(String number) {
        super(number);
    }

    public int getHourlyRate() {
        return 10;
    }

    public String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    public Car(String number) {
        super(number);
    }

    public int getHourlyRate() {
        return 30;
    }

    public String getType() {
        return "Car";
    }
}

class Truck extends Vehicle {
    public Truck(String number) {
        super(number);
    }

    public int getHourlyRate() {
        return 50;
    }

    public String getType() {
        return "Truck";
    }
}

// Parking ticket
class ParkingTicket {
    private final Vehicle vehicle;
    private final int slotNumber;

    public ParkingTicket(Vehicle vehicle, int slotNumber) {
        this.vehicle = vehicle;
        this.slotNumber = slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException(
                "Parking minutes cannot be negative");
        }

        if (minutes == 0) {
            return 0;
        }

        int hours = (minutes + 59) / 60;
        return hours * vehicle.getHourlyRate();
    }
}

// Parking management using an array
class ParkingFacility {
    private final ParkingTicket[] slots;
    private int totalCollection = 0;

    public ParkingFacility(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "Capacity must be positive");
        }

        slots = new ParkingTicket[capacity];
    }

    public String parkVehicle(
            String type, String registration, int slotNumber) {

        if (registration == null || registration.trim().isEmpty()) {
            return "Invalid registration number";
        }

        if (type == null) {
            return "Unknown vehicle type";
        }

        Vehicle vehicle;

        switch (type.trim().toLowerCase()) {
            case "bike":
                vehicle = new Bike(registration.trim());
                break;
            case "car":
                vehicle = new Car(registration.trim());
                break;
            case "truck":
                vehicle = new Truck(registration.trim());
                break;
            default:
                return "Unknown vehicle type";
        }

        if (slotNumber < 1 || slotNumber > slots.length) {
            return "Invalid slot number";
        }

       
for (ParkingTicket ticket : slots) {
    if (ticket != null &&
        ticket.getVehicle().getRegistrationNumber()
            .equalsIgnoreCase(registration.trim())) {
        return "Duplicate registration number";
    }
}

if (slots[slotNumber - 1] != null) {
    return "Slot is occupied";
}


        slots[slotNumber - 1] =
            new ParkingTicket(vehicle, slotNumber);

        return "Parked successfully";
    }

    public String exitVehicle(String registration, int minutes) {
        if (minutes < 0) {
            return "Invalid parking duration";
        }

        for (int i = 0; i < slots.length; i++) {
            ParkingTicket ticket = slots[i];

            if (ticket != null &&
                ticket.getVehicle().getRegistrationNumber()
                    .equalsIgnoreCase(registration.trim())) {

                int fee = ticket.calculateFee(minutes);
                totalCollection += fee;
                slots[i] = null;

                return "Exited: " + registration
                    + ", Fee: Rs." + fee;
            }
        }

        return "Vehicle not found";
    }

    public int getTotalCollection() {
        return totalCollection;
    }

    public int getFreeSlots() {
        int free = 0;

        for (ParkingTicket ticket : slots) {
            if (ticket == null) {
                free++;
            }
        }

        return free;
    }
}

// Main class and test cases
public class SmartParkingSystem {
    public static void main(String[] args) {
        ParkingFacility parking = new ParkingFacility(2);

        System.out.println("=== SMART PARKING SYSTEM TESTS ===");

        System.out.println("1. Bike entry: "
            + parking.parkVehicle("Bike", "TN01AB1234", 1));

        System.out.println("2. Car entry: "
            + parking.parkVehicle("Car", "TN02CD5678", 2));

        System.out.println("3. Last free slot check: "
            + parking.getFreeSlots() + " free slots");

        System.out.println("4. Full parking: "
            + parking.parkVehicle("Truck", "TN03EF9012", 1));

        System.out.println("5. Duplicate registration: "
            + parking.parkVehicle("Car", "TN01AB1234", 2));

        System.out.println("6. Unknown vehicle: "
            + parking.parkVehicle("Bus", "TN04GH1234", 1));

        System.out.println("7. Invalid slot: "
            + parking.parkVehicle("Bike", "TN05JK1234", 3));

        System.out.println("8. Zero-minute stay: "
            + parking.exitVehicle("TN01AB1234", 0));

        System.out.println("9. Exact 60-minute boundary: "
            + parking.exitVehicle("TN02CD5678", 60));

        System.out.println("10. Daily collection: Rs."
            + parking.getTotalCollection());

        System.out.println("11. Invalid duration: "
            + parking.exitVehicle("TN09ZZ9999", -5));

        System.out.println("12. Vehicle leaves; slot reused: "
            + parking.parkVehicle("Truck", "TN03EF9012", 1));

        System.out.println("13. Truck stays 61 minutes: "
            + parking.exitVehicle("TN03EF9012", 61));

        System.out.println("14. Final collection: Rs."
            + parking.getTotalCollection());
    }
}
