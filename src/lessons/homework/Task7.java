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

        Work7_4 work73 = new Work7_4();

        // очистка от небуквенных символов и разбивка на слова
        List<String> wordsList = work73.splitWordList(stringList);

        // карта частот слов
        TreeMap<String, Integer> mapFreqWord = work73.frequencyMapWordA(wordsList);

        // выводим на экран первые топ 10 слов
        work73.printMapFreq(mapFreqWord, 10);


        // разбивка на символы с очисткой от небуквенных символов
        List<String> charListClear = work73.splitCharStrList(wordsList);

        // карта частот символов
        TreeMap<String, Integer> mapFreqCharClear = work73.frequencyMapWordA(charListClear);


        // выводим на экран первые топ 5 очищенных символов
        work73.printMapFreq(mapFreqCharClear, 5);

        // количество уникальных слов
        Set<String> setOne = new HashSet<>(wordsList);// множество уникальных слов
        System.out.println("\n количество уникальных слов:  " + setOne.size());

    }

}
