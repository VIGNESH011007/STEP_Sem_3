package Week6_Assignment;

public class Assignment5 {
    class GymMember {
        private static int counter = 2000;
        private static int membersEnrolled = 0;

        public final String membershipNumber;
        protected int monthlyFee;
        private int feesPaid = 0;

        public GymMember(int monthlyFee) {
            this.monthlyFee = monthlyFee;
            counter++;
            this.membershipNumber = "GYM-" + counter;
            membersEnrolled++;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }

        public void payFee(int amount) {
            this.feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            payFee(amount);
        }

        public int getFeesPaid() {
            return this.feesPaid;
        }

        public static boolean isValidReferralCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }
            return code.charAt(0) == 'G'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }

        public static String processWeeklyCheckIn(GymMember[] members) {
            int processed = 0;
            int skipped = 0;
            int group = 0;
            int individual = 0;

            for (GymMember m : members) {
                if (m == null) {
                    skipped++;
                    continue;
                }
                processed++;
                if (m instanceof GroupClassMember) {
                    group++;
                } else {
                    individual++;
                }
            }
            return processed + " processed | " + skipped + " null skipped | " + group + " group | " + individual + " individual";
        }
    }

    class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }
    }
}
