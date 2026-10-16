
class Vehicle {
    private String vehicleNo;
    private String vehicleType;

    public Vehicle() {
        this("UNKNOWN", "Car");
    }

    public Vehicle(String vehicleNo, String vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    public Vehicle(Vehicle other) {
        this(other.vehicleNo, other.vehicleType);
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}

class ParkingSlot {
    private int floor;
    private int slotNo;
    private boolean occupied;

    public ParkingSlot() {
        this(0, 0);
    }

    public ParkingSlot(int floor, int slotNo) {
        this.floor = floor;
        this.slotNo = slotNo;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void release() {
        occupied = false;
    }

    public String getLocation() {
        return "Floor " + floor + ", Slot " + slotNo;
    }
}

public class ParkingTicket {
    private static int nextTicketNo = 1001;

    private final int ticketNo;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final String entryTime;

    public ParkingTicket() {
        this(new Vehicle(), new ParkingSlot(), "Not recorded");
    }

    public ParkingTicket(Vehicle vehicle, ParkingSlot slot) {
        this(vehicle, slot, java.time.LocalDateTime.now().toString());
    }

    public ParkingTicket(
            Vehicle vehicle, ParkingSlot slot, String entryTime) {

        if (vehicle == null || slot == null || entryTime == null) {
            throw new IllegalArgumentException(
                "Vehicle, slot and entry time are required.");
        }

        this.ticketNo = nextTicketNo++;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public ParkingTicket(ParkingTicket other) {
        this(other.vehicle, other.slot, other.entryTime);
    }

    public void displayTicket() {
        System.out.println("Ticket Number: " + ticketNo);
        System.out.println("Vehicle Number: " + vehicle.getVehicleNo());
        System.out.println("Vehicle Type: " + vehicle.getVehicleType());
        System.out.println("Slot: " + slot.getLocation());
        System.out.println("Entry Time: " + entryTime);
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("TN01AB1234", "Car");
        ParkingSlot s1 = new ParkingSlot(0, 1);

        ParkingTicket t1 =
            new ParkingTicket(v1, s1, "2026-10-16 10:00");

        ParkingTicket t2 = new ParkingTicket(t1);

        t1.displayTicket();
        t2.displayTicket();
    }
}
