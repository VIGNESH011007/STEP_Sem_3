package Week8_Assignment;
import java.util.*;
public class Assignment3 {


    abstract class Seat {
        private final String seatNumber;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getSeatNumber() { return seatNumber; }
        public abstract double getPrice();
    }

    class RegularSeat extends Seat {
        public RegularSeat(String seatNumber) { super(seatNumber); }
        @Override
        public double getPrice() { return 150.00; }
    }

    class PremiumSeat extends Seat {
        public PremiumSeat(String seatNumber) { super(seatNumber); }
        @Override
        public double getPrice() { return 250.00; }
    }

    class ReclinerSeat extends Seat {
        public ReclinerSeat(String seatNumber) { super(seatNumber); }
        @Override
        public double getPrice() { return 400.00; }
    }

    class Customer {
        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    class Show {
        private final String showName;
        private boolean started;
        private final Set<String> bookedSeatNumbers = new HashSet<>();

        public Show(String showName) {
            this.showName = showName;
            this.started = false;
        }

        public boolean hasStarted() { return started; }
        public void startShow() { this.started = true; }

        public boolean isSeatBooked(String seatNumber) {
            return bookedSeatNumbers.contains(seatNumber);
        }

        public boolean reserveSeats(List<Seat> seats) {
            for (Seat seat : seats) {
                if (bookedSeatNumbers.contains(seat.getSeatNumber())) {
                    System.out.println("Seat " + seat.getSeatNumber() + " is already booked for this show.");
                    return false;
                }
            }
            for (Seat seat : seats) {
                bookedSeatNumbers.add(seat.getSeatNumber());
            }
            return true;
        }

        public void releaseSeats(List<Seat> seats) {
            for (Seat seat : seats) {
                bookedSeatNumbers.remove(seat.getSeatNumber());
            }
        }
    }

    static class Booking {
        private final Customer customer;
        private final Show show;
        private final List<Seat> seats;
        private boolean cancelled = false;

        private Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = new ArrayList<>(seats);
        }

        public static Booking createBooking(Customer customer, Show show, List<Seat> seats) {
            if (seats == null || seats.isEmpty()) {
                System.out.println("Booking must contain at least 1 seat.");
                return null;
            }
            if (seats.size() > 6) {
                System.out.println("Maximum of 6 seats allowed per booking.");
                return null;
            }
            if (show.hasStarted()) {
                System.out.println("Cannot book: Show has already started.");
                return null;
            }

            if (!show.reserveSeats(seats)) {
                return null;
            }

            Booking booking = new Booking(customer, show, seats);
            double total = booking.getTotal();
            List<String> ids = new ArrayList<>();
            for (Seat s : seats) ids.add(s.getSeatNumber());
            System.out.printf("Booking confirmed for %s: %s. Total: %.2f.%n",
                    customer.getName(), String.join(", ", ids), total);
            return booking;
        }

        public double getTotal() {
            double total = 0;
            for (Seat s : seats) total += s.getPrice();
            return total;
        }

        public boolean cancel() {
            if (cancelled) {
                System.out.println("Booking is already cancelled.");
                return false;
            }
            if (show.hasStarted()) {
                System.out.println("Cannot cancel booking: Show has already started.");
                return false;
            }
            show.releaseSeats(seats);
            cancelled = true;
            List<String> ids = new ArrayList<>();
            for (Seat s : seats) ids.add(s.getSeatNumber());
            System.out.println(customer.getName() + "'s booking cancelled. Seats " + String.join(", ", ids) + " released.");
            return true;
        }
    }
}
