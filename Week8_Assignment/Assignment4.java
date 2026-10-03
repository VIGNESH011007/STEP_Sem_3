package Week8_Assignment;

public class Assignment4 {
    interface MembershipPlan {
        String getName();
        double calculateFee();
    }

    class MonthlyPlan implements MembershipPlan {
        @Override
        public String getName() { return "Monthly"; }
        @Override
        public double calculateFee() { return 1000.00; }
    }

    class QuarterlyPlan implements MembershipPlan {
        @Override
        public String getName() { return "Quarterly"; }
        @Override
        public double calculateFee() { return 3 * 1000.00 * 0.90; } // 2,700.00
    }

    class AnnualPlan implements MembershipPlan {
        @Override
        public String getName() { return "Annual"; }
        @Override
        public double calculateFee() { return 12 * 1000.00 * 0.75; } // 9,000.00
    }

    enum Status {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    class Member {
        private final String name;

        public Member(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private Status status;

        public Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.status = Status.ACTIVE;
            System.out.printf("%s membership created for %s. Fee: %,.2f. Status: %s.%n",
                    plan.getName(), member.getName(), plan.calculateFee(), getStatusString());
        }

        public String getStatusString() {
            switch (status) {
                case ACTIVE: return "Active";
                case FROZEN: return "Frozen";
                case EXPIRED: return "Expired";
                default: return "";
            }
        }

        public void checkIn() {
            if (status == Status.ACTIVE) {
                System.out.println(member.getName() + " checked in successfully.");
            } else {
                System.out.println("Check-in denied: " + member.getName() + "'s membership is " + getStatusString() + ".");
            }
        }

        public void freeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            if (status == Status.FROZEN) {
                System.out.println("Membership is already frozen.");
                return;
            }
            status = Status.FROZEN;
            System.out.println(member.getName() + "'s membership frozen. Status: " + getStatusString() + ".");
        }

        public void unfreeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            if (status == Status.ACTIVE) {
                System.out.println("Membership is already active.");
                return;
            }
            status = Status.ACTIVE;
            System.out.println(member.getName() + "'s membership unfrozen. Status: " + getStatusString() + ".");
        }

        public void expire() {
            status = Status.EXPIRED;
            System.out.println(member.getName() + "'s membership expired. Status: " + getStatusString() + ".");
        }
    }
}
