package model;

public class Bike extends Vehicle {

    private int engineCC;

    // Default constructor
    public Bike() {
        super();
        this.engineCC = 100;
    }

    // Parameterized constructor
    public Bike(String vehicleId, String brand, String model,
                double dailyRate, int engineCC) {

        super(vehicleId, brand, model, dailyRate, VehicleType.BIKE);
        this.engineCC = engineCC;
    }

    public int getEngineCC() {
        return engineCC;
    }

    public void setEngineCC(int engineCC) {
        this.engineCC = engineCC;
    }

    @Override
    public double calculateRent(int days) {

        double baseRent = getDailyRate() * days;

        // 5% discount for rentals of 5 days or more.
        if (days >= 5) {
            baseRent = baseRent * 0.95;
        }

        return baseRent;
    }

    @Override
    public String toString() {
        return super.toString() + " | Engine: " + engineCC + "cc";
    }
}