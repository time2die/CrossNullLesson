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

        // очистка от небуквенных символов и разбивка на слова
        ArrayList<String> wordsList = splitWordList(stringList);

        // карта частот слов
        TreeMap<Integer, String> mapFreqWord = frequencyMapWord(wordsList);

        // разбивка на символы без очистки
        ArrayList<Character>  charList = splitCharList(stringList);

        // карта частот символов
        TreeMap<Integer, Character> mapFreqChar =  frequencyMapChar(charList);

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

        // выводим на экран первые топ 5 неочищенных символов
        System.out.println("первые топ 5 неочищенных символов");
        int count1 = 0;
        for(Map.Entry e : mapFreqChar.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            count1++;
            if(count1==5){
                break;
            }
        }

        // выводим на экран первые топ 5 очищенных символов
        System.out.println("первые топ 5 очищенных символов");
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
     *  Разбивка на слова и очистка. Возввращает StringBuffer
     * */
    public StringBuffer splitWord(List<String>stringList){
        StringBuffer strBuff = new StringBuffer();

        strBuff.append(stringList);// List -> StringBuffer

        Character ch;

        for(int i=0; i < strBuff.length(); i++) {
            ch = strBuff.charAt(i);

            // перевод всех символов в нижний регистр
            strBuff.setCharAt(i,Character.toLowerCase(ch));

            // замена всех небуквенных символов на перенос строки
            if(!Character.isLetter(ch)) strBuff.setCharAt(i,'\n');
        }
        return strBuff;
    }

    /**
     * Создание частотной карты слов
     * */
    public TreeMap<Integer, String> frequencyMapWord(ArrayList<String>arrayList){
        // множество уникальных слов
        Set<String> setOne = new HashSet<>(arrayList);// множество значений arrayList для ключей Map

        //HashMap<String, Integer> mapFrequency = new HashMap<>();// создать Карту
        TreeMap<Integer, String> mapFreqSortA = new TreeMap<>();// создать Карту

        // заполнить Карту:
        // ключ Карты - уникальное значение из множетва setOne
        // значение Карты - частота встречаемости каждого ключа данной Карты (значение setOne) в listOne
        for(Iterator<String> iter = setOne.iterator(); iter.hasNext();){// проходимся итератором по множеству setOne
            String val = iter.next();// сохраним очередное значение множества setOne...
            int freq = Collections.frequency(arrayList, val);// сохраним его частоту

            //1. уникальное слово - ключ, а его частота - значение
            //mapFrequency.put(val, freq);

            // Сортировка: если такая частота уже есть, то дописать слово через пробел к уже имеющемуся
            if(mapFreqSortA.containsKey(freq)){
                val = mapFreqSortA.get(freq)+" "+val;
            }

            //2. Сортировка частот слов по возрастанию ключа (TreeMap)
            //частота - ключ, а уникальные слова с такой частотой - значение
            //слова с одинаковой частотой складываются в строку через пробел
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
