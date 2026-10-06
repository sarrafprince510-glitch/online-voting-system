import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        VotingService service = new VotingService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=====================================");
        System.out.println("   ONLINE VOTING SYSTEM");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
               
                System.out.println("Invalid input. Please enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    registerVoter(sc, service);
                    break;
                case 2:
                    showCandidates(service);
                    break;
                case 3:
                    castVote(sc, service);
                    break;
                case 4:
                    service.showResults();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting. Thank you!");
                    break;
                default:
                    System.out.println("Please choose a valid option (1-5).");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Register as voter");
        System.out.println("2. View candidates");
        System.out.println("3. Cast vote");
        System.out.println("4. Show results");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void registerVoter(Scanner sc, VotingService service) {
        System.out.print("Enter Voter ID (e.g. V001): ");
        String voterId = sc.nextLine().trim();
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        int age;
        System.out.print("Enter Age: ");
        try {
            age = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age entered. Registration cancelled.");
            return;
        }

        String result = service.registerVoter(voterId, name, age);
        System.out.println(result);
    }

    private static void showCandidates(VotingService service) {
        System.out.println("\n--- CANDIDATES ---");
        for (Candidate c : service.getCandidates()) {
            System.out.println(c);
        }
    }

    private static void castVote(Scanner sc, VotingService service) {
        System.out.print("Enter your Voter ID: ");
        String voterId = sc.nextLine().trim();
        showCandidates(service);
        System.out.print("Enter Candidate ID to vote for: ");
        int candidateId;
        try {
            candidateId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid candidate ID. Vote cancelled.");
            return;
        }
        String result = service.castVote(voterId, candidateId);
        System.out.println(result);
    }
}
