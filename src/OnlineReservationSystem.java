import java.util.*;

class User {
    String userId;
    String password;

    User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}

class Reservation {
    static int counter = 1000;
    String pnr;
    String name;
    String trainNo;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;

    Reservation(String name, String trainNo, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.pnr = "PNR" + counter++;
        this.name = name;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    public void display() {
        System.out.println("PNR: " + pnr);
        System.out.println("Name: " + name);
        System.out.println("Train No: " + trainNo);
        System.out.println("Train Name: " + trainName);
        System.out.println("Class Type: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }
}

public class OnlineReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> loginData = new HashMap<>();
    static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        // Preloaded login credentials
        loginData.put("user1", "pass1");

        System.out.println("Welcome to Online Reservation System");
        if (login()) {
            while (true) {
                System.out.println("\n1. Make Reservation\n2. Cancel Reservation\n3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        makeReservation();
                        break;
                    case 2:
                        cancelReservation();
                        break;
                    case 3:
                        System.out.println("Thank you for using the system!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid login. Exiting.");
        }
    }

    static boolean login() {
        System.out.print("Enter Login ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();
        return loginData.containsKey(id) && loginData.get(id).equals(pass);
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();

        // For simplicity, train name is fetched based on train number
        String trainName = getTrainName(trainNo);

        System.out.print("Enter Class Type (e.g., Sleeper/AC): ");
        String classType = sc.nextLine();
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String date = sc.nextLine();
        System.out.print("From: ");
        String from = sc.nextLine();
        System.out.print("To: ");
        String to = sc.nextLine();

        Reservation r = new Reservation(name, trainNo, trainName, classType, date, from, to);
        reservations.put(r.pnr, r);
        System.out.println("Reservation Successful. Your PNR is: " + r.pnr);
    }

    static void cancelReservation() {
        System.out.print("Enter PNR Number to cancel: ");
        String pnr = sc.nextLine();
        if (reservations.containsKey(pnr)) {
            Reservation r = reservations.get(pnr);
            System.out.println("Reservation Details:");
            r.display();

            System.out.print("Do you want to cancel this reservation? (yes/no): ");
            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation Cancelled.");
            } else {
                System.out.println("Cancellation Aborted.");
            }
        } else {
            System.out.println("Invalid PNR.");
        }
    }

    static String getTrainName(String trainNo) {
        // Dummy train number mapping
        Map<String, String> trains = new HashMap<>();
        trains.put("12345", "Express One");
        trains.put("67890", "Fast Rail");
        return trains.getOrDefault(trainNo, "Unknown Train");
    }
}
