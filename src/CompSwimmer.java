public class CompSwimmer extends Member {//Pair programming all

    private double pb;
    private String discipline;

    String placeInComp = "0";

    public CompSwimmer(int memberID, String fName, String lName, int birthYear, String discipline, double pb, String placeInComp) {
        this.discipline = discipline;
        this.pb = pb;
        this.placeInComp = placeInComp;
        setMemberID(memberID);
        setFName(fName);
        setLName(lName);
        setBirthYear(birthYear);
    }

    public double getPb() {
        return pb;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getPlaceInComp() {
        return placeInComp;
    }
}
