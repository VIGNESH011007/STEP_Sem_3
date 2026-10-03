package Week8_Assignment;
import java.util.*;
public class Assignment1 {


    // Strategy/Polymorphic wash type definition
    interface WashType {
        String getName();
        int getDurationMinutes();
        double getCharge();
    }

    class QuickWash implements WashType {
        @Override
        public String getName() { return "Quick"; }
        @Override
        public int getDurationMinutes() { return 30; }
        @Override
        public double getCharge() { return 20.00; }
    }

    class NormalWash implements WashType {
        @Override
        public String getName() { return "Normal"; }
        @Override
        public int getDurationMinutes() { return 45; }
        @Override
        public double getCharge() { return 30.00; }
    }

    class HeavyWash implements WashType {
        @Override
        public String getName() { return "Heavy"; }
        @Override
        public int getDurationMinutes() { return 60; }
        @Override
        public double getCharge() { return 45.00; }
    }

    class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class WashingMachine {
        private final String machineId;
        private boolean busy;
        private WashCycle currentCycle;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
            this.busy = false;
        }

        public String getMachineId() {
            return machineId;
        }

        public boolean isBusy() {
            return busy;
        }

        public void startWash(Student student, WashType washType) {
            if (busy) {
                System.out.println("Machine " + machineId + " is currently busy.");
                return;
            }
            this.busy = true;
            this.currentCycle = new WashCycle(student, this, washType);
            System.out.printf("%s wash started on %s for %s (%d min). Charge: %.2f.%n",
                    washType.getName(), machineId, student.getName(),
                    washType.getDurationMinutes(), washType.getCharge());
        }

        public void completeCycle() {
            if (!busy || currentCycle == null) {
                System.out.println("Machine " + machineId + " is not currently running a cycle.");
                return;
            }
            this.busy = false;
            this.currentCycle = null;
            System.out.println(machineId + " cycle completed. " + machineId + " is now free.");
        }
    }

    class WashCycle {
        private final Student student;
        private final WashingMachine machine;
        private final WashType washType;

        public WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public Student getStudent() { return student; }
        public WashingMachine getMachine() { return machine; }
        public WashType getWashType() { return washType; }
    }
}
