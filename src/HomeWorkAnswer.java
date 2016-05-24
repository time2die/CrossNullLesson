import java.util.*;

public class HomeWorkAnswer {

    List<Integer> getInput() {
        Random r = new Random();
        int size = r.nextInt(10) + 5;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(r.nextInt(10));
        }
        // 1 2 3 4 5 6
        List duplicate = result.subList(0, size / 2 ) ;
        result.addAll(duplicate) ;

        return result;
    }


    public void work() {
        List<Integer> l1 = getInput();
        System.out.println(l1);

        Set<Integer> uniqSet  = new HashSet<>() ;
        for(Integer iter : l1){
            uniqSet.add(iter) ;
        }
        System.out.println(uniqSet);


        //1 - 0
        //20 - 1
        //22 - 4
        //-1 - 5

        Map<Integer,Integer> uniqMap = new TreeMap<>() ;
        for(Integer iter : l1){
            if(uniqMap.containsKey(iter)){
                Integer howManyTimes = uniqMap.get(iter);
                howManyTimes ++ ;
                uniqMap.put(iter,howManyTimes) ;
            }else{
                uniqMap.put(iter,1) ;
            }
        }
        System.out.println(uniqMap);


        Iterator<Integer> itKeys = uniqMap.keySet().iterator() ;
        while (itKeys.hasNext()){
            int value = uniqMap.get(itKeys.next()) ;
            if(value ==1 ){
                itKeys.remove();
            }
        }

        System.out.println(uniqMap);

    }


    Comparator<Integer> reverseSortCompare = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            int delta1 = o1.intValue() % 2;
            int delta2 = o2.intValue() % 2;
            if (delta1 == delta2) {

                return o1.compareTo(o2);
            }
            if (delta1 == 0)
                return -1;

            return +1;
        }
    };
}
