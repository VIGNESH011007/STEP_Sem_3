package Week6_Assignment;
import java.util .*;
public class Assignment1 {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int sessionsAttended = 0;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId: must be at least 4 characters.");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void attendSession() {
            this.sessionsAttended++;
        }

        public int getSessionsAttended() {
            return this.sessionsAttended;
        }

        public static String signUpBatch(String[] memberIds, int monthlyFee) {
            int signedUp = 0;
            int rejected = 0;

            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
            return "Signed Up: " + signedUp + " | Rejected: " + rejected;
        }
    }

    class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }
    }
}