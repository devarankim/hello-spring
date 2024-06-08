import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int[] arr = {2};

        List<Integer> list = new ArrayList<Integer>();

        for(int i=0;i<arr.length;i++){
            if(list.contains(arr[i])){
                System.out.println(arr[i]);
            } else {
                list.add(i);
            }
        }

        System.out.println("undefined");
    }
}