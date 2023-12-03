
import java.util.Comparator;
import java.util.Map;


public class CustomerReveComp implements Comparator<Map.Entry<String, Double>> {
    //source: https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values

    
    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
       return o2.getValue().compareTo(o1.getValue());
    }
}

//THIS COMPARES AND SORTS  THEMAP  VALUE-BY-VALUE OF THE MAP ENTRIES 