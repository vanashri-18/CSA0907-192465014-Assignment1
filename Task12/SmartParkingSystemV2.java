
import java.util.HashSet;
import java.util.Set;

interface Pass {
    int getPrice();
}

class OneDayPass implements Pass {
    public int getPrice() {
        return 100;
    }
}

abstract class Vehicle {
    protected String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract String getType();
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    public String getType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    public Bike(String registrationNumber) {
        super(registrationNumber);
    }

    public String getType() {
        return "Bike";
    }
}

class EBike extends Vehicle {
    public EBike(String registrationNumber) {
        super(registrationNumber);
    }

    public String getType() {
        return "E-Bike";
    }
}

class DeliveryVan extends Vehicle {
    public DeliveryVan(String registrationNumber) {
        super(registrationNumber);
    }

    public String getType() {
        return "Delivery Van";
    }
}

class Truck extends Vehicle {
    public Truck(String registrationNumber) {
        super(registrationNumber);
    }

    public String getType() {
        return "Truck";
    }
}

class ParkingTicket {
    Vehicle vehicle;

    ParkingTicket(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

class ParkingFacility {
    private final ParkingTicket[][] floors = {
        new ParkingTicket[3],
        new ParkingTicket[4],
        new ParkingTicket[2],
        new ParkingTicket[5],
        new ParkingTicket[3]
    };

    private final Set<String> parkedVehicles = new HashSet<>();
    private final Set<String> passHolders = new HashSet<>();
    private int collection = 0;

    public String parkVehicle(Vehicle vehicle, int floor, int slot) {
        if (floor < 1 || floor > floors.length) {
            return "Invalid floor number";
        }

        if (slot < 1 || slot > floors[floor - 1].length) {
            return "Invalid slot number";
        }

        String reg = vehicle.getRegistrationNumber();

        if (parkedVehicles.contains(reg)) {
            return "Duplicate registration number";
        }

        if (floors[floor - 1][slot - 1] != null) {
            return "Slot is occupied";
        }

        floors[floor - 1][slot - 1] = new ParkingTicket(vehicle);
        parkedVehicles.add(reg);

        return vehicle.getType() + " parked successfully";
    }

    public String buyOneDayPass(String registrationNumber) {
        if (passHolders.contains(registrationNumber)) {
            return "Pass already purchased";
        }

        passHolders.add(registrationNumber);
        int price = new OneDayPass().getPrice();
        collection += price;

        return "One-day pass purchased: Rs." + price;
    }

    public String exitVehicle(
        String registrationNumber,
        int minutes,
        int exitHour
    ) {
        if (minutes < 0 || exitHour < 0 || exitHour > 23) {
            return "Invalid parking duration or exit time";
        }

        for (int f = 0; f < floors.length; f++) {
            for (int s = 0; s < floors[f].length; s++) {
                ParkingTicket ticket = floors[f][s];

                if (ticket != null &&
                    ticket.vehicle.getRegistrationNumber()
                        .equals(registrationNumber)) {

                    int fee = calculateFee(
                        ticket.vehicle, minutes, exitHour
                    );

                    floors[f][s] = null;
                    parkedVehicles.remove(registrationNumber);

                    if (passHolders.remove(registrationNumber)) {
                        fee = 0;
                    } else {
                        collection += fee;
                    }

                    return "Exited " + registrationNumber
                        + ", Fee: Rs." + fee;
                }
            }
        }

        return "Vehicle not found";
    }

    private int calculateFee(
        Vehicle vehicle, int minutes, int exitHour
    ) {
        if (vehicle instanceof EBike) {
            return 25;
        }

        int rate;

        if (vehicle instanceof DeliveryVan) {
            rate = (exitHour >= 9 && exitHour < 17) ? 60 : 30;
        } else if (vehicle instanceof Car) {
            rate = 30;
        } else if (vehicle instanceof Bike) {
            rate = 10;
        } else if (vehicle instanceof Truck) {
            rate = 50;
        } else {
            return 0;
        }

        return (int) Math.ceil(minutes / 60.0) * rate;
    }

    public int getCollection() {
        return collection;
    }

    public void displayFloorCapacity() {
        System.out.println("\n--- FLOOR CAPACITY ---");

        for (int f = 0; f < floors.length; f++) {
            int free = 0;

            for (ParkingTicket ticket : floors[f]) {
                if (ticket == null) {
                    free++;
                }
            }

            System.out.println(
                "Floor " + (f + 1) + ": "
                + free + "/" + floors[f].length
                + " slots free"
            );
        }
    }
}

public class SmartParkingSystemV2 {
    public static void main(String[] args) {
        ParkingFacility parking = new ParkingFacility();

        System.out.println("=== SMART PARKING SYSTEM V2 ===");

        System.out.println("1. " + parking.parkVehicle(
            new Car("TN01AB1234"), 1, 1
        ));

        System.out.println("2. " + parking.parkVehicle(
            new EBike("TN02EB5678"), 2, 1
        ));

        System.out.println("3. " + parking.parkVehicle(
            new DeliveryVan("TN03DV9012"), 3, 1
        ));

        System.out.println("4. " +
            parking.buyOneDayPass("TN01AB1234"));

        System.out.println("5. " + parking.exitVehicle(
            "TN02EB5678", 120, 12
        ));

        System.out.println("6. " + parking.exitVehicle(
            "TN03DV9012", 60, 10
        ));

        System.out.println("7. " + parking.exitVehicle(
            "TN01AB1234", 180, 14
        ));

        System.out.println("8. " + parking.parkVehicle(
            new Bike("TN04BK1111"), 5, 3
        ));

        System.out.println("\n--- EDGE CASE TESTS ---");

        System.out.println("9. Invalid floor: " +
            parking.parkVehicle(
                new Car("TN05AA1111"), 6, 1
            ));

        System.out.println("10. Invalid slot: " +
            parking.parkVehicle(
                new Car("TN06BB2222"), 1, 4
            ));

        System.out.println("11. Duplicate registration: " +
            parking.parkVehicle(
                new Bike("TN04BK1111"), 4, 1
            ));

        System.out.println("12. Occupied slot: " +
            parking.parkVehicle(
                new Car("TN07CC3333"), 5, 3
            ));

        parking.displayFloorCapacity();

        System.out.println("\nTotal collection: Rs."
            + parking.getCollection());
    }
}
