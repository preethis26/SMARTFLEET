package service;

import model.*;

public class RentalService {

    private Vehicle[] vehicles;
    private Rental[] rentals;

    private int rentalCount = 0;
    private int rentalNumber = 1001;

    private BillingService billingService;

    // Constructor
    public RentalService() {

        billingService = new BillingService();

        // Array of Vehicle objects
        vehicles = new Vehicle[6];
        rentals = new Rental[20];

        vehicles[0] = new Car(
                "V101", "Honda", "City", 1800, 4);

        vehicles[1] = new Bike(
                "V102", "Royal Enfield", "Classic 350", 800, 350);

        vehicles[2] = new Car(
                "V103", "Toyota", "Innova", 2500, 5);

        vehicles[3] = new Bike(
                "V104", "Honda", "Activa", 600, 125);

        vehicles[4] = new Car(
                "V105", "Tata", "Nexon", 2200, 4);

        vehicles[5] = new Bike(
                "V106", "Yamaha", "R15", 900, 155);
    }

    // =========================================================
    // DISPLAY AVAILABLE VEHICLES
    // =========================================================

    public void displayVehicles() {

        System.out.println(
                "\n================ AVAILABLE VEHICLES ================"
        );

        System.out.printf(
                "%-6s %-15s %-18s %-8s %s%n",
                "ID", "BRAND", "MODEL", "TYPE", "RATE/DAY"
        );

        System.out.println(
                "-----------------------------------------------------"
        );

        boolean found = false;

        for (Vehicle vehicle : vehicles) {

            if (!vehicle.isAvailable()) {
                continue;
            }

            System.out.println(vehicle);
            found = true;
        }

        if (!found) {

            System.out.println(
                    "No vehicles are currently available."
            );
        }
    }

    // =========================================================
    // FIND VEHICLE
    // =========================================================

