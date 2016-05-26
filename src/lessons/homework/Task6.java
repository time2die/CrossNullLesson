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

        //ReadInput readInput = new ReadInput();// объект класа ReadInput

        List<String> stringList =  new ArrayList<>();
        stringList.addAll(ReadInput.readText());

        //- посчитать частотность повторения всех слов и вывести на экран топ 10
        String strArray = stringList.toString();// List -> String

        // очистка слов - удаляем "лишние" символы, оставляем все буквы и пробелы
        String strClear = strArray;
        strClear = strClear.replaceAll("[^а-яА-Я a-zA-Z]", "");

        // массив слов
        String[] stringArray =  strClear.trim().split("\\s+");// разбить строку на слова

        //-------------
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(stringArray));// String -> List

        Set<String> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map
        HashMap<String, Integer> mapFrequency = new HashMap<>();// создать Карту
        TreeMap<Integer, String> mapFreqSort = new TreeMap<>();// создать Карту

        // заполнить Карту:
        // ключ Карты - уникальное значение из множетва setOne
        // значение Карты - частота встречаемости каждого ключа данной Карты (значение setOne) в listOne
        for(Iterator<String> iter = setOne.iterator(); iter.hasNext();){// проходимся итератором по множеству setOne
            String val = iter.next();// сохраним очередное значение множества setOne...
            int freq = Collections.frequency(arrayList, val);// сохраним его частоту

            // ... это значение -> ключ к Map;
            // ... частота его в arrayList -> значение Map
            mapFrequency.put(val, freq);

            // Сортировка: если такая частота уже есть, то дописать слово через пробел к уже имеющемуся
            if(mapFreqSort.containsKey(freq)){
                val = mapFreqSort.get(freq)+" "+val;
            }

            // Сортированная по возрастанию ключа (TreeMap) карта частот
            mapFreqSort.put(freq,val);
        }

        System.out.println(mapFreqSort);

    }

}
