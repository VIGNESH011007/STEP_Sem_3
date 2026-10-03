package Week6_Class_Problem;
public class Problem1 {
    static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        private int booksBorrowed = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid memberId: must be at least 4 characters.");
            }
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        public void borrowBook() {
            this.booksBorrowed++;
        }

        public int getBooksBorrowed() {
            return this.booksBorrowed;
        }

        public static String enrollBatch(String[] memberIds, int borrowLimit) {
            int enrolled = 0;
            int rejected = 0;

            for (String id : memberIds) {
                try {
                    new LibraryMember(id, borrowLimit);
                    enrolled++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
            return "Enrolled: " + enrolled + " | Rejected: " + rejected;
        }
    }

    class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }
    }
}