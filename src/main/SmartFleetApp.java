package main;

import java.util.Scanner;
import service.RentalService;
import model.Vehicle;

public class SmartFleetApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RentalService service = new RentalService();

        boolean running = true;

        System.out.println();
        System.out.println(
                "=============================================="
        );
        System.out.println(
                "       SMARTFLEET VEHICLE RENTAL SYSTEM"
        );
        System.out.println(
                "=============================================="
        );

        while (running) {

            System.out.println();
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Search Vehicle");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Rental History");
            System.out.println("6. System Statistics");
            System.out.println("7. Exit");

            System.out.print("\nEnter choice: ");

            int choice;

            // Validate menu input
            if (scanner.hasNextInt()) {

                choice = scanner.nextInt();
                scanner.nextLine();

            } else {

                System.out.println(
                        "Please enter a number from 1 to 7."
                );

                scanner.nextLine();
                continue;
            }

            switch (choice) {

                // =================================================
                // 1. VIEW AVAILABLE VEHICLES
                // =================================================

                case 1:

                    service.displayVehicles();

                    break;

                // =================================================
                // 2. SEARCH VEHICLE
                // =================================================

                case 2:

                    System.out.print(
                            "\nEnter brand or model to search: "
                    );

                    String keyword = scanner.nextLine();

                    service.searchVehicle(keyword);

                    break;

                // =================================================
                // 3. RENT VEHICLE
                // =================================================

                case 3:

                    // Show available vehicles first
                    service.displayVehicles();

                    System.out.print(
                            "\nEnter Vehicle ID: "
                    );

                    String vehicleId = scanner.nextLine();

                    // Find selected vehicle
                    Vehicle selectedVehicle =
                            service.findVehicle(vehicleId);

                    if (selectedVehicle == null) {

                        System.out.println(
                                "Vehicle ID not found."
                        );

                        break;
                    }

                    // Check availability
                    if (!selectedVehicle.isAvailable()) {

                        System.out.println(
                                "This vehicle is already rented."
                        );

                        break;
                    }

                    // -------------------------------------------------
                    // CUSTOMER NAME VALIDATION
                    // -------------------------------------------------

                    System.out.print(
                            "Enter Customer Name: "
                    );

                    String name =
                            scanner.nextLine().trim();

                    if (name.isEmpty()) {

                        System.out.println(
                                "Customer name cannot be empty."
                        );

                        break;
                    }

                    // Name should contain letters and spaces only
                    if (!name.matches("[a-zA-Z ]+")) {

                        System.out.println(
                                "Invalid name. Please use letters "
                                        + "and spaces only."
                        );

                        break;
                    }

                    // -------------------------------------------------
                    // PHONE VALIDATION
                    // -------------------------------------------------

                    System.out.print(
                            "Enter Phone: "
                    );

                    String phone =
                            scanner.nextLine().trim();

                    /*
                     * Valid Indian mobile number:
                     * - Exactly 10 digits
                     * - Starts with 6, 7, 8 or 9
                     */
                    if (!phone.matches("[6-9][0-9]{9}")) {

                        System.out.println(
                                "Invalid phone number. "
                                        + "Enter a valid 10-digit "
                                        + "Indian mobile number."
                        );

                        break;
                    }

                    // -------------------------------------------------
                    // RENTAL DAYS
                    // -------------------------------------------------

                    System.out.print(
                            "Enter Number of Days: "
                    );

                    int days;

                    if (scanner.hasNextInt()) {

                        days = scanner.nextInt();
                        scanner.nextLine();

                    } else {

                        System.out.println(
                                "Invalid number of days."
                        );

                        scanner.nextLine();

                        break;
                    }

                    if (days <= 0) {

                        System.out.println(
                                "Number of days must be greater than 0."
                        );

                        break;
                    }

                    // -------------------------------------------------
                    // CALCULATE BILL
                    // -------------------------------------------------

                    double amount =
                            service.calculateRentalAmount(
                                    vehicleId,
                                    days
                            );

                    if (amount < 0) {

                        System.out.println(
                                "Unable to calculate rental amount."
                        );

                        break;
                    }

                    // -------------------------------------------------
                    // BILL PREVIEW
                    // -------------------------------------------------

                    System.out.println(
                            "\n================ RENTAL BILL PREVIEW ================"
                    );

                    System.out.println(
                            "Customer       : "
                                    + name
                    );

                    System.out.println(
                            "Phone          : "
                                    + phone
                    );

                    System.out.println(
                            "Vehicle        : "
                                    + selectedVehicle
                                    .getVehicleLabel()
                    );

                    System.out.println(
                            "Vehicle Type   : "
                                    + selectedVehicle
                                    .getVehicleType()
                    );

                    System.out.println(
                            "Rental Days    : "
                                    + days
                    );

                    System.out.printf(
                            "Rate Per Day   : Rs.%.2f%n",
                            selectedVehicle.getDailyRate()
                    );

                    System.out.printf(
                            "Final Amount   : Rs.%.2f%n",
                            amount
                    );

                    System.out.println(
                            "======================================================"
                    );

                    // -------------------------------------------------
                    // RENTAL CONFIRMATION
                    // -------------------------------------------------

                    System.out.print(
                            "Confirm rental? (Y/N): "
                    );

                    String confirmation =
                            scanner.nextLine();

                    if (confirmation
                            .trim()
                            .equalsIgnoreCase("Y")) {

                        service.confirmRental(
                                vehicleId,
                                name,
                                phone,
                                days
                        );

                    } else {

                        System.out.println(
                                "\nRental cancelled."
                        );
                    }

                    break;

                // =================================================
                // 4. RETURN VEHICLE
                // =================================================

                case 4:

                    service.displayActiveRentals();

                    System.out.print(
                            "\nEnter Rental ID to return: "
                    );

                    String rentalId =
                            scanner.nextLine();

                    service.returnVehicle(
                            rentalId
                    );

                    break;

                // =================================================
                // 5. RENTAL HISTORY
                // =================================================

                case 5:

                    service.displayHistory();

                    break;

                // =================================================
                // 6. SYSTEM STATISTICS
                // =================================================

                case 6:

                    service.displayStatistics();

                    break;

                // =================================================
                // 7. EXIT
                // =================================================

                case 7:

                    running = false;

                    System.out.println(
                            "\n=============================================="
                    );

                    System.out.println(
                            "Thank you for using SmartFleet!"
                    );

                    System.out.println(
                            "=============================================="
                    );

                    break;

                // =================================================
                // INVALID OPTION
                // =================================================

                default:

                    System.out.println(
                            "\nInvalid choice. "
                                    + "Please select 1-7."
                    );
            }
        }

        scanner.close();
    }
}