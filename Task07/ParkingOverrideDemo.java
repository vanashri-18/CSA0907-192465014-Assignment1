
import java.util.Objects;

abstract class Vehicle {
    private final String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public final String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract double calculateFee(int hours);

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{registrationNumber='" + registrationNumber + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vehicle)) {
            return false;
        }

        Vehicle other = (Vehicle) obj;
        return registrationNumber.equalsIgnoreCase(
                other.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber.toUpperCase());
    }
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 15.0;
    }
}

class ElectricCar extends Vehicle {
    public ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 25.0;
    }
}

public class ParkingOverrideDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("TN01AB1234");
        Vehicle bike = new Motorcycle("TN02CD5678");
        Vehicle electricCar = new ElectricCar("TN03EF9012");
        Vehicle duplicateCar = new Car("tn01ab1234");

        System.out.println(car);
        System.out.println("Car fee: Rs. " + car.calculateFee(2));
        System.out.println("Motorcycle fee: Rs. "
                + bike.calculateFee(2));
        System.out.println("Electric car fee: Rs. "
                + electricCar.calculateFee(2));

        System.out.println("Same registration? "
                + car.equals(duplicateCar));
        System.out.println("Same hash code? "
                + (car.hashCode() == duplicateCar.hashCode()));
    }
}
