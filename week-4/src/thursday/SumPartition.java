package thursday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SumPartition { 
private static Set<List<Integer>> listOLists = new LinkedHashSet<List<Integer>>();


    public static Set<List<Integer>> partition(int n) {
    	
    	List<Integer> list = new ArrayList<>();
        partition(n, n, list);
        return listOLists;
    }
    public static void partition(int n, int max, List<Integer> list) { 
        if (n == 0) {
        	Collections.sort(list, Collections.reverseOrder());
        	List<Integer> l = new ArrayList<>(list);
        	listOLists.add(l);
            return;
        }
 
        for (int i = n; i >= 1; i--) {
        	list.add(Integer.valueOf(i));
            partition(n-i, i, list);
            list.remove(Integer.valueOf(i));
        }
    }

    public static void main(String[] args) { 
        Set<List<Integer>> partitions = partition(10);
        partitions.forEach(System.out::println);
        
    }

}