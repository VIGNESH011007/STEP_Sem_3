package Week8_Class;
import java.time.LocalDate;
import java.util.*;
public class Problem4 {

    abstract class Room {
        private final String roomNumber;

        public Room(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() { return roomNumber; }
        public abstract String getCategoryName();
        public abstract double calculatePrice(long days);
    }

    class StandardRoom extends Room {
        public StandardRoom(String roomNumber) { super(roomNumber); }
        @Override
        public String getCategoryName() { return "Standard Room"; }
        @Override
        public double calculatePrice(long days) { return days * 100.0; }
    }

    class DeluxeRoom extends Room {
        public DeluxeRoom(String roomNumber) { super(roomNumber); }
        @Override
        public String getCategoryName() { return "Deluxe Room"; }
        @Override
        public double calculatePrice(long days) { return days * 180.0; }
    }

    class Suite extends Room {
        public Suite(String roomNumber) { super(roomNumber); }
        @Override
        public String getCategoryName() { return "Suite"; }
        @Override
        public double calculatePrice(long days) { return days * 300.0; }
    }

    class Customer {
        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    class Reservation {
        private final Customer customer;
        private final Room room;
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final double price;
        private boolean active = true;

        public Reservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
            this.customer = customer;
            this.room = room;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);
            this.price = room.calculatePrice(days);
        }

        public boolean isActive() { return active; }
        public Room getRoom() { return room; }
        public Customer getCustomer() { return customer; }
        public double getPrice() { return price; }

        public boolean overlaps(LocalDate start, LocalDate end) {
            if (!active) return false;
            return !(checkOut.isEqual(start) || checkOut.isBefore(start) || checkIn.isEqual(end) || checkIn.isAfter(end));
        }

        public void cancel() {
            this.active = false;
        }
    }

    class Hotel {
        private final List<Reservation> reservations = new ArrayList<>();

        public boolean isAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
            for (Reservation res : reservations) {
                if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlaps(checkIn, checkOut)) {
                    return false;
                }
            }
            return true;
        }

        public Reservation bookRoom(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut, String label) {
            if (!isAvailable(room, checkIn, checkOut)) {
                System.out.println(room.getCategoryName() + " " + room.getRoomNumber() + " is not available from " + label + ".");
                return null;
            }
            Reservation res = new Reservation(customer, room, checkIn, checkOut);
            reservations.add(res);
            System.out.printf("Reservation confirmed for %s, %s %s (%s). Price: $%.2f.%n",
                    customer.getName(), room.getCategoryName(), room.getRoomNumber(), label, res.getPrice());
            return res;
        }

        public void cancelReservation(Reservation res, String label) {
            if (res != null && res.isActive()) {
                res.cancel();
                System.out.println("Reservation for " + res.getCustomer().getName() + ", "
                        + res.getRoom().getCategoryName() + " " + res.getRoom().getRoomNumber()
                        + " (" + label + ") cancelled successfully.");
            }
        }
    }
}
