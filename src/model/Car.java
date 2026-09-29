package model;

public class Car extends Vehicle {

    private int numberOfDoors;

    // Default constructor
    public Car() {
        super();
        this.numberOfDoors = 4;
    }

    // Parameterized constructor
    public Car(String vehicleId, String brand, String model,
               double dailyRate, int numberOfDoors) {

        super(vehicleId, brand, model, dailyRate, VehicleType.CAR);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double calculateRent(int days) {

        double baseRent = getDailyRate() * days;

        // 10% discount for rentals of 7 days or more.
        if (days >= 7) {
            baseRent = baseRent * 0.90;
        }

        return baseRent;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Doors: "
                + numberOfDoors;
    }
}