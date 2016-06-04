package lessons.homework;

import java.util.*;

/**
 * Created by place2work on 31.05.2016.
 *
 * Задание
 * используя метод readText класса ReadInput выможете получить текс Войны и Мир
 * - посчитать частотность повторения всех слов и вывести на экран топ 10
 * - посчитать частотность повторения всех символов и вывести на экран топ 5
 * - подсчитать количество уникальных слов в тексте
 */


public class Task7 {

    public void start() {

        List<String> stringList =  new ArrayList<>();
        stringList.addAll(ReadInput.readText());

        Work7_1 work7_1 = new Work7_1();

        // очистка от небуквенных символов и разбивка на слова
        ArrayList<String> wordsList = work7_1.splitWordList(stringList);

        // карта частот слов
        TreeMap<String, Integer> mapFreqWord = work7_1.frequencyMapWordA(wordsList);

        // разбивка на символы с очисткой от небуквенных символов
        ArrayList<Character>  charListClear = work7_1.splitCharList(wordsList);

        // карта частот символов
        TreeMap<Integer, Character> mapFreqCharClear =  work7_1.frequencyMapChar(charListClear);

        // выводим на экран первые топ 10 слов
        System.out.println("первые топ 20 слов");
        TreeMap<Integer, String> sortedMap = work7_1.reversMap(mapFreqWord);//
        int count = 0;
        for(Map.Entry e : sortedMap.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            count++;
            if(count==20){
                break;
            }
        }
/**/

        // выводим на экран первые топ 5 очищенных символов
        System.out.println("первые топ 5 символов");
        int count2 = 0;
        for(Map.Entry e : mapFreqCharClear.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            count2++;
            if(count2==5){
                break;
            }
        }

        // количество уникальных слов
        Set<String> setOne = new HashSet<>(wordsList);// множество уникальных слов
        System.out.println("\n количество уникальных слов:  " + setOne.size());

//////////
/*
        myInterface myIfc;

        myIfc = () -> {
            return 98;
        };

        double a = myIfc.myMeth();
        */

///////////////
    }

   /*
    interface myInterface{
        double myMeth();
    }
    */

}
