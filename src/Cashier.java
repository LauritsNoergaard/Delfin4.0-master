import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cashier { //PairProgramming

    //TODO opdater betalingsstaus ved nyt år   //NeedToHave???

    Ui ui = new Ui();
    Filehandler f = new Filehandler();
    ArrayList<Member> allMemberList = f.getAllMembersList();

    public void cashierMenu() {
        String[] cashierMenuChoices = {"1. View missing payments", "2. View payment status from ID",
                "3. Register Payment", "9. Exit back to main menu"};
        Menu cashierMenu = new Menu("Welcome back", "Pick the number:", cashierMenuChoices);
        boolean runWhile = true;
        do {
            cashierMenu.printMenu();
            switch (cashierMenu.readChoice()) {
                case 1:
                    viewMissingPayments();
                    break;
                case 2:
                    viewMemberPaymentStatus();
                    break;
                case 3:
                    registerPayment();
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
    }

    private void registerPayment() {
        int memberId;
        int price = -1;
        String pChoice = "";
        memberId = ui.readInt("Enter member ID: ");


        if (memberId > allMemberList.size() || memberId < 0) {
            ui.println("This member does not exist");
        }else {
            if (allMemberList.get(memberId).hasPaid()) {
                ui.println("This member has already paid");

            } else {
                price = generatePrice(memberId);
                ui.println("This member has to pay: " + price + " DKK");

                pChoice = ui.readString("Confirm payment for member: " + memberId + "\n Enter true or false: ");

                if (pChoice.equalsIgnoreCase("true")) {
                    f.editMember(memberId, 9, "true");

                } else if (pChoice.equalsIgnoreCase("false")) {
                    f.editMember(memberId, 9, "false");
                } else {
                    ui.println("You didn't type \"true\" or \"false\", going back to cashier menu");
                }
            }
        }
    }

    private int generatePrice(int iD) {
        int birthYear = -1;
        boolean isActive = false;

        for (int i = 0; i < allMemberList.size(); i++) {
            if (allMemberList.get(i).getMemberID() == iD) {
                birthYear = allMemberList.get(i).getBirthYear();
                isActive = allMemberList.get(i).isActive();
            }
        }
        if (!isActive) {
            return 500;
        }
        // <18 = 1000kr
        if (LocalDateTime.now().getYear() - birthYear < 18) {
            return 1000;
            // >60 = 1200kr
        } else if (LocalDateTime.now().getYear() - birthYear > 60) {
            return (int) (1600 * 0.75);
        } else {
            return 1600;
        }
    }

    private void viewMissingPayments() {
        ui.println("ALL MEMBERS WITH MISSING PAYMENTS: ");
        for (int i = 0; i < allMemberList.size(); i++) {
            if (!allMemberList.get(i).hasPaid()) {
                ui.println("ID: " + allMemberList.get(i).getMemberID() + ", Last name: "
                        + allMemberList.get(i).getlName() + ", Amount: "
                        + generatePrice(allMemberList.get(i).getMemberID()) + " DKK");
            }
        }
    }

    private void viewMemberPaymentStatus() {
        int memberId;
        int price = -1;

        try{
            memberId = ui.readInt("Enter member ID: ");
            price = generatePrice(memberId);

            if (!allMemberList.get(memberId).hasPaid()) {
                ui.println("This member has to pay: " + price + " DKK");
            } else {
                ui.println("This member has paid: " + price + " DKK");
            }
        }catch (Exception e){
            ui.println("Member id out of bounds");
        }
    }
}
