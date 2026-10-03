package Week6_Assignment;
import java.util.Arrays;
public class Assignment3 {
    class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int[] lateFeeHistory = new int[10];
        private int feeCount = 0;
        private int totalLateFees = 0;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            if (feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount++] = amount;
            }
            totalLateFees += amount;
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, feeCount);
        }

        public int getTotalLateFees() {
            return totalLateFees;
        }
    }

    class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }
}