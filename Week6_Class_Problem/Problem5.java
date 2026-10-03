package Week6_Class_Problem;

public class Problem5 {
    class LibraryMember {
        private static int counter = 100;
        private static int membersEnrolled = 0;

        public final String memberNumber;
        protected int borrowLimit;
        private int booksBorrowed = 0;

        public LibraryMember(int borrowLimit) {
            this.borrowLimit = borrowLimit;
            counter++;
            this.memberNumber = "LIB-" + counter;
            membersEnrolled++;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }

        public void borrowBook() {
            this.booksBorrowed++;
        }

        public void borrowBook(String genre) {
            borrowBook();
        }

        public int getBooksBorrowed() {
            return this.booksBorrowed;
        }

        public static boolean isValidRenewalCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }
            return code.charAt(0) == 'R'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isUpperCase(code.charAt(3));
        }

        public static String processNightlyAudit(LibraryMember[] members) {
            int processed = 0;
            int skipped = 0;
            int faculty = 0;
            int regular = 0;

            for (LibraryMember m : members) {
                if (m == null) {
                    skipped++;
                    continue;
                }
                processed++;
                if (m instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
            return processed + " processed | " + skipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
        }
    }

    class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }
    }
}
