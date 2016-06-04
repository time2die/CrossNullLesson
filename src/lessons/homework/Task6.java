package lessons.homework;

import java.util.*;

//import static lessons.homework.ReadInput.readText;

/**
 * Задание
 * используя метод readText класса ReadInput выможете получить текс Войны и Мир
 * - посчитать частотность повторения всех слов и вывести на экран топ 10
 * - посчитать частотность повторения всех символов и вывести на экран топ 5
 * - подсчитать количество уникальных слов в тексте
 */

public class Task6 {

    public void start() {

        List<String> stringList =  new ArrayList<>();
        stringList.addAll(ReadInput.readText());

        Work6_2 work6_2 = new Work6_2();

        // очистка от небуквенных символов и разбивка на слова
        ArrayList<String> wordsList = work6_2.splitWordList(stringList);

        // карта частот слов
        TreeMap<String, Integer> mapFreqWord = work6_2.frequencyMapWordA(wordsList);

        // разбивка на символы с очисткой от небуквенных символов
        ArrayList<Character>  charListClear = work6_2.splitCharList(wordsList);

        // карта частот символов
        TreeMap<Integer, Character> mapFreqCharClear =  work6_2.frequencyMapChar(charListClear);

        // выводим на экран первые топ 10 слов
        System.out.println("первые топ 20 слов");
        TreeMap<Integer, String> sortMap = work6_2.reversMap(mapFreqWord);//
        int count = 0;
        for(Map.Entry e : sortMap.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            count++;
            if(count==20){
                break;
            }
        }

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

    }

}
