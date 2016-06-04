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

        Work7_1 work7_1 = new Work7_1();

        // очистка от небуквенных символов и разбивка на слова
        ArrayList<String> wordsList = work7_1.splitWordList(stringList);

        // карта частот слов
        TreeMap<String, Integer> mapFreqWord = work7_1.frequencyMapWordA(wordsList);

        // разбивка на символы с очисткой от небуквенных символов
        ArrayList<Character>  charListClear = work7_1.splitCharList(wordsList);

        // карта частот символов
        TreeMap<Integer, Character> mapFreqCharClear =  work7_1.frequencyMapChar(charListClear);

        // выводим на экран первые топ 10 слов
        System.out.println("первые топ 20 слов");
        TreeMap<Integer, String> sortedMap = reversMap(mapFreqWord);//
        int count = 0;
        for(Map.Entry e : sortedMap.entrySet()){
            System.out.println(e.getKey()+" "+ e.getValue());
            count++;
            if(count==20){
                break;
            }
        }
/**/

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

//////////
/*
        myInterface myIfc;

        myIfc = () -> {
            return 98;
        };

        double a = myIfc.myMeth();
        */



        //ArrayList <String> arrayListSort = frequencyMapWordA(wordsList);

        //int firstInx = arrayListSort.indexOf(arrayListSort.get(0));
        //int lastInx = arrayListSort.lastIndexOf(arrayListSort.get(0));

        //int i=0;

        //TreeSet<String> setOne = new TreeSet<>(arrayListSort);// множество значений arrayList для ключей Map
/*
        TreeSet<String> sortSet = new TreeSet<>();
        //TreeMap<Integer, String> mapFreqSort = new TreeMap();
        TreeMap<String, Integer> mapFreqSort = new TreeMap();
        int freq1 = 1;
        int iAdd = 1;
        String str ="";
        int count = 0;
        int freq;

        for(int i=0;i<wordsList.size();i++) {
            boolean isAdd = sortSet.add(wordsList.get(i));

            //System.out.println(str);
            if(!isAdd){// если такое слово уже есть
                freq1++;// счетчик частоты слова увеличиваем
                mapFreqSort.put(str, freq1);// запишем в карту новую частоту
                System.out.println(str+" "+freq1);
            }else {// если такого слова еще нет
                freq1 = 1;// счетчик частоты слова сбрасываем
                count++;// счетчик новых слов увеличиваем
                iAdd = i;
                str = wordsList.get(i);// запомним новое слово
                mapFreqSort.put(str, freq1);// запишем в карту новое слово
                System.out.println(str+" "+freq1);
            }
            */

/*
            if(isAdd){



                //mapFreqSort.put(count,arrayListSort.get(i));
                //mapFreqSort.put(arrayListSort.get(i),count);



            }else{
                mapFreqSort.put(arrayListSort.get(iAdd),freq1);
                System.out.println(mapFreqSort.get(arrayListSort.get(iAdd))+" "+freq1);
                System.out.println(mapFreqSort.get(arrayListSort.get(iAdd))+" "+freq1);
            }
            */

        //while (true){
/*
            firstInx = arrayListSort.indexOf(arrayListSort.get(i));
            lastInx = arrayListSort.lastIndexOf(arrayListSort.get(i));
            */
            /*
            firstInx = arrayListSort.indexOf(arrayListSort.get(0));
            lastInx = arrayListSort.lastIndexOf(arrayListSort.get(0));

            //String str = arrayListSort.get(i);
            //Collections.replaceAll(arrayListSort, str);
            arrayListSort.removeAll(arrayListSort.subList(firstInx,lastInx));
            freq = (lastInx - firstInx) + 1;
            count++;
            System.out.println(count);
            i = lastInx+1;
            //if (i == arrayListSort.size()) break;
            if(arrayListSort.isEmpty()) break;
            */

        //}
        //System.out.println(count);
        //System.out.println(firstInx+" "+lastInx+" "+freq);

///////////////
    }

   /*
    interface myInterface{
        double myMeth();
    }
    */


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
            /**/
            int freq = Collections.frequency(arrayList, setIter);// сохраним его частоту

            // Для сортировки - если такая частота уже есть, то дописать слово через пробел к уже имеющемуся
            // иначе произойдет замена одного слова другим
            if(mapFreqSortA.containsKey(freq)){
                setIter = mapFreqSortA.get(freq)+" "+setIter;
            }
            // Сортировка частот слов по убыванию ключа
            mapFreqSortA.put(freq,setIter);

            //arrayList.remove(setIter);

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
