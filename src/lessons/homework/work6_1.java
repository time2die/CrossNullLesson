package lessons.homework;

import java.util.*;

/**
 * Created by place2work on 04.06.2016.
 */
public class Work6_1 {

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
     * Вывод на экран первые Top N значений карты частот
     * */
    public void printTop(){

    }

    /**
     * Байты - эксперимент
     * */

    public List<Object> splitCharList1(List<String>stringListIn){

        List<Object> objectList = new ArrayList<>();

        for(int i=0;i<stringListIn.size();i++) {
            String str = stringListIn.get(i);
            byte[] bytArr = str.getBytes();

            //String str1 = Arrays.toString(bytArr);//1
            //String str2 = new String(bytArr);//2 !!!

            Collections.addAll(objectList, bytArr);
            //objectList.addAll(Arrays.asList(bytArr));
        }

        String str2 = new String((byte[]) objectList.get(123));
        char ch = str2.charAt(0);
        System.out.println(str2);
        System.out.println(ch);

        return objectList;
    }


}
