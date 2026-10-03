package Week8_Class;
import java.util.*;
public class Problem3 {


    abstract class Question {
        protected final String text;
        protected final int points;

        public Question(String text, int points) {
            this.text = text;
            this.points = points;
        }

        public int getPoints() { return points; }
        public abstract boolean evaluate(String answer);
    }

    class MultipleChoiceQuestion extends Question {
        private final String correctAnswer;

        public MultipleChoiceQuestion(String text, int points, String correctAnswer) {
            super(text, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim());
        }
    }

    class TrueFalseQuestion extends Question {
        private final String correctAnswer;

        public TrueFalseQuestion(String text, int points, String correctAnswer) {
            super(text, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim());
        }
    }

    class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }

    class Examination {
        private final String title;
        private final List<Question> questions = new ArrayList<>();

        public Examination(String title) {
            this.title = title;
        }

        public String getTitle() { return title; }
        public void addQuestion(Question q) { questions.add(q); }
        public List<Question> getQuestions() { return Collections.unmodifiableList(questions); }

        public int getMaxScore() {
            int max = 0;
            for (Question q : questions) max += q.getPoints();
            return max;
        }
    }

    class Attempt {
        private final Student student;
        private final Examination exam;
        private final Map<Integer, String> answers = new HashMap<>();
        private boolean submitted = false;

        public Attempt(Student student, Examination exam) {
            this.student = student;
            this.exam = exam;
            System.out.println(exam.getTitle() + " started by " + student.getName() + ".");
        }

        public void recordAnswer(int questionIndex, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            answers.put(questionIndex, answer);
            System.out.println("Answer recorded for Question " + questionIndex + ".");
        }

        public void submit() {
            if (submitted) {
                System.out.println("Exam has already been submitted.");
                return;
            }
            submitted = true;
            System.out.println(exam.getTitle() + " submitted by " + student.getName() + ".");

            int totalScore = 0;
            StringBuilder result = new StringBuilder("Result: ");
            List<Question> questions = exam.getQuestions();

            for (int i = 0; i < questions.size(); i++) {
                int qNum = i + 1;
                Question q = questions.get(i);
                String ans = answers.get(qNum);
                boolean correct = ans != null && q.evaluate(ans);

                if (correct) {
                    totalScore += q.getPoints();
                    result.append("Question ").append(qNum).append(": Correct (").append(q.getPoints()).append(" points)");
                } else {
                    result.append("Question ").append(qNum).append(": Incorrect (0 points)");
                }

                if (i < questions.size() - 1) {
                    result.append(", ");
                }
            }
            System.out.println(result + ". Total score: " + totalScore + "/" + exam.getMaxScore() + ".");
        }
    }
}
