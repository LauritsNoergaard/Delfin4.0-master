import java.util.Comparator;

public class CompSwimmerSorter implements Comparator<CompSwimmer> { //Pair programming all
    @Override
    public int compare(CompSwimmer o1, CompSwimmer o2) {
        int comparison = -1;
        switch (chosenSorter){
            case PB -> comparison = Double.compare(o1.getPb() , o2.getPb());
        }
        return comparison;
    }
}
