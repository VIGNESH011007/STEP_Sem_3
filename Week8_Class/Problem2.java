package Week8_Class;
import java.time.LocalDate;
public class Problem2 {

    enum LeaveStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    abstract class Employee {
        private final String name;

        public Employee(String name) {
            this.name = name;
        }

        public String getName() { return name; }
        public abstract boolean canApplyForLeave(int days);
    }

    class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String name) { super(name); }
        @Override
        public boolean canApplyForLeave(int days) { return days <= 30; }
    }

    class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String name) { super(name); }
        @Override
        public boolean canApplyForLeave(int days) { return days <= 10; }
    }

    class LeaveRequest {
        private final Employee employee;
        private final String dateRange;
        private LeaveStatus status;

        public LeaveRequest(Employee employee, String dateRange) {
            this.employee = employee;
            this.dateRange = dateRange;
            this.status = LeaveStatus.PENDING;
            System.out.println("Leave request submitted for " + employee.getName() + " (" + dateRange + "). Status: Pending.");
        }

        public LeaveStatus getStatus() { return status; }

        public void approve(String reviewerName) {
            if (status != LeaveStatus.PENDING) {
                System.out.println("Cannot review: Request is not in Pending status.");
                return;
            }
            status = LeaveStatus.APPROVED;
            System.out.println(employee.getName() + "'s leave request (" + dateRange + ") approved. Status: Approved.");
        }

        public void reject(String reviewerName) {
            if (status != LeaveStatus.PENDING) {
                System.out.println("Cannot review: Request is not in Pending status.");
                return;
            }
            status = LeaveStatus.REJECTED;
            System.out.println(employee.getName() + "'s leave request (" + dateRange + ") rejected. Status: Rejected.");
        }

        public void setPending() {
            if (status == LeaveStatus.APPROVED || status == LeaveStatus.REJECTED) {
                System.out.println("Cannot change leave request status from " + (status == LeaveStatus.APPROVED ? "Approved" : "Rejected") + " to Pending.");
                return;
            }
            status = LeaveStatus.PENDING;
        }
    }
}
