# SmartFleet Vehicle Rental System

## Project Description

SmartFleet is a Java-based Vehicle Rental System developed using Object-Oriented Programming concepts.

The system allows users to:

- View available vehicles
- Search for vehicles
- Rent vehicles
- Return rented vehicles
- View rental history
- View system statistics

## Technologies Used

- Java
- Object-Oriented Programming
- Arrays
- Inheritance
- Abstraction
- Interfaces
- Method Overriding
- Method Overloading
- Constructor Overloading
- Encapsulation
- Enum
- Exception/Input Handling
- Java Scanner

## Project Structure

src/
├── main/
│   └── SmartFleetApp.java
│
├── model/
│   ├── Vehicle.java
│   ├── Car.java
│   ├── Bike.java
│   ├── Customer.java
│   ├── Rental.java
│   ├── Rentable.java
│   └── VehicleType.java
│
└── service/
    ├── BillingService.java
    └── RentalService.java

## How to Run

Compile:

javac -d out src\model\*.java src\service\*.java src\main\*.java

Run:

java -cp out main.SmartFleetApp

## Main Features

1. View Available Vehicles
2. Search Vehicle
3. Rent Vehicle
4. Return Vehicle
5. Rental History
6. System Statistics
7. Exit

## OOP Concepts Demonstrated

- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Interfaces
- Constructor Overloading
- Method Overloading
- Method Overriding
- Dynamic Method Binding
- Static Members
- Final Members
- Enum
- Packages