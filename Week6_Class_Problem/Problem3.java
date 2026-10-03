package Week6_Class_Problem;
import java.util.Arrays;

public class Problem3 {
    class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        private int[] fineHistory = new int[10];
        private int fineCount = 0;
        private int totalFine = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        protected void chargeFine(int amount) {
            if (fineCount < fineHistory.length) {
                fineHistory[fineCount++] = amount;
            }
            totalFine += amount;
        }

        public int[] getFineHistory() {
            return Arrays.copyOf(fineHistory, fineCount);
        }

        public int getTotalFine() {
            return totalFine;
        }
    }

    class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }
}
