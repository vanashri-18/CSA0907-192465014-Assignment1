
class Vehicle {
    protected String vehicleNumber;
    protected double parkingRate;

    public Vehicle(String vehicleNumber, double parkingRate) {
        System.out.println("1. Vehicle constructor");
        this.vehicleNumber = vehicleNumber;
        this.parkingRate = parkingRate;
    }

    public double calculateFee(int hours) {
        return parkingRate * hours;
    }

    public void display() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Parking Fee: " + calculateFee(2));
    }
}

class Car extends Vehicle {
    protected String model;

    public Car(String vehicleNumber, double parkingRate, String model) {
        super(vehicleNumber, parkingRate);
        System.out.println("2. Car constructor");
        this.model = model;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Car Model: " + model);
    }
}

class ElectricCar extends Car {
    private int batteryCapacity;

    public ElectricCar(String vehicleNumber, double parkingRate,
                       String model, int batteryCapacity) {
        super(vehicleNumber, parkingRate, model);
        System.out.println("3. ElectricCar constructor");
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String vehicleNumber, double parkingRate) {
        super(vehicleNumber, parkingRate);
        System.out.println("Motorcycle constructor");
    }
}

public class ParkingInheritanceDemo {
    public static void main(String[] args) {
        ElectricCar car = new ElectricCar(
            "TN01AB1234", 30.0, "Tata Nexon EV", 40
        );

        System.out.println("\n--- Vehicle Details ---");
        car.display();
    }
}
