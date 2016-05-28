package lessons.homework;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Задание
 * используя метод readText класса ReadInput выможете получить текс Войны и Мир
 * - посчитать частотность повторения всех слов и вывести на экран топ 10
 * - посчитать частотность повторения всех символов и вывести на экран топ 5
 * - подсчитать количество уникальных слов в тексте
 */

public class Task6 {

    public void start(){
        final List<String> book = ReadInput.readText();
        //мапы для подсчета количества слов и букв. Коллекция и счетчик готовы для многопоточности
        final Map<String, AtomicLong> wordsMap = Collections.synchronizedMap(new LinkedHashMap<>());
        final Map<Character, AtomicLong> charsMap = Collections.synchronizedMap(new LinkedHashMap<>());
        //Что считать словом не ясно, буду считать все что попадает под паттерн ниже, но в него попадет много однобуквенного мусора
        final Pattern pattern = Pattern.compile("([^а-яёА-ЯЁa-zA-Z])");

        book.parallelStream().forEach(row -> {
            //разбиваю строку на "слова" по табу, пробелу.
            final List<String> stringList= Arrays.asList(row.split("\\p{P}?[\\s\\t]+"));

            for (String word: stringList){
                word = word.replaceAll(String.valueOf(pattern), "" ).toLowerCase(); //очищаю строки от знаков препинания и привожу все символы к нижнему регистру
                if (!word.equals("")){ //после чистки слова может не остаться
                    wordsMap.putIfAbsent(word, new AtomicLong(0)); //если ключ не существует, то создать
                    Long counter = wordsMap.get(word).incrementAndGet(); //увеличить счетчик и получить его для сравнения в топе
                }
            }
            for (Character ch : row.toLowerCase().toCharArray()) {
                charsMap.putIfAbsent(ch, new AtomicLong(0)); //если ключ не существует, то создать
                charsMap.get(ch).incrementAndGet(); //увеличить счетчик
            }
        });
        System.out.println("Top 10 words: "+getTopWords(wordsMap, (long) 10));
        System.out.println("Top 5 chars: "+getTopChars(charsMap, (long) 5));
        System.out.println("Uniq words: "+wordsMap.size());
        //для интереса какие слова употреблялись всего один раз
        System.out.println("Single use words:"+getTopSingleWords(wordsMap).size());
    }

    private static Map<String, AtomicLong> getTopWords(Map<String, AtomicLong> map, Long qty ) {
            Map<String, AtomicLong> result = new LinkedHashMap<>();
            //компаратора по value не нашел, выбирать 10 топов в цикле скучно, сортирую стримами
            Stream<Map.Entry<String, AtomicLong>> st = map.entrySet().parallelStream(); //создаю параллельный стрим с энтрями мапы

            st.sorted( Map.Entry.comparingByValue((value1, value2)-> ((Long) value2.get()).compareTo(value1.get())) ) // сортирую
                    .limit(qty).forEachOrdered( e -> result.put(e.getKey(), e.getValue()) ); // отбираю сколько нужно

            return result;
    }

    private static Map<Character, AtomicLong> getTopChars(Map<Character, AtomicLong> map, Long qty ) {
        // а дженерики я еще ен понял, потому повторный код
        Map<Character, AtomicLong> result = Collections.synchronizedMap(new LinkedHashMap<>());
        Stream<Map.Entry<Character, AtomicLong>> st = map.entrySet().parallelStream();

        st.sorted( Map.Entry.comparingByValue((value1, value2)-> ((Long) value2.get()).compareTo(value1.get())) )
                .limit(qty).forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }

    private static Map<String, AtomicLong> getTopSingleWords(Map<String, AtomicLong> map) {
        Map<String, AtomicLong> result = new LinkedHashMap<>();
        Stream<Map.Entry<String, AtomicLong>> st = map.entrySet().parallelStream(); //создаю параллельный стрим с энтрями мапы

        st.sorted( Map.Entry.comparingByValue((value1, value2)-> ((Long) value2.get()).compareTo(value1.get())) ) // сортирую
                .filter(value -> value.getValue().get()==1).forEach( e -> result.put(e.getKey(), e.getValue()) ); // фильтрую

        return result;
    }

}
