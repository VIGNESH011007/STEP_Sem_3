package Week6_Assignment;

public class Assignment4 {
    class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int sessionsAttended = 0;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public String displayInfo() {
            return "Standard | Sessions: " + getSessionsAttended();
        }

        public static String batchPrint(GymMember[] members) {
            StringBuilder sb = new StringBuilder();
            for (GymMember member : members) {
                sb.append(member.displayInfo());
                if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                }
                sb.append(" | ");
            }
            return sb.toString();
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

        @Override
        public String displayInfo() {
            return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }
}
