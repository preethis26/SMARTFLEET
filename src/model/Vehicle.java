package model;

public abstract class Vehicle implements Rentable {

    // Constant used for tax calculation.
    public static final double TAX_RATE = 0.05;

    // Static variable shared by all Vehicle objects.
    private static int totalVehicles = 0;

    // Private fields demonstrate encapsulation.
    private String vehicleId;
    private String brand;
    private String model;
    private double dailyRate;
    private VehicleType vehicleType;
    private boolean available;

    // Default constructor
    public Vehicle() {
        this("UNKNOWN", "UNKNOWN", "UNKNOWN",
                0.0, VehicleType.CAR);
    }

    // Parameterized constructor
    public Vehicle(String vehicleId, String brand,
                   String model, double dailyRate,
                   VehicleType vehicleType) {

        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.dailyRate = dailyRate;
        this.vehicleType = vehicleType;
        this.available = true;

        totalVehicles++;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return available;
    }

    public static int getTotalVehicles() {
        return totalVehicles;
    }

    @Override
    public void rent() {
        available = false;
    }

    @Override
    public void returnVehicle() {
        available = true;
    }

    // Each subclass provides its own rent calculation.
    public abstract double calculateRent(int days);

    // Final because the vehicle identification format should remain fixed.
    public final String getVehicleLabel() {
        return vehicleId + " - " + brand + " " + model;
    }

    @Override
    public String toString() {

        return String.format(
                "%-5s %-13s %-17s %-6s Rs.%.2f",
                vehicleId,
                brand,
                model,
                vehicleType,
                dailyRate
        );
    }
}