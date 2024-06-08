import java.util.HashSet;

class Scratch {
    public static void main(String[] args) {
        int[] arr = {2,4,60,9,4,1,2};

        HashSet<Integer> set = new HashSet<Integer>();

        for(int i : arr){
            if(set.contains(i)) {
                System.out.println(i);
                break;
            } else {
               set.add(i);
            }
        }
        System.out.println("no 중복 아 맥북 사용 어렵다잉ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ근데 적응해야해 그래야 살아남지");
    }
}