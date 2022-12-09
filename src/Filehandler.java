import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filehandler {

    private final String fileName = "memberData.txt";
    private ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<CompSwimmer> compList = new ArrayList<>();
    private ArrayList<FitnessSwimmer> fitnessList = new ArrayList<>();
    Ui ui = new Ui();

    public ArrayList getAllMembersList() {
        updateMemberList();
        return memberList;
    }//Method written by Mathias


    public ArrayList getCompList() {
        updateCompList();
        return compList;
    } //Method written by Mathias

    public ArrayList getFitnessList() {
        updateFitnessList();
        return fitnessList;
    } //Method written by Mathias

    public void checkMember() {
        ArrayList<Member> memberList = getAllMembersList();
        int memberId;

        memberId = ui.readInt("Write the member ID: \n");
        try {
            System.out.printf("%-10s%-18s%-17s%-18s%-17s%-14s\n", "ID", "First Name", "Last Name", "Birth Year",
                    "Is Active", "Has Paid");
            System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
            System.out.printf("%-10s%-18s%-17s%-18s%-17s%-14s\n", memberList.get(memberId).getMemberID(),
                    memberList.get(memberId).getfName(), memberList.get(memberId).getlName(),
                    memberList.get(memberId).getBirthYear(), memberList.get(memberId).isActive(),
                    memberList.get(memberId).hasPaid());

        } catch (Exception e) {
            System.out.println("ID: " + memberId + " is invalid or not in the system.");
        }
    } //Method written by Martin

    public void saveNewMemberInFile(String newMemberData) {
        try {
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write(nextAvailableMemberId() + ";" + newMemberData + "\n");
            myWriter.close();
        } catch (IOException e) {
            ui.println("Something went wrong. please contact the developer with the line below");
            ui.println("Something went wrong in FileHandler.saveNewMemberInFile()...");
            e.printStackTrace();
        }
    } //Method written by Mathias

    public int nextAvailableMemberId() throws IOException {
        int nextId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                nextId++;
            }
        }
        return nextId;
    } //Method written by Mathias


    public void editMember(int memberID, int tokenIndex, String newData) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String lineRead = reader.readLine();
            while (lineRead != null) {
                String[] memberToken = lineRead.split(";");
                List<String> list = Arrays.asList(memberToken);
                if (Integer.parseInt(list.get(0)) == (memberID)) {
                    editFile(tokenIndex, newData, list, memberID);
                    lineRead = null;
                } else {
                    lineRead = reader.readLine(); //goes to next line
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Pair programming all

    private void updateMemberList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String lineRead = reader.readLine();
            while (lineRead != null) {
                stringToMember(lineRead);
                lineRead = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Method written by Mathias

    private void updateFitnessList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String lineRead = reader.readLine();
            while (lineRead != null) {
                stringToFitness(lineRead);
                lineRead = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Method written by Mathias

    private void updateCompList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String lineRead = reader.readLine();
            while (lineRead != null) {
                stringToComp(lineRead);
                lineRead = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Method written by Mathias

    private void stringToMember(String memberString) {
        String[] memberToken = memberString.split(";");
        List<String> list = Arrays.asList(memberToken);
        int memberID = Integer.parseInt(list.get(0));
        String fName = list.get(1);
        String lName = list.get(2);
        int birthYear = Integer.parseInt(list.get(3));
        boolean isActive = Boolean.parseBoolean(list.get(4));
        boolean hasPaid = Boolean.parseBoolean(list.get(9));

        Member newMember = new Member(memberID, fName, lName, birthYear, hasPaid, isActive);
        memberList.add(newMember);
    } //Method written by Mathias

    private void stringToFitness(String fitnessString) {
        String[] memberToken = fitnessString.split(";");
        List<String> list = Arrays.asList(memberToken);
        if (list.get(5).equals("true")) {
            int memberID = Integer.parseInt(list.get(0));
            String fName = list.get(1);
            String lName = list.get(2);
            int birthYear = Integer.parseInt(list.get(3));

            FitnessSwimmer newFitnessMember = new FitnessSwimmer(memberID, fName, lName, birthYear);
            fitnessList.add(newFitnessMember);
        }
    } //Method written by Mathias


    private void stringToComp(String compString) {
        String[] memberToken = compString.split(";");
        List<String> list = Arrays.asList(memberToken);
        if (list.get(6).equals("true")) {
            int memberID = Integer.parseInt(list.get(0));
            String fName = list.get(1);
            String lName = list.get(2);
            int birthYear = Integer.parseInt(list.get(3));

            String discipline = list.get(7);
            double time = Double.parseDouble(list.get(8));

            String placeInComp = list.get(10);

            CompSwimmer newCompMember = new CompSwimmer(memberID, fName, lName, birthYear, discipline, time, placeInComp);
            compList.add(newCompMember);
        }
    }//Method written by Mathias

    private void editFile(int tokenIndex, String newData, List<String> list, int memberId) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("memberData.txt"));
        list.set(tokenIndex, newData);

        String newMemberData = list.get(0) + ";" + list.get(1) + ";" + list.get(2) + ";" + list.get(3) + ";" +
                list.get(4) + ";" + list.get(5) + ";" + list.get(6) + ";" + list.get(7) + ";" + list.get(8) + ";" +
                list.get(9) + ";" + list.get(10) + ";";

        lines.set(memberId, newMemberData);
        Files.write(Path.of(fileName), lines);
    }//Pair programming all
}
