import java.util.Comparator;

public class CompSwimmerSorter implements Comparator<CompSwimmer> {

    public enum ChosenSorter {
        PB
    }

    private ChosenSorter chosenSorter;

    public CompSwimmerSorter(ChosenSorter chosensorter){
        this.chosenSorter = chosensorter;
    }

    @Override
    public int compare(CompSwimmer o1, CompSwimmer o2) {
        int comparison = -1;
        switch (chosenSorter){
            case PB -> comparison = Double.compare(o1.getPb() , o2.getPb());
        }
        return comparison;
    }
}
