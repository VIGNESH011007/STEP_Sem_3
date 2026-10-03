
package Week6_Class_Problem;
public class Problem2 {
    class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        private int booksBorrowed = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        public void borrowBook() {
            this.booksBorrowed++;
        }

        public int getBooksBorrowed() {
            return this.booksBorrowed;
        }

        public String displayInfo() {
            return "General Member | Books Borrowed: " + getBooksBorrowed();
        }

        public static String classifyGeneration(LibraryMember member) {
            if (member instanceof HonorsStudentMember) {
                return "Multilevel descendant (3 generations deep)";
            } else if (member instanceof FacultyMember) {
                return "Hierarchical sibling (independent branch)";
            }
            return "Base/Intermediate generation";
        }

        public static int getTotalBooksBorrowed(LibraryMember[] members) {
            int total = 0;
            for (LibraryMember member : members) {
                if (member != null) {
                    total += member.getBooksBorrowed();
                }
            }
            return total;
        }
    }

    class StudentMember extends LibraryMember {
        protected String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        public String displayInfo() {
            return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    class HonorsStudentMember extends StudentMember {
        private int bonusLimit;

        public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
            super(memberId, borrowLimit, course);
            this.bonusLimit = bonusLimit;
        }

        @Override
        public String displayInfo() {
            return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(String memberId, int borrowLimit, String department) {
            super(memberId, borrowLimit);
            this.department = department;
        }

        @Override
        public String displayInfo() {
            return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
        }
    }
}