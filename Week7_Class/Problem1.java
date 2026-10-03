package Week7_Class;

public class Problem1 {
    abstract class Toy {
        private static int counter = 1000;
        private final String toyId;

        public Toy() {
            counter++;
            this.toyId = "TOY-" + counter;
        }

        public String getToyId() {
            return toyId;
        }

        public abstract String makeSound();
    }

    class ToyCar extends Toy {
        private String name;

        public ToyCar(String name) {
            super();
            this.name = name;
        }

        @Override
        public String makeSound() {
            return name + ": Vroom vroom!";
        }
    }

    class ToyRobot extends Toy {
        private String name;

        public ToyRobot(String name) {
            super();
            this.name = name;
        }

        @Override
        public String makeSound() {
            return name + ": Beep boop!";
        }
    }
}
