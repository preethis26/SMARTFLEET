package model;

public class Rental {

    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double amount;
    private boolean returned;

    // Default constructor
    public Rental() {
        this(
                "R000",
                new Customer(),
                new Car(),
                0,
                0.0
        );
    }

    // Parameterized constructor
    public Rental(
            String rentalId,
            Customer customer,
            Vehicle vehicle,
            int days,
            double amount) {

        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.amount = amount;
        this.returned = false;
    }

    // Getter and setter methods

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        this.returned = true;
    }

    // Overriding toString() from Object
    @Override
    public String toString() {

        return String.format(
                "%-8s %-15s %-25s Rs.%.2f",
                rentalId,
                customer.getName(),
                vehicle.getVehicleLabel(),
                amount
        );
    }
}