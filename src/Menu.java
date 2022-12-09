import java.util.Scanner;

public class Menu {

    private String menuHeader;
    private String leadText;
    private String[] menuItems;

    public Menu(String menuHeader, String leadText, String[] menuItems) {
        this.menuHeader = menuHeader;
        this.leadText = leadText;
        this.menuItems = menuItems;
    }

    public void printMenu() {
        String printHeader = menuHeader + "\n";
        for (int i = 0; i < menuItems.length; i++) printHeader += menuItems[i] + "\n";
        System.out.println("\n" + printHeader);
    }

    public int readChoice() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        int choice = -1;
        while (!validChoice) {
            System.out.print(leadText);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                validChoice = true;
            } else {
                scanner.nextLine();
            }
        }
//        scanner.nextLine();
        return choice;
    }
}

