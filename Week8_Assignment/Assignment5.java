package Week8_Assignment;
import java.util.*;
public class Assignment5 {

    interface NotificationChannel {
        void send(Student student, Notice notice);
    }

    class EmailChannel implements NotificationChannel {
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[Email -> " + student.getName() + "] " + notice.getTitle());
        }
    }

    class SmsChannel implements NotificationChannel {
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[SMS -> " + student.getName() + "] " + notice.getTitle());
        }
    }

    class AppChannel implements NotificationChannel {
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[App -> " + student.getName() + "] " + notice.getTitle());
        }
    }

    class Student {
        private final String name;
        private final String department;
        private final List<NotificationChannel> preferredChannels = new ArrayList<>();

        public Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }

        public void addPreferredChannel(NotificationChannel channel) {
            if (channel != null && !preferredChannels.contains(channel)) {
                preferredChannels.add(channel);
            }
        }

        public List<NotificationChannel> getPreferredChannels() {
            return Collections.unmodifiableList(preferredChannels);
        }
    }

    class Notice {
        private final String title;
        private final Set<String> targetDepartments;

        public Notice(String title, Set<String> targetDepartments) {
            this.title = title;
            this.targetDepartments = targetDepartments;
        }

        public String getTitle() { return title; }
        public Set<String> getTargetDepartments() { return targetDepartments; }
    }

    class NoticeBoard {
        private final List<Student> students = new ArrayList<>();

        public void registerStudent(Student student) {
            students.add(student);
        }

        public void postNotice(String title, Set<String> targetDepartments) {
            if (title == null || title.trim().isEmpty()) {
                System.out.println("Cannot post notice: Title is required.");
                return;
            }
            if (targetDepartments == null || targetDepartments.isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
                return;
            }

            Notice notice = new Notice(title, targetDepartments);
            System.out.println("Notice '" + title + "' posted to " + String.join(", ", targetDepartments) + ".");

            for (Student student : students) {
                if (targetDepartments.contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getPreferredChannels()) {
                        channel.send(student, notice);
                    }
                }
            }
        }
    }
}
