package lessons.homework;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by place2work on 05.06.2016.
 */

public class Work7_3 {

    /**
     * Меняет местами ключ и значение а так же сортирует по убыванию
     * */
    public TreeMap<Integer, String> reversMap(TreeMap<String, Integer> treeMap){
        TreeMap<Integer, String> flipMap = new TreeMap(Collections.reverseOrder());
        for (Map.Entry entry : treeMap.entrySet()) {
            flipMap.put((Integer) entry.getValue(), (String)entry.getKey());
        }

        return flipMap;
    }

    /**
     * Карта частот слов сортированная по возрастанию
     * */

    public TreeMap<String, Integer> frequencyMapWordA(List<String> arrayList){

        Collections.sort(arrayList);

        TreeSet<String> sortSet = new TreeSet<>();
        TreeMap<String, Integer> mapFreqSort = new TreeMap<>();

        int freq = 1;
        String word ="";

        for(int i=0;i<arrayList.size();i++) {
            boolean isAdd = sortSet.add(arrayList.get(i));

            if (!isAdd) {// если такое слово уже есть
                freq++;// счетчик частоты слова увеличиваем
                mapFreqSort.put(word, freq);// запишем в карту новую частоту

            } else {// если такого слова еще нет
                freq = 1;// счетчик частоты слова сбрасываем
                word = arrayList.get(i);// запомним новое слово
                mapFreqSort.put(word, freq);// запишем в карту новое слово

            }
        }

        return mapFreqSort;
    }

    /**
     *  Разбивка на символы
     * */

    public List<String> splitCharStrList(List<String>stringListIn){

        List<String>charStrList = stringListIn
                .stream()
                .flatMap((p) -> letters(p))// вызов моей вспомогательной ф-ии
                .collect(Collectors.toList());

        return charStrList;
    }

    /**
     * Вспомогательная ф-ия для разбиения на символы
     * */
    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++)
            result.add(s.substring(i, i + 1));
        return result.stream();
    }

    /**
     *  Разбивка текста на слова и очистка с помощью Stream() и Лямбда-выражений.
     *  Возввращает List<String>
     * */
    public List<String> splitWordList(List<String>stringListIn){

        List<String>stringListOut = stringListIn
                .stream()// поток
                .map((p) -> p.replaceAll("[^а-яА-Я a-zA-Z]", ""))// удаление всех не буквенных символов
                .map((p) -> p.toLowerCase())// перевод всех символов в нижний регистр
                .flatMap((p) -> Arrays.asList(p.split("\\s")).stream())// разбить строку на массив слов по первому пробелу
                .collect(Collectors.toList());// представить результат в виде коллекции

        return stringListOut;
    }

    /**
     * Вывод на экран первые N top значений карты частот
     * */
    void printMapFreq(TreeMap<String, Integer> mapFreq, int num){
        System.out.println("первые топ"+num+"слов");
        TreeMap<Integer, String> sortedMap = reversMap(mapFreq);//
        int count = 0;
        for(Map.Entry e : sortedMap.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            count++;
            if(count==num){
                break;
            }
        }
    }
}
