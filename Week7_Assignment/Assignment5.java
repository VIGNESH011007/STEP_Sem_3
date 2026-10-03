package Week7_Assignment;

public class Assignment5 {
    abstract class Drone {
        public Drone() {
        }

        public abstract String fly();
    }

    interface Trackable {
        String getLocation();
    }

    class DeliveryDrone extends Drone implements Trackable {
        private String id;

        public DeliveryDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return "Delivery drone " + id + " flying";
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    class ScoutDrone extends Drone {
        private String id;

        public ScoutDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return "Scout drone " + id + " scouting";
        }
    }

    class GroundRobot implements Trackable {
        private String id;

        public GroundRobot(String id) {
            this.id = id;
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }

        public static String getLocationIfTrackable(Object o) {
            if (o instanceof Trackable) {
                Trackable t = (Trackable) o;
                return t.getLocation();
            }
            return "Tracking not available";
        }
    }
}
