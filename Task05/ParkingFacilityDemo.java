
class ParkingTicket {
    private static int nextTicketNumber = 1001;
    private final int ticketNumber;
    private final String vehicleNumber;

    static {
        System.out.println("Parking Ticket System Initialized");
    }

    public ParkingTicket(String vehicleNumber) {
        this.ticketNumber = nextTicketNumber++;
        this.vehicleNumber = vehicleNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}

class ParkingFacility {
    private static int totalOccupancy = 0;
    private final String facilityName;

    public ParkingFacility(String facilityName) {
        this.facilityName = facilityName;
    }

    public ParkingTicket parkVehicle(String vehicleNumber) {
        ParkingTicket ticket = new ParkingTicket(vehicleNumber);
        totalOccupancy++;
        return ticket;
    }

    public void vehicleLeaves(ParkingTicket ticket) {
        if (ticket != null && totalOccupancy > 0) {
            totalOccupancy--;
            System.out.println(
                ticket.getVehicleNumber() + " left " + facilityName);
        }
    }

    public static int getTotalOccupancy() {
        return totalOccupancy;
    }
}

public class ParkingFacilityDemo {
    public static void main(String[] args) {
        ParkingFacility north = new ParkingFacility("North Gate");
        ParkingFacility south = new ParkingFacility("South Gate");

        ParkingTicket t1 = north.parkVehicle("TN01AB1234");
        ParkingTicket t2 = south.parkVehicle("TN02CD5678");

        System.out.println("Ticket 1: " + t1.getTicketNumber());
        System.out.println("Ticket 2: " + t2.getTicketNumber());
        System.out.println("Occupancy: " +
            ParkingFacility.getTotalOccupancy());

        north.vehicleLeaves(t1);

        t1 = null;

        System.out.println("Occupancy after exit: " +
            ParkingFacility.getTotalOccupancy());
        System.out.println("Ticket 2 remains: " +
            t2.getVehicleNumber());
    }
}
