package Week7_Assignment;

public class Assignment1 {
    interface Ringable {
        String ring();
    }

    class AlarmClock implements Ringable {
        private String time;

        public AlarmClock(String time) {
            this.time = time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + time;
        }

        public static void ringAll(Ringable[] devices) {
            for (Ringable device : devices) {
                if (device != null) {
                    System.out.println(device.ring());
                }
            }
        }
    }

    class Doorbell implements Ringable {
        private String location;

        public Doorbell(String location) {
            this.location = location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + location;
        }
    }
}
