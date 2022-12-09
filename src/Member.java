import java.time.LocalDateTime;

public class Member {

    private String fName;
    private String lName;
    private int birthYear;
    private int memberID;
    private boolean isJunior = false;
    private boolean hasPaid;

    private boolean isActive;

    public Member(){

    }

    public Member(int memberID, String fName, String lName, int birthYear, boolean hasPaid, boolean isActive) {
        this.memberID = memberID;
        this.fName = fName;
        this.lName = lName;
        this.birthYear = birthYear;
        isJunior = LocalDateTime.now().getYear() - birthYear < 18;
        this.hasPaid = hasPaid;
        this.isActive = isActive;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getMemberID() {
        return memberID;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public boolean isActive(){
        return isActive;
    }

    public boolean hasPaid(){
        return hasPaid;
    }

}