    public Vehicle findVehicle(String id) {

        if (id == null || id.trim().isEmpty()) {

            return null;
        }

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleId()
                    .equalsIgnoreCase(id.trim())) {

                return vehicle;
            }
        }

        return null;
    }

    // =========================================================
    // SEARCH VEHICLE
    // =========================================================

    public void searchVehicle(String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            System.out.println(
                    "Please enter a search keyword."
            );

            return;
        }

        boolean found = false;

        System.out.println(
                "\n================ SEARCH RESULTS ================"
        );

        System.out.printf(
                "%-6s %-15s %-18s %-8s %s%n",
                "ID", "BRAND", "MODEL", "TYPE", "RATE/DAY"
        );

        System.out.println(
                "-----------------------------------------------------"
        );

        for (Vehicle vehicle : vehicles) {

            String searchText =
                    (vehicle.getBrand() + " "
                            + vehicle.getModel()).toLowerCase();

            if (searchText.contains(
                    keyword.trim().toLowerCase())) {

                System.out.println(vehicle);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No matching vehicle found."
            );
        }
    }

    // =========================================================
    // CALCULATE RENTAL AMOUNT
    // =========================================================

    // Returns the calculated rental amount.
    public double calculateRentalAmount(
            String vehicleId,
            int days) {

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null
                || !vehicle.isAvailable()) {

            return -1;
        }

        if (days <= 0) {

            return -1;
        }

        return billingService.calculateBill(
                vehicle,
                days
        );
    }

    // =========================================================
    // CONFIRM AND CREATE RENTAL
    // =========================================================

    // Creates the rental only after user confirmation.
    public void confirmRental(
            String vehicleId,
            String customerName,
            String phone,
            int days) {

        if (days <= 0) {

            System.out.println(
                    "Invalid number of days."
            );

            return;
        }

        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {

            System.out.println(
                    "Vehicle ID not found."
            );

            return;
        }

        if (!vehicle.isAvailable()) {

            System.out.println(
                    "Vehicle is already rented."
            );

            return;
        }

        if (customerName == null
                || customerName.trim().isEmpty()) {

            System.out.println(
                    "Customer name cannot be empty."
            );

            return;
        }

        if (phone == null
                || phone.trim().isEmpty()) {

            System.out.println(
                    "Phone number cannot be empty."
            );

            return;
        }

        if (rentalCount >= rentals.length) {

            System.out.println(
                    "Rental storage is full."
            );

            return;
        }

        // Create Customer object
        Customer customer = new Customer(
                "C" + rentalNumber,
                customerName,
                phone
        );

        // Calculate rental amount
        double amount =
                billingService.calculateBill(
                        vehicle,
                        days
                );

        // =====================================================
        // EXPLICIT TYPE CASTING
        // =====================================================

        int wholeAmount =
                billingService.convertAmountToInt(amount);

        // =====================================================
        // INTERFACE REFERENCE
        // =====================================================

        Rentable rentableVehicle = vehicle;

        // =====================================================
        // CREATE RENTAL
        // =====================================================

        String rentalId =
                "R" + rentalNumber++;

        Rental rental = new Rental(
                rentalId,
                customer,
                vehicle,
                days,
                amount
        );

        rentals[rentalCount++] = rental;

        // Calling interface method through interface reference
        rentableVehicle.rent();

        System.out.println(
                "\nRENTAL SUCCESSFUL"
        );

        System.out.println(
                "Rental ID : " + rentalId
        );

        System.out.println(
                "Customer  : " + customer.getName()
        );

        System.out.println(
                "Vehicle   : " + vehicle.getVehicleLabel()
        );

        System.out.printf(
                "Amount    : Rs.%.2f%n",
                amount
        );

        // Shows explicit casting result
        System.out.println(
                "Whole Amount (after casting): Rs."
                        + wholeAmount
        );
    }

    // =========================================================
    // DISPLAY ACTIVE RENTALS
    // =========================================================

    public void displayActiveRentals() {

        System.out.println(
                "\n================ ACTIVE RENTALS ================"
        );

        boolean found = false;

        System.out.printf(
                "%-10s %-15s %-25s%n",
                "RENTAL ID",
                "CUSTOMER",
                "VEHICLE"
        );

        System.out.println(
                "--------------------------------------------------"
        );

        for (Rental rental : rentals) {

            if (rental == null) {

                break;
            }

            if (!rental.isReturned()) {

                System.out.printf(
                        "%-10s %-15s %-25s%n",
                        rental.getRentalId(),
                        rental.getCustomer().getName(),
                        rental.getVehicle().getVehicleLabel()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No active rentals."
            );
        }
    }

    // =========================================================
    // RETURN VEHICLE
    // =========================================================

    public void returnVehicle(String rentalId) {

        if (rentalId == null
                || rentalId.trim().isEmpty()) {

            System.out.println(
                    "Rental ID cannot be empty."
            );

            return;
        }

        for (Rental rental : rentals) {

            if (rental == null) {

                break;
            }

            if (rental.getRentalId()
                    .equalsIgnoreCase(
                            rentalId.trim())) {

                if (rental.isReturned()) {

                    System.out.println(
                            "This rental has already been returned."
                    );

                    return;
                }

                rental.getVehicle().returnVehicle();

                rental.markReturned();

                System.out.println(
                        "\nVEHICLE RETURNED SUCCESSFULLY"
                );

                System.out.println(
                        "Rental ID : "
                                + rental.getRentalId()
                );

                System.out.println(
                        "Vehicle   : "
                                + rental.getVehicle()
                                .getVehicleLabel()
                );

                return;
            }
        }

        System.out.println(
                "Rental ID not found."
        );
    }

    // =========================================================
    // RENTAL HISTORY
    // =========================================================

    public void displayHistory() {

        System.out.println(
                "\n================ RENTAL HISTORY ================"
        );

        if (rentalCount == 0) {

            System.out.println(
                    "No rentals have been recorded yet."
            );

            return;
        }

        for (int i = 0;
             i < rentalCount;
             i++) {

            System.out.println(
                    rentals[i]
            );
        }
    }

    // =========================================================
    // SYSTEM STATISTICS
    // =========================================================

    public void displayStatistics() {

        int available = 0;
        int rented = 0;

        double totalRevenue = 0;

        // Count available and rented vehicles
        for (Vehicle vehicle : vehicles) {

            if (vehicle.isAvailable()) {

                available++;

            } else {

                rented++;
            }
        }

        // Calculate total revenue
        for (int i = 0;
             i < rentalCount;
             i++) {

            totalRevenue +=
                    rentals[i].getAmount();
        }

        System.out.println(
                "\n================ SYSTEM STATISTICS ================"
        );

        System.out.println(
                "Total Vehicles     : "
                        + Vehicle.getTotalVehicles()
        );

        System.out.println(
                "Available Vehicles : "
                        + available
        );

        System.out.println(
                "Currently Rented   : "
                        + rented
        );

        System.out.println(
                "Total Rentals      : "
                        + rentalCount
        );

        System.out.printf(
                "Total Revenue      : Rs.%.2f%n",
                totalRevenue
        );
    }
}