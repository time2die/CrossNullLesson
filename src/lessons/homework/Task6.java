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

        // очистка и разбивка на слова
        ArrayList<String> arrayList = splitWordList(stringList);

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

        // выводим на экран первые топ 10 слов
        System.out.println("первые топ 10 слов");
        int i = 0;
        for(Map.Entry e : mapFreqSortD.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            i++;
            if(i==10){
                break;
            }
        }

        // количество уникальных слов
        System.out.println("\n количество уникальных слов:  " + setOne.size());
     /*   */
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
            String[] strArr =  str.trim().split("\\s+");// разбить строку на слова по первому пробелу
            stringListOut.addAll(Arrays.asList(strArr));// упаковать строки в контейнер

        }

        return stringListOut;
    }

    /**
     *  Разбивка на слова и очистка. Возввращает StringBuffer
     * */
    public StringBuffer splitWord(List<String>stringList){
        StringBuffer strArr = new StringBuffer();

        strArr.append(stringList);// List -> StringBuffer

        Character ch;

        for(int i=0; i < strArr.length(); i++) {
            ch = strArr.charAt(i);

            // перевод всех символов в нижний регистр
            strArr.setCharAt(i,Character.toLowerCase(ch));

            // замена всех небуквенных символов на перенос строки
            if(!Character.isLetter(ch)) strArr.setCharAt(i,'\n');
        }
        return strArr;
    }

}
