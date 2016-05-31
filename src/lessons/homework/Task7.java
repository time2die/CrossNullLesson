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
     * Создание частотной карты слов
     * */
    public TreeMap<Integer, String> frequencyMapWord(ArrayList<String>arrayList){
        // множество уникальных слов
        Set<String> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map
        TreeMap<Integer, String> mapFreqSortA = new TreeMap<>();// создать Карту

        // заполнить Карту:
        for(Iterator<String> iter = setOne.iterator(); iter.hasNext();){// проходимся итератором по множеству setOne
            String val = iter.next();// сохраним очередное значение множества setOne...
            int freq = Collections.frequency(arrayList, val);// сохраним его частоту

            // Для сортировки - если такая частота уже есть, то дописать слово через пробел к уже имеющемуся
            // иначе произойдет замена одного слова другим
            if(mapFreqSortA.containsKey(freq)){
                val = mapFreqSortA.get(freq)+" "+val;
            }
            // Сортировка частот слов по возрастанию ключа (TreeMap)
            mapFreqSortA.put(freq,val);
        }

        //3. Сортировка частот слов по убыванию ключа
        TreeMap<Integer, String> mapFreqSortD = new TreeMap(Collections.reverseOrder());
        mapFreqSortD.putAll(mapFreqSortA);

        return mapFreqSortD;
    }

    /**
     * Создание частотной карты символов
     * */
    public TreeMap<Integer, Character> frequencyMapChar(ArrayList<Character>arrayList){
        // множество уникальных слов
        HashSet<Character> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map
        TreeMap<Integer, Character> mapFreqSortA = new TreeMap<>();// создать Карту

        // заполнить Карту:
        for(Iterator<Character> iter = setOne.iterator(); iter.hasNext();){// проходимся итератором по множеству setOne
            char val = iter.next();// сохраним очередное значение множества setOne...
            int freq = Collections.frequency(arrayList, val);// сохраним его частоту
            mapFreqSortA.put(freq,val);
        }

        //3. Сортировка частот слов по убыванию ключа
        TreeMap<Integer, Character> mapFreqSortD = new TreeMap(Collections.reverseOrder());
        mapFreqSortD.putAll(mapFreqSortA);

        return mapFreqSortD;
    }


}
