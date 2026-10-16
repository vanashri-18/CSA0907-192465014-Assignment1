
import java.time.LocalTime;

interface Chargeable {
    double chargingFee(int kWh);

    default void showChargingMessage() {
        System.out.println("Electric vehicle charging is available.");
    }

    static String getChargingPolicy() {
        return "Charging fee depends on units consumed.";
    }
}

abstract class Vehicle {
    protected String registrationNumber;
    protected double hourlyRate;

    public Vehicle(String registrationNumber, double hourlyRate) {
        this.registrationNumber = registrationNumber;
        this.hourlyRate = hourlyRate;
    }

    public abstract double calculateFee(int minutes);

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double calculateFee(LocalTime in, LocalTime out) {
        long minutes = java.time.Duration.between(in, out).toMinutes();
        if (minutes < 0) {
            minutes += 24 * 60;
        }
        return calculateFee((int) minutes);
    }
}

class Car extends Vehicle {
    public Car(String registrationNumber) {
        super(registrationNumber, 30.0);
    }

    @Override
    public double calculateFee(int minutes) {
        return Math.ceil(minutes / 60.0) * hourlyRate;
    }
}

class ElectricCar extends Car implements Chargeable {
    public ElectricCar(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("TN01A1234");
        ElectricCar ev = new ElectricCar("TN02B5678");

        System.out.println("Car parking fee: Rs " +
                car.calculateFee(120));

        System.out.println("EV parking fee: Rs " +
                ev.calculateFee(60));

        System.out.println("EV charging fee: Rs " +
                ev.chargingFee(5));

        ev.showChargingMessage();

        System.out.println(Chargeable.getChargingPolicy());

        System.out.println("Time-based fee: Rs " +
                car.calculateFee(
                        LocalTime.of(10, 0),
                        LocalTime.of(11, 30)));
    }
}
