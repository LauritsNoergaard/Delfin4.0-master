import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chairman {
    Ui ui = new Ui();
    Scanner in = new Scanner(System.in);
    Filehandler filehandler = new Filehandler();

    public void chairmanMenu() throws IOException {
        String[] chairmanMenuChoices = {"1. Create new member", "2. Look up member with a given memberID",
                "3. Print all fitness members", "4. Print all competition members", "9. Exit chairman menu"};
        Menu chairmanMenu = new Menu("---CHAIRMAN MENU---", "Pick the number: ", chairmanMenuChoices);
        boolean runWhile = true;
        do {
            chairmanMenu.printMenu();
            switch (chairmanMenu.readChoice()) {
                case 1:
                    createMember();
                    break;
                case 2:
                    filehandler.checkMember();
                    break;
                case 3:
                    printFitnessMembers();
                    break;
                case 4:
                    printCompMembers();
                    break;
                case 9:
                    ui.println("Going back to main menu...");
                    runWhile = false;
                    break;
                default:
                    ui.println("Invalid choice");
                    break;
            }
        } while (runWhile);
    } //Pair programming all

    public void createMember() throws IOException {
        int newId = filehandler.nextAvailableMemberId();
        filehandler.saveNewMemberInFile(userInputForNewMember());
        ui.println("New member was added with the ID:" + newId);
    } //Mathias


    public void printFitnessMembers() {
        ArrayList<FitnessSwimmer> fitList = filehandler.getFitnessList();
        for (int i = 0; i < fitList.size(); i++) {
            System.out.printf("ID: %-3d %-10s %-12s %-4d\n", fitList.get(i).getMemberID(),
                    fitList.get(i).getfName(), fitList.get(i).getlName(), fitList.get(i).getBirthYear());
        }

    }// Method written by Laurits

    public void printCompMembers() {
        ArrayList<CompSwimmer> compList = filehandler.getCompList();
        for (int i = 0; i < compList.size(); i++) {
            ui.print("ID: " + compList.get(i).getMemberID() + ", " + compList.get(i).getfName() + " " +
                    compList.get(i).getlName() + ", " + compList.get(i).getBirthYear() + ", " +
                    compList.get(i).getPlaceInComp() + "\n");
        }
    }//    Method written by Emil

    private String userInputForNewMember() {

        ui.println("Insert members first name: ");
        String fName = ui.readString();
        ui.println("Insert members last name: ");
        String lName = ui.readString();
        ui.println("Insert members birth year: ");
        String birthYear = ui.readBirthYear();
        System.out.println("Does the user want an (1)Active or (2)Passive membership?");
        boolean isActive = ui.intToBool();
        System.out.println("Does the user want to have a (1)Fitness or (2)Competition membership?");
        boolean isFitness = ui.intToBool();
        boolean isComp = !isFitness;
        String placeInComp = "0";

        String compChoice = "noDiscipline";
        if (isComp == true) compChoice = compChoice();
        System.out.println("Has the user payed? (1)yes (2)no");
        boolean hasPayed = ui.intToBool();

        String newMemberData = fName + ";" + lName + ";" + birthYear + ";" + isActive + ";" + isFitness + ";" + isComp + ";"
                + compChoice + ";" + "0.0" + ";" + hasPayed + ";" + placeInComp + ";";
        return newMemberData;

    } //Method written by everyone (Pair-programming)

    private String compChoice() {
        String choice = "";

        Menu compMenu = new Menu("Choose the discipline", "Please type a number between 1 and 5: \n",
                new String[]{"1. Crawl", "2. Breaststroke", "3. Butterfly", "4. Backstroke", "5. Medley"});
        compMenu.printMenu();
        int numChoice = -1;
        boolean validChoice = false;
        while (!validChoice) {
            if (numChoice < 1 || numChoice > 5)
                numChoice = compMenu.readChoice();
            else
                validChoice = true;
        }
        switch (numChoice) {
            case 1 -> choice = "Crawl";
            case 2 -> choice = "Breaststroke";
            case 3 -> choice = "Butterfly";
            case 4 -> choice = "Backstroke";
            case 5 -> choice = "Medley";
        }

        return choice;
    } //Method written by everyone (Pair-programming)
}
