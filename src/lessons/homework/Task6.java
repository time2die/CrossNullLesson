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


        ReadInput readInput = new ReadInput();// объект класа ReadInput
        //List<String> stringList =  ReadInput.readText();// сохранить текст из файла в List
        List<String> stringList =  new ArrayList<>();
        stringList.addAll(ReadInput.readText());

        //- посчитать частотность повторения всех слов и вывести на экран топ 10
        String strArray = stringList.toString();// List -> String

        // очистка слов - удаляем "лишние" символы, оставляем все буквы и пробелы
        String strClear = strArray;
        strClear = strClear.replaceAll("[^а-яА-Я a-zA-Z]", "");
        //System.out.println(strClear);

        // массив слов
        String[] stringArray =  strClear.trim().split("\\s+");// разбить строку на слова
        /*
        for(int i=0;i<stringArray.length;i++){
            System.out.println(stringArray[i]);
        }
        */
        //-------------
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(stringArray));// String -> List
        //System.out.println(arrayList.get(0));

        Set<String> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map
        HashMap<String, Integer> mapFrequency = new HashMap<>();// создать Карту
        // заполнить Карту:
        // ключ Карты - уникальное значение из множетва setOne
        // значение Карты - частота встречаемости каждого ключа данной Карты (значение setOne) в listOne
        for(Iterator<String> iter = setOne.iterator(); iter.hasNext();){// проходимся итератором по множеству setOne
            String val = iter.next();// сохраним очередное значение множества setOne...
            // ... это значение -> ключ к Map;
            // ... частота его в arrayList -> значение Map
            mapFrequency.put(val, Collections.frequency(arrayList, val));
            //Map1.put(val, Collections.frequency(stringList, "\b"));
        }

        System.out.println(mapFrequency);

    }

}
