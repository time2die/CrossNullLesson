package lessons.homework;

import java.util.*;

/**
 * Created by place2work on 04.06.2016.
 */

public class Work7_1 {

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

    public TreeMap<String, Integer> frequencyMapWordA(ArrayList<String> arrayList){

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

    public ArrayList<Character> splitCharList(List<String>stringListIn){

        ArrayList<Character>charList = new ArrayList<>();

        for(String str : stringListIn){// для каждой строки из коллекции
            str = str.toLowerCase();// перевод всех символов в нижний регистр
            for(char ch : str.toCharArray()){// для каждого символа из строки
                charList.add(ch);
            }
        }

        return charList;
    }

    /**
     *  Разбивка на слова и очистка. Возввращает ArrayList<String>
     * */
    public ArrayList<String> splitWordList(List<String>stringListIn){
        ArrayList<String>stringListOut = new ArrayList<>();

        for(String str : stringListIn) {

            str = str.replaceAll("[^а-яА-Я a-zA-Z]", "");// удаление всех не буквенных символов
            str = str.toLowerCase();// перевод всех символов в нижний регистр
            String[] strArr =  str.trim().split("\\s+");// разбить строку на массив слов по первому пробелу
            stringListOut.addAll(Arrays.asList(strArr));// упаковать строки в контейнер

        }

        return stringListOut;
    }

    /**
     * Создание частотной карты символов
     * */
    public TreeMap<Integer, Character> frequencyMapChar(ArrayList<Character>arrayList){
        // множество уникальных слов
        HashSet<Character> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map
        // создать TreeMap с сортировкой по убыванию ключа
        TreeMap<Integer, Character> mapFreqSortA = new TreeMap(Collections.reverseOrder());

        // заполнить Карту:
        for(Character setIter : setOne){// для каждого символа из множества setOne
            mapFreqSortA.put(Collections.frequency(arrayList,setIter), setIter);// сохраним частоту и значение
        }

        return mapFreqSortA;
    }

}


