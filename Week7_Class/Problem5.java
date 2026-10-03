package Week7_Class;

public class Problem5 {
    abstract class DeliveryNote {
        protected String trackingId;

        public DeliveryNote(String trackingId) {
            this.trackingId = trackingId;
        }

        public abstract String confirmDelivery();

        public String confirmDelivery(String signature) {
            return confirmDelivery() + ", signed by " + signature;
        }

        public static void logAll(DeliveryNote[] notes) {
            for (DeliveryNote note : notes) {
                if (note != null) {
                    System.out.println(note.confirmDelivery());
                }
            }
        }
    }

    class ParcelNote extends DeliveryNote {
        public ParcelNote(String trackingId) {
            super(trackingId);
        }

        @Override
        public String confirmDelivery() {
            return "Parcel " + trackingId + " delivered";
        }
    }

    class LetterNote extends DeliveryNote {
        public LetterNote(String trackingId) {
            super(trackingId);
        }

        @Override
        public String confirmDelivery() {
            return "Letter " + trackingId + " delivered";
        }
    }
}
