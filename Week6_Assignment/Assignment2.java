package Week6_Assignment;
public class Assignment2 {
    class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int sessionsAttended = 0;

        public GymMember(String memberId, int monthlyFee) {
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void attendSession() {
            this.sessionsAttended++;
        }

        public int getSessionsAttended() {
            return this.sessionsAttended;
        }

        public String displayInfo() {
            return "Standard Member | Sessions: " + getSessionsAttended();
        }

        public static String classifyGeneration(GymMember member) {
            if (member instanceof EliteMember) {
                return "Multilevel descendant (3 generations deep)";
            } else if (member instanceof GroupClassMember) {
                return "Hierarchical sibling (independent branch)";
            }
            return "Base/Intermediate generation";
        }

        public static int getTotalSessionsAttended(GymMember[] members) {
            int total = 0;
            for (GymMember member : members) {
                if (member != null) {
                    total += member.getSessionsAttended();
                }
            }
            return total;
        }
    }

    class PremiumMember extends GymMember {
        protected String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }

    class EliteMember extends PremiumMember {
        private String lockerNumber;

        public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
            super(memberId, monthlyFee, trainerName);
            this.lockerNumber = lockerNumber;
        }

        @Override
        public String displayInfo() {
            return "Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
        }
    }

    class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(String memberId, int monthlyFee, String className) {
            super(memberId, monthlyFee);
            this.className = className;
        }

        @Override
        public String displayInfo() {
            return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
        }
    }
}