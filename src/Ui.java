import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {

    Scanner in = new Scanner(System.in);

    public void println(String msg) {
        System.out.println(msg);
    }

    public void print(String msg) {
        System.out.print(msg);
    }

    public String readString() {
        return in.nextLine();
    }

    public String readString(String msg) {
        print(msg);
        return readString();
    }

    public int readInt(String msg) {
        boolean validChoice = false;
        int number = -1;
        while (!validChoice) {
            print(msg);
            if (in.hasNextInt()) {
                number = in.nextInt();
                validChoice = true;
            } else {
                in.nextLine();
            }
        }
        in.nextLine();//scanner bug
        return number;
    }

    public String readBirthYear() {
        boolean validChoice = false;
        int number = -1;
        while (!validChoice) {
            if (in.hasNextInt()) {
                number = in.nextInt();
                validChoice = true;
                if (number < 1900 || (number > LocalDateTime.now().getYear() - 1)) {
                    validChoice = false;
                    System.out.println("Please type a valid birth year");
                }
            } else {
                System.out.println("Please type a VALID birth year ");
                in.nextLine();
            }
        }
        return String.valueOf(number);
    } //Pair programming all

    public boolean intToBool() {
        boolean validChoice = false;
        int number = -1;
        while (!validChoice) {
            if (in.hasNextInt()) {
                number = in.nextInt();
                validChoice = true;
                if (number > 2 || number < 1) {
                    validChoice = false;
                    System.out.println("Please type 1 or 2");
                }
            } else {
                System.out.println("Please type a NUMBER; 1 or 2");
                in.nextLine();
                in.nextLine(); // If this is not here then it will print ^^ twice
            }
        }
        in.nextLine(); //scanner bug
        if (number == 1) {
            return true;
        } else {
            return false;
        }
    } //Pair programming all

    public void logo() {
        System.out.println("▓█████▄ ▓█████  ██▓      █████▒██▓ ███▄    █ ▓█████  ███▄    █");
        System.out.println("▒██▀ ██▌▓█   ▀ ▓██▒    ▓██   ▒▓██▒ ██ ▀█   █ ▓█   ▀  ██ ▀█   █ ");
        System.out.println("░██   █▌▒███   ▒██░    ▒████ ░▒██▒▓██  ▀█ ██▒▒███   ▓██  ▀█ ██▒");
        System.out.println("░▓█▄   ▌▒▓█  ▄ ▒██░    ░▓█▒  ░░██░▓██▒  ▐▌██▒▒▓█  ▄ ▓██▒  ▐▌██▒");
        System.out.println("░▒████▓ ░▒████▒░██████▒░▒█░   ░██░▒██░   ▓██░░▒████▒▒██░   ▓██░");
        System.out.println(" ▒▒▓  ▒ ░░ ▒░ ░░ ▒░▓  ░ ▒ ░   ░▓  ░ ▒░   ▒ ▒ ░░ ▒░ ░░ ▒░   ▒ ▒");
        System.out.println(" ░ ▒  ▒  ░ ░  ░░ ░ ▒  ░ ░      ▒ ░░ ░░   ░ ▒░ ░ ░  ░░ ░░   ░ ▒░");
        System.out.println(" ░ ░  ░    ░     ░ ░    ░ ░    ▒ ░   ░   ░ ░    ░      ░   ░ ░ ");
        System.out.println("   ░       ░  ░    ░  ░        ░           ░    ░  ░         ░ ");
        System.out.println(" ░   ");
    } //Laurits
}