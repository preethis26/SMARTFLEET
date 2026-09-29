package model;

import java.util.Objects;

public class Customer {

    private String customerId;
    private String name;
    private String phone;

    // Default constructor
    public Customer() {
        this("C000", "Unknown", "Unknown");
    }

    // Parameterized constructor
    public Customer(
            String customerId,
            String name,
            String phone) {

        this.customerId = customerId;
        this.name = name.trim();
        this.phone = phone.trim();
    }

    // Getter methods

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // Setter methods

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    // Used by the search feature.
    public boolean matches(String keyword) {

        keyword = keyword.trim();

        return name.equalsIgnoreCase(keyword)
                || name.toLowerCase()
                        .contains(keyword.toLowerCase());
    }

    // =========================================================
    // toString() OVERRIDING
    // =========================================================

    @Override
    public String toString() {

        return customerId
                + " | "
                + name
                + " | "
                + phone;
    }

    // =========================================================
    // equals() OVERRIDING
    // =========================================================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null
                || getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;

        return Objects.equals(
                customerId,
                other.customerId
        );
    }

    // hashCode() kept consistent with equals()
    @Override
    public int hashCode() {

        return Objects.hash(customerId);
    }
}