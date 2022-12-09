import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Coach {
    Ui ui = new Ui();
    Filehandler f = new Filehandler();
    Scanner in = new Scanner(System.in);

    ArrayList<Member> allMemberList = f.getAllMembersList();

    ArrayList<CompSwimmer> compList = f.getCompList();
    ArrayList<CompSwimmer> crawl = new ArrayList<>();
    ArrayList<CompSwimmer> breaststroke = new ArrayList<>();
    ArrayList<CompSwimmer> backstroke = new ArrayList<>();
    ArrayList<CompSwimmer> butterfly = new ArrayList<>();
    ArrayList<CompSwimmer> medley = new ArrayList<>();


    public void coachMenu() {
        Menu cMenu = new Menu("---COACH MENU---", "Type a number: ", new String[]{"1. View teams sorted by age",
                "2. View top 5", "3. Register new personal records",
                "4. View swimmers personal best", "5. Register new place in competition", "9. Exit coach menu"});
        boolean runWhile = true;
        do {
            cMenu.printMenu();
            switch (cMenu.readChoice()) {
                case 1 -> new Coach().viewAgeSortedTeams();
                case 2 -> new Coach().viewTop5();
                case 3 -> new Coach().registerNewRecord();
                case 4 -> new Coach().viewCompPersonalRecord();
                case 5 -> new Coach().registerPlaceInComp();
                case 9 -> runWhile = false;
                default -> ui.println("Invalid choice");
            }
        } while (runWhile);
    } //Pair programming all

    private void viewAgeSortedTeams() {
        ArrayList<CompSwimmer> compList = f.getCompList();

        ui.println("Competition swimmers under 18: ");
        for (int i = 0; i < compList.size(); i++) {
            if (LocalDateTime.now().getYear() - compList.get(i).getBirthYear() < 18) {
                ui.println(compList.get(i).getfName() + " " + compList.get(i).getlName());
            }
        }
        ui.println("\n");
        ui.println("Competition swimmers over 18: ");
        for (int i = 0; i < compList.size(); i++) {
            if (LocalDateTime.now().getYear() - compList.get(i).getBirthYear() > 18) {
                ui.println(compList.get(i).getfName() + " " + compList.get(i).getlName());
            }
        }
    } //Method written by Emil, Laurits & Mathias

    private void viewCompPersonalRecord() {
        int memberId;
        memberId = ui.readInt("Enter member ID: ");
        boolean validComp = false;

        for (int i = 0; i < compList.size(); i++) {
            if (compList.get(i).getMemberID() == memberId) {
                validComp = true;
                ui.println(compList.get(i).getlName() + " has a pb of " + compList.get(i).getPb() + "s in " +
                        compList.get(i).getDiscipline());
            }
        }
        if (!validComp) {
            ui.println("The member with the given Member Id is not a competition swimmer, or does not exist  ¯\\_( ͡❛ ͜ʖ ͡❛)_/¯");
        }
    } // Pair programming all

    private void registerNewRecord() {
        boolean validChoice = false;
        int memberID = -1;
        memberID = ui.readInt("Please input the ID of the member you want to change: ");

        if (memberID > allMemberList.size() || memberID < 0) {
            ui.println("This member does not exist");
        } else {
            ui.print("Please type the new record like this example \"20,34\": ");
            double newRecord = -1.0;

            while (!validChoice) {
                if (in.hasNextDouble()) {
                    newRecord = in.nextDouble();
                    validChoice = true;
                } else
                    System.err.println("You need to type a number above 0 (remember to use a comma)");
                in.nextLine(); //Scanner bug
            }
            f.editMember(memberID, 8, String.valueOf(newRecord));
        }

    } //Laurits & Martin

    private void registerPlaceInComp() {
        boolean validChoice = false;
        int memberID = -1;
        memberID = ui.readInt("Please input the ID of the member you want to change: ");

        if (memberID > allMemberList.size() || memberID < 0) {
            ui.println("This member does not exist");
        } else {
            int placeInComp = -1;
            ui.print("Please type the place the swimmer came in: 1/2/3: ");
            while (!validChoice) {
                if (in.hasNextInt()) {
                    placeInComp = in.nextInt();
                    validChoice = true;
                } else
                    System.err.println("You need to type a number above 0");
                in.nextLine(); //Scanner bug
            }
            f.editMember(memberID, 10, String.valueOf(placeInComp));
        }
    } //Laurits

    private void divideByDiscipline() {
        for (int i = 0; i < compList.size(); i++) {
            switch (compList.get(i).getDiscipline()) {
                case "Crawl" -> crawl.add(compList.get(i));
                case "Breaststroke" -> breaststroke.add(compList.get(i));
                case "Backstroke" -> backstroke.add(compList.get(i));
                case "Butterfly" -> butterfly.add(compList.get(i));
                case "Medley" -> medley.add(compList.get(i));
            }
        }
    } //Emil

    private void sortTop5() {
        divideByDiscipline();

        CompSwimmerSorter sortByPb = new CompSwimmerSorter(CompSwimmerSorter.ChosenSorter.PB);
        crawl.sort(sortByPb);
        breaststroke.sort(sortByPb);
        backstroke.sort(sortByPb);
        butterfly.sort(sortByPb);
        medley.sort(sortByPb);
    }


    private void viewTop5() {
        sortTop5();
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
            case 1 -> printTop5(crawl, "CRAWL");
            case 2 -> printTop5(breaststroke, "BREASTSTROKE");
            case 3 -> printTop5(butterfly, "BUTTERFLY");
            case 4 -> printTop5(backstroke, "BACKSTROKE");
            case 5 -> printTop5(medley, "MEDLEY");
        }
    } //Pair programming all

    private void printTop5(ArrayList<CompSwimmer> listname, String discipline) { //Alle med pair-programming
        ui.println("--------------------------------");
        ui.println("TOP 5 " + discipline + " SWIMMERS");
        ui.println("ID -- LAST NAME -- TIME");
        ui.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        //System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");

        if (listname.size() == 0) {
            ui.println("There are no competition swimmers in this discipline");
        } else {
            for (int i = 0; i < listname.size() && i < 5; i++) {
                if(listname.get(i).getPb() > 0){
                    System.out.printf("%-6s%-13s%-15s\n", listname.get(i).getMemberID(), listname.get(i).getlName(),
                            listname.get(i).getPb());
                    ui.println("----------------------------");
                }

            }
        }
    }
}



