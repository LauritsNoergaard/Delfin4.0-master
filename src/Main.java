import java.io.IOException;

public class Main { //Pair programming all

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        new Ui().logo(); //Prints logo
        String[] MenuChoices = {"1. Chairman Menu", "2. Coach Menu",
                "3. Cashier Menu", "9. Exit program"};
        Menu mainMenu = new Menu("Welcome back", "Pick the number: ", MenuChoices);
        boolean runWhile = true;
        do {
            mainMenu.printMenu();
            switch (mainMenu.readChoice()) {
                case 1:
                    new Chairman().chairmanMenu();
                    break;
                case 2:
                    new Coach().coachMenu();
                    break;
                case 3:
                    new Cashier().cashierMenu();
                    break;
                case 9:
                    System.out.println("Exiting Program...");
                    runWhile = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (runWhile);
    }
}