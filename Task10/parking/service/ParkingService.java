package parking.service;

import parking.model.ParkingSlot;

public class ParkingService {
    public double calculateFee(ParkingSlot slot) {
        int duration = slot.getDurationMinutes();

        if (duration == 0) {
            return 0.0;
        }

        int hours = (duration + 59) / 60;
        return hours * 30.0;
    }

    public boolean isSameSlot(Integer first, Integer second) {
        return first != null && first.equals(second);
    }
}