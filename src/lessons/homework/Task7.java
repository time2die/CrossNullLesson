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

        // очистка от небуквенных символов и разбивка на слова
        ArrayList<String> wordsList = splitWordList(stringList);

        // карта частот слов
        TreeMap<Integer, String> mapFreqWord = frequencyMapWord(wordsList);

        // разбивка на символы с очисткой от небуквенных символов
        ArrayList<Character>  charListClear = splitCharList(wordsList);

        // карта частот символов
        TreeMap<Integer, Character> mapFreqCharClear =  frequencyMapChar(charListClear);

        // выводим на экран первые топ 10 слов
        System.out.println("первые топ 20 слов");
        int count = 0;
        for(Map.Entry e : mapFreqWord.entrySet()){
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
     * Создание частотной карты слов
     * */
    public TreeMap<Integer, String> frequencyMapWord(ArrayList<String>arrayList){
        // множество уникальных слов
        Set<String> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map
        // создать TreeMap с сортировкой по убыванию ключа
        TreeMap<Integer, String> mapFreqSortA = new TreeMap(Collections.reverseOrder());

        // заполнить Карту:
        for(String setIter : setOne){// для каждой строки из множества setOne
            int freq = Collections.frequency(arrayList, setIter);// сохраним его частоту

            // Для сортировки - если такая частота уже есть, то дописать слово через пробел к уже имеющемуся
            // иначе произойдет замена одного слова другим
            if(mapFreqSortA.containsKey(freq)){
                setIter = mapFreqSortA.get(freq)+" "+setIter;
            }
            // Сортировка частот слов по возрастанию ключа (TreeMap)
            mapFreqSortA.put(freq,setIter);
        }

        return mapFreqSortA;
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
