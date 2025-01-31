import java.util.*;

public class HospitalManagementSystem {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private final List<Staff> staffMembers = new ArrayList<>();
    private final Map<String, Integer> inventory = new HashMap<>();

    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();
        hms.runSystem();
    }

    public void runSystem() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. View EHR");
            System.out.println("4. Generate Bill");
            System.out.println("5. Manage Inventory");
            System.out.println("6. Manage Staff");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    scheduleAppointment(scanner);
                    break;
                case 3:
                    displayEHR(scanner);
                    break;
                case 4:
                    generateBill(scanner);
                    break;
                case 5:
                    inventoryOptions(scanner);
                    break;
                case 6:
                    staffOptions(scanner);
                    break;
                case 7:
                    System.out.println("System shutting down. Goodbye!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void registerPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter patient address: ");
        String address = scanner.nextLine();

        patients.add(new Patient(name, age, address));
        System.out.println("Patient successfully registered.");
    }

    private void scheduleAppointment(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter doctor name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        appointments.add(new Appointment(patientName, doctorName, date));
        System.out.println("Appointment scheduled successfully.");
    }

    private void displayEHR(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(patientName)) {
                System.out.println("EHR for " + patientName + ":");
                System.out.println(patient);
                return;
            }
        }

        System.out.println("No records found for the patient.");
    }

    private void generateBill(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter bill amount: ");
        double amount = scanner.nextDouble();

        System.out.println("Invoice for " + patientName + ": $" + amount);
    }

    private void inventoryOptions(Scanner scanner) {
        System.out.println("1. View Inventory");
        System.out.println("2. Update Inventory");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("--- Current Inventory ---");
                for (Map.Entry<String, Integer> item : inventory.entrySet()) {
                    System.out.println(item.getKey() + ": " + item.getValue());
                }
                break;
            case 2:
                System.out.print("Enter item name: ");
                String itemName = scanner.nextLine();
                System.out.print("Enter item quantity: ");
                int quantity = scanner.nextInt();

                inventory.put(itemName, inventory.getOrDefault(itemName, 0) + quantity);
                System.out.println("Inventory updated successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void staffOptions(Scanner scanner) {
        System.out.println("1. View Staff");
        System.out.println("2. Add Staff");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("--- Staff Members ---");
                for (Staff staff : staffMembers) {
                    System.out.println(staff);
                }
                break;
            case 2:
                System.out.print("Enter staff name: ");
                String name = scanner.nextLine();
                System.out.print("Enter staff role: ");
                String role = scanner.nextLine();

                staffMembers.add(new Staff(name, role));
                System.out.println("Staff member added successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    static class Patient {
        private final String name;
        private final int age;
        private final String address;

        public Patient(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Age: " + age + ", Address: " + address;
        }
    }

    static class Appointment {
        private final String patientName;
        private final String doctorName;
        private final String date;

        public Appointment(String patientName, String doctorName, String date) {
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Patient: " + patientName + ", Doctor: " + doctorName + ", Date: " + date;
        }
    }

    static class Staff {
        private final String name;
        private final String role;

        public Staff(String name, String role) {
            this.name = name;
            this.role = role;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Role: " + role;
        }
    }
}
