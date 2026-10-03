package Week8_Class;
import java.util.*;
public class Problem1 {


    abstract class Vehicle {
        private final String id;
        private boolean available = true;

        public Vehicle(String id) {
            this.id = id;
        }

        public String getId() { return id; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }

        public abstract double calculateRentalCharge(int days);
    }

    class Sedan extends Vehicle {
        public Sedan(String id) { super(id); }
        @Override
        public double calculateRentalCharge(int days) { return days * 50.0; }
    }

    class SUV extends Vehicle {
        public SUV(String id) { super(id); }
        @Override
        public double calculateRentalCharge(int days) { return days * 80.0; }
    }

    class Truck extends Vehicle {
        public Truck(String id) { super(id); }
        @Override
        public double calculateRentalCharge(int days) { return days * 120.0; }
    }

    class Customer {
        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    static class Rental {
        private final Customer customer;
        private final Vehicle vehicle;
        private final int days;
        private final double charge;
        private boolean returned = false;

        private Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.charge = vehicle.calculateRentalCharge(days);
        }

        public static Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getId() + " is currently unavailable.");
                return null;
            }
            vehicle.setAvailable(false);
            Rental rental = new Rental(customer, vehicle, days);
            System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                    vehicle.getId(), customer.getName(), rental.charge);
            return rental;
        }

        public void returnVehicle() {
            if (returned) {
                System.out.println("Vehicle already returned.");
                return;
            }
            vehicle.setAvailable(true);
            returned = true;
            System.out.println(vehicle.getId() + " returned by " + customer.getName() + ".");
        }
    }
}
