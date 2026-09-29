package model;

public interface Rentable {

    double calculateRent(int days);

    void rent();

    void returnVehicle();
}
