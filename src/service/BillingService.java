package service;

import model.Vehicle;

public class BillingService {

    // Final constant
    private static final double TAX_RATE = 0.05;

    public double calculateBill(
            Vehicle vehicle,
            int days) {

        double baseRent =
                vehicle.calculateRent(days);

        double discount = 0;

        if (days >= 7) {

            discount =
                    baseRent * 0.10;
        }

        double taxableAmount =
                baseRent - discount;

        // Operator precedence:
        // multiplication (*) happens before addition (+)
        double total =
                taxableAmount
                        + taxableAmount * TAX_RATE;

        return total;
    }

    // Explicit type conversion / casting
    public int convertAmountToInt(
            double amount) {

        return (int) amount;
    }

    // Method overloading
    public double calculateBill(
            Vehicle vehicle,
            int days,
            double extraCharge) {

        return calculateBill(vehicle, days)
                + extraCharge;
    }
}