package lessons.homework;

import java.util.*;

/**
 * Created by place2work on 04.06.2016.
 */

public class Work6_2 {

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

    public TreeMap<String, Integer> frequencyMapWordA(ArrayList<String>arrayList){

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

    public ArrayList<Character> splitCharList(List<String> stringListIn){

        ArrayList<Character>charList = new ArrayList<>();

        for(int i = 0; i < stringListIn.size(); i++) {
            String str = stringListIn.get(i);
            str = str.toLowerCase();// перевод всех символов в нижний регистр
            char[] ch = str.toCharArray();
            for (int j = 0; j < ch.length; j++) {
                charList.add(ch[j]);
            }
        }

        return charList;
    }

    /**
     *  Разбивка на слова и очистка. Возввращает ArrayList<String>
     * */
    public ArrayList<String> splitWordList(List<String>stringListIn){
        ArrayList<String>stringListOut = new ArrayList<>();

        for(int i=0; i < stringListIn.size(); i++) {

            String str = stringListIn.get(i);// строка из коллекции
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

        //HashMap<Character, Integer> mapFrequency = new HashMap<>();// создать Карту
        TreeMap<Integer, Character> mapFreqSortA = new TreeMap<>();// создать Карту

        // заполнить Карту:
        // ключ Карты - уникальное значение из множетва setOne
        // значение Карты - частота встречаемости каждого ключа данной Карты (значение setOne) в listOne
        for(Iterator<Character> iter = setOne.iterator(); iter.hasNext();){// проходимся итератором по множеству setOne
            char val = iter.next();// сохраним очередное значение множества setOne...
            int freq = Collections.frequency(arrayList, val);// сохраним его частоту

            //1. уникальное слово - ключ, а его частота - значение
            //mapFrequency.put(val, freq);

            // Сортировка: если такая частота уже есть, то дописать слово через пробел к уже имеющемуся
            /*
            if(mapFreqSortA.containsKey(freq)){
                val = mapFreqSortA.get(freq)+' '+val;
            }
            */
            //2. Сортировка частот слов по возрастанию ключа (TreeMap)
            //частота - ключ, а уникальные слова с такой частотой - значение
            //слова с одинаковой частотой складываются в строку через пробел
            mapFreqSortA.put(freq,val);
        }

        //3. Сортировка частот слов по убыванию ключа
        TreeMap<Integer, Character> mapFreqSortD = new TreeMap(Collections.reverseOrder());
        mapFreqSortD.putAll(mapFreqSortA);

        return mapFreqSortD;
    }




}
