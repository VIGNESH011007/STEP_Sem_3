package Week8_Assignment;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Assignment2 {


    abstract class Assignment {
        private final String title;
        private final int maxMarks;
        private final LocalDate dueDate;

        public Assignment(String title, int maxMarks, LocalDate dueDate) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        public String getTitle() { return title; }
        public int getMaxMarks() { return maxMarks; }
        public LocalDate getDueDate() { return dueDate; }

        // Polymorphic penalty calculation
        public abstract double calculateFinalMarks(double rawMarks, int daysLate);
        public abstract String getPenaltyDescription(int daysLate);
    }

    class CodingAssignment extends Assignment {
        public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
            super(title, maxMarks, dueDate);
        }

        @Override
        public double calculateFinalMarks(double rawMarks, int daysLate) {
            if (daysLate <= 0) return rawMarks;
            double penaltyFraction = Math.min(1.0, daysLate * 0.10);
            return Math.max(0.0, rawMarks * (1.0 - penaltyFraction));
        }

        @Override
        public String getPenaltyDescription(int daysLate) {
            return (daysLate * 10) + "% late penalty";
        }
    }

    class WrittenAssignment extends Assignment {
        public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
            super(title, maxMarks, dueDate);
        }

        @Override
        public double calculateFinalMarks(double rawMarks, int daysLate) {
            if (daysLate <= 0) return rawMarks;
            double penaltyFraction = Math.min(1.0, daysLate * 0.20);
            return Math.max(0.0, rawMarks * (1.0 - penaltyFraction));
        }

        @Override
        public String getPenaltyDescription(int daysLate) {
            return (daysLate * 20) + "% late penalty";
        }
    }

    enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    class Submission {
        private final Student student;
        private final Assignment assignment;
        private LocalDate submissionDate;
        private SubmissionStatus status;
        private Double finalMarks;

        public Submission(Student student, Assignment assignment, LocalDate submissionDate) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
            this.status = SubmissionStatus.SUBMITTED;
            this.finalMarks = null;

            int daysLate = (int) ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
            if (daysLate <= 0) {
                System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: " + getStatus());
            } else {
                System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + daysLate + " days late). Status: " + getStatus());
            }
        }

        public String getStatus() {
            return status == SubmissionStatus.SUBMITTED ? "Submitted" : "Graded";
        }

        public boolean resubmit(LocalDate newSubmissionDate) {
            if (this.status == SubmissionStatus.GRADED) {
                System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
                return false;
            }
            this.submissionDate = newSubmissionDate;
            System.out.println("Resubmission accepted for '" + assignment.getTitle() + "'.");
            return true;
        }

        public void grade(double awardedMarks) {
            if (this.status == SubmissionStatus.GRADED) {
                System.out.println("Submission already graded.");
                return;
            }
            int daysLate = (int) ChronoUnit.DAYS.between(assignment.getDueDate(), submissionDate);
            this.status = SubmissionStatus.GRADED;

            if (daysLate <= 0) {
                this.finalMarks = awardedMarks;
                System.out.printf("%s graded: %.0f/%d. Status: %s%n",
                        student.getName(), this.finalMarks, assignment.getMaxMarks(), getStatus());
            } else {
                this.finalMarks = assignment.calculateFinalMarks(awardedMarks, daysLate);
                System.out.printf("%s graded: %.0f/%d after %s. Status: %s%n",
                        student.getName(), this.finalMarks, assignment.getMaxMarks(),
                        assignment.getPenaltyDescription(daysLate), getStatus());
            }
        }
    }
}
